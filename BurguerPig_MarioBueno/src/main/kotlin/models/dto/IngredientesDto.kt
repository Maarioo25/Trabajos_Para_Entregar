package models.dto

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root
    (name = "Ingredientes")
data class IngredientesDto(
    @field: ElementList(name = "Ingredientes", inline = true)
    @param: ElementList(name = "Ingredientes", inline = true)
    val ingredientes: List<IngredienteDto>
)
