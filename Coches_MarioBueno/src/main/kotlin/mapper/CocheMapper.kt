package mapper

import models.Coche
import models.dto.CocheDto
import models.dto.CochesDto

fun CocheDto.toCoche() = Coche(
    id = id,
    marca = marca,
    modelo = modelo,
    precio = precio,
    tipoMotor = enumValueOf(tipoMotor)
)

fun Coche.toDto() = CocheDto(
    id = this.id,
    marca = this.marca,
    modelo = this.modelo,
    precio = this.precio,
    tipoMotor = this.tipoMotor.toString()
)

fun List<Coche>.cochesToDto() = CochesDto(coches = map{ it.toDto() })

fun CochesDto.toCoches() = coches.map { it.toCoche() }