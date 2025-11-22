package com.zybooks.recipeapp.data

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun createFatSecretApi(auth: FatSecretAuth): FatSecretApi {
    val client = OkHttpClient.Builder()
        .addInterceptor(TokenInterceptor(auth))
        .build()

    return Retrofit.Builder()
        .baseUrl("https://platform.fatsecret.com/rest/server.api/") // FatSecret REST base
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(FatSecretApi::class.java)
}

// Interceptor to add Bearer token to every request
class TokenInterceptor(private val auth: FatSecretAuth) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        val token = runBlocking { auth.getAccessToken() } // suspend -> blocking here
        requestBuilder.addHeader("Authorization", "Bearer $token")
        return chain.proceed(requestBuilder.build())
    }
}
