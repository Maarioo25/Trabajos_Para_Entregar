import controllers.CocheController
import controllers.ConductorController
import enums.TipoMotor
import models.Coche
import models.Conductor
import repositories.CocheRepository
import repositories.ConductorRepository
import services.CocheStorage
import services.ConductorStorage
import services.DBStorage
import utils.localDateFromString
import java.util.*

fun main() {
    DBStorage
    val controllerCoche = CocheController(CocheRepository, CocheStorage)
    val controllerConductor = ConductorController(ConductorRepository, ConductorStorage)

    controllerCoche.guardarTodo(CocheStorage.cargarDesdeCSV())
    controllerConductor.saveAll(ConductorStorage.cargarDesdeCSV())

    val conductor1 = Conductor(UUID.randomUUID(), "Marcos", localDateFromString("2023-12-20"))
    val conductor2 = Conductor(UUID.randomUUID(), "Martín", localDateFromString("2023-02-15"))
    val conductor3 = Conductor(UUID.randomUUID(), "Cecilia", localDateFromString("2022-10-27"))

    val conductores = listOf<Conductor>(conductor1, conductor2, conductor3)

    controllerConductor.saveAll(ConductorStorage.exportarJSON(conductores))

    val coche1 = Coche(6, "Ford", "500", 500.00, TipoMotor.GASOLINA)
    val coche2 = Coche(7, "Alfa Romeo", "Stazione", 1500.00, TipoMotor.DIESEL)
    val coche3 = Coche(8, "SEAT", "Ibiza", 1200.00, TipoMotor.GASOLINA)

    val coches = listOf(coche1, coche2, coche3)

    controllerCoche.guardarTodo(CocheStorage.exportarJSON(coches))

    controllerCoche.cargarDesdeJSON()
}