package com.zybooks.recipeapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.zybooks.recipeapp.data.Recipe
import com.zybooks.recipeapp.data.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RecipeViewModel : ViewModel() {
    private val repository = RecipeRepository()
    private val _recipes = MutableStateFlow<List<Recipe>>(repository.getAllRecipes())
    val recipes: StateFlow<List<Recipe>> = _recipes

    private val _selectedRecipe = MutableStateFlow<Recipe?>(null)
    val selectedRecipe: StateFlow<Recipe?> = _selectedRecipe

    fun selectRecipe(recipe: Recipe) {
        _selectedRecipe.value = recipe
    }

    // Toggle favorite status
    fun toggleFavorite(recipe: Recipe) {
        val updatedList = _recipes.value.map {
            if (it.id == recipe.id) it.copy(isFavorite = !it.isFavorite) else it
        }
        _recipes.value = updatedList

        // If the currently selected recipe is the one toggled, update it too
        if (_selectedRecipe.value?.id == recipe.id) {
            _selectedRecipe.value = _selectedRecipe.value?.copy(isFavorite = !recipe.isFavorite)
        }
    }
}

