package com.zybooks.recipeapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zybooks.recipeapp.data.RecipePreferences

class RecipeViewModelFactory(
    private val recipePreferences: RecipePreferences
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipeViewModel::class.java)) {
            return RecipeViewModel(recipePreferences) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
