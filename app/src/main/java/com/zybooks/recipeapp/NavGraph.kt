package com.zybooks.recipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zybooks.recipeapp.data.FatSecretApi
import com.zybooks.recipeapp.data.FatSecretAuth
import com.zybooks.recipeapp.data.RecipePreferences
import com.zybooks.recipeapp.data.createFatSecretApi
import com.zybooks.recipeapp.RecipeViewModel
import com.zybooks.recipeapp.RecipeViewModelFactory
import com.zybooks.recipeapp.screens.FavoritesScreen
import com.zybooks.recipeapp.screens.RecipeDetailScreen
import com.zybooks.recipeapp.screens.RecipeGalleryScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val context = LocalContext.current

    // Initialize RecipePreferences
    val recipePreferences = RecipePreferences(context)

    // Initialize FatSecretAuth (replace with your actual clientId and clientSecret)
    val fatSecretAuth = FatSecretAuth(
        clientId = YOUR_CLIENT_ID",
        clientSecret = "YOUR_SECRET_ID"
    )

    // Create FatSecretApi instance
    val fatSecretApi: FatSecretApi = createFatSecretApi(fatSecretAuth)

    // Create the ViewModel with both RecipePreferences and FatSecretApi
    val viewModel: RecipeViewModel = viewModel(
        factory = RecipeViewModelFactory(
            recipePreferences = recipePreferences,
            fatSecretApi = fatSecretApi
        )
    )

    NavHost(
        navController = navController,
        startDestination = "gallery"
    ) {
        // Gallery Screen
        composable("gallery") {
            val recipes = viewModel.recipes.collectAsState().value
            RecipeGalleryScreen(
                recipes = recipes,
                onRecipeClick = { recipe ->
                    viewModel.selectRecipe(recipe)
                    navController.navigate("detail/${recipe.id}")
                },
                onFavoritesClick = { navController.navigate("favorites") }
            )
        }

        // Recipe Detail Screen
        composable("detail/{recipeId}") { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getString("recipeId")?.toIntOrNull()
            val selectedRecipe = viewModel.recipes.collectAsState().value.find { it.id == recipeId }

            selectedRecipe?.let { recipe ->
                RecipeDetailScreen(
                    recipe = recipe,
                    viewModel = viewModel,
                    onBackClick = { navController.popBackStack() },
                    onFavoritesClick = { viewModel.toggleFavorite(recipe) }
                )
            }
        }

        // Favorites Screen
        composable("favorites") {
            val favoriteRecipes = viewModel.recipes.collectAsState().value.filter { it.isFavorite }
            FavoritesScreen(
                recipes = favoriteRecipes,
                onBackClick = { navController.popBackStack() },
                onRecipeClick = { recipe ->
                    viewModel.selectRecipe(recipe)
                    navController.navigate("detail/${recipe.id}")
                }
            )
        }
    }
}
