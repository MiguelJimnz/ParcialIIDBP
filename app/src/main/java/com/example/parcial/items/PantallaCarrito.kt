package com.example.parcial.items

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.parcial.items.Producto

@Composable
fun PantallaCarrito(navController: NavController, viewModel: ProductosViewModel){

    val carrito = viewModel.carrito
    val total = viewModel.totalCarrito()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Carrito de Compras", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(carrito) { producto ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(producto.nombre, style = MaterialTheme.typography.subtitle1)
                        Text("$${producto.precio}", style = MaterialTheme.typography.body2)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Total: $${"%.2f".format(total)}", style = MaterialTheme.typography.h6)

        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            viewModel.limpiarCarrito()
            Toast.makeText(LocalContext.current, "Compra finalizada", Toast.LENGTH_SHORT).show()
            navController.popBackStack()
        }) {
            Text("Finalizar Compra")
        }

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButton(onClick = { navController.popBackStack() }) {
            Text("Volver al Catálogo")
        }
    }
}