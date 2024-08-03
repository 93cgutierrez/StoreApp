package co.edu.unab.etdm.eden.storeapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import co.edu.unab.etdm.eden.storeapp.home.screen.HomeScreen
import co.edu.unab.etdm.eden.storeapp.home.viewmodel.HomeViewModel
import co.edu.unab.etdm.eden.storeapp.product.screen.ProductDetailScreen
import co.edu.unab.etdm.eden.storeapp.product.viewmodel.ProductDetailViewModel
import co.edu.unab.etdm.eden.storeapp.ui.theme.StoreAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context: Context = LocalContext.current
            val navController = rememberNavController()
            val currentBackStack by navController.currentBackStackEntryAsState()
            val currentDestination: NavDestination? = currentBackStack?.destination
            val currentScreen: StoreAppDestinations =
                StoreAppDestinations.mainScreens.find { it.route == currentDestination?.route }
                    ?: StoreAppDestinations.HomeDestination
            StoreAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text(text = currentScreen.title) },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.Magenta,
                                titleContentColor = Color.White,
                            )
                        )
                    },
                    bottomBar = {
                        NavigationBar(containerColor = Color.Magenta, contentColor = Color.White) {
                            NavigationBarItem(
                                selected = StoreAppDestinations.HomeDestination.route == currentScreen.route,
                                onClick = {
                                    Toast.makeText(context, "home", Toast.LENGTH_SHORT).show()
                                    navController.navigate(
                                        StoreAppDestinations
                                            .HomeDestination.route
                                    ) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Filled.Home,
                                        contentDescription = "home",
                                    )
                                },
                                label = {
                                    Text(text = "Home")
                                })
                            NavigationBarItem(
                                selected = StoreAppDestinations.ProfileDestination.route == currentScreen.route,
                                onClick = {
                                    Toast.makeText(context, "profile", Toast.LENGTH_SHORT).show()
                                    navController.navigate(StoreAppDestinations.ProfileDestination.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Filled.Person,
                                        contentDescription = "profile",
                                    )
                                },
                                label = {
                                    Text(text = "Profile")
                                })
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = StoreAppDestinations.HomeDestination.route
                    ) {
                        composable(StoreAppDestinations.HomeDestination.route) {
                            HomeScreen(
                                navController,
                                Modifier.padding(innerPadding),
                                HomeViewModel()
                            )
                        }
                        composable(StoreAppDestinations.ProfileDestination.route) {
                            ProfileScreen()
                        }
                        composable(
                            StoreAppDestinations
                                .ProductDetailDestination.route,
                            arguments = listOf(navArgument(NavArgs.ProductId.key) {
                                type = NavType.IntType
                            })
                        ) {
                            //log
                            val TAG = "HomeScreen"
                            Log.d(TAG, "NAV INIT")
                            it.arguments?.let { it1 ->
                                ProductDetailScreen(
                                    productId = it1.getInt(NavArgs.ProductId.key),
                                    product = null,
                                    navController = navController,
                                    modifier = Modifier.padding(innerPadding),
                                    viewModel = ProductDetailViewModel(),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StoreAppTheme {
        Greeting("Android")
    }
}