package models

data class Ingrediente (val id: Int, val name: String, val price: Double){
    override fun toString(): String {
        return "$id,$name,$price"
    }

    companion object {
        fun separarString(input: String): Ingrediente {
            val x = input.split(",")
            val id = x[0].toInt()
            val nombre = x[1].trim()
            val precio = x[2].toDouble()
            return Ingrediente(id, nombre, precio)
        }
    }

}