package com.zybooks.recipeapp.data

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Query

// --- DTOs for API responses ---

// Summary of a food item in a search list
data class FoodSummary(
    val food_id: String,
    val food_name: String
)

// Wrapper for the list of foods
data class FoodsWrapper(
    val food: List<FoodSummary>
)

// Top-level search response
data class FoodSearchResponse(
    @SerializedName("foods")
    val foods: FoodsWrapper
)

// Detailed food response
data class FoodDetailResponse(
    @SerializedName("food")
    val food: FoodApiDetail
)

// Actual food details
data class FoodApiDetail(
    val food_id: String,
    val food_name: String,
    val servings: ServingsWrapper?
)

// Servings wrapper
data class ServingsWrapper(
    val serving: List<ServingDetail>?
)

// Single serving details
data class ServingDetail(
    val serving_id: String?,
    val serving_description: String?,
    val metric_serving_amount: String?,
    val metric_serving_unit: String?,
    val calories: String?,
    val carbohydrate: String?,
    val protein: String?,
    val fat: String?
)

// High-level Nutrition info returned to ViewModel
data class NutritionInfo(
    val name: String,
    val servingSize: String?,
    val calories: String?,
    val protein: String?,
    val fat: String?,
    val carbs: String?
)

// --- Retrofit API Interface ---
interface FatSecretApi {
    @GET("rest/server.api?method=foods.search&format=json")
    suspend fun searchFoods(
        @Query("search_expression") query: String
    ): FoodSearchResponse

    @GET("rest/server.api?method=food.get.v2&format=json")
    suspend fun getFood(
        @Query("food_id") foodId: String
    ): FoodDetailResponse
}
