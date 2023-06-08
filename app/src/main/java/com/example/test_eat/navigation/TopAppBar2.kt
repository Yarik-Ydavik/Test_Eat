package com.example.test_eat.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.test_eat.R
import java.text.DateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
@Composable
fun TopBar2(navController: NavHostController, name: String) {
    TopAppBar(
        navigationIcon = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.padding(start = 16.dp))
                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(Icons.Filled.ArrowBack, "backIcon", tint = Color.Black)
                }
            }
        },
        title = {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                Text(text = name, color = Color.Black, fontSize = 18.sp)
            }
        },
        actions = {
            Image(
                painter = painterResource(id = R.drawable.person),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(44.dp)
                    .clip(CircleShape)
            )
        },
        backgroundColor = Color.White
    )
}