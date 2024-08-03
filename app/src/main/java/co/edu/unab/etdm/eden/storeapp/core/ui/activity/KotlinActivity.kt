package co.edu.unab.etdm.eden.storeapp.core.ui.activity

import co.edu.unab.etdm.eden.storeapp.product.data.Product
import co.edu.unab.etdm.eden.storeapp.product.data.ProductDiscount
import co.edu.unab.etdm.eden.storeapp.login.data.User
import android.app.Activity
import android.os.Bundle
import android.util.Log
import co.edu.unab.etdm.eden.storeapp.R

class KotlinActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        val email: String = "pepito@gamil.com"
        val password: String = "123547"

        val message = login(email, password)

        Log.d("Login info: ", message)

        var keyBoard: Product = Product(id = 1, price = 5000, name = "Teclado Gamer")
        var screen: Product = Product(
            id = 2,
            price = 10000,
            name = "Pantalla Gamer",
            description = "Pantalla 60 Pulgadas 8k"
        )

        var newScreen: ProductDiscount =
            ProductDiscount(id = 3, name = "Pantalla Super Gamer", price = 50000, discount = 15)

    }

    private fun login(email: String, password: String): String {
        return when (email == getUser().email && password == getUser().password) {
            true -> "Iniciando sesión...."
            false -> "Usuario o contraseña invalido porfavor intente de nuevo"
        }

    }

    private fun getUser(): User {
        return User(email = "pepito@gamil.com", password = "123547")
    }
}

