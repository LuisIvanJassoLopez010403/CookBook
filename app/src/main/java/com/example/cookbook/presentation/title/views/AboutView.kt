package com.example.cookbook.presentation.title.views

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cookbook.R
import com.example.cookbook.navigation.Routes

@Composable
fun AboutView(navController: NavController) {

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 0.dp),
        verticalArrangement = Arrangement.Top
    ) {
        item {
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                TextButton(onClick = { navController.navigate(Routes.TitleView) }) {
                    Text(
                        text = "< " + stringResource(id = R.string.Back),
                        fontSize = 18.sp,
                        color = Color(0xFFFFA500),
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.Title),
                    style = androidx.compose.material3.MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(text = stringResource(id = R.string.Explanation))

            Spacer(modifier = Modifier.height(20.dp))

            Row {
                Text(
                    text = stringResource(id = R.string.Subject) + " ",
                    style = androidx.compose.material3.MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(text = stringResource(id = R.string.SubjectClass))
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row {
                Text(
                    text = stringResource(id = R.string.Developers),
                    style = androidx.compose.material3.MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Column {
                DeveloperRow(
                    name = "Ricardo Andrés Jiménez Madero",
                    githubUrl = "https://github.com/JimenezRicardoA",
                    imageResId = R.drawable.perfil2,
                    navController = navController
                )

                Spacer(modifier = Modifier.height(10.dp))

                DeveloperRow(
                    name = "Victor Mauricio Hernández Carreón",
                    githubUrl = "https://github.com/MauCarreon1",
                    imageResId = R.drawable.perfil3,
                    navController = navController
                )

                Spacer(modifier = Modifier.height(10.dp))

                DeveloperRow(
                    name = "Luis Iván Jasso López",
                    githubUrl = "https://github.com/LuisIvanJassoLopez010403",
                    imageResId = R.drawable.perfil1,
                    navController = navController
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun DeveloperRow(
    name: String,
    githubUrl: String,
    imageResId: Int,
    navController: NavController
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = "$name Profile Picture",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column {
            Text(text = name)
            Text(
                text = githubUrl,
                color = Color.Blue,
                modifier = Modifier.pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            val encodedUrl = Uri.encode(githubUrl)
                            navController.navigate("webview/$encodedUrl")
                        }
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAboutView() {
    AboutView(rememberNavController())
}