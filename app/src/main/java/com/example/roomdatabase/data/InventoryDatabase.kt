package com.example.roomdatabase.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*
* Kelas ini dibentuk untuk dapat membuat dan mengelola instansiasi dari Room database
* Untuk mendeklarasikan bahwa kelas ini berfungsi sebagai Room Database maka perlu untuk dianotasikan "@Database"
* lalu juga perlu untuk mendeklarasikan entity yang akan digunakan yaitu data class Item.
*
* Kelas InventoryDatabase bersifat abstract karena nantinya fungsi Room yang akan melakukan implementasinya.
* */

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class InventoryDatabase: RoomDatabase() {
    /*
    * Fungsi itemDao akan digunakan untuk melakukan return pada objek yang ada di ItemDao sebelumnya.
    * Sehingga, fungsi ini akan memungkinkan agar dapat memanggil fungsi fungsi yang ada di interface
    * itemDao.
    * */
    abstract fun itemDao(): ItemDao

    /*
    * Companion object merupakan sebuah cara untuk dapat melakukan deklarasi object yang dapat diakses tanpa
    * harus melakukan instansiasi object tersebut. Companion object digunakan untuk menampung
    * variabel Instance yang digunakan untuk menyimpan referensi ke database ketika database tersebut dibuat.
    * */
    companion object {
        @Volatile
        private var Instance: InventoryDatabase? = null

        /*
        * merupakan fungsi yang digunakan untuk membuat database yang mana akan menggunakan instance yang sama
        * sebagaimana yang telah disetor pada variable Instance sebelumnya.
        * */
        fun getDatabase(context: Context): InventoryDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}