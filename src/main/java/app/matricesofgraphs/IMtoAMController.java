package app.matricesofgraphs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class IMtoAMController {

    FileChooser fileChooser = new FileChooser();


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem openFile;

    @FXML
    private GridPane paneIncidence;

    @FXML
    private GridPane paneAdjacency;

    @FXML
    private Button bttnOKsize;

    @FXML
    private Button bttnCompute;

    @FXML
    private ChoiceBox<Integer> nodes;

    @FXML
    private ChoiceBox<Integer> edges;

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
            paneIncidence.getColumnConstraints().clear();
            paneIncidence.getRowConstraints().clear();
            paneIncidence.getChildren().clear();
            //
            int column = 0;
            int row = 1;
            int count = 0;

            for (int i = 0; i < rows*columns; i++) {
                if (column == columns){
                    column = 0;
                    row++;
                }
                paneIncidence.add(new TextField(imList.get(count)), column++, row);
                count++;

                //set item grid width, height
                paneIncidence.setMinWidth(Region.USE_COMPUTED_SIZE);
                paneIncidence.setPrefWidth(Region.USE_COMPUTED_SIZE);
                paneIncidence.setMaxWidth(Region.USE_PREF_SIZE);

                paneIncidence.setMinHeight(Region.USE_COMPUTED_SIZE);
                paneIncidence.setPrefHeight(Region.USE_COMPUTED_SIZE);
                paneIncidence.setMaxHeight(Region.USE_PREF_SIZE);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        nodes.getItems().addAll(2,3,4,5,6,7,8);
        nodes.setValue(2);
        edges.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);
        edges.setValue(1);

        //OK button - Set sizes for matrix
        bttnOKsize.setOnAction(actionEvent -> {
            //Clear pane
            paneIncidence.getColumnConstraints().clear();
            paneIncidence.getRowConstraints().clear();
            paneIncidence.getChildren().clear();
            //
            System.out.println(nodes.getValue().toString() + " " + edges.getValue().toString());

            //Display IM
            int column = 0;
            int row = 1;

            for (int i = 0; i < nodes.getValue()*edges.getValue(); i++) {
                if (column == edges.getValue()){
                    column = 0;
                    row++;
                }
                paneIncidence.add(new TextField("0"), column++, row);

                //set item grid width, height
                paneIncidence.setMinWidth(Region.USE_COMPUTED_SIZE);
                paneIncidence.setPrefWidth(Region.USE_COMPUTED_SIZE);
                paneIncidence.setMaxWidth(Region.USE_PREF_SIZE);

                paneIncidence.setMinHeight(Region.USE_COMPUTED_SIZE);
                paneIncidence.setPrefHeight(Region.USE_COMPUTED_SIZE);
                paneIncidence.setMaxHeight(Region.USE_PREF_SIZE);
            }
        });

        //Compute button
        bttnCompute.setOnAction(actionEvent -> {
            //Clear pane
            paneAdjacency.getColumnConstraints().clear();
            paneAdjacency.getRowConstraints().clear();
            paneAdjacency.getChildren().clear();

            //Fill matrix from i pane

            //Get matrix of elements of paneI
            Node[][] gridPaneArray = new Node[paneIncidence.getRowCount()-1][paneIncidence.getColumnCount()];
            /*for(Node node : paneIncidence.getChildren()) {
                gridPaneArray[paneIncidence.getRowIndex(node)][paneIncidence.getColumnIndex(node)] = node;
            }*/
            //Weird fix
            GettingElementFromGridPane getEl = new GettingElementFromGridPane();
            for (int i = 0; i < paneIncidence.getRowCount()-1; i++) {
                for (int j = 0; j < paneIncidence.getColumnCount(); j++) {
                    gridPaneArray[i][j] = getEl.getNodeFromGridPane(paneIncidence, j, i+1);
                }
            }

            //Fill matrix
            int[][] im = new int[paneIncidence.getRowCount()-1][paneIncidence.getColumnCount()];
            for (int i = 0; i < paneIncidence.getRowCount()-1; i++) {
                for (int j = 0; j < paneIncidence.getColumnCount(); j++) {
                    im[i][j] = Integer.parseInt(((TextField)gridPaneArray[i][j]).getText().trim());
                }
            }
            //Compute
            MatricesConversion mc = new MatricesConversion();
            mc.setIm(im);
            //AM
            int[][] am = mc.convertIMtoAM();

            //Display AM
            for (int i = 0; i < am.length; i++) {
                for (int j = 0; j < am.length; j++) {
                    paneAdjacency.add(new TextField(String.valueOf(am[i][j])), j, i);

                    //set item grid width, height
                    paneAdjacency.setMinWidth(Region.USE_COMPUTED_SIZE);
                    paneAdjacency.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    paneAdjacency.setMaxWidth(Region.USE_PREF_SIZE);

                    paneAdjacency.setMinHeight(Region.USE_COMPUTED_SIZE);
                    paneAdjacency.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    paneAdjacency.setMaxHeight(Region.USE_PREF_SIZE);
                }
            }
        });


    }
}
