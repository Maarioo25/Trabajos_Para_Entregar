package loschinos_mariobueno.loschinos_mariobueno

import javafx.application.Application
import javafx.stage.Stage
import loschinos_mariobueno.loschinos_mariobueno.routes.RoutesManager

class HelloApplication : Application() {
    override fun start(stage: Stage) {
        RoutesManager.apply {
            aplicacion = this@HelloApplication
        }
        RoutesManager.initMainStage(stage)
    }
}

fun main() {
    Application.launch(HelloApplication::class.java)
}