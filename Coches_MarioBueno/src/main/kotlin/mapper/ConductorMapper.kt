package mapper

import models.Conductor
import models.dto.ConductorDto
import models.dto.ConductoresDto
import utils.localDateFromString
import java.util.*

fun ConductorDto.toConductor() = Conductor(
    uuid = UUID.fromString(uuid),
    nombre = nombre,
    fechaCarnet = localDateFromString(fechaCarnet)
)

fun Conductor.toDto() = ConductorDto(
    uuid = this.uuid.toString(),
    nombre = this.nombre,
    fechaCarnet = this.fechaCarnet.toString()
)

fun List<Conductor>.toDtoList() = ConductoresDto( conductores = map { it.toDto() })

fun ConductoresDto.toConductores() = conductores.map { it.toConductor() }