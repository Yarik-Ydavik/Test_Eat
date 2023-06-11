package com.example.test_eat.navigation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.test_eat.data.Kitchens_data.categor
import com.example.test_eat.navigation.TopBar1
import com.example.test_eat.network.request.HomeRequest
import com.example.test_eat.ui.theme.sf_font
import com.example.test_eat.utils.Constants
import com.example.test_eat.viewmodels.MainViewModel
import kotlinx.coroutines.runBlocking

@SuppressLint("MutableCollectionMutableState")
@Composable
fun HOME_SCREEN(
    navcontroller: NavHostController,
) {
    val viewModel = viewModel<MainViewModel>()
    val kitchens = viewModel.kitchens.observeAsState().value

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TopBar1()
        if (viewModel.isLoading){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {CircularProgressIndicator() }
        }else{
            LazyColumn (
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                items( kitchens!!.size){ index ->
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(170.dp), contentAlignment = Alignment.Center) {
                        Box(
                            modifier = Modifier
                                .clickable {
                                    navcontroller.navigate(route = Constants.NavigationItem.Home.route+"/${index}")
                                }) {
                            Image(
                                painter = rememberAsyncImagePainter(model = kitchens[index].image_url),
                                contentDescription = kitchens[index].name,
                                contentScale = ContentScale.FillWidth
                            )
                            Text(
                                text =  kitchens[index].name,
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
}