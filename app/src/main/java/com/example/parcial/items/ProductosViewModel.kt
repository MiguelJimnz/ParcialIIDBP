package com.example.parcial.items

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel

class ProductosViewModel : ViewModel() {
    private val _productos = mutableStateListOf<Producto>()
    val productos: List<Producto> get() = _productos

    private val _carrito = mutableStateListOf<Producto>()
    val carrito: SnapshotStateList<Producto> = _carrito

    private var nextId = 1



    fun agregarProducto(producto: Producto) {
        _productos.add(producto.copy(id = nextId++))
    }

    fun obtenerProductoPorId(id: Int): Producto? {
        return _productos.find { it.id == id }
    }

    fun agregarAlCarrito(producto: Producto) {
        _carrito.add(producto)
    }

    fun totalCarrito(): Double {
        return _carrito.sumOf { it.precio }
    }

    fun limpiarCarrito() {
        _carrito.clear()
    }
}