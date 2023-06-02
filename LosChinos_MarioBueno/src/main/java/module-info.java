module loschinos_mariobueno.loschinos_mariobueno {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires java.desktop;


    opens loschinos_mariobueno.loschinos_mariobueno to javafx.fxml;
    exports loschinos_mariobueno.loschinos_mariobueno;

    opens loschinos_mariobueno.loschinos_mariobueno.controllers to javafx.fxml;
    exports loschinos_mariobueno.loschinos_mariobueno.controllers;
}