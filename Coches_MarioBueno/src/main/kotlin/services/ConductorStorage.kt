package services

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import config.AppConfig
import mapper.toConductores
import mapper.toDtoList
import models.Conductor
import models.dto.ConductoresDto
import utils.localDateFromString
import java.io.File
import java.nio.file.Files.createFile
import java.util.*

object ConductorStorage: StorageMain<Conductor> {
    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    @OptIn(ExperimentalStdlibApi::class)
    private val adapter = moshi.adapter<ConductoresDto>()

    private val csvFile = File(AppConfig.dataOutput + File.separator + "conductores.csv")
    private val jsonFile = File(AppConfig.dataOutput + File.separator + "conductores.json")

    override fun cargarDesdeCSV(): List<Conductor> {
        if (!csvFile.exists()){
            return emptyList()
        }
        val datos = csvFile.readLines().drop(1).map{ it.split(",") }.map{
            campos -> Conductor(
            uuid = UUID.fromString(campos[0]),
            nombre = campos[1],
            fechaCarnet = localDateFromString(campos[2])
            )
        }
        datos.forEach{ println(it) }
        return datos
    }

    override fun cargarDesdeJSON(): List<Conductor> {
        if (!jsonFile.exists()){
            return emptyList()
        }

        val datos = adapter.fromJson(jsonFile.readText())!!.toConductores()
        datos.forEach { println(it) }
        return datos
    }

    override fun exportarJSON(lista: List<Conductor>): List<Conductor> {
        if (!jsonFile.exists()){
            createFile(jsonFile.toPath())
        }
        jsonFile.writeText(adapter.indent("   ").toJson(lista.toDtoList()))
        return lista
    }











}