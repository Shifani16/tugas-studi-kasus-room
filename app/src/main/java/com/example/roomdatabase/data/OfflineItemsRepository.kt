package com.example.roomdatabase.data

import kotlinx.coroutines.flow.Flow

/*
* Kelas OfflineItemsRepository adalah kelas yang mengimplementasikan Inteface ItemsRepository
* dengan menggunakan override, fungsi yang terdapat pada ItemsRepository dapat dipanggil dimana saja
* dan mengembalikan hasil sesuai dengan implementasi fungsinya. Untuk implementasi fungsinya menggunakan
* pemanggilan fungsi yang terdapat pada itemDao.
* */

class OfflineItemsRepository(private val itemDao: ItemDao) : ItemsRepository {
    override fun getAllItemStream(): Flow<List<Item>> = itemDao.getAllItems()

    override fun getItemStream(itemId: Int): Flow<Item?> = itemDao.getItemById(itemId)

    override suspend fun insertItem(item: Item) = itemDao.insert(item)

    override suspend fun deleteItem(item: Item) = itemDao.delete(item)

    override suspend fun updateItem(item: Item) = itemDao.update(item)



}