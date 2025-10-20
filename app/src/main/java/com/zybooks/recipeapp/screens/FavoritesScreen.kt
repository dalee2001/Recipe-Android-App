package com.zybooks.recipeapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.zybooks.recipeapp.data.Recipe

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    recipes: List<Recipe>,
    onBackClick: () -> Unit,
    onRecipeClick: (Recipe) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Favorites") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(android.R.drawable.ic_menu_revert),
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        modifier = modifier
    ) { paddingValues ->
        if (recipes.isEmpty()) {
            // Placeholder text when no favorites exist
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No favorites yet! Tap the heart on a recipe to add it here.",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Gray
                )
            }
        } else {
            // Grid of favorite recipes (like RecipeGalleryScreen)
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(recipes) { recipe ->
                    Card(
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .clickable { onRecipeClick(recipe) },
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            recipe.imageResId?.let { imageId ->
                                Image(
                                    painter = painterResource(id = imageId),
                                    contentDescription = recipe.title,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.TopStart)
                                    .background(
                                        Brush.verticalGradient(
                                            colors = listOf(
                                                Color(0xAA000000),
                                                Color.Transparent
                                            )
                                        )
                                    )
                            ) {
                                Text(
                                    text = recipe.title,
                                    style = MaterialTheme.typography.titleMedium.copy(color = Color.White),
                                    modifier = Modifier
                                        .align(Alignment.TopStart)
                                        .padding(8.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
