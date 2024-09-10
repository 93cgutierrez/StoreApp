package co.edu.unab.etdm.cg.storeapp.updateproduct.data.repository

import co.edu.unab.etdm.cg.storeapp.core.data.local.dao.ProductDAO
import co.edu.unab.etdm.cg.storeapp.core.data.network.FirebaseClient
import co.edu.unab.etdm.cg.storeapp.core.ui.model.Product
import co.edu.unab.etdm.cg.storeapp.core.ui.model.toProductEntity
import co.edu.unab.etdm.cg.storeapp.product.datasource.ProductFirestoreDatasource
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UpdateProductRepository @Inject constructor(
    private val productDAO: ProductDAO,
    private val datasource: ProductFirestoreDatasource,
) {
    suspend fun updateProduct(product: Product) {
        productDAO.updateProduct(product.toProductEntity().copy(id = product.id))
    }

    fun updateProductFirestore(product: Product) {
        datasource.update(product)
    }
}