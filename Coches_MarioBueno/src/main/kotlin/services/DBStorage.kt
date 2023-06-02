package services

import config.AppConfig
import java.sql.Connection
import java.sql.DriverManager

object DBStorage {
    val db: Connection get() = DriverManager.getConnection(AppConfig.databaseUrl)

    init {
        if (AppConfig.dbVaciarTablas){
            vaciarTablas()
        }
        crearTablas()
    }

    private fun crearTablas() {
        val tabla1 = """CREATE TABLE IF NOT EXISTS coches(
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        marca TEXT NOT NULL,
        modelo TEXT NOT NULL,
        precio TEXT NOT NULL,
        precio TEXT NOT NULL,
        tipoMotor TEXT NOT NULL
        )""".trimIndent()

        val tabla2 = """CREATE TABLE IF NOT EXISTS conductores(
        uuid UUID,
        nombre TEXT NOT NULL,
        fechaCarnet TEXT NOT NULL )
        """.trimIndent()

        db.use{
            it.createStatement().use {
                    statement -> statement.executeUpdate(tabla1)
                statement.executeUpdate(tabla2)
            }
        }
    }

    private fun vaciarTablas() {
        val tabla1 = "DROP TABLE IF EXISTS coches"
        val tabla2 = "DROP TABLE IF EXISTS conductores"
        db.use{
            it.createStatement().use {
                statement -> statement.executeUpdate(tabla1)
                statement.executeUpdate(tabla2)
            }
        }
    }
}



