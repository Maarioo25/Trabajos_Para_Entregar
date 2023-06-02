package services.hamburguesa

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import exception.ArchivoNoEditableException
import models.Hamburguesa
import repositories.StorageService
import utils.UuidAdapter
import java.io.File
import java.nio.file.Files.createFile
import utils.toPrettyJson
import validator.canRead
import validator.canWrite
import validator.existeArchivo


@ExperimentalStdlibApi
object HamburguesasJSON: StorageService<Hamburguesa> {
    private val path: String = System.getProperty("user.dir")
    private val fileHamburguesa = File("${path + File.separator}data${File.separator}Hamburguesas.json")
    private val moshi = Moshi.Builder().add(UuidAdapter()).addLast(KotlinJsonAdapterFactory()).build()
    private val jsonAdapter = moshi.adapter<List<Hamburguesa>>()

    override fun guardar(lista: List<Hamburguesa>) {
        if (!existeArchivo(fileHamburguesa)){
            createFile(fileHamburguesa.toPath())
        }
        if (canWrite(fileHamburguesa)){
            fileHamburguesa.writeText(jsonAdapter.toPrettyJson(lista))
        }else
            ArchivoNoEditableException()
    }

    override fun cargar(): List<Hamburguesa> {
        return if (!fileHamburguesa.exists() || !canRead(fileHamburguesa)) {
            emptyList()
        }else {
            jsonAdapter.fromJson(fileHamburguesa.readText())!!
        }
    }
}
