package validator

import java.io.File

fun existeArchivo(file: File): Boolean {
    return file.exists()
}

fun canRead(file: File): Boolean {
    return file.exists() && file.canRead()
}

fun canWrite(file: File): Boolean {
    return !existeArchivo(file) || (existeArchivo(file) &&  file.canWrite())
}