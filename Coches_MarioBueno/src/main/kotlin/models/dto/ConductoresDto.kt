package models.dto

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root
    (name = "Conductores")
data class ConductoresDto(
    @field: Attribute(name = "conductores")
    @param: Attribute(name = "conductores")
    val conductores: List<ConductorDto>
)
