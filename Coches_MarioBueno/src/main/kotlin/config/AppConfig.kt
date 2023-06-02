package config

import java.io.FileInputStream
import java.util.Properties

object AppConfig {
    private var input: String = "data"
    val dataInput get() = input

    private var output: String = "data"
    val dataOutput get() = output

    private var _dbUrl: String = "jdbc:sqlite:db.db"
    val databaseUrl get() = _dbUrl

    private var _dbDropTable: Boolean = false
    val dbVaciarTablas get() = _dbDropTable

    init {
        val file = ClassLoader.getSystemResource("program.properties").file
        val properties = Properties()

        properties.load(FileInputStream(file))
        input = properties.getProperty("data.input", "data")
        output = properties.getProperty("data.output", "data")
        _dbUrl = properties.getProperty("database.url", "jdbc:sqlite:db.db")
        _dbDropTable = properties.getProperty("database.droptable", "false").toBoolean()
    }




}