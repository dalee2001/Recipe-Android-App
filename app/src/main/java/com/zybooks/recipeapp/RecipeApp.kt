package com.zybooks.recipeapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RecipeApp(viewModel: RecipeViewModel = viewModel()) {
    NavGraph(viewModel = viewModel)
}