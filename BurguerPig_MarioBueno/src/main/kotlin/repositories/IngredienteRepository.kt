package repositories

import models.Hamburguesa
import models.Ingrediente
import mu.KotlinLogging

class IngredienteRepository(private val storage: StorageService<Ingrediente>) {
    private val logger = KotlinLogging.logger {}

    private val carne = Ingrediente(1, "carne", 2.00)
    private val lechuga = Ingrediente(2, "lechuga", 0.5)
    private val queso = Ingrediente(3, "queso", 1.00)
    private val tomate = Ingrediente(4, "tomate", 0.65)
    private val cebolla = Ingrediente(5, "cebolla", 0.40)
    private val bacon = Ingrediente(6, "bacon", 0.7)

    val ingredientes = mutableListOf<Ingrediente>(carne, lechuga, queso,tomate,cebolla,bacon)

    fun findById(id: Int): Ingrediente? {
        cargar()
        return ingredientes.find { it.id == id }
    }

    fun getAll(): List<Ingrediente>{
        cargar()
        return ingredientes.toList()
    }

    fun add(ingrediente: Ingrediente): Ingrediente? {
        (findById(ingrediente.id))?: run {
            ingredientes.add(ingrediente)
            guardar()
            return ingrediente
        }
        return null
    }







    fun guardar() {
        logger.debug("Guardando Ingredientes")
        storage.guardar(ingredientes)
    }

    fun cargar() {
       logger.debug("Cargando Ingredientes")
       storage.cargar()
    }

}
