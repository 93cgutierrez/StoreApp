package co.edu.unab.etdm.cg.storeapp.home.data.repository

import co.edu.unab.etdm.cg.storeapp.core.data.local.dao.ProductDAO
import co.edu.unab.etdm.cg.storeapp.core.ui.model.Product
import co.edu.unab.etdm.cg.storeapp.core.ui.model.toProduct
import co.edu.unab.etdm.cg.storeapp.core.ui.model.toProductEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeRepository @Inject constructor(private val productDAO: ProductDAO) {
    val products: Flow<List<Product>> = productDAO.getAllProducts().map { items ->
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