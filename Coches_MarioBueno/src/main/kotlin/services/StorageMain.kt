package services

interface StorageMain <T> {
    fun exportarJSON(lista: List<T>): List<T>

    fun cargarDesdeCSV(): List<T>

    fun cargarDesdeJSON(): List<T>
}