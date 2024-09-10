package co.edu.unab.etdm.cg.storeapp.updateproduct.data.repository

import co.edu.unab.etdm.cg.storeapp.core.data.local.dao.ProductDAO
import co.edu.unab.etdm.cg.storeapp.core.data.network.FirebaseClient
import co.edu.unab.etdm.cg.storeapp.core.ui.model.Product
import co.edu.unab.etdm.cg.storeapp.core.ui.model.toProductEntity
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UpdateProductRepository @Inject constructor(
    private val productDAO: ProductDAO,
    private val firebaseClient: FirebaseClient
) {
    suspend fun updateProduct(product: Product) {
        productDAO.updateProduct(product.toProductEntity().copy(id = product.id))
    }

    suspend fun updateProductFirestore(product: Product) {
        val productEntity = product.toProductEntity()
        firebaseClient.firestoreDB.collection("products")
            .document(productEntity.id.toString())
            .set(productEntity)
            .await()
    }
}