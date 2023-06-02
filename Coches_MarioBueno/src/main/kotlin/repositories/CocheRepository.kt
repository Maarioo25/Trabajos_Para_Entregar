package repositories

import models.Coche
import services.DBStorage.db

object CocheRepository: DBFunciones<Long, Coche> {

    override fun guardarEnDB(item: Coche): Coche {
        val codigo = "INSERT INTO coches VALUES(null, ?,?,?,?)"

        db.use {
            it.prepareStatement(codigo).use{
                    statement ->
                statement.setString(1, item.marca)
                statement.setString(2, item.modelo)
                statement.setDouble(3, item.precio)
                statement.setString(4, item.tipoMotor.toString())

                statement.executeUpdate()
            }
        }
        return item
    }

    override fun borrarDeDB(id: Long): Boolean {
        val codigo = "DELETE FROM coches WHERE id = ?"
        db.use {
            it.prepareStatement(codigo).use{
                    statement ->
                statement.setLong(1, id)
                statement.executeUpdate()
            }
        }
        return true
    }

    override fun vaciarTablas(): Boolean {
        val codigo = "DELETE FROM coches"
        db.use {
            it.prepareStatement(codigo).use{
                    statement -> statement.executeUpdate()
            }
        }
        return true
    }
}