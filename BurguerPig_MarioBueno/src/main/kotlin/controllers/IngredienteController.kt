package controllers

import repositories.IngredienteRepository

class IngredienteController(private val ingredienteRepository: IngredienteRepository) {
    fun guardar() {
        ingredienteRepository.guardar()
    }

    fun cargar() {
        ingredienteRepository.cargar()
    }
}