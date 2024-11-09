package com.example.roomdatabase.data

import kotlinx.coroutines.flow.Flow

/*
* Kelas ItemsRepository digunakan sebagai kelas abstraksi untuk
* mendeklarasikan operasi-operasi yang dilakukan pada data. Kelas ItemsRepository
* bersifat interface sehingga nantinya kelas yang memanggil interface ini harus
* menyediakan implementasi untuk semua fungsi yang dipanggilnya.
* */

interface ItemsRepository {
    fun getAllItemStream(): Flow<List<Item>>

    fun getItemStream(itemId: Int): Flow<Item?>

    /*
    * menggunakan suspend agar fungsi dapat berjalan secara asinkron dan dijalankan oleh coroutine.
    * */
    suspend fun insertItem(item: Item)

    suspend fun deleteItem(item: Item)

    suspend fun updateItem(item: Item)
}