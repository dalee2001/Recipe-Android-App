package com.zybooks.recipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zybooks.recipeapp.screens.FavoritesScreen
import com.zybooks.recipeapp.screens.RecipeDetailScreen
import com.zybooks.recipeapp.screens.RecipeGalleryScreen

@Composable
fun NavGraph(viewModel: RecipeViewModel) {
    val navController = rememberNavController()

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
                onFavoritesClick = {
                    navController.navigate("favorites")
                }
            )
        }

        // Recipe Detail Screen
        composable("detail/{recipeId}") { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getString("recipeId")?.toIntOrNull()
            val recipe = viewModel.recipes.collectAsState().value.find { it.id == recipeId }
            recipe?.let {
                RecipeDetailScreen(
                    recipe = it,
                    onBackClick = { navController.popBackStack() },
                    onFavoritesClick = { viewModel.toggleFavorite(it) } // HERE
                )
            }
        }

        // Favorites Screen (placeholder)
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
