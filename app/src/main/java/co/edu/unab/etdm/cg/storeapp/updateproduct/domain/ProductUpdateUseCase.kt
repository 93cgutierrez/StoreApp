package co.edu.unab.etdm.cg.storeapp.updateproduct.domain

import co.edu.unab.etdm.cg.storeapp.core.ui.model.Product
import co.edu.unab.etdm.cg.storeapp.updateproduct.data.repository.UpdateProductRepository
import javax.inject.Inject

class ProductUpdateUseCase @Inject constructor(
    private val productRepository: UpdateProductRepository) {
    suspend operator fun invoke(product: Product) {
        productRepository.updateProduct(product)
    }
}