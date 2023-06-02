package models.dto

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root
    (name = "Coche")
data class CocheDto (
    @field: Element(name = "Id")
    @param: Element(name = "Id")
    val id: Long,

    @field: Element(name = "Marca")
    @param: Element(name = "Marca")
    val marca: String,

    @field: Element(name = "Modelo")
    @param: Element(name = "Modelo")
    val modelo: String,

    @field: Element(name = "Precio")
    @param: Element(name = "Precio")
    val precio: Double,

    @field: Element(name = "TipoMotor")
    @param: Element(name = "TipoMotor")
    val tipoMotor: String,
)