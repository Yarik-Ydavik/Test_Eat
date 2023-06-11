package com.example.test_eat.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_eat.data.Kitchens_data.categor
import com.example.test_eat.data.dishes.Dishe
import com.example.test_eat.network.request.DishesRequest
import com.example.test_eat.network.request.HomeRequest
import kotlinx.coroutines.launch


class MainViewModel(
    private val savedStateHandle: SavedStateHandle
):ViewModel() {

    val kitchens: MutableLiveData<List<categor>> = savedStateHandle.getLiveData("info1", emptyList<categor>())
    val dishes: MutableLiveData<List<Dishe>> = savedStateHandle.getLiveData("info2", emptyList<Dishe>())

    var isLoading : Boolean by mutableStateOf(false)
        private set

    init {
        isLoading = true
        viewModelScope.launch {
            val response1 = HomeRequest().getListKitchen().сategories
            val response2 = DishesRequest().getListDishes().dishes
            FillKitchens(response1)
            FillDishes(response2)
            isLoading = false
        }
    }
    fun loadKitchens(){
        isLoading = true
        viewModelScope.launch {
            savedStateHandle["info1"] = HomeRequest().getListKitchen().сategories
            isLoading = false
        }
    }
    private fun FillKitchens(response: List<categor>) {
        viewModelScope.launch {
            savedStateHandle["info1"] = response
            kitchens.value = response // добавляем список кухонь в нашу ViewModel
        }
    }
    private fun FillDishes(response: List<Dishe>){
        viewModelScope.launch {
            savedStateHandle["info2"] = response
        }
    }
}