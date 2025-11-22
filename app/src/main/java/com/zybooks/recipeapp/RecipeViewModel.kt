package com.zybooks.recipeapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zybooks.recipeapp.data.Recipe
import com.zybooks.recipeapp.data.RecipePreferences
import com.zybooks.recipeapp.data.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class RecipeViewModel(private val recipePreferences: RecipePreferences) : ViewModel() {
    private val repository = RecipeRepository()
    private val _recipes = MutableStateFlow<List<Recipe>>(repository.getAllRecipes())
    val recipes: StateFlow<List<Recipe>> = _recipes

    private val _selectedRecipe = MutableStateFlow<Recipe?>(null)
    val selectedRecipe: StateFlow<Recipe?> = _selectedRecipe

    init {
        // Load favorites and notes from DataStore
        viewModelScope.launch {
            val favorites = recipePreferences.getFavorites().first()
            val notes = recipePreferences.getNotes().first()

            val updatedList = _recipes.value.map { recipe ->
                recipe.copy(
                    isFavorite = favorites.contains(recipe.id),
                    note = notes[recipe.id] ?: ""
                )
            }
            _recipes.value = updatedList
        }
    }

    fun selectRecipe(recipe: Recipe) {
        _selectedRecipe.value = recipe
    }

    // Toggle favorite status and save to DataStore
    fun toggleFavorite(recipe: Recipe) {
        val updatedList = _recipes.value.map {
            if (it.id == recipe.id) it.copy(isFavorite = !it.isFavorite) else it
        }
        _recipes.value = updatedList

        _selectedRecipe.value = _selectedRecipe.value?.let {
            if (it.id == recipe.id) it.copy(isFavorite = !recipe.isFavorite) else it
        }

        // Save updated favorites to DataStore
        viewModelScope.launch {
            val favorites = _recipes.value.filter { it.isFavorite }.map { it.id }.toSet()
            recipePreferences.setFavorites(favorites)
        }
    }

    // Update note for a recipe and save to DataStore
    fun updateNote(recipe: Recipe, note: String) {
        val updatedList = _recipes.value.map {
            if (it.id == recipe.id) it.copy(note = note) else it
        }
        _recipes.value = updatedList

        _selectedRecipe.value = _selectedRecipe.value?.let {
            if (it.id == recipe.id) it.copy(note = note) else it
        }

        // Save note to DataStore
        viewModelScope.launch {
            recipePreferences.setNote(recipe.id, note)
        }
    }
}
