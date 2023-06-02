package services.ingrediente

import exception.ArchivoNoEditableException
import models.Ingrediente
import repositories.StorageService
import validator.canRead
import validator.canWrite
import java.io.File
import java.nio.file.Files

object IngredientesCSV: StorageService<Ingrediente> {
    private val path: String = System.getProperty("user.dir")
    private val fileIngredientes = File("${path + File.separator}data${File.separator}Ingredientes.csv")
    override fun guardar(lista: List<Ingrediente>) {
        if (!fileIngredientes.exists()){
            Files.createFile(fileIngredientes.toPath())
        }
        if (canWrite(fileIngredientes)){
            fileIngredientes.writeText("ID, Nombre, Precio" + "\n")
            lista.forEach {
                fileIngredientes.appendText("${it.id}, ${it.name}, ${it.price} " + "\n")
            }
        }else
            ArchivoNoEditableException()
    }
    override fun cargar(): List<Ingrediente> {
        return if (!fileIngredientes.exists() || !canRead(fileIngredientes)) {
            emptyList()
        }else {
            val leerArchivo = fileIngredientes.readLines().drop(1).map { x -> x.split(",") }.map { x -> Ingrediente(id = x[0].toInt(), name = x[1].trim(), price = x[3].toDouble())
            }
            leerArchivo.forEach {println(it)}
            leerArchivo
        }

    }
}