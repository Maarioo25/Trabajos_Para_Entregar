package models.dto

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root
(name = "Ingrediente")
data class IngredienteDto(
    @field: Attribute(name = "id")
    @param: Attribute(name = "id")
    val id: Int,

    @field: Element(name = "Nombre")
    @param: Element(name = "Nombre")
    val name: String,

    @field: Attribute(name = "Precio")
    @param: Attribute(name = "Precio")
    val price: Double
)
