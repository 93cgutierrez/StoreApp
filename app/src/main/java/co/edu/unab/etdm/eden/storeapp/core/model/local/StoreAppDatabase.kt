package co.edu.unab.etdm.eden.storeapp.core.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import co.edu.unab.etdm.eden.storeapp.core.model.local.dao.ProductDAO
import co.edu.unab.etdm.eden.storeapp.core.model.local.entity.ProductEntity

@Database(entities = [ProductEntity::class], version = 1)
abstract class StoreAppDatabase : RoomDatabase() {
    abstract fun productDAO(): ProductDAO
}