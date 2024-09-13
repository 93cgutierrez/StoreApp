package co.edu.unab.etdm.cg.storeapp.core.ui.model

class ProductDiscount(id: Int, name: String, price: Int, var discount: Int) : Product(id, name, price) {
}