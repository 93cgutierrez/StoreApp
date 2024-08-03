package co.edu.unab.etdm.eden.storeapp.home.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import co.edu.unab.etdm.eden.storeapp.core.model.local.dao.ProductDAO
import co.edu.unab.etdm.eden.storeapp.product.model.Product
import co.edu.unab.etdm.eden.storeapp.product.model.toProduct
import co.edu.unab.etdm.eden.storeapp.product.model.toProductEntity
import javax.inject.Inject

class HomeRepository @Inject constructor(private val productDAO: ProductDAO) {
    val products: LiveData<List<Product>> = productDAO.getAllProducts().map { items ->
        items.map {
            it.toProduct()
        }
    }

    suspend fun saveProduct(product: Product) {
        productDAO.addProduct(product.toProductEntity())
    }

    suspend fun saveProducts(products: List<Product>) {
        productDAO.addProducts(products.map { it.toProductEntity() })
    }

    suspend fun updateProduct(product: Product): Int {
        return productDAO.updateProduct(product.toProductEntity())
    }

    suspend fun deleteProduct(product: Product) {
        productDAO.deleteProduct(product.toProductEntity())
    }

    suspend fun deleteAllProducts() {
        productDAO.deleteAllProducts()
    }
}