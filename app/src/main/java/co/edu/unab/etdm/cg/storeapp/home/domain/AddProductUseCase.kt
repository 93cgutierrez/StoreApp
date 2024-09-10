package co.edu.unab.etdm.cg.storeapp.home.domain

import co.edu.unab.etdm.cg.storeapp.home.data.repository.HomeRepository
import co.edu.unab.etdm.cg.storeapp.core.ui.model.Product
import javax.inject.Inject

class AddProductUseCase @Inject constructor(
    private val productRepository: HomeRepository
) {
    suspend operator fun invoke(product: Product) {
        productRepository.saveProductFirestore(product)
        //productRepository.saveProduct(product)
    }
}