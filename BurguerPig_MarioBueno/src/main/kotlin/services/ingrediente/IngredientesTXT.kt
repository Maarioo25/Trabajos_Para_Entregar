package services.ingrediente

import exception.ArchivoNoEditableException
import models.Ingrediente
import repositories.StorageService
import validator.canRead
import validator.canWrite
import validator.existeArchivo
import java.io.File
import java.nio.file.Files

object IngredientesTXT: StorageService<Ingrediente> {
    private val path: String = System.getProperty("user.dir")
    private val fileIngredientes = File("${path + File.separator}data${File.separator}Ingredientes.txt")

    override fun guardar(lista: List<Ingrediente>) {
        if (!existeArchivo(fileIngredientes)){
            Files.createFile(fileIngredientes.toPath())
        }
        if (canWrite(fileIngredientes)){
            lista.forEach{
                fileIngredientes.appendText(it.id.toString() + "\n")
                fileIngredientes.appendText(it.name + "\n")
                fileIngredientes.appendText(it.price.toString() + "\n")
            }
        }else
            ArchivoNoEditableException()
    }

    override fun cargar(): List<Ingrediente> {
        return if (!fileIngredientes.exists() || !canRead(fileIngredientes)) {
            emptyList()
        }else {
            fileIngredientes.useLines {
                it.map { line ->
                    val (id, nombre, precio) = line.split(",")
                    Ingrediente(
                        id = id.toInt(),
                        name = nombre,
                        price = precio.toDouble()
                    )
                }
            }.toList()
        }
    }
}