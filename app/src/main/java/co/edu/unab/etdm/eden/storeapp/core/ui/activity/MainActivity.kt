package co.edu.unab.etdm.eden.storeapp.core.ui.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavArgument
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import co.edu.unab.etdm.eden.storeapp.product.ui.screen.ProductRegisterScreen
import co.edu.unab.etdm.eden.storeapp.NavArgs
import co.edu.unab.etdm.eden.storeapp.StoreAppDestinations
import co.edu.unab.etdm.eden.storeapp.home.ui.screen.HomeScreen
import co.edu.unab.etdm.eden.storeapp.home.ui.viewmodel.HomeViewModel
import co.edu.unab.etdm.eden.storeapp.product.ui.screen.ProductDetailScreen

import co.edu.unab.etdm.eden.storeapp.product.ui.viewmodel.ProductDetailViewModel
import co.edu.unab.etdm.eden.storeapp.product.ui.viewmodel.ProductRegisterViewModel
import co.edu.unab.etdm.eden.storeapp.profile.ui.screen.ProfileScreen
import co.edu.unab.etdm.eden.storeapp.profile.ui.viewmodel.ProfileViewModel
import co.edu.unab.etdm.eden.storeapp.ui.theme.StoreAppTheme
import co.edu.unab.etdm.eden.storeapp.updateproduct.screen.ProductUpdateScreen
import co.edu.unab.etdm.eden.storeapp.updateproduct.viewmodel.ProductUpdateViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val tag = "HomeScreen"
    private val homeViewModel: HomeViewModel by viewModels()
    private val profileViewModel: ProfileViewModel by viewModels()
    private val productDetailViewModel: ProductDetailViewModel by viewModels()
    private val productRegisterViewModel: ProductRegisterViewModel by viewModels()
    private val productUpdateViewModel: ProductUpdateViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //coroutine
        lifecycleScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                Toast.makeText(baseContext, "Hi", Toast.LENGTH_SHORT).show()
            }
        }
        //homeViewModel.deleteAllProducts()
        //homeViewModel.loadFakeProductList()
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
                                containerColor = Color.Blue,
                                titleContentColor = Color.White,
                            )
                        )
                    },
                    bottomBar = {
                        NavigationBar(
                            containerColor = Color.Blue,
                            contentColor = Color.White,
                            tonalElevation = 8.dp
                        ) {
                            NavigationBarItem(
                                selected = StoreAppDestinations.HomeDestination.route == currentScreen.route,
                                onClick = {
                                    Toast.makeText(context, "home", Toast.LENGTH_SHORT).show()
                                    navController.navigate(
                                        StoreAppDestinations.HomeDestination.route
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
                                        tint = if (StoreAppDestinations.HomeDestination.route
                                            == currentScreen.route
                                        )
                                            Color.Blue else Color.White,
                                    )
                                },
                                label = {
                                    Text(
                                        text = "Home",
                                        color = if (StoreAppDestinations.ProfileDestination.route
                                            == currentScreen.route
                                        )
                                            Color.White else Color.White
                                    )
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
                                        tint = if (StoreAppDestinations.ProfileDestination.route
                                            == currentScreen.route
                                        )
                                            Color.Blue else Color.White
                                    )
                                },
                                label = {
                                    Text(
                                        text = "Profile",
                                        color = if (StoreAppDestinations.ProfileDestination.route
                                            == currentScreen.route
                                        )
                                            Color.White else Color.White,
                                    )
                                })
                        }
                    },
                    floatingActionButton = {
                        //TODO: LEAVE VIEWMODEL
                        val showAddButton: Boolean = true
                        if(showAddButton) {
                            FloatingActionButton(
                                onClick = {
                                    navController.navigate(StoreAppDestinations.ProductRegisterDestination.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                containerColor = Color.Blue,
                                content = {
                                    Icon(Icons.Filled.Add, contentDescription = "Add product")
                                },
                            )
                        }
                    },
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = StoreAppDestinations.HomeDestination.route
                    ) {
                        composable(StoreAppDestinations.HomeDestination.route) {
                            HomeScreen(
                                navController,
                                Modifier.padding(innerPadding),
                                homeViewModel
                            )
                        }
                        composable(StoreAppDestinations.ProfileDestination.route) {
                            ProfileScreen(profileViewModel)
                        }
                        composable(
                            StoreAppDestinations.ProductDetailDestination.route,
                            arguments = listOf(navArgument(NavArgs.ProductId.key) {
                                type = NavType.IntType
                            })
                        ) {
                            //log
                            Log.d(tag, "NAV INIT")
                            it.arguments?.let { it1 ->
                                val productId: Int = it1.getInt(NavArgs.ProductId.key)
                                val productId2: NavArgument? =
                                    navController.currentDestination?.arguments?.get(NavArgs.ProductId.key)
                                if (navController.currentDestination?.route == StoreAppDestinations
                                        .ProductDetailDestination.createRoute(productId)
                                ) {
                                    return@composable
                                }
                                Log.d(tag, "NAV1")
                                ProductDetailScreen(
                                    productId = productId,
                                    product = null,
                                    navController = navController,
                                    modifier = Modifier.padding(innerPadding),
                                    viewModel = productDetailViewModel,
                                )
                            }
                        }
                        composable(
                            StoreAppDestinations.ProductUpdateDestination.route,
                            arguments = listOf(navArgument(NavArgs.ProductId.key) {
                                type = NavType.IntType
                            })
                        ) {
                            //log
                            Log.d(tag, "NAV INIT")
                            it.arguments?.let { it1 ->
                                val productId: Int = it1.getInt(NavArgs.ProductId.key)
                                val productId2: NavArgument? =
                                    navController.currentDestination?.arguments?.get(NavArgs.ProductId.key)
                                if (navController.currentDestination?.route == StoreAppDestinations
                                        .ProductDetailDestination.createRoute(productId)
                                ) {
                                    return@composable
                                }
                                Log.d(tag, "NAV1")
                                ProductUpdateScreen(
                                    productId = productId,
                                    product = null,
                                    navController = navController,
                                    modifier = Modifier.padding(innerPadding),
                                    viewModel = productUpdateViewModel,
                                )
                            }
                        }
                        composable(StoreAppDestinations.ProductRegisterDestination.route) {
                            ProductRegisterScreen(
                                navController = navController,
                                modifier = Modifier.padding(innerPadding),
                                viewModel = productRegisterViewModel,
                            )
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