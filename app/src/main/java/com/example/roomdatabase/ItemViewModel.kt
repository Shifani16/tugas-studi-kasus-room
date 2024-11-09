package com.example.roomdatabase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdatabase.data.Item
import com.example.roomdatabase.data.ItemsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/*
* kelas ItemViewModel merupakan kelas yang mengimplementasikan ViewModel.
* ViewModel berguna untuk mengelola dan menyimpan data terkait dengan user interface
* dan memastikan data persist selama perubahan UI. Untuk operasi CRUD, fungsi CRUD menggunakan
* coroutine agar dapat dijalankan dengan asinkron dan tidak menggangu thread UI.
* */

class ItemViewModel(private val itemsRepository: ItemsRepository) : ViewModel() {
    val getAllItems: Flow<List<Item>> = itemsRepository.getAllItemStream()

    fun addItem(item: Item) {
        viewModelScope.launch {
            itemsRepository.insertItem(item)
        }
    }

    fun getItemById(itemId: Int): Flow<Item?> {
        return itemsRepository.getItemStream(itemId)
    }

    fun updateItem(item: Item) {
        viewModelScope.launch {
            itemsRepository.updateItem(item)
        }
    }

    fun deleteItem(item: Item) {
        viewModelScope.launch {
            itemsRepository.deleteItem(item)
        }
    }

}