package com.zybooks.recipeapp.data

class NutritionRepository(private val api: FatSecretApi) {

    // --- Search for foods (returns a list of FoodSummary) ---
    suspend fun searchFoods(query: String): List<FoodSummary> {
        return api.searchFoods(query).foods.food
    }

    // --- Get detailed API food object ---
    suspend fun getFoodDetails(foodId: String): FoodApiDetail {
        return api.getFood(foodId).food
    }

    // --- High-level method used by RecipeViewModel ---
    suspend fun searchNutrition(recipeName: String): NutritionInfo? {

        // Search
        val results = searchFoods(recipeName)
        if (results.isEmpty()) return null

        // First food
        val firstFood = results.first()

        // Fetch detailed info
        val details = getFoodDetails(firstFood.food_id)

        // First serving (most FatSecret items have multiple)
        val firstServing = details.servings?.serving?.firstOrNull()

        return NutritionInfo(
            name = details.food_name,
            servingSize = firstServing?.serving_description ?: "",
            calories = firstServing?.calories ?: "",
            protein = firstServing?.protein ?: "",
            fat = firstServing?.fat ?: "",
            carbs = firstServing?.carbohydrate ?: ""
        )
    }
}
