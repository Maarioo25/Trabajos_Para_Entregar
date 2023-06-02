package repositories

interface StorageService<T> {
    fun guardar(lista: List<T>)
    fun cargar(): List<T>
}