package models

import java.time.LocalDate
import java.util.*

class Conductor(val uuid: UUID, val nombre: String, val fechaCarnet: LocalDate) {
    override fun toString(): String {
        return "Id: $uuid, Nombre: $nombre, Fecha del Carnet: $fechaCarnet"
    }
}