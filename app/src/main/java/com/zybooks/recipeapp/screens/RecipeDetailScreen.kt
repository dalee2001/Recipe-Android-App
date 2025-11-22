package com.zybooks.recipeapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.zybooks.recipeapp.RecipeViewModel
import com.zybooks.recipeapp.data.NutritionInfo
import com.zybooks.recipeapp.data.Recipe

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(
    recipe: Recipe,
    viewModel: RecipeViewModel,
    onBackClick: () -> Unit,
    onFavoritesClick: () -> Unit
) {
    var note by remember { mutableStateOf(recipe.note ?: "") }
    var showNutritionDialog by remember { mutableStateOf(false) }

    val nutritionInfo by viewModel.nutritionInfo.collectAsState()
    val isLoadingNutrition by viewModel.isLoadingNutrition.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(android.R.drawable.ic_menu_revert),
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(padding)
                .fillMaxSize()
        ) {
            // --- Recipe image and title ---
            Box(modifier = Modifier.fillMaxWidth()) {
                recipe.imageResId?.let { imageId ->
                    Image(
                        painter = painterResource(id = imageId),
                        contentDescription = recipe.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color(0xAA000000), Color.Transparent)
                            )
                        )
                )

                Text(
                    text = recipe.title,
                    style = MaterialTheme.typography.titleLarge.copy(color = Color.White),
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                )

                IconButton(
                    onClick = onFavoritesClick,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp)
                ) {
                    Icon(
                        imageVector = if (recipe.isFavorite) Icons.Filled.Favorite
                        else Icons.Filled.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = Color.Red
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // --- Show Nutrition Facts button ---
            Button(
                onClick = {
                    viewModel.fetchNutrition(recipe.title)
                    showNutritionDialog = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text("Show Nutrition Facts")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // --- Ingredients ---
            Text(text = "Ingredients", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    recipe.ingredients.forEach { ingredient ->
                        Text("• $ingredient", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // --- Instructions ---
            Text(text = "Instructions", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(recipe.instructions, style = MaterialTheme.typography.bodyMedium)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // --- Notes ---
            Text(text = "Notes", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                TextField(
                    value = note,
                    onValueChange = {
                        note = it
                        viewModel.updateNote(recipe, it)
                    },
                    placeholder = { Text("Tap here to add notes...") },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        cursorColor = Color.Black
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        // --- Nutrition Facts Dialog ---
        if (showNutritionDialog) {
            AlertDialog(
                onDismissRequest = { showNutritionDialog = false },
                confirmButton = {
                    TextButton(onClick = { showNutritionDialog = false }) {
                        Text("Close")
                    }
                },
                title = { Text(text = "Nutrition Facts") },
                text = {
                    Column {
                        if (isLoadingNutrition) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                CircularProgressIndicator()
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Loading...")
                            }
                        } else if (nutritionInfo != null) {
                            nutritionInfo?.let { info: NutritionInfo ->
                                Text("Calories: ${info.calories}")
                                Text("Fat: ${info.fat}")
                                Text("Protein: ${info.protein}")
                                Text("Carbs: ${info.carbs}")
                                Text("Serving Size: ${info.servingSize}")
                            }
                        } else {
                            Text("Failed to load nutrition info.")
                        }
                    }
                }
            )
        }
    }
}
