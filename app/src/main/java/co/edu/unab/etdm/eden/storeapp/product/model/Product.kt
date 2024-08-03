package co.edu.unab.etdm.eden.storeapp.product.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable


@Parcelize
@Serializable
open class Product(
    var id: Int,  // Unique identifier for each product. In a real-world scenario, this would be a database ID.
    var name: String,
    var price: Int,
    var description: String = "Sin descripción",
    var image: String = "https://www.libreriahuequito.com/public/images/productos/default.png"
) : Parcelable {

    override fun toString(): String {
        return "id: $id name: $name, price: $price, description: $description and image: $image"
    }

}