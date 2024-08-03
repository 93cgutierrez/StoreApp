package co.edu.unab.etdm.eden.storeapp.extension

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder

//NavHostController extension
private fun NavHostController.navigateOnce(route: String) {
    if (this.currentDestination?.route !== route) {
        this.navigate(route)
    }
}

private fun NavHostController.navigateOnce(
    route: String,
    builder: NavOptionsBuilder.() -> Unit
) {
    if (this.currentDestination?.route !== route) {
        this.navigate(route, builder)
    }
}

private fun NavController.navigateOnce(route: String) {
    if (this.currentDestination?.route !== route) {
        this.navigate(route)
    }
}

private fun NavController.navigateOnce(
    route: String,
    builder: NavOptionsBuilder.() -> Unit
) {
    if (this.currentDestination?.route !== route) {
        this.navigate(route, builder)
    }
}