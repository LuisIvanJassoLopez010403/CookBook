package com.example.cookbook.presentation.user.views

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.cookbook.R
import com.example.cookbook.navigation.Routes
import com.example.cookbook.presentation.user.viewmodels.UpdateUserViewModel
import com.example.cookbook.presentation.user.viewmodels.UpdateUserViewModelFactory
import com.example.cookbook.utils.uriToBase64

@Composable
fun UserEditView(navController: NavController) {
    val appContext = LocalContext.current.applicationContext
    val updateUserViewModel: UpdateUserViewModel = viewModel(factory = UpdateUserViewModelFactory(appContext))

    var bio by remember { mutableStateOf(updateUserViewModel.bio) }
    var profilePicture by remember { mutableStateOf(updateUserViewModel.profilePicture) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            profilePicture = it.toString()
            val base64Image = uriToBase64(appContext, it)
            if (base64Image != null) {
                updateUserViewModel.profilePicture = base64Image
                println("Profile Picture Base64 generated: $base64Image")
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        // Cancel Button
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = { navController.navigate(Routes.UserView) }) {
                Text(
                    text = "Cancel",
                    fontSize = 18.sp,
                    color = Color(0xFFFFA500),
                    textAlign = TextAlign.Start
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Editable fields
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.size(100.dp),
                contentAlignment = Alignment.Center
            ) {
                val profileImagePainter = rememberAsyncImagePainter(
                    model = if (profilePicture.isEmpty()) {
                        null
                    } else {
                        profilePicture
                    },
                    placeholder = painterResource(id = R.drawable.userplaceholdericon),
                    error = painterResource(id = R.drawable.userplaceholdericon)
                )

                Image(
                    painter = profileImagePainter,
                    contentDescription = "Profile Picture",
                    modifier = Modifier.size(100.dp)
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            TextButton(onClick = { launcher.launch("image/*") }) {
                Text(
                    text = "Change Profile Picture",
                    fontSize = 18.sp,
                    color = Color(0xFFFFA500),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = bio,
                onValueChange = { bio = it },
                label = { Text(text = "Bio") },
                shape = RoundedCornerShape(15.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    updateUserViewModel.bio = bio
                    updateUserViewModel.updateUserDetails()
                    navController.navigate(Routes.UserView)
                },
                modifier = Modifier
                    .shadow(10.dp, RoundedCornerShape(25.dp))
                    .widthIn(min = 200.dp, max = 300.dp)
                    .align(Alignment.CenterHorizontally)
                    .height(50.dp)
                    .border(1.5.dp, Color(0xFFFFA500), RoundedCornerShape(25.dp))
                    .shadow(10.dp, RoundedCornerShape(25.dp)),
                border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFFFFFFF))
            ) {
                Text(
                    text = "Apply Changes",
                    fontSize = 18.sp,
                    color = Color(0xFFFFA500)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserEditView() {
    UserEditView(rememberNavController())
}