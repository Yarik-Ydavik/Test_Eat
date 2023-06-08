package com.example.test_eat.navigation.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.android.style.LetterSpacingSpanPx
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.test_eat.R
import com.example.test_eat.data.Kitchens_data.categor
import com.example.test_eat.data.filter_button.filter_buttons
import com.example.test_eat.navigation.TopBar2
import com.example.test_eat.ui.theme.sf_font
import com.example.test_eat.viewmodels.MainViewModel

@Composable
fun CATEGORYA_SCREEN(card: categor, navController: NavHostController, viewModel: MainViewModel) {
    val openDialog = remember { mutableStateOf(false) }
    var indexDish by remember { mutableStateOf(0) }

    val buttonStates = remember { mutableStateListOf<Boolean>() }
    var buttonTags = remember { mutableStateListOf<String>() }
    filter_buttons.forEach { buttonStates.add(false) }
    Column {
        TopBar2(navController, card.name)
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .horizontalScroll(
                        state = rememberScrollState(),
                        enabled = true,
                        flingBehavior = null,
                    ),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                filter_buttons.forEachIndexed { index, filterButton ->
                    Button(
                        onClick = {
                            buttonStates[index] = !buttonStates[index]
                            if (buttonStates[index]){
                                buttonTags.add(filterButton.name)
                            } else buttonTags.remove(filterButton.name)
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (buttonStates[index]) { // меняем цвет выбранной кнопки
                                Color(red = 51, green = 100, blue = 224)
                            } else {
                                Color(red = 248, green = 247, blue = 245)
                            },
                            contentColor = if (buttonStates[index]){ Color.White } else Color.Black
                        )
                    ) {
                        Text(text = filterButton.name)
                    }
                }
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(10.dp)
            ){
                viewModel.dishes.value?.let {
                    items(it.size){ index ->
                        if (buttonTags.size == 0 || buttonTags.contains("Всё меню")){
                            Column(modifier = Modifier
                                .padding(6.dp)
                                .width(119.dp)
                                .height(150.dp)) {
                                Box(
                                    modifier = Modifier
                                        .size(109.dp)
                                        .clip(RoundedCornerShape(10))
                                        .background(Color(red = 248, green = 247, blue = 245))
                                        .clickable {
                                            indexDish = index
                                            openDialog.value = true

                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        painter =
                                        rememberAsyncImagePainter(
                                            ImageRequest.Builder(LocalContext.current)
                                                .data(data = if (viewModel.dishes.value!![index].image_url != "") viewModel.dishes.value!![index].image_url else viewModel.dishes.value!![index].description).apply(block = fun ImageRequest.Builder.() {
                                                    crossfade(true)
                                                    placeholder(R.drawable.bag_icon) // указываем изображение-заглушку
                                                }).build()
                                        ),
                                        modifier = Modifier.size(90.dp),
                                        contentDescription = viewModel.dishes.value!![index].name,
                                        contentScale = ContentScale.FillBounds
                                    )
                                }
                                Text(
                                    text = viewModel.dishes.value!![index].name,
                                    fontFamily = sf_font,
                                    fontWeight = FontWeight(400),
                                    fontSize = 14.sp
                                )

                            }
                        }else{
                            if (viewModel.dishes.value!![index].tegs.containsAll(buttonTags)){
                                Column(modifier = Modifier
                                    .padding(6.dp)
                                    .width(119.dp)
                                    .height(150.dp)) {
                                    Box(
                                        modifier = Modifier
                                            .size(109.dp)
                                            .clip(RoundedCornerShape(10))
                                            .background(Color(red = 248, green = 247, blue = 245))
                                            .clickable {
                                                indexDish = index
                                                openDialog.value = true

                                            },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Image(
                                            painter =
                                            rememberAsyncImagePainter(
                                                ImageRequest.Builder(LocalContext.current)
                                                    .data(data = if (viewModel.dishes.value!![index].image_url != "") viewModel.dishes.value!![index].image_url else viewModel.dishes.value!![index].description).apply(block = fun ImageRequest.Builder.() {
                                                        crossfade(true)
                                                        placeholder(R.drawable.bag_icon) // указываем изображение-заглушку
                                                    }).build()
                                            ),
                                            modifier = Modifier.size(90.dp),
                                            contentDescription = viewModel.dishes.value!![index].name,
                                            contentScale = ContentScale.FillBounds
                                        )
                                    }
                                    Text(
                                        text = viewModel.dishes.value!![index].name,
                                        fontFamily = sf_font,
                                        fontWeight = FontWeight(400),
                                        fontSize = 14.sp
                                    )

                                }
                            }
                        }
                    }
                }
            }
            // Обработка диалогового окна
            if (openDialog.value) {
                Dialog(
                    onDismissRequest = {
                        openDialog.value = false
                    },
                    content = {
                        Column(
                            modifier = Modifier
                                .clip(RoundedCornerShape(5))
                                .background(Color.White)
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp), contentAlignment = Alignment.TopEnd) {
                                Box(
                                    modifier = Modifier
                                        .height(250.dp)
                                        .width(320.dp)
                                        .clip(RoundedCornerShape(5))
                                        .background(Color(red = 248, green = 247, blue = 245)),
                                    contentAlignment = Alignment.Center
                                ) {

                                    Image(
                                        painter =
                                        rememberAsyncImagePainter(
                                            ImageRequest.Builder(LocalContext.current)
                                                .data(data = if (viewModel.dishes.value!![indexDish].image_url != "") viewModel.dishes.value!![indexDish].image_url else viewModel.dishes.value!![indexDish].description).apply(block = fun ImageRequest.Builder.() {
                                                    crossfade(true)
                                                    placeholder(R.drawable.bag_icon) // указываем изображение-заглушку
                                                }).build()
                                        ),
                                        modifier = Modifier.size(180.dp),
                                        contentDescription = viewModel.dishes.value!![indexDish].name,
                                        contentScale = ContentScale.Fit
                                    )
                                }
                                Row() {
                                    Spacer(modifier = Modifier.width(5.dp))
                                    // Добавить в избранное
                                    Box(modifier = Modifier
                                        .padding(vertical = 5.dp)
                                        .size(32.dp)
                                        .clip(RoundedCornerShape(30))
                                        .background(Color.White)
                                        .clickable {

                                        }
                                        , contentAlignment = Alignment.Center) {
                                        Icon(painterResource(id = R.drawable.favorite), contentDescription = "favorite", modifier = Modifier.size(22.dp))
                                    }
                                    Spacer(modifier = Modifier.width(5.dp))
                                    // Закрыть
                                    Box(modifier = Modifier
                                        .padding(vertical = 5.dp)
                                        .size(32.dp)
                                        .clip(RoundedCornerShape(30))
                                        .background(Color.White)
                                        .clickable {
                                            openDialog.value = false
                                        }
                                        , contentAlignment = Alignment.Center) {
                                        Icon(Icons.Default.Close, contentDescription = "favorite", modifier = Modifier.size(22.dp))
                                    }
                                    Spacer(modifier = Modifier.width(5.dp))

                                }
                            }
                            Column(
                                modifier = Modifier.width(280.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = viewModel.dishes.value!![indexDish].name,
                                    fontFamily = sf_font,
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight(600),
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                                Row(
                                    modifier = Modifier
                                        .padding(vertical = 6.dp)
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Start
                                ) {
                                    Text(
                                        text = "${viewModel.dishes.value!![indexDish].price} ₽ ",
                                        fontFamily = sf_font,
                                        color = Color.Black,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight(400),
                                        overflow = TextOverflow.Ellipsis,
                                    )
                                    Text(
                                        text = "• ${viewModel.dishes.value!![indexDish].weight}г",
                                        fontFamily = sf_font,
                                        color = Color.LightGray,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight(200),
                                        overflow = TextOverflow.Ellipsis,
                                    )
                                }
                                Text(
                                    viewModel.dishes.value!![indexDish].description,
                                    fontFamily = sf_font,
                                    color = Color.Gray,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight(200),
                                    overflow = TextOverflow.Ellipsis,

                                )
                                Button(onClick = { /*TODO*/ }) {
                                    Text(text = "Добавить в корзину")
                                }
                            }
                        }
                    },
                )
            }
        }
    }
}