package com.example.parcial.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableTarget
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.parcial.R


@Composable
fun PantallaDetalles( navController: NavController,
                      viewModel: ProductosViewModel,
                      productoId: Int)
{

    val producto = viewModel.obtenerProductoPorId(productoId)

    producto?.let {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(it.nombre, style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = rememberImagePainter(
                    data = it.imagenUrl,
                    builder = {
                        error(R.drawable.ic_broken_image)
                    }
                ),
                contentDescription = "Imagen del producto",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Precio: $${it.precio}", style = MaterialTheme.typography.body1)
            Text(it.descripcion, style = MaterialTheme.typography.body2)

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                viewModel.agregarAlCarrito(it)
                navController.popBackStack()
            }) {
                Text("Agregar al Carrito")
            }

            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(onClick = { navController.popBackStack() }) {
                Text("Volver")
            }
        }
    } ?: Text("Producto no encontrado.")
}
