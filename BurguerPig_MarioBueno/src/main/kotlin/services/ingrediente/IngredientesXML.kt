package services.ingrediente

import exception.ArchivoNoEditableException
import mappers.ingredienteToDto
import mappers.toIngredienteList
import models.Ingrediente
import org.simpleframework.xml.core.Persister
import repositories.StorageService
import validator.canRead
import validator.canWrite
import java.io.File
import java.nio.file.Files

object IngredientesXML: StorageService<Ingrediente> {
    private val path: String = System.getProperty("user.dir")
    private val fileIngredientes = File("${path + File.separator}data${File.separator}Ingredientes.xml")
    private val serializer = Persister()
    override fun guardar(lista: List<Ingrediente>) {
        if (!fileIngredientes.exists()){
            Files.createFile(fileIngredientes.toPath())
        }
        if (canWrite(fileIngredientes)){
            serializer.write(lista.ingredienteToDto(), fileIngredientes)
            println(lista)
        }else
            ArchivoNoEditableException()
    }
    override fun cargar(): List<Ingrediente> {
        return if (!fileIngredientes.exists() || !canRead(fileIngredientes)) {
            emptyList()
        }else {
            val cargar = serializer.read(models.dto.IngredientesDto::class.java, fileIngredientes).toIngredienteList()
            cargar.forEach {println(it)}
            cargar
        }
    }
}