package co.edu.unab.etdm.eden.storeapp.core.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import co.edu.unab.etdm.eden.storeapp.core.data.local.entity.ProductEntity

@Dao
interface ProductDAO {

    @Query("SELECT * FROM products")
    fun getAllProducts(): LiveData<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE id = :productId")
    fun getProductById(productId: Int): LiveData<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(productEntity: ProductEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProducts(productEntities: List<ProductEntity>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProduct(productEntity: ProductEntity): Int

    @Delete
    suspend fun deleteProduct(productEntity: ProductEntity)

    //delete all products
    @Query("DELETE FROM products")
    suspend fun deleteAllProducts()

}