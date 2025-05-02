package com.example.parcial.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.parcial.R
import androidx.compose.material.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*

import androidx.compose.ui.unit.dp

@Composable
fun PantallaPrincipal(navController: NavController, viewModel: ProductosViewModel) {
    val productos = viewModel.productos
    val carrito = viewModel.carrito
    val total by remember { derivedStateOf { viewModel.totalCarrito() } }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Catálogo de Productos",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
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
        Column(modifier = Modifier
            .padding(paddingValues)
            .padding(16.dp)) {

            Text("Total en carrito: $${"%.2f".format(total)}", style = MaterialTheme.typography.headlineSmall)
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
                            val imagen = if (producto.imagenUrl.isNotBlank()) producto.imagenUrl else null
                            AsyncImage(
                                model = imagen,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(64.dp)
                                    .padding(8.dp),
                                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                                error = painterResource(id = R.drawable.ic_launcher_foreground)
                            )
                            Column {
                                Text(producto.nombre, style = MaterialTheme.typography.titleMedium)
                                Text(text = "$${producto.precio}", style = MaterialTheme.typography.bodyMedium)
                            }
                        }
                    }
                }
            }
        }
    }
}