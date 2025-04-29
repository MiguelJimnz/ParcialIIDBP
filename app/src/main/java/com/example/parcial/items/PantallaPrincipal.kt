package com.example.parcial.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.parcial.R

@Composable
fun PantallaPrincipal(navController: NavController, viewModel: ProductosViewModel) {
    val productos = viewModel.productos
    val total = viewModel.totalCarrito()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Catálogo de Productos") })
        },
        floatingActionButton = {
            Column {
                FloatingActionButton(onClick = { navController.navigate("registro_producto") }) {
                    Icon(Icons.Default.Add, contentDescription = "Agregar Producto")
                }
                Spacer(modifier = Modifier.height(8.dp))
                FloatingActionButton(onClick = { navController.navigate("pantalla_carrito") }) {
                    Icon(Icons.Default.ShoppingCart, contentDescription = "Ver Carrito")
                }
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
            Text("Total en carrito: $${"%.2f".format(total)}", style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn {
                items(productos) { producto ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable { navController.navigate("pantalla_detalles/${producto.id}") }
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = rememberImagePainter(
                                    data = producto.imagenUrl,
                                    builder = {
                                        error(R.drawable.placeholder)
                                    }
                                ),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(64.dp)
                                    .padding(8.dp)
                            )
                            Column {
                                Text(producto.nombre, style = MaterialTheme.typography.subtitle1)
                                Text("$${producto.precio}", style = MaterialTheme.typography.body2)
                            }
                        }
                    }
                }
            }
        }
    }
}