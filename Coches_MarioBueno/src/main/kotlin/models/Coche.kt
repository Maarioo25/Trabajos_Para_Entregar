package models

import enums.TipoMotor

class Coche (val id: Long, val marca: String, val modelo: String, val precio: Double, val tipoMotor: TipoMotor) {
    override fun toString(): String {
        return "Id: $id, Marca: $marca, Modelo: $modelo, Precio: $precio, Tipo de Motor: $tipoMotor"
    }
}