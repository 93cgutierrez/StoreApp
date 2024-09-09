package co.edu.unab.etdm.cg.storeapp.product.data.repository

import androidx.lifecycle.map
import co.edu.unab.etdm.cg.storeapp.core.data.local.dao.ProductDAO
import co.edu.unab.etdm.cg.storeapp.core.ui.model.toProduct
import javax.inject.Inject

class ProductDetailRepository @Inject constructor(private val productDAO: ProductDAO) {
    fun getProductById(productId: Int) = productDAO.getProductById(productId = productId)
        .map { item -> item.toProduct() }
}