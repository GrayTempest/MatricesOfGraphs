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
    private Button bttnMinSpanTree;

    @FXML
    private Button bttnGraphColoring;

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
                //File f = new File("src/main/java/app/matricesofgraphs/style.css");//Path from content root ONLY
                //File f = new File("src/main/resources/app/matricesofgraphs/style.css");//Path from content root ONLY
                //scene.getStylesheets().clear();
                //scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
                //scene.getStylesheets().add("style.css");
                stage.setTitle("Поиск пути");

                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        bttnMinSpanTree.setOnAction(actionEvent -> {
            bttnMinSpanTree.getScene().getWindow().hide();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/app/matricesofgraphs/min_spanning_tree.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();

                stage.setTitle("Остовое дерево");

                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        bttnGraphColoring.setOnAction(actionEvent -> {
            bttnGraphColoring.getScene().getWindow().hide();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/app/matricesofgraphs/graph_coloring.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();

                stage.setTitle("Раскраска графа");

                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
