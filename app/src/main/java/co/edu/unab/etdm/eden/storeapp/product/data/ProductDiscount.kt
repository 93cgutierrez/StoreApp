package co.edu.unab.etdm.eden.storeapp.product.data

class ProductDiscount(id: Int, name: String, price: Int, var discount: Int) : Product(id, name, price) {
}