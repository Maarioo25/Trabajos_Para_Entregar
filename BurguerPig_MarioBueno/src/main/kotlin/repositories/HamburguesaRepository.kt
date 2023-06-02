package repositories

import models.Hamburguesa
import models.Ingrediente
import mu.KotlinLogging

class HamburguesaRepository (private val storage: StorageService<Hamburguesa>) {
    private val logger = KotlinLogging.logger {}

    private val carne = Ingrediente(1, "carne", 2.00)
    private val lechuga = Ingrediente(2, "lechuga", 0.5)
    private val queso = Ingrediente(3, "queso", 1.00)
    private val tomate = Ingrediente(4, "tomate", 0.65)
    private val cebolla = Ingrediente(5, "cebolla", 0.40)
    private val bacon = Ingrediente(6, "bacon", 0.7)

    private val hamburguesaConQueso = Hamburguesa(1, "Hamburguesa con Queso", listOf(carne, queso))
    private val hamburguesaConLechuga = Hamburguesa(2, "Hamburguesa con Lechuga", listOf(carne, lechuga))
    private val hamburguesaCompleta = Hamburguesa(3, "Hamburguesa Completa", listOf(carne, lechuga, queso, bacon, cebolla, tomate))
    private val hamburguesaSinTomate = Hamburguesa(4, "Hamburguesa sin Tomate", listOf(carne, queso, bacon, cebolla, lechuga))

    private val hamburguesas = mutableListOf(hamburguesaConQueso, hamburguesaConLechuga, hamburguesaCompleta, hamburguesaSinTomate)

    fun findById(id: Int): Hamburguesa? {
        cargar()
        return hamburguesas.find { it.id == id }
    }

    fun getAll(): List<Hamburguesa>{
        cargar()
        return hamburguesas.toList()
    }

    fun add(hamburguesa: Hamburguesa): Hamburguesa? {
        (findById(hamburguesa.id))?: run {
            hamburguesas.add(hamburguesa)
            guardar()
            return hamburguesa
        }
        return null
    }

    fun guardar() {
        logger.debug("Guardando hamburguesas")
        storage.guardar(hamburguesas)
    }

    fun cargar() {
        logger.debug("Cargando hamburguesas")
        storage.cargar()
    }

}