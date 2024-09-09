package co.edu.unab.etdm.cg.storeapp.updateproduct.data.repository

import co.edu.unab.etdm.cg.storeapp.core.data.local.dao.ProductDAO
import co.edu.unab.etdm.cg.storeapp.product.data.Product
import co.edu.unab.etdm.cg.storeapp.product.data.toProductEntity
import javax.inject.Inject

class UpdateProductRepository @Inject constructor(private val productDAO: ProductDAO) {
    suspend fun updateProduct(product: Product) {
        productDAO.updateProduct(product.toProductEntity().copy(id = product.id))
    }
}