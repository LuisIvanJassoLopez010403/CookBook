package com.example.cookbook.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookbook.R

@Composable
fun UserView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopEnd
        ) {
            IconButton(onClick = { /* TODO */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.settingicon),
                    contentDescription = "Settings",
                    tint = Color(0xFFFFA500),
                    modifier = Modifier.size(50.dp)
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.userplaceholdericon),
                    contentDescription = "User Icon",
                    tint = Color(0xFFFFA500),
                    modifier = Modifier.size(400.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text("@username", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Text("Followers: 108", fontSize = 22.sp)

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "Lorem ipsum dolor sit amet consectetur adipiscing elit Ut et massa mi. Aliquam in hendrerit urna.",
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 18.dp),
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                MenuButton(icon = painterResource(id = R.drawable.bookicon), text = "My Recipes", iconTint = Color(0xFF00FF00))
                MenuButton(icon = painterResource(id = R.drawable.clipboardicon), text = "Lists")
                MenuButton(icon = painterResource(id = R.drawable.usercheckicon), text = "Following")
                MenuButton(icon = painterResource(id = R.drawable.historyicon), text = "History")
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item { RecipeCard(title = "Carnitas Tacos", time = "25 min", image = painterResource(id = R.drawable.cookbooklogo3)) }
                item { RecipeCard(title = "California Roll", time = "20 min", image = painterResource(id = R.drawable.cookbooklogo3)) }
                item { RecipeCard(title = "Burger", time = "15 min", image = painterResource(id = R.drawable.cookbooklogo3)) }
            }
        }
    }
}

@Composable
fun MenuButton(icon: Painter, text: String, iconTint: Color = Color(0xFFFFA500)) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(painter = icon, contentDescription = text, tint = iconTint, modifier = Modifier.size(50.dp))
        Text(text, fontSize = 16.sp)
    }
}

@Composable
fun RecipeCard(title: String, time: String, image: Painter) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(painter = image, contentDescription = title, modifier = Modifier.size(150.dp))
        Spacer(modifier = Modifier.height(4.dp))
        Text(title, fontSize = 16.sp)
        Text(time, fontSize = 12.sp, color = Color.Gray)
    }
}
