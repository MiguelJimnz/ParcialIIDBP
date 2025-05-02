package com.example.parcial.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableTarget
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.parcial.R


@Composable
fun PantallaDetalle(navController: NavController, viewModel: ProductosViewModel, productoId: Int) {
    val producto = viewModel.obtenerProductoPorId(productoId)

    producto?.let {
        Column(modifier = Modifier.padding(16.dp)) {
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
            Spacer(modifier = Modifier.height(16.dp))
            Text(producto.nombre, style = MaterialTheme.typography.headlineSmall)
            Text("Precio: $${producto.precio}", style = MaterialTheme.typography.bodyLarge)
            Text("Descripción: ${producto.descripcion}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                viewModel.agregarAlCarrito(producto)
                navController.popBackStack()
            }) {
                Text("Agregar al carrito")
            }
        }
    } ?: Text("Producto no encontrado")
}