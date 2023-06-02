package exception

sealed class ArchivoException(msg: String): RuntimeException (msg)
class ArchivoNoEditableException : ArchivoException("El archivo no se puede editar")
