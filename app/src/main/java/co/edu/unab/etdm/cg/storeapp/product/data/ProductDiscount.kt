package co.edu.unab.etdm.cg.storeapp.product.data

class ProductDiscount(id: Int, name: String, price: Int, var discount: Int) : Product(id, name, price) {
}