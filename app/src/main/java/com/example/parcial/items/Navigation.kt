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

@Composable
fun Navigation(viewModel: ProductosViewModel = viewModel()){




    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "pantalla_principal") {

        composable("pantalla_principal") {
            PantallaPrincipal(navController = navController, viewModel = viewModel)
        }

        composable("registro_producto") {
            RegistroProductos(navController = navController, viewModel = viewModel)
        }

        composable("pantalla_carrito") {
            PantallaCarrito(navController = navController, viewModel = viewModel)
        }

        composable(
            "pantalla_detalles/{productoId}",
            arguments = listOf(navArgument("productoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("productoId") ?: 0
            PantallaDetalles(navController = navController, viewModel = viewModel, productoId = id)
        }
    }



}
data class Producto(
    val id: Int,
    val nombre: String,
    val precio: Double,
    val descripcion: String,
    val imagenUrl: String
)
class ProductosViewModel : ViewModel() {


    var productos = mutableStateListOf<Producto>()



    private val carrito = mutableStateListOf<Producto>()



    fun agregarProducto(producto: Producto) {
        productos.add(producto)
    }


    fun agregarAlCarrito(producto: Producto) {
        carrito.add(producto)
    }


    fun obtenerProductoPorId(id: Int): Producto? {
        return productos.find { it.id == id }
    }


    fun totalCarrito(): Double {
        return carrito.sumOf { it.precio }
    }


    fun limpiarCarrito() {
        carrito.clear()
    }
}