package com.example.library.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.library.ui.theme.screens.SplashScreen
import com.example.library.ui.theme.screens.dashboard.DashboardScreen
import com.example.library.ui.theme.screens.login.LoginScreen
import com.example.library.ui.theme.screens.products.AddproductScreen
import com.example.library.ui.theme.screens.products.UpdateproductScreen
import com.example.library.ui.theme.screens.products.ViewProducts
import com.example.library.ui.theme.screens.register.RegisterScreen

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController(),startDestination: String = ROUTE_SPLASH){
    NavHost(navController=navController, startDestination = startDestination){
        composable (ROUTE_SPLASH){ SplashScreen{
            navController.navigate(ROUTE_REGISTER){
                popUpTo(ROUTE_SPLASH){inclusive = true} } } }
        composable (ROUTE_REGISTER){ RegisterScreen(navController) }
        composable (ROUTE_LOGIN){ LoginScreen(navController) }
        composable (ROUTE_DASHBOARD){ DashboardScreen(navController) }
        composable (ROUTE_ADD_PRODUCT){ AddproductScreen(navController) }
        composable(ROUTE_VIEW_PRODUCTS){ ViewProducts(navController) }
        composable("$ROUTE_UPDATE_PRODUCT/{productId}") {
                passedData -> UpdateproductScreen(
            navController, passedData.arguments?.getString("productId")!! )
        }
    }

}