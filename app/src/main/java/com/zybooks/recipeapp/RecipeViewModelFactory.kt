package com.zybooks.recipeapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zybooks.recipeapp.data.FatSecretApi
import com.zybooks.recipeapp.data.NutritionRepository
import com.zybooks.recipeapp.data.RecipePreferences

class RecipeViewModelFactory(
    private val recipePreferences: RecipePreferences,
    private val fatSecretApi: FatSecretApi
) : ViewModelProvider.Factory {

    // Create NutritionRepository with the provided FatSecretApi
    private val nutritionRepository: NutritionRepository by lazy {
        NutritionRepository(fatSecretApi)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipeViewModel::class.java)) {
            return RecipeViewModel(recipePreferences, nutritionRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
