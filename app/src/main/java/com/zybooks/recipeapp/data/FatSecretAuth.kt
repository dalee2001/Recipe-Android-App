package com.zybooks.recipeapp.data

import android.util.Base64
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException

class FatSecretAuth(
    private val clientId: String,
    private val clientSecret: String
) {
    private val client = OkHttpClient()
    private var cachedToken: String? = null
    private var tokenExpiration: Long = 0L

    suspend fun getAccessToken(): String = withContext(Dispatchers.IO) {
        val currentTime = System.currentTimeMillis()
        // Return cached token if valid
        if (cachedToken != null && currentTime < tokenExpiration) {
            return@withContext cachedToken!!
        }

        // Encode clientId:clientSecret in Base64
        val credentials = "$clientId:$clientSecret"
        val basicAuth = "Basic " + Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)

        val formBody = FormBody.Builder()
            .add("grant_type", "client_credentials")
            .build()

        val request = Request.Builder()
            .url("https://oauth.fatsecret.com/connect/token")
            .post(formBody)
            .header("Authorization", basicAuth)
            .header("Content-Type", "application/x-www-form-urlencoded")
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            val body = response.body?.string() ?: throw IOException("Empty response")
            val json = JSONObject(body)
            val token = json.getString("access_token")
            val expiresIn = json.getLong("expires_in") // seconds

            cachedToken = token
            tokenExpiration = currentTime + (expiresIn * 1000) - 5000 // refresh 5s early
            return@withContext token
        }
    }
}
