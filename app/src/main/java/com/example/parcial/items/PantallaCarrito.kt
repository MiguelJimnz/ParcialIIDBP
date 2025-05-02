package com.example.parcial.items

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items


import androidx.compose.material3.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.parcial.items.Producto
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.foundation.lazy.itemsIndexed


@Composable
fun PantallaCarrito(navController: NavController, viewModel: ProductosViewModel) {
    val carrito = viewModel.carrito
    val context = LocalContext.current
    val productosEnCarrito by remember { derivedStateOf { viewModel.carrito.toList() } }
    val total by remember { derivedStateOf { viewModel.totalCarrito() } }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Text(
            text = "Carrito de Compras",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        if (productosEnCarrito.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "El carrito está vacío",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                itemsIndexed(productosEnCarrito) { index, producto ->
                    Card(

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(producto.nombre, style = MaterialTheme.typography.titleMedium)
                                Text(
                                    "$${"%.2f".format(producto.precio)}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }
            }

            Divider(modifier = Modifier.padding(vertical = 8.dp))
            Text(
                "Total: $${"%.2f".format(total)}",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.align(Alignment.End)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        val scope = rememberCoroutineScope()

        Button(
            onClick = {
                navController.popBackStack()
                scope.launch {
                    delay(300) // pequeña pausa para asegurar recomposición
                    viewModel.limpiarCarrito()
                    Toast
                        .makeText(context, "Compra finalizada", Toast.LENGTH_SHORT)
                        .show()
                }
            },
            enabled = productosEnCarrito.isNotEmpty(),
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
            )
        ) {
            Text("Finalizar Compra")
        }

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver al Catálogo")
        }
    }
}