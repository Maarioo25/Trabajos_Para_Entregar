package controllers

import models.Conductor
import repositories.ConductorRepository
import services.ConductorStorage

class ConductorController (val repository: ConductorRepository, val storage: ConductorStorage){

    fun saveAll(items: List<Conductor>): List<Conductor> {
        items.forEach { repository.guardarEnDB(it)}
        return items
    }

    fun vaciarTablas(): Boolean {
        return repository.vaciarTablas()
    }

    fun borrarPorID(uuid: String): Boolean {
        return repository.borrarDeDB(uuid)
    }

    fun guardar(item: Conductor): Conductor {
        return repository.guardarEnDB(item)
    }

    fun guardarEnJSON(items: List<Conductor>): List<Conductor> {
        return storage.exportarJSON(items)
    }

    fun cargarDesdeJSON(): List<Conductor> {
        return storage.cargarDesdeJSON()
    }

    fun cargarDesdeCSV(): List<Conductor> {
        return storage.cargarDesdeCSV()
    }






}