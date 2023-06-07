package com.example.test_eat.navigation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.test_eat.data.Kitchens_data.Kitchens
import com.example.test_eat.data.Kitchens_data.categor
import com.example.test_eat.navigation.TopBar1
import com.example.test_eat.network.request.HomeRequest
import com.example.test_eat.ui.theme.sf_font
import kotlinx.coroutines.runBlocking

@Composable
fun HOME_SCREEN(){
    var response: MutableList<categor>
    runBlocking {
        response = HomeRequest().getListKitchen().сategories
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TopBar1()
        LazyColumn (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            items(response.count()){ index ->
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp), contentAlignment = Alignment.Center) {
                    Box() {
                        Image(
                            painter = rememberAsyncImagePainter(model = response[index].image_url),
                            contentDescription = response[index].name,
                            contentScale = ContentScale.FillWidth
                        )
                        Text(
                            text =  response[index].name,
                            color = Color.Black,
                            modifier = Modifier.padding(20.dp),
                            fontSize = 20.sp,
                            fontFamily = sf_font,
                            fontWeight = FontWeight(500)
                        )
                    }

                }


            }
        }
    }

}