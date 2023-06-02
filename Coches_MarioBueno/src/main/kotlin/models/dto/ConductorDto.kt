package models.dto

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root
    (name = "Conductor")
data class ConductorDto(

    @field: Attribute(name = "uuid")
    @param: Attribute(name = "uuid")
    val uuid: String,

    @field: Attribute(name = "nombre")
    @param: Attribute(name = "nombre")
    val nombre: String,

    @field: Attribute(name = "fechaCarnet")
    @param: Attribute(name = "FechaCarnet")
    val fechaCarnet: String,
)
