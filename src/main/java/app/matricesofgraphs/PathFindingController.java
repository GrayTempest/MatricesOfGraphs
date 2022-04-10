package app.matricesofgraphs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.web.WebView;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class PathFindingController {

    FileChooser fileChooser = new FileChooser();

    int[][] am;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem openFile;

    @FXML
    private GridPane paneAdjacency;

    @FXML
    private Button bttnOKsize;

    @FXML
    private Button bttnCompute;

    @FXML
    private ChoiceBox<Integer> nodes;

    @FXML
    private WebView webView;

    @FXML
    void openFile(ActionEvent event) {
        File file = fileChooser.showOpenDialog(new Stage());
        try {
            //Read file (Getting matrix from file)
            Scanner scanner = new Scanner(file);
            ArrayList<String> strings = new ArrayList<String>();
            //Copy file to ArrayList
            while (scanner.hasNextLine()){
                strings.add(scanner.nextLine());
            }
            //Get count of rows and columns in matrix
            int rows = strings.size();
            String[] rowSample = strings.get(0).trim().split("\\s+");
            int columns = rowSample.length;

            System.out.println(rows);
            System.out.println(columns);

            /*ArrayList<Double> imList = new ArrayList<Double>();
            for (int i = 0; i < rows; i++) {
                rowSample = strings.get(i).trim().split("\\s+");
                for (int j = 0; j < columns; j++) {
                    imList.add(Double.parseDouble(rowSample[j]));
                }
            }*/

            //Get array(list) of all elements in matrix
            ArrayList<String> imList = new ArrayList<String>();
            for (int i = 0; i < rows; i++) {
                rowSample = strings.get(i).trim().split("\\s+");
                for (int j = 0; j < columns; j++) {
                    imList.add(rowSample[j]);
                }
            }

            //Display IM
            //Clear pane
            paneAdjacency.getColumnConstraints().clear();
            paneAdjacency.getRowConstraints().clear();
            paneAdjacency.getChildren().clear();
            //
            int column = 0;
            int row = 1;
            int count = 0;

            for (int i = 0; i < rows*columns; i++) {
                if (column == columns){
                    column = 0;
                    row++;
                }
                paneAdjacency.add(new TextField(imList.get(count)), column++, row);
                count++;

                //set item grid width, height
                paneAdjacency.setMinWidth(Region.USE_COMPUTED_SIZE);
                paneAdjacency.setPrefWidth(Region.USE_COMPUTED_SIZE);
                paneAdjacency.setMaxWidth(Region.USE_PREF_SIZE);

                paneAdjacency.setMinHeight(Region.USE_COMPUTED_SIZE);
                paneAdjacency.setPrefHeight(Region.USE_COMPUTED_SIZE);
                paneAdjacency.setMaxHeight(Region.USE_PREF_SIZE);
            }

            //Getting matrix from file to 2D array field
            count = 0;
            am = new int[rows][columns];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    am[i][j] = Integer.parseInt(imList.get(count));
                    count++;
                }

            }

            //Change selected nodes
            nodes.setValue(columns);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        nodes.getItems().addAll(2,3,4,5,6,7,8);
        nodes.setValue(2);

        //OK button - Set sizes for matrix
        bttnOKsize.setOnAction(actionEvent -> {
            //Clear pane
            paneAdjacency.getColumnConstraints().clear();
            paneAdjacency.getRowConstraints().clear();
            paneAdjacency.getChildren().clear();
            //
            System.out.println(nodes.getValue().toString() + " " + nodes.getValue().toString());

            //Display IM
            int column = 0;
            int row = 1;

            for (int i = 0; i < nodes.getValue()*nodes.getValue(); i++) {
                if (column == nodes.getValue()){
                    column = 0;
                    row++;
                }
                paneAdjacency.add(new TextField("0"), column++, row);

                //set item grid width, height
                paneAdjacency.setMinWidth(Region.USE_COMPUTED_SIZE);
                paneAdjacency.setPrefWidth(Region.USE_COMPUTED_SIZE);
                paneAdjacency.setMaxWidth(Region.USE_PREF_SIZE);

                paneAdjacency.setMinHeight(Region.USE_COMPUTED_SIZE);
                paneAdjacency.setPrefHeight(Region.USE_COMPUTED_SIZE);
                paneAdjacency.setMaxHeight(Region.USE_PREF_SIZE);
            }
        });

        //Compute button
        bttnCompute.setOnAction(actionEvent -> {
            //Get matrix of elements of paneA
            Node[][] gridPaneArray = new Node[paneAdjacency.getRowCount()-1][paneAdjacency.getColumnCount()];

            //Weird fix
            GettingElementFromGridPane getEl = new GettingElementFromGridPane();
            for (int i = 0; i < paneAdjacency.getRowCount()-1; i++) {
                for (int j = 0; j < paneAdjacency.getColumnCount(); j++) {
                    gridPaneArray[i][j] = getEl.getNodeFromGridPane(paneAdjacency, j, i+1);
                }
            }

            //Fill matrix (2D array)
            am = new int[paneAdjacency.getRowCount()-1][paneAdjacency.getColumnCount()];
            for (int i = 0; i < paneAdjacency.getRowCount()-1; i++) {
                for (int j = 0; j < paneAdjacency.getColumnCount(); j++) {
                    am[i][j] = Integer.parseInt(((TextField)gridPaneArray[i][j]).getText().trim());
                }
            }

            PathFinding pf = new PathFinding();
            pf.setAm(am);
            pf.pathFind();

            webView.getEngine().loadContent(pf.getPathV());
        });


    }
}
