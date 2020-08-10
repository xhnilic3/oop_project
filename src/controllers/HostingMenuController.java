package controllers;

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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import products.components.Ram;
import products.databases.DatabaseAgregator;
import products.databases.ProductDatabase;
import products.databases.UserDatabase;
import products.hostings.Hosting;
import products.servers.Vps;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class HostingMenuController extends ProductMenu implements Initializable {


    @FXML private MenuItem createWphostingItem;
    @FXML private MenuButton menuButton;

    @FXML private TableView<Hosting> tableView;
    @FXML private TableColumn<Hosting, String> hostingUuid;
    @FXML private TableColumn<Hosting, String> hostingDomain;
    @FXML private TableColumn<Hosting, String> hostingDbmsType;
    @FXML private TableColumn<Hosting, String> hostingDbName;

    // setting of values according to occurrences of  names of variables similar to strings below

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hostingUuid.setCellValueFactory(new PropertyValueFactory<Hosting, String>("uuid"));
        hostingDomain.setCellValueFactory(new PropertyValueFactory("hostingDomain"));
        hostingDbmsType.setCellValueFactory(new PropertyValueFactory("hostingDbmsType"));
        hostingDbName.setCellValueFactory(new PropertyValueFactory("hostingDbName"));
        tableView.setItems(getHostingInfo());
    }
    // filling of observablelist with objects info
    public ObservableList<Hosting> getHostingInfo() {
        ObservableList<Hosting> servers = FXCollections.observableArrayList();
        ProductDatabase db = DatabaseAgregator.getHostingDb();
        for (Map.Entry mapElement : db.getMap().entrySet()) {
            String key = (String)mapElement.getKey();


            Hosting value = (Hosting) mapElement.getValue();

            servers.add(value);
        }
        return servers;
    }


    // TODO refactor in order to make path as attribute so there will not be needed to make method for every window
    private void switchScene(ActionEvent event, String path) throws IOException {
        // change scene
        Parent createVpsParent = FXMLLoader.load(getClass().getResource(path));
        Scene createVpsScene = new Scene(createVpsParent);

        // get the Stage information
        Stage window = (Stage) menuButton.getScene().getWindow();
        window.setScene(createVpsScene);
        window.show();
    }

    @FXML private void switchFindHostingScene(ActionEvent event) throws IOException {
        String path = "../views/mainmenuviews/hostingadministration/wphosting/FindWpHostingScene.fxml";
        switchScene(event, path);
    }



    @FXML
    private void handleCreateButtonAction(ActionEvent event) {

        createWphostingItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent)  {
                String path = "../views/mainmenuviews/hostingadministration/wphosting/CreateWpHostingScene.fxml";
                try {
                    switchScene(event, path);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }) ;

    }

    // delete chosen hosting from db
    public void deleteButtonPushed(ActionEvent event) throws IOException {

        DatabaseAgregator.getHostingDb().getMap().remove(tableView.getSelectionModel().getSelectedItem().getUuid());
        Parent serverMenuParent = FXMLLoader.load(getClass().getResource("../views/mainmenuviews/" +
                "HostingMenuScene.fxml"));
        Scene serverMenuScene = new Scene(serverMenuParent);

        // get the Stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(serverMenuScene);
        window.show();

    }

}
