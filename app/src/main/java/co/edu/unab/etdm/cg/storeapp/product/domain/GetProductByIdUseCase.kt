package co.edu.unab.etdm.cg.storeapp.product.domain

import androidx.lifecycle.LiveData
import co.edu.unab.etdm.cg.storeapp.core.ui.model.Product
import co.edu.unab.etdm.cg.storeapp.product.data.repository.ProductDetailRepository
import javax.inject.Inject

class GetProductByIdUseCase @Inject constructor(
    private val productRepository: ProductDetailRepository
) {
    operator fun invoke(id: Int): LiveData<Product> = productRepository.getProductById(id)
}