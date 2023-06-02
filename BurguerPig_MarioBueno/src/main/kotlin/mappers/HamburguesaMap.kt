package mappers

import models.Hamburguesa
import models.dto.HamburguesaDto
import models.dto.HamburguesasDto
import java.util.*

fun Hamburguesa.toDto() = HamburguesaDto(
    id = this.id.toString(),
    name = this.name,
    ingredientes = this.ingredientes,
    price = this.price
)

fun HamburguesaDto.toHamburguesa() = Hamburguesa(
    id = id.toInt(),
    name = name,
    ingredientes = ingredientes,
    price = price
)

fun List<Hamburguesa>.hamburguesaToDto() = HamburguesasDto(
    hamburguesas = map { it.toDto() }
)

fun HamburguesasDto.toHamburguesasList() = hamburguesas.map { it.toHamburguesa()}