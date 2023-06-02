package services.hamburguesa

import exception.ArchivoNoEditableException
import models.Hamburguesa
import models.Ingrediente
import repositories.StorageService
import validator.canRead
import validator.canWrite
import validator.existeArchivo
import java.io.File
import java.nio.file.Files
import java.util.*

object HamburguesasCSV: StorageService<Hamburguesa> {
    private val path: String = System.getProperty("user.dir")
    private val fileHamburguesa = File("${path + File.separator}data${File.separator}Hamburguesas.csv")
    override fun guardar(lista: List<Hamburguesa>) {
        if (!existeArchivo(fileHamburguesa)){
            Files.createFile(fileHamburguesa.toPath())
        }
        if (canWrite(fileHamburguesa)){
            fileHamburguesa.writeText("ID, Nombre, Ingrediente, Precio" + "\n")
            lista.forEach {
                fileHamburguesa.appendText("${it.id}, ${it.name}, ${it.ingredientes.joinToString("-*-")}, ${it.price} " + "\n")
            }
        }else
            ArchivoNoEditableException()
    }
    override fun cargar(): List<Hamburguesa> {
        return if (!fileHamburguesa.exists() || !canRead(fileHamburguesa)) {
            emptyList()
        }else {
            val leerArchivo = fileHamburguesa.readLines().drop(1).map { x -> x.split(",") }.map { x ->
                Hamburguesa(
                    id = x[0].toInt(),
                    name = x[1].trim(),
                    ingredientes = (CSVtoIngredientes(x[2])),
                    price = x[3].toDouble()
                )
            }
            leerArchivo.forEach { println(it) }
            leerArchivo
        }
    }
    private fun CSVtoIngredientes(x: String): List<Ingrediente> {
        val lista = mutableListOf<Ingrediente>()
        val xx = x.removeSurrounding("[", "]").split("-*-")
        xx.forEach {
            lista.add(Ingrediente.separarString(it))
        }
        return lista
    }
}