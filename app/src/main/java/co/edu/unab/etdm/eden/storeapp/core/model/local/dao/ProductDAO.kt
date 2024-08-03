package co.edu.unab.etdm.eden.storeapp.core.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import co.edu.unab.etdm.eden.storeapp.core.model.local.entity.ProductEntity

@Dao
interface ProductDAO {

    @Query("SELECT * FROM products")
    fun getAllProducts(): LiveData<ProductEntity>

    @Query("SELECT * FROM products WHERE id = :productId")
    fun getProductById(productId: Int): LiveData<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProduct(productEntity: ProductEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateProduct(productEntity: ProductEntity): Int

    @Delete
    fun deleteProduct(productEntity: ProductEntity)

}