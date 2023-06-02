package services

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import config.AppConfig
import mapper.cochesToDto
import mapper.toCoches
import models.Coche
import models.dto.CochesDto
import java.io.File
import java.nio.file.Files.createFile

object CocheStorage: StorageMain<Coche> {
    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    @OptIn(ExperimentalStdlibApi::class)
    private val adapter = moshi.adapter<CochesDto>()

    private val csvFile = File(AppConfig.dataOutput + File.separator + "coches.csv")
    private val jsonFile = File(AppConfig.dataOutput + File.separator + "coches.json")

    override fun exportarJSON(lista: List<Coche>): List<Coche> {
        if (!jsonFile.exists()) {
            createFile(jsonFile.toPath())
        }
        jsonFile.writeText(adapter.indent("   ").toJson(lista.cochesToDto()))
        return lista
    }

    override fun cargarDesdeCSV(): List<Coche> {
        if (!csvFile.exists()) {
            return emptyList()
        }
        val datos = csvFile.readLines().drop(1).map { it.split(",") }.map { campos ->
            Coche(
                id = campos[0].toLong(),
                marca = campos[1],
                modelo = campos[2],
                precio = campos[3].toDouble(),
                tipoMotor = enumValueOf(campos[4])
            )
        }
        datos.forEach { println(it) }
        return datos
    }

    override fun cargarDesdeJSON(): List<Coche> {
        if (!jsonFile.exists()) {
            return emptyList()
        }
        val datos = adapter.fromJson(jsonFile.readText())!!.toCoches()
        datos.forEach { println(it) }
        return datos
    }
}