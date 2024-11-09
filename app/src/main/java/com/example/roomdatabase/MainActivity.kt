package com.example.roomdatabase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.RoomDatabase
import com.example.roomdatabase.data.InventoryDatabase
import com.example.roomdatabase.data.Item
import com.example.roomdatabase.data.ItemsRepository
import com.example.roomdatabase.data.OfflineItemsRepository
import com.example.roomdatabase.ui.theme.RoomDatabaseTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MainActivity : ComponentActivity() {

    private val database by lazy { InventoryDatabase.getDatabase(applicationContext) } // variabel untuk menyimpan instance dari InventoryDatabase
    private val repository by lazy { OfflineItemsRepository(database.itemDao()) } // variabel untuk menyimpan instance dari OfflineItemsRepository, yang merupakan implementasi dari ItemsRepository
    private val viewModel: ItemViewModel by viewModels { ItemViewModelFactory(repository) } // variabel yang merupakan instance dari ItemViewModel untuk mengelola data terkait item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RoomDatabaseTheme {
                RoomDatabaseApp(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun RoomDatabaseApp(viewModel: ItemViewModel, modifier: Modifier = Modifier) {
    /*
    * Navigasi aplikasi
    * */
    val navController = rememberNavController()
    NavHost(navController, startDestination = "inventory") {
        composable("inventory") { AppListScreen(navController, viewModel) }
        composable("addItem") { AddItemScreen(navController, viewModel) }
        composable("editItem/{itemId}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")?.toIntOrNull() ?: return@composable
            EditItemScreen(navController = navController, viewModel = viewModel, itemId = itemId)
        }
    }
}

@Preview
@Composable
private fun RoomDatabaseAppPreview() {
    val previewViewModel = ItemViewModel(object : ItemsRepository {
        override fun getAllItemStream(): Flow<List<Item>> = flowOf(emptyList())
        override fun getItemStream(id: Int): Flow<Item?> {
            TODO("Not yet implemented") }
        override suspend fun insertItem(item: Item) {}
        override suspend fun deleteItem(item: Item) {}
        override suspend fun updateItem(item: Item) {}
    })


    RoomDatabaseApp(viewModel = previewViewModel)
}