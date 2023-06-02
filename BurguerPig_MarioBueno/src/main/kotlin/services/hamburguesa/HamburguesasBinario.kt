package services.hamburguesa

import exception.ArchivoNoEditableException
import models.Hamburguesa
import repositories.StorageService
import validator.canRead
import validator.canWrite
import validator.existeArchivo
import java.io.File
import java.nio.file.Files.createFile

object HamburguesasBinario: StorageService<Hamburguesa> {
    private val path: String = System.getProperty("user.dir")
    private val fileHamburguesa = File("${path + File.separator}data${File.separator}Hamburguesas.bin")

    override fun guardar(lista: List<Hamburguesa>) {
        if (!existeArchivo(fileHamburguesa)){
            createFile(fileHamburguesa.toPath())
        }
        if (canWrite(fileHamburguesa)){
            val crear = fileHamburguesa.outputStream().use{
                lista.forEach { columna ->
                    it.write(columna.id.toString().toByteArray() + "\n".toByteArray())
                    it.write(columna.name.toByteArray() + "\n".toByteArray())
                    it.write(columna.ingredientes.joinToString("---").toByteArray())
                    it.write(columna.price.toString().toByteArray() + "\n".toByteArray())
                }
            }
            println(crear)
        }else
            ArchivoNoEditableException()
    }

    //No he sido capaz de cargar un archivo binario
    override fun cargar(): List<Hamburguesa> {
        if (!fileHamburguesa.exists() || !canRead(fileHamburguesa)) {
            return emptyList()
        }else {
            TODO()
        }
    }
}