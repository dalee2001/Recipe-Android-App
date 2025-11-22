package com.zybooks.recipeapp.data

/**
 * Recipe.kt
 *
 * Data model representing a single recipe.
 * This class holds all the information for a recipe, including:
 *  - id: unique identifier
 *  - title: recipe name
 *  - category: type of cuisine or category
 *  - ingredients: list of ingredients
 *  - instructions: cooking instructions
 *  - imageResId: optional drawable resource ID for recipe image
 *
 * This class is used by the Repository and ViewModel to provide data
 * to the UI screens.
 */
data class Recipe(
    val id: Int,                      // Unique identifier for the recipe
    val title: String,                 // Name of the recipe
    val category: String,              // Category or cuisine type
    val ingredients: List<String>,     // List of ingredient names
    val instructions: String,          // Step-by-step instructions
    val imageResId: Int? = null,       // Optional local drawable resource ID
    var isFavorite: Boolean = false,    // Tracks if recipe is favorited
    var note: String = ""

)