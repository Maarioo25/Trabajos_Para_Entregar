package models.dto

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root
    (name = "Coches")
data class CochesDto (
    @field: ElementList(name = "Coches", inline = true)
    @param: ElementList(name = "Coches", inline = true)
    val coches: List<CocheDto>
)