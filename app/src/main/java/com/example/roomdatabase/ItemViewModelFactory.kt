package com.example.roomdatabase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomdatabase.data.ItemsRepository

/*
* Kelas ItemViewModelFactory digunakan untuk membuat dan menyediakan kelas ItemViewModel
* karena mengimplementasikan ViewModelProvider.Factory yang diperlukan untuk membangunan
* instance viewModel.
* */

class ItemViewModelFactory(private val itemsRepository: ItemsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ItemViewModel(itemsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}