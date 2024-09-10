package co.edu.unab.etdm.cg.storeapp.home.data.repository

import co.edu.unab.etdm.cg.storeapp.core.data.local.dao.ProductDAO
import co.edu.unab.etdm.cg.storeapp.core.data.network.FirebaseClient
import co.edu.unab.etdm.cg.storeapp.core.ui.model.Product
import co.edu.unab.etdm.cg.storeapp.core.ui.model.toProduct
import co.edu.unab.etdm.cg.storeapp.core.ui.model.toProductEntity
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

private const val COLLECTION_NAME_PRODUCTS = "products"

class HomeRepository @Inject constructor(
    private val productDAO: ProductDAO,
    private val firebaseClient: FirebaseClient
) {
    val products: Flow<List<Product>> = productDAO.getAllProducts().map { items ->
        items.map {
            it.toProduct()
        }
    }

    suspend fun productsFirestore(): QuerySnapshot? {
        return firebaseClient.firestoreDB.collection(COLLECTION_NAME_PRODUCTS).get().await()
    }

    suspend fun saveProductFirestore(product: Product) {
        val productEntity = product.toProductEntity()
        firebaseClient.firestoreDB.collection(COLLECTION_NAME_PRODUCTS)
            .document(productEntity.id.toString())
            .set(productEntity)
            .await()
    }

    suspend fun deleteProductFirestore(product: Product) {
        firebaseClient.firestoreDB.collection(COLLECTION_NAME_PRODUCTS)
            .document(product.id.toString())
            .delete()
            .await()
    }


    suspend fun saveProduct(product: Product) {
        productDAO.addProduct(product.toProductEntity())
    }

    suspend fun saveProducts(products: List<Product>) {
        productDAO.addProducts(products.map { it.toProductEntity() })
    }

    suspend fun deleteProduct(product: Product) {
        productDAO.deleteProduct(product.toProductEntity())
    }

    suspend fun deleteAllProducts() {
        productDAO.deleteAllProducts()
    }
}