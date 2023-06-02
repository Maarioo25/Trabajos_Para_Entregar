package controllers

import models.Coche
import repositories.CocheRepository
import services.CocheStorage

class CocheController(val repository: CocheRepository, val storage: CocheStorage) {

    fun guardaEnDB(item: Coche): Coche {
        repository.guardarEnDB(item)
        return item
    }

    fun guardarTodo(items: List<Coche>): List<Coche> {
        items.forEach{ repository.guardarEnDB(it) }
        return items
    }

    fun borrarPorID(id: Long): Boolean {
        return repository.borrarDeDB(id)
    }

    fun vaciarTablas(): Boolean {
        return repository.vaciarTablas()
    }

    fun guardarEnJSON(items: List<Coche>): List<Coche> {
        return storage.exportarJSON(items)
    }

    fun cargarDesdeJSON(): List<Coche> {
        return storage.cargarDesdeJSON()
    }

    fun cargarDesdeCSV(): List<Coche> {
        return storage.cargarDesdeCSV()
    }
}