package app.matricesofgraphs;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bttnPathFinding;

    @FXML
    private Button bttnIMtoAM;

    @FXML
    void initialize() {
        bttnIMtoAM.setOnAction(actionEvent -> {
            bttnIMtoAM.getScene().getWindow().hide();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/app/matricesofgraphs/im_to_am.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();


                //f
                File f = new File("src/main/java/app/matricesofgraphs/style.css");//Path from content root ONLY
                scene.getStylesheets().clear();
                scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));

                stage.setTitle("Матрица инцидентности");

                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });





        bttnPathFinding.setOnAction(actionEvent -> {
            bttnPathFinding.getScene().getWindow().hide();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/app/matricesofgraphs/path_finding.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();


                //f
                File f = new File("src/main/java/app/matricesofgraphs/style.css");//Path from content root ONLY
                scene.getStylesheets().clear();
                scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));

                stage.setTitle("Матрица инцидентности");

                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
