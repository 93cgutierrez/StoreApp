package co.edu.unab.etdm.cg.storeapp.product.data.repository

import androidx.lifecycle.map
import co.edu.unab.etdm.cg.storeapp.core.data.local.dao.ProductDAO
import co.edu.unab.etdm.cg.storeapp.core.ui.model.toProduct
import co.edu.unab.etdm.cg.storeapp.product.datasource.ProductAPIDatasource
import co.edu.unab.etdm.cg.storeapp.product.datasource.ProductFirestoreDatasource
import javax.inject.Inject

class ProductDetailRepository @Inject constructor(
    private val productDAO: ProductDAO,
    private val firestoreDatasource: ProductFirestoreDatasource,
    private val productAPIDataSource: ProductAPIDatasource,  // TODO: CG 20240910 Check if this is necessary or not for the Firestore implementation. If not, remove it.  // TODO: CG 20240910 Add comments to explain the purpose of this repository.  // TODO: CG 20240910 Consider using Room's LiveData or Flow for fetching data from the database.'
) {
    fun getProductById(productId: Int) = productDAO.getProductById(productId = productId)
        .map { item -> item.toProduct() }

    fun getProductByIdFirestore(productId: Int) = firestoreDatasource.getById(productId)

    fun getProductByIdAPI(productId: Int) = productAPIDataSource.getById(productId)

}