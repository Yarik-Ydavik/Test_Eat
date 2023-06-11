package com.example.test_eat.viewmodels


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.test_eat.data.dishes.Dishe



class BagViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val _bag = MutableLiveData<MutableList<Dishe>>(mutableListOf())
    val bag: LiveData<MutableList<Dishe>> = _bag

    fun addToBag(item: Dishe) {
        val currentList = _bag.value ?: mutableListOf()
        currentList.add(item)
        _bag.value = currentList
    }
    fun deleteFromBag(item: Dishe){
        val currentList = _bag.value ?: mutableListOf()
        currentList.remove(item)
        _bag.value = currentList
    }
    fun increaseCount(index : Int ){
        val currentList = _bag.value ?: mutableListOf()
        val dishe = currentList[index]
        dishe.count +=1
        _bag.value = currentList
    }
    fun decreaseCount(index : Int ){
        val currentList = _bag.value ?: mutableListOf()
        val dishe = currentList[index]
        dishe.count -=1
        _bag.value = currentList
    }
}
