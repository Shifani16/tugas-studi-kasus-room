package com.example.roomdatabase.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/*
* Data Access Object atau DAO berguna untuk mengelola data yang ada di dalam Room Database
* DAO berbentuk interface yang digunakan sebagai sebuah wadah untuk melakukan
* deklarasi macam macam operasi seperti CRUD ataupun query query lainnya.
*
* Untuk membentuk sebuah interface DAO, pertama tama dapat dilakukan dengan menuliskan
* anotasi "@Dao"nya. Setelah itu, bentuk fungsi operasi yang ingin dilakukan dengan memanfaatkan
* fungsi suspend. Hal ini akan membuat operasi dapat berjalan secara asinkron dan dapat dijalankan dalam
* coroutine tanpa harus menunggu dan menghentikan proses yang tengah berjalan.
*
*
* */
@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) // diatur agar apabila ada primary key yg bernilai sama maka tidak akan mengubah data yang ada sebelumnya.
    suspend fun insert(item: Item)

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * FROM items ORDER BY name ASC") // query untuk mengambil semua item
    fun getAllItems(): Flow<List<Item>>

    @Query("SELECT * FROM items WHERE id = :itemId") // query untuk mengambil item berdasarkan ID
    fun getItemById(itemId: Int): Flow<Item?>

}