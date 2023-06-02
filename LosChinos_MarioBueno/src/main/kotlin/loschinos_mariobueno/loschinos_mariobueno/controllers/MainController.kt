package loschinos_mariobueno.loschinos_mariobueno.controllers

import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory
import loschinos_mariobueno.loschinos_mariobueno.routes.RoutesManager
import kotlin.system.exitProcess

class MainController {
    private var rondasGanadasDeLaIA: Int = 0
    private var rondasGanadasDelJugador: Int = 0
    @FXML
    private lateinit var menuInformacion: MenuItem

    @FXML
    private lateinit var menuAcercaDe: MenuItem

    @FXML
    private lateinit var textRondasGanadasIA: TextArea

    @FXML
    private lateinit var textRondasGanadasJugador: TextArea

    @FXML
    private lateinit var seleccionIA: TextArea

    @FXML
    private lateinit var spinnerSeleccionJugador: Spinner<Int>

    @FXML
    private lateinit var spinnerSeleccionBolasIA: Spinner<Int>

    @FXML
    private lateinit var IA_Acierta: Label

    @FXML
    private lateinit var IA_noAcierta: Label

    @FXML
    private lateinit var Jugador_Acierta: Label

    @FXML
    private lateinit var Jugador_noAcierta: Label

    @FXML
    private lateinit var botonEnviar: Button

    @FXML
    private fun initialize() {
        textRondasGanadasIA.isEditable = false
        textRondasGanadasJugador.isEditable = false
        seleccionIA.isEditable = false

        IA_Acierta.isVisible = false
        IA_noAcierta.isVisible = false
        Jugador_Acierta.isVisible = false
        Jugador_noAcierta.isVisible = false

        spinnerSeleccionBolasIA.valueFactory = IntegerSpinnerValueFactory(3, 7, 3, 2)
        spinnerSeleccionJugador.valueFactory = IntegerSpinnerValueFactory(3, 7, 3, 2)

        botonEnviar.setOnAction { clickBoton(spinnerSeleccionJugador.value, spinnerSeleccionBolasIA.value) }

        menuInformacion.setOnAction { RoutesManager.initInformacionStage() }
        menuAcercaDe.setOnAction { RoutesManager.initAcercaDeStage()}
    }

    @FXML
    private fun clickBoton(canicasJugador: Int, seleccionJugador: Int) {
        val canicasIA = listOf(3, 5, 7).random()
        val seleccionIAvalue = listOf(3, 5, 7).random()
        seleccionIA.text = canicasIA.toString()
        when {
            canicasIA == seleccionJugador && seleccionIAvalue != canicasJugador -> {Jugador_Acierta.isVisible = true; IA_noAcierta.isVisible = true;sumarRondaJugador(canicasIA, seleccionIAvalue, canicasJugador, seleccionJugador)}
            canicasIA != seleccionJugador && seleccionIAvalue == canicasJugador -> {IA_Acierta.isVisible = true;Jugador_noAcierta.isVisible = true; sumarRondaIA(canicasIA, seleccionIAvalue, canicasJugador, seleccionJugador)}
            else -> empate(canicasIA, seleccionIAvalue, canicasJugador, seleccionJugador)
        }
        if (rondasGanadasDelJugador == 3 || rondasGanadasDeLaIA == 3){
            fin(rondasGanadasDeLaIA)
        }
    }

    private fun sumarRondaJugador(canicasIA:Int, seleccionIA: Int, canicasJugador: Int, seleccionJugador: Int) {
        rondasGanadasDelJugador++
        textRondasGanadasJugador.text = rondasGanadasDelJugador.toString()
        val info = Alert(Alert.AlertType.INFORMATION)
        info.title = "Información de la Ronda"
        info.headerText = "Has Acertado!!"
        info.contentText = "La IA ha cogido $canicasIA canicas y ha dicho que tu tenías $seleccionIA canicas, tú has cogido $canicasJugador canicas y has dicho que la IA tenía $seleccionJugador canicas, por tanto, tú ganas!!"
        info.showAndWait()
        if (!info.isShowing) {
            Jugador_Acierta.isVisible = false
            IA_noAcierta.isVisible = false
        }
    }

    private fun sumarRondaIA(canicasIA:Int, seleccionIA: Int, canicasJugador: Int, seleccionJugador: Int) {
        rondasGanadasDeLaIA++
        textRondasGanadasIA.text = rondasGanadasDeLaIA.toString()
        val info = Alert(Alert.AlertType.INFORMATION)
        info.title = "Información de la Ronda"
        info.headerText = "La IA ha acertado."
        info.contentText = "La IA ha cogido $canicasIA canicas y ha dicho que tu tenías $seleccionIA canicas, tú has cogido $canicasJugador canicas y has dicho que la IA tenía $seleccionJugador canicas, por tanto, la IA ha ganado."
        info.showAndWait()
        if (!info.isShowing) {
            Jugador_noAcierta.isVisible = false
            IA_Acierta.isVisible = false
        }
    }

    private fun empate(canicasIA:Int, seleccionIA: Int, canicasJugador: Int, seleccionJugador: Int) {
        if (canicasIA == seleccionJugador){
            Jugador_Acierta.isVisible = true
            IA_Acierta.isVisible = true
        }else {
            Jugador_noAcierta.isVisible = true
            IA_noAcierta.isVisible = true
        }
        val info = Alert(Alert.AlertType.INFORMATION)
        info.title = "Información de la Ronda"
        info.headerText = "Habéis Empatado."
        info.contentText = "La IA ha cogido $canicasIA canicas y ha dicho que tu tenías $seleccionIA canicas, tú has cogido $canicasJugador canicas y has dicho que la IA tenía $seleccionJugador canicas, por tanto, Habéis empatado."
        info.showAndWait()
        if (!info.isShowing) {
            Jugador_noAcierta.isVisible = false
            Jugador_Acierta.isVisible = false
            IA_Acierta.isVisible = false
            IA_noAcierta.isVisible = false
        }
    }

    private fun fin(rondasIA: Int) {
        val info = Alert(Alert.AlertType.CONFIRMATION)
        if (rondasIA == 3) {
            info.title = "Fin de la Partida"
            info.headerText = "Fin de la partida"
            info.contentText = """La IA ha conseguido 3 victorias, has perdido la partida.
                |¿Quieres jugar otra partida?
            """.trimMargin()
        }else {
            info.title = "Fin de la Partida"
            info.headerText = "Fin de la partida"
            info.contentText = """Has conseguido 3 victorias, has ganado la partida!!
                |¿Quieres jugar otra partida?
            """.trimMargin()
        }
        val resultado = info.showAndWait()
        when (resultado.get()) {
            ButtonType.CANCEL -> exitProcess(0)
            ButtonType.OK -> {
                rondasGanadasDeLaIA = 0
                rondasGanadasDelJugador = 0
                textRondasGanadasIA.text = rondasGanadasDeLaIA.toString()
                textRondasGanadasJugador.text = rondasGanadasDelJugador.toString()
            }
        }
    }
}