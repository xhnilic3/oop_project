package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import products.components.Os;
import products.components.Os.Distributions;
import products.components.Ram;
import products.databases.DatabaseAgregator;
import products.databases.ProductDatabase;
import products.servers.Server;
import products.servers.Vps;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ServerMenuController extends ProductMenu implements Initializable {

    private ProductDatabase vpsDb;
    private DatabaseAgregator agregator = new DatabaseAgregator();


    @FXML private Label label;

    @FXML private MenuButton menuButton;
    @FXML private MenuItem vpsMenuItem;
    @FXML private MenuItem vdcMenuItem;
    @FXML private MenuItem dedicatedmenuItem;


    @FXML private TableView<Vps> tableView;
    @FXML private TableColumn<Vps, String> serverUuid;
    @FXML private TableColumn<Vps, String> serverDistro;
    @FXML private TableColumn<Ram, Integer> serverRam;
    @FXML private TableColumn<Vps, Integer> serverStorage;
    @FXML private TableColumn<Vps, String> serverTypeOfStorage;


    private String log = "Log:\n";

    // setting of values according to occurrences of  names of variables similar to strings below

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       serverUuid.setCellValueFactory(new PropertyValueFactory<Vps, String>("uuid"));
       serverDistro.setCellValueFactory(new PropertyValueFactory("serverDistro"));
       serverRam.setCellValueFactory(new PropertyValueFactory("serverRam"));
       serverStorage.setCellValueFactory(new PropertyValueFactory("serverStorage"));
       serverTypeOfStorage.setCellValueFactory(new PropertyValueFactory("serverTypeOfStorage"));
       tableView.setItems(getVpsInfo());
    }


    /**
     * filling of observable list with objects data
     * @return
     */
    public ObservableList<Vps> getVpsInfo() {
        ObservableList<Vps> servers = FXCollections.observableArrayList();
        ProductDatabase db = DatabaseAgregator.getVpsDb();
        for (Map.Entry mapElement : db.getMap().entrySet()) {
            String key = (String)mapElement.getKey();


            Vps value = (Vps) mapElement.getValue();

            servers.add(value);
        }
        return servers;
    }

    private void handleTableView(ActionEvent event) throws IOException {

    }

    private void switchCreateVpsScene(ActionEvent event) throws IOException {
        // change scene
        Parent createVpsParent = FXMLLoader.load(getClass().getResource("../views/mainmenuviews/" +
                "serveradministration/vps/CreateVpsScene.fxml"));
        Scene createVpsScene = new Scene(createVpsParent);

        // get the Stage information
        //Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage window = (Stage) menuButton.getScene().getWindow();
        window.setScene(createVpsScene);
        window.show();
    }

    @FXML
    private void handleCreateButtonAction(ActionEvent event) {

        vpsMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent)  {
                System.out.println("VPS created..");
                log += "VPS created..\n";
                //label.setText(log);



                try {
                    switchCreateVpsScene(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }) ;

        vdcMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("VDC created..");
                log += "VDC created..\n";
                //label.setText(log);
            }
        });

        dedicatedmenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Dedicated server created..");
                log += "Dedicated server created..\n";
                label.setText(log);

            }
        });


    }

    /**
     * Searching for occurency of give uuid in db
     * @param event
     * @throws IOException
     */
    public void findButtonPushed(javafx.event.ActionEvent event) throws IOException {
        Parent findVpsParent = FXMLLoader.load(getClass().getResource("../views/mainmenuviews/" +
                "serveradministration/vps/FindVpsScene.fxml"));
        Scene findVpsScene = new Scene(findVpsParent);

        // get the Stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(findVpsScene);
        window.show();
    }

    /**
     * add new VPS to db
     * @param event
     * @throws IOException
     */
    public void createVpsButtonPushed(javafx.event.ActionEvent event) throws IOException {
        Parent findVpsParent = FXMLLoader.load(getClass().getResource("../views/mainmenuviews/" +
                "serveradministration/vps/CreateVpsScene.fxml"));
        Scene findVpsScene = new Scene(findVpsParent);

        // get the Stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(findVpsScene);
        window.show();
    }

    /*
    * This method will remove the selected server
    */
    public void deleteButtonPushed(ActionEvent event) throws IOException {

        DatabaseAgregator.getVpsDb().getMap().remove(tableView.getSelectionModel().getSelectedItem().getUuid());
        Parent serverMenuParent = FXMLLoader.load(getClass().getResource("../views/mainmenuviews/" +
                "ServerMenuScene.fxml"));
        Scene serverMenuScene = new Scene(serverMenuParent);

        // get the Stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(serverMenuScene);
        window.show();

    }


}
