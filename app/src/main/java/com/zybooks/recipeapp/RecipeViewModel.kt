package com.zybooks.recipeapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zybooks.recipeapp.data.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class RecipeViewModel(
    private val recipePreferences: RecipePreferences,
    private val nutritionRepository: NutritionRepository
) : ViewModel() {

    private val repository = RecipeRepository()

    private val _recipes = MutableStateFlow<List<Recipe>>(repository.getAllRecipes())
    val recipes: StateFlow<List<Recipe>> = _recipes

    private val _selectedRecipe = MutableStateFlow<Recipe?>(null)
    val selectedRecipe: StateFlow<Recipe?> = _selectedRecipe

    private val _nutritionInfo = MutableStateFlow<NutritionInfo?>(null)
    val nutritionInfo: StateFlow<NutritionInfo?> = _nutritionInfo

    // --- Loading state for nutrition info ---
    private val _isLoadingNutrition = MutableStateFlow(false)
    val isLoadingNutrition: StateFlow<Boolean> = _isLoadingNutrition

    init {
        viewModelScope.launch {
            val favorites = recipePreferences.getFavorites().first()
            val notes = recipePreferences.getNotes().first()

            _recipes.value = _recipes.value.map { recipe ->
                recipe.copy(
                    isFavorite = favorites.contains(recipe.id),
                    note = notes[recipe.id] ?: ""
                )
            }
        }
    }

    fun selectRecipe(recipe: Recipe) {
        _selectedRecipe.value = recipe
    }

    fun toggleFavorite(recipe: Recipe) {
        val updatedList = _recipes.value.map {
            if (it.id == recipe.id) it.copy(isFavorite = !it.isFavorite) else it
        }
        _recipes.value = updatedList

        _selectedRecipe.value = _selectedRecipe.value?.let {
            if (it.id == recipe.id) it.copy(isFavorite = !it.isFavorite) else it
        }

        viewModelScope.launch {
            val favorites = _recipes.value.filter { it.isFavorite }.map { it.id }.toSet()
            recipePreferences.setFavorites(favorites)
        }
    }

    fun updateNote(recipe: Recipe, note: String) {
        val updatedList = _recipes.value.map {
            if (it.id == recipe.id) it.copy(note = note) else it
        }
        _recipes.value = updatedList

        _selectedRecipe.value = _selectedRecipe.value?.let {
            if (it.id == recipe.id) it.copy(note = note) else it
        }

        viewModelScope.launch {
            recipePreferences.setNote(recipe.id, note)
        }
    }

    // --- Fetch nutrition info with loading state ---
    fun fetchNutrition(recipeName: String) {
        viewModelScope.launch {
            _isLoadingNutrition.value = true
            try {
                val info = nutritionRepository.searchNutrition(recipeName)
                _nutritionInfo.value = info
            } catch (e: Exception) {
                _nutritionInfo.value = null
                e.printStackTrace()  // <-- log the real error
            } finally {
                _isLoadingNutrition.value = false
            }
        }
    }
}
