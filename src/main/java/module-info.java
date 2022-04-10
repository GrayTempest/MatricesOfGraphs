module app.matricesofgraphs {
    requires javafx.controls;
    requires javafx.fxml;


    opens app.matricesofgraphs to javafx.fxml;
    exports app.matricesofgraphs;
}