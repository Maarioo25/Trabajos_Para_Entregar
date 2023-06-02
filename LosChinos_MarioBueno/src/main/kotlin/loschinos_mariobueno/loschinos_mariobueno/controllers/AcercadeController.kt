package loschinos_mariobueno.loschinos_mariobueno.controllers

import javafx.fxml.FXML
import javafx.scene.control.Hyperlink
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import loschinos_mariobueno.loschinos_mariobueno.utils.getResourceAsStream

class AcercadeController {
    @FXML
    private lateinit var imagenCanica: ImageView

    @FXML
    private lateinit var hyperlink: Hyperlink

    @FXML
    private fun initialize() {
        imagenCanica.image = Image(getResourceAsStream("images/imagen.png"))
        hyperlink.setOnAction {
            val enlace = "https://github.com/Maarioo25"
            java.awt.Desktop.getDesktop().browse(java.net.URI(enlace))
        }
    }
}