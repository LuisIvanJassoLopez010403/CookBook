package com.example.cookbook.presentation.user.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.cookbook.presentation.user.models.RecipeHistory

@Composable
fun RecipeCard(recipeHistory: RecipeHistory, modifier: Modifier = Modifier) {
    val recipe = recipeHistory.recipeId

    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = modifier.padding(8.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Imagen de la receta
            Image(
                painter = rememberAsyncImagePainter(recipe.image),
                contentDescription = recipe.nameRecipe,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
            // Detalles de la receta
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = recipe.nameRecipe,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${recipe.preptime} min",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
                Text(
                    text = recipe.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
