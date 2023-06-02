package services.ingrediente

import exception.ArchivoNoEditableException
import models.Ingrediente
import repositories.StorageService
import validator.canRead
import validator.canWrite
import java.io.File
import java.nio.file.Files.createFile

object IngredientesBinario: StorageService<Ingrediente> {
    private val path: String = System.getProperty("user.dir")
    private val fileIngredientes = File("${path + File.separator}data${File.separator}Ingredientes.bin")

    override fun guardar(lista: List<Ingrediente>) {
        if (!fileIngredientes.exists()){
            createFile(fileIngredientes.toPath())
        }
        if (canWrite(fileIngredientes)){
            val crear = fileIngredientes.outputStream().use{
                lista.forEach { columna ->
                    it.write(columna.id.toString().toByteArray() + "\n".toByteArray())
                    it.write(columna.name.toByteArray() + "\n".toByteArray())
                    it.write(columna.price.toString().toByteArray() + "\n".toByteArray())
                }
            }
            println(crear)
        }else
            ArchivoNoEditableException()
    }

    override fun cargar(): List<Ingrediente> {
        if (!fileIngredientes.exists() || !canRead(fileIngredientes)) {
            return emptyList()
        }else {
            TODO()
        }
    }

}