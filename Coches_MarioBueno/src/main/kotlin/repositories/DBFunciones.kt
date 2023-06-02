package repositories

interface DBFunciones<ID, T> {
    fun guardarEnDB(item: T): T

    fun borrarDeDB(id: ID): Boolean

    fun vaciarTablas(): Boolean
}