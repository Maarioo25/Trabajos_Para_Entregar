package controllers

import repositories.HamburguesaRepository

class HamburguesaController(
    val hamburguesaRepository: HamburguesaRepository,
) {
    fun guardar(){
        hamburguesaRepository.guardar()
    }
    fun cargar(){
        hamburguesaRepository.cargar()
    }
}