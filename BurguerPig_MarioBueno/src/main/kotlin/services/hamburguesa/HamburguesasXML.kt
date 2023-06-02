package services.hamburguesa

import exception.ArchivoNoEditableException
import mappers.hamburguesaToDto
import mappers.toHamburguesasList
import models.Hamburguesa
import org.simpleframework.xml.core.Persister
import repositories.StorageService
import validator.canRead
import validator.canWrite
import validator.existeArchivo
import java.io.File
import java.nio.file.Files

object HamburguesasXML: StorageService<Hamburguesa> {
    private val path: String = System.getProperty("user.dir")
    private val fileHamburguesa = File("${path + File.separator}data${File.separator}Hamburguesas.xml")
    private val serializer = Persister()
    override fun guardar(lista: List<Hamburguesa>) {
        if (!existeArchivo(fileHamburguesa)){
            Files.createFile(fileHamburguesa.toPath())
        }
        if (canWrite(fileHamburguesa)){
            serializer.write(lista.hamburguesaToDto(), fileHamburguesa)
            println(lista)
        }else
            ArchivoNoEditableException()
    }
    override fun cargar(): List<Hamburguesa> {
        return if (!fileHamburguesa.exists() || !canRead(fileHamburguesa)) {
            emptyList()
        }else {
            val cargar = serializer.read(models.dto.HamburguesasDto::class.java, fileHamburguesa).toHamburguesasList()
            cargar.forEach {println(it)}
            cargar
        }
    }
}