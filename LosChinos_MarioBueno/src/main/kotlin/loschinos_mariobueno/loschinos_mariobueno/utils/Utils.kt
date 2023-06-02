package loschinos_mariobueno.loschinos_mariobueno.utils

import loschinos_mariobueno.loschinos_mariobueno.routes.RoutesManager
import java.io.InputStream

fun getResourceAsStream(resource: String): InputStream {
    return RoutesManager.aplicacion::class.java.getResourceAsStream(resource)
        ?: throw RuntimeException("No se ha encontrado el recurso como stream: $resource")
}