package com.example.test_eat.navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.test_eat.R
import com.example.test_eat.data.dishes.Dishe
import com.example.test_eat.ui.theme.sf_font
import com.example.test_eat.viewmodels.BagViewModel

@SuppressLint("MutableCollectionMutableState")
@Composable
fun BASKET_SCREEN(viewModel2: BagViewModel) {
    val list = viewModel2.bag.observeAsState().value
    val elements by remember { mutableStateOf(list) }
    var value by remember { mutableStateOf(1) }

    var price by remember { mutableStateOf(elements?.sumOf { it.price * it.count } ?: 0) }

    Column() {
        TopBar1()
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (elements != null && elements!!.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                ) {
                    items(elements!!.size) { index ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            // Картинка товара
                            Box(
                                modifier = Modifier
                                    .size(64.dp)
                                    .clip(RoundedCornerShape(10))
                                    .background(Color(red = 248, green = 247, blue = 245)),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter =
                                    rememberAsyncImagePainter(
                                        ImageRequest.Builder(LocalContext.current)
                                            .data(data = if (elements!![index].image_url != "") elements!![index].image_url else elements!![index].description)
                                            .apply(block = fun ImageRequest.Builder.() {
                                                crossfade(true)
                                                placeholder(R.drawable.bag_icon)
                                            }).build()
                                    ),
                                    modifier = Modifier.size(58.dp),
                                    contentDescription = elements!![index].name,
                                    contentScale = ContentScale.Fit
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .height(64.dp)
                                    .weight(1f)
                                    .padding(vertical = 6.dp, horizontal = 6.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = elements!![index].name,
                                    color = Color.Black,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight(400),
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "${elements!![index].price} ₽ ",
                                        color = Color.Black,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight(400),
                                        overflow = TextOverflow.Ellipsis,
                                    )
                                    Text(
                                        text = "• ${elements!![index].weight}г",
                                        color = Color.LightGray,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight(200),
                                        overflow = TextOverflow.Ellipsis,
                                    )
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .height(64.dp)
                                    .width(100.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(
                                    modifier = Modifier
                                        .height(40.dp)
                                        .width(100.dp)
                                        .clip(RoundedCornerShape(40))
                                        .background(Color(red = 248, green = 247, blue = 245)),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceAround
                                ) {
                                    Box(contentAlignment = Alignment.Center, modifier = Modifier
                                        .padding(start = 5.dp)
                                        .height(24.dp)
                                        .width(24.dp)
                                        .clickable {
                                            if (elements!![index].count - 1 < 1) viewModel2.deleteFromBag(
                                                elements!![index]
                                            )
                                            else price -= elements!![index].price

                                            viewModel2.decreaseCount(index)
                                        }
                                    ) {
                                        Icon(Icons.Default.Remove, contentDescription = "Minus")
                                    }
                                    Box(
                                        contentAlignment = Alignment.Center, modifier = Modifier
                                            .height(20.dp)
                                            .width(20.dp)
                                    ) {
                                        viewModel2.bag.observeForever { it ->
                                            value = it[index].count
                                        }
                                        Text(
                                            text = list!![index].count.toString(),
                                            fontSize = 16.sp,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                    Box(contentAlignment = Alignment.Center, modifier = Modifier
                                        .padding(end = 5.dp)
                                        .height(24.dp)
                                        .width(24.dp)
                                        .clickable {
                                            price += elements!![index].price
                                            viewModel2.increaseCount(index)
                                        }
                                    ) {
                                        Icon(Icons.Default.Add, contentDescription = "Plus")
                                    }
                                }
                            }

                        }
                    }
                }
                Button(
                    onClick = {

                        Log.d("result", price.toString())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(red = 51, green = 100, blue = 224),
                        contentColor = Color(red = 248, green = 247, blue = 245)
                    )
                ) {
                    Text(text = "Оплатить ${price} ₽")
                }

            } else Text(text = "Ваша корзина пуста", fontSize = 14.sp)
        }


    }
}





