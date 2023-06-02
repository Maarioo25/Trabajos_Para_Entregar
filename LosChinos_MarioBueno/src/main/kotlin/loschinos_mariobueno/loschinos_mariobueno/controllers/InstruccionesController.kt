package loschinos_mariobueno.loschinos_mariobueno.controllers

import javafx.fxml.FXML
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import loschinos_mariobueno.loschinos_mariobueno.utils.getResourceAsStream

class InstruccionesController {
    @FXML
    private lateinit var imagenCanica: ImageView

    @FXML
    private fun initialize() {
        imagenCanica.image = Image(getResourceAsStream("images/imagen.png"))
    }
}