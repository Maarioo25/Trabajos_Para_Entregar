package models

import java.io.Serializable

data class Hamburguesa (val id: Int, val name: String, val ingredientes: List<Ingrediente>, val price: Double = ingredientes.sumOf { it.price }) : Serializable {

    override fun toString(): String {
        return "$id,$name,${ingredientes.joinToString(";")}"
    }
}