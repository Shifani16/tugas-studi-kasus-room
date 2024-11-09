package com.example.roomdatabase.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
* Sesuai dengan namanya, Room database merupakan sebuah database yang disimpan secara lokal.
* Maka dari itu, untuk membuat database tersebut diperlukan suatu 'Entity' dan data class yang
* berguna untuk merepresentasikan class yang akan digunakan di database.
*
* Untuk menandakan bahwa suatu data class tersebut adalah entity dapat dengan mudah hanya dengan menambahkan
* anotasi '@Entity' diikuti dengan nama table databasenya.
*
* Selain itu, sama seperti database sql, suatu table database harus memiliki suatu primary key yang dapat digunakan
* untuk identifier. Sama seperti entity, untuk menandai suatu variable adalah suatu primary key dapat dilakukan dengan
* menambah anotasi '@PrimaryKey' disamping variabel.
* */
@Entity(tableName = "items")
data class Item(
    @PrimaryKey
    val id: Int = 0,
    val name: String,
    val price: Double,
    val quantity: Int
)
