module app.matricesofgraphs {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;


    opens app.matricesofgraphs to javafx.fxml;
    exports app.matricesofgraphs;
}