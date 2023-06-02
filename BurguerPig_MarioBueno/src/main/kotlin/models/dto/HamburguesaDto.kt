package models.dto

import models.Ingrediente
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root


@Root
    (name = "Hamburguesa")
data class HamburguesaDto(
    @field: Attribute(name = "id")
    @param: Attribute(name = "id")
    val id: String,

    @field: Element(name = "Nombre")
    @param: Element(name = "Nombre")
    val name: String,

    @field: ElementList(name = "Ingredientes", inline = true, required = false)
    @param: ElementList(name = "Ingredientes", inline = true, required = false)
    val ingredientes: List<Ingrediente>,

    @field: Attribute(name = "Precio")
    @param: Attribute(name = "Precio")
    val price: Double
)

