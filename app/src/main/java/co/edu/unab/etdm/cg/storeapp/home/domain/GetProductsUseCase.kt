package co.edu.unab.etdm.cg.storeapp.home.domain

import co.edu.unab.etdm.cg.storeapp.home.data.repository.HomeRepository
import co.edu.unab.etdm.cg.storeapp.core.ui.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private val homeRepository: HomeRepository) {
    operator fun invoke(): Flow<List<Product>> {
        //ROOM
        //return homeRepository.products
        /*        return flow {
                    val products: List<Product> = homeRepository.productsFirestore()?.documents?.map {
                        it.toObject(Product::class.java)!!
                    } ?: emptyList()
                    emit(products)
                }*/
        //FIRESTORE
        //return homeRepository.productsFirestore()
        return homeRepository.productsAPI()
    }
}