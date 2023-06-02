package models.dto

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root
    (name = "Hamburguesas")
data class HamburguesasDto(
    @field: ElementList(name = "Hamburguesa", inline = true)
    @param: ElementList(name = "Hamburguesa", inline = true)
    val hamburguesas: List<HamburguesaDto>
)
