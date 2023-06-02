import controllers.HamburguesaController
import controllers.IngredienteController
import models.Hamburguesa
import models.Ingrediente
import repositories.HamburguesaRepository
import repositories.IngredienteRepository
import services.hamburguesa.*
import services.ingrediente.*

@OptIn(ExperimentalStdlibApi::class)
fun main() {

    //Para guardas cualquier tipo de archivo cambiar la extension del service, el output es la carpeta data.

    val ingredientesRepository = IngredienteRepository(IngredientesJSON)
    val hamburguesasRepository = HamburguesaRepository(HamburguesasJSON)

    val ingredientesController = IngredienteController(ingredientesRepository)
    val hamburguesaController = HamburguesaController(hamburguesasRepository)

    hamburguesaController.guardar()
    ingredientesController.guardar()

    val carne = Ingrediente(1, "carne", 2.00)
    val lechuga = Ingrediente(2, "lechuga", 0.5)
    val queso = Ingrediente(3, "queso", 1.00)
    val tomate = Ingrediente(4, "tomate", 0.65)
    val cebolla = Ingrediente(5, "cebolla", 0.40)
    val bacon = Ingrediente(6, "bacon", 0.7)
    val hamburguesaConQueso = Hamburguesa(1, "Hamburguesa con Queso", listOf(carne, queso))
    val hamburguesaConLechuga = Hamburguesa(2, "Hamburguesa con Lechuga", listOf(carne, lechuga))
    val hamburguesaCompleta = Hamburguesa(3, "Hamburguesa Completa", listOf(carne, lechuga, queso, bacon, cebolla, tomate))
    val hamburguesaSinTomate = Hamburguesa(4, "Hamburguesa sin Tomate", listOf(carne, queso, bacon, cebolla, lechuga))

    val hamburguesas = mutableListOf(hamburguesaConQueso, hamburguesaConLechuga, hamburguesaCompleta, hamburguesaSinTomate)
    val ingredientes = mutableListOf(carne, lechuga, queso,tomate,cebolla,bacon)

    hamburguesaMasCara(hamburguesas)
    hamburguesaConMasIngredientes(hamburguesas)
    numeroDeHamburguesasPorIngrediente(hamburguesas, ingredientes)
    hamburguesasAgrupadasPorNumeroDeIngredientes(hamburguesas)
    precioMedioHamburguesas(hamburguesas)
    precioMedioIngredientes(ingredientes)
}

fun hamburguesaMasCara(hamburguesas: List<Hamburguesa>){
    println("La hamburguesa más cara es: " + hamburguesas.maxBy { it.price }.name)
}

fun hamburguesaConMasIngredientes(hamburguesas: List<Hamburguesa>){
    println("La hamburguesa con más ingredientes es: " + hamburguesas.maxBy { it.ingredientes.count() }.name)
}

fun numeroDeHamburguesasPorIngrediente(hamburguesas: List<Hamburguesa>, ingredientes: List<Ingrediente>){
    for (ingrediente in ingredientes){
        println(ingrediente.name + " : " + hamburguesas.count { it.ingredientes.contains(ingrediente) })
    }
}

fun hamburguesasAgrupadasPorNumeroDeIngredientes(hamburguesas: List<Hamburguesa>){
    println(hamburguesas.groupBy { it.ingredientes.count()})
}

fun precioMedioHamburguesas(hamburguesas: List<Hamburguesa>){
    println(hamburguesas.map {it.price}.average())
}

fun precioMedioIngredientes(ingredientes: List<Ingrediente>){
    println(ingredientes.map { it.price}.average())
}