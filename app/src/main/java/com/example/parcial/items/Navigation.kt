package com.example.parcial.items

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.compose.runtime.mutableStateListOf

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val viewModel: ProductosViewModel = viewModel()

    NavHost(navController = navController, startDestination = "pantalla_principal") {
        composable("pantalla_principal") {
            PantallaPrincipal(navController, viewModel)
        }
        composable("registro_producto") {
            RegistroProductos(navController, viewModel)
        }
        composable("pantalla_carrito") {
            PantallaCarrito(navController, viewModel)
        }
        composable(
            "pantalla_detalles/{productoId}",
            arguments = listOf(navArgument("productoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("productoId") ?: 0
            PantallaDetalle(navController, viewModel, productoId = id)
        }
    }
}