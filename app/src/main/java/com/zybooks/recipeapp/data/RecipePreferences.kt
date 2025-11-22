package com.zybooks.recipeapp.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.first

// Extension property for DataStore
val Context.dataStore by preferencesDataStore(name = "recipe_preferences")

class RecipePreferences(private val context: Context) {

    companion object {
        private val FAVORITES_KEY = stringSetPreferencesKey("favorites")
        private fun noteKey(recipeId: Int) = stringPreferencesKey("note_$recipeId")
    }

    /** Get all favorite recipe IDs as a Flow<Set<Int>> */
    fun getFavorites(): Flow<Set<Int>> = context.dataStore.data.map { prefs ->
        prefs[FAVORITES_KEY]?.map { it.toInt() }?.toSet() ?: emptySet()
    }

    /** Save favorite recipe IDs */
    suspend fun setFavorites(favorites: Set<Int>) {
        context.dataStore.edit { prefs ->
            prefs[FAVORITES_KEY] = favorites.map { it.toString() }.toSet()
        }
    }

    /** Get all notes as Flow<Map<Int, String>> */
    fun getNotes(): Flow<Map<Int, String>> = context.dataStore.data.map { prefs ->
        prefs.asMap()
            .filter { it.key.name.startsWith("note_") }
            .map { entry ->
                val id = entry.key.name.removePrefix("note_").toInt()
                id to (entry.value as String)
            }.toMap()
    }

    /** Save or update note for a recipe */
    suspend fun setNote(recipeId: Int, note: String) {
        context.dataStore.edit { prefs ->
            prefs[noteKey(recipeId)] = note
        }
    }

    /** Optional: get note for a single recipe */
    suspend fun getNote(recipeId: Int): String {
        return context.dataStore.data.map { prefs ->
            prefs[noteKey(recipeId)] ?: ""
        }.first()
    }
}
