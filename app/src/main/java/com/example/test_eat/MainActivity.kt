package com.example.test_eat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.test_eat.navigation.BottomNavigationBar
import com.example.test_eat.navigation.SetupNavController
import com.example.test_eat.ui.theme.Test_EatTheme
import com.example.test_eat.viewmodels.BagViewModel
import com.example.test_eat.viewmodels.MainViewModel

class MainActivity : ComponentActivity() {
    private val viewModel = MainViewModel(savedStateHandle = SavedStateHandle())
    private val bagViewModel = BagViewModel(savedStateHandle = SavedStateHandle())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Test_EatTheme {
                val navController : NavHostController = rememberNavController()

                Scaffold(
                    bottomBar = { BottomNavigationBar(navController) },
                    content = { padding ->
                        Box(modifier = Modifier.padding(padding)) {
                            // Показываем контент
                            SetupNavController(
                                navController = navController,
                                viewModel = viewModel,
                                bagViewModel = bagViewModel
                            )
                        }
                    }
                )
            }
        }

    }
}


