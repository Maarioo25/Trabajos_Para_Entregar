package services.ingrediente

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import exception.ArchivoNoEditableException
import models.Ingrediente
import repositories.StorageService
import utils.UuidAdapter
import java.io.File
import java.nio.file.Files.createFile
import utils.toPrettyJson
import validator.canRead
import validator.canWrite


@ExperimentalStdlibApi
object IngredientesJSON: StorageService<Ingrediente> {
    private val path: String = System.getProperty("user.dir")
    private val fileIngredientes = File("${path + File.separator}data${File.separator}Ingredientes.json")
    private val moshi = Moshi.Builder().add(UuidAdapter()).addLast(KotlinJsonAdapterFactory()).build()
    private val jsonAdapter = moshi.adapter<List<Ingrediente>>()

    override fun guardar(lista: List<Ingrediente>) {
        if (!fileIngredientes.exists()){
            createFile(fileIngredientes.toPath())
        }
        if (canWrite(fileIngredientes)){
            fileIngredientes.writeText(jsonAdapter.toPrettyJson(lista))
        }else
            ArchivoNoEditableException()
    }
    override fun cargar(): List<Ingrediente> {
        return if (!fileIngredientes.exists() || !canRead(fileIngredientes)) {
            emptyList()
        }else {
            jsonAdapter.fromJson(fileIngredientes.readText())!!
        }

    }
}
