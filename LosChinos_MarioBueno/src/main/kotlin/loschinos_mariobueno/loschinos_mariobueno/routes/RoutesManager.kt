package loschinos_mariobueno.loschinos_mariobueno.routes

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Modality
import javafx.stage.Stage
import loschinos_mariobueno.loschinos_mariobueno.utils.getResourceAsStream

object RoutesManager {
    lateinit var aplicacion: Application

    private var imagen = "images/imagen.png"

    enum class Views(val path: String) {
        MAIN("views/main-view.fxml"),
        INFORMACION("views/informacion-view.fxml"),
        ACERCADE("views/acercade-view.fxml")
    }

    fun initMainStage (stage: Stage) {
        val fxmlLoader = FXMLLoader(aplicacion::class.java.getResource(Views.MAIN.path))
        val scene = Scene(fxmlLoader.load(), 600.0, 400.0)
        stage.apply {
            title = "Los Chinos"
            icons.add(Image(getResourceAsStream(imagen)))
            isResizable = false
            stage.scene = scene
            show()
        }
    }

    fun initInformacionStage () {
        val fxmlLoader = FXMLLoader(aplicacion::class.java.getResource(Views.INFORMACION.path))
        val scene = Scene(fxmlLoader.load(), 500.0, 300.0)
        val stage = Stage()
        stage.apply {
            initModality(Modality.APPLICATION_MODAL)
            title = "Información"
            icons.add(Image(getResourceAsStream(imagen)))
            isResizable = false
            stage.scene = scene
            show()
        }
    }



    fun initAcercaDeStage (stage: Stage = Stage()) {
        val fxmlLoader = FXMLLoader(aplicacion::class.java.getResource(Views.ACERCADE.path))
        val scene = Scene(fxmlLoader.load(), 500.0, 300.0)
        stage.apply {
            initModality(Modality.APPLICATION_MODAL)
            title = "Acerca De"
            icons.add(Image(getResourceAsStream(imagen)))
            isResizable = false
            stage.scene = scene
            show()
        }
    }
}