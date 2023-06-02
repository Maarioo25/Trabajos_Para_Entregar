package services.hamburguesa

import exception.ArchivoNoEditableException
import models.Hamburguesa
import models.Ingrediente
import repositories.StorageService
import validator.canRead
import validator.canWrite
import validator.existeArchivo
import java.io.*
import java.nio.file.Files
import java.util.*

object HamburguesasTXT: StorageService<Hamburguesa> {
    private val path: String = System.getProperty("user.dir")
    private val fileHamburguesa = File("${path + File.separator}data${File.separator}Hamburguesas.txt")

    override fun guardar(lista: List<Hamburguesa>) {
        if (!existeArchivo(fileHamburguesa)){
            Files.createFile(fileHamburguesa.toPath())
        }
        if (canWrite(fileHamburguesa)){
            lista.forEach{
                fileHamburguesa.appendText(it.id.toString() + "\n")
                fileHamburguesa.appendText(it.name + "\n")
                fileHamburguesa.appendText(it.ingredientes.map {it.toString()}.joinToString { "," } + "\n")
                fileHamburguesa.appendText(it.price.toString() + "\n")
            }
        }else
            ArchivoNoEditableException()
    }
    override fun cargar(): List<Hamburguesa> {
        if (!fileHamburguesa.exists() || !canRead(fileHamburguesa)) {
            return emptyList()
        }else {
            return fileHamburguesa.useLines {
                it.map { line ->
                    val (id, nombre, ingredientes) = line.split(",")
                    Hamburguesa(
                        id = id.toInt(),
                        name = nombre,
                        ingredientes = ingredientes.split(",").map{ingrediente ->
                            val (id, nombre, precio) = ingrediente.split(",")
                            Ingrediente(
                                id = id.toInt(),
                                name = nombre,
                                price = precio.toDouble()
                            )
                        }
                    )
                }.toList()
            }
        }

    }











}