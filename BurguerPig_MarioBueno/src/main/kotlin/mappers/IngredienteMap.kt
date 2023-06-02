package mappers

import models.Ingrediente
import models.dto.IngredienteDto
import models.dto.IngredientesDto

fun IngredienteDto.toIngrediente() = Ingrediente(
    id = this.id,
    name = this.name,
    price = this.price
)

fun Ingrediente.toDto() = IngredienteDto(
    id = id,
    name = name,
    price = price
)

fun List<Ingrediente>.ingredienteToDto() = IngredientesDto(
    ingredientes = map{it.toDto()}
)

fun IngredientesDto.toIngredienteList() = ingredientes.map {it.toIngrediente()}