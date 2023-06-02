package repositories

import models.Conductor
import services.DBStorage.db

object ConductorRepository: DBFunciones<String, Conductor> {

    override fun guardarEnDB(item: Conductor): Conductor {
        val codigo = "INSERT INTO conductores VALUES(?,?,?)"

        db.use {
            it.prepareStatement(codigo).use{
                statement ->
                statement.setString(1, item.uuid.toString())
                statement.setString(2, item.nombre)
                statement.setString(3, item.fechaCarnet.toString())

                statement.executeUpdate()
            }
        }
        return item
    }

    override fun borrarDeDB(id: String): Boolean {
        val codigo = "DELETE FROM conductores WHERE uuid = ?"
        db.use {
            it.prepareStatement(codigo).use{
                statement ->
                statement.setString(1, id)
                statement.executeUpdate()
            }
        }
        return true
    }

    override fun vaciarTablas(): Boolean {
        val codigo = "DELETE FROM conductores"
        db.use {
            it.prepareStatement(codigo).use{
                statement -> statement.executeUpdate()
            }
        }
        return true
    }






}