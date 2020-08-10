package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import products.databases.DatabaseAgregator;
import products.databases.ProductDatabase;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    @FXML private Button hostingSceneButton;
    @FXML private Button serverSceneButton;
    @FXML private Button dbSceneButton;

    private ProductDatabase  vpsDb;
    private DatabaseAgregator agregator = new DatabaseAgregator();




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO
    }

   /* public MainMenuController(ProductDatabase vpsDb){ this.vpsDb = vpsDb;}*/
    /*
    * When this method is called, it will change the Scene to
    * a HostingMenu view
    */
    public void hostingButtonPushed(javafx.event.ActionEvent event) throws IOException {
        Parent hostingMenuParent = FXMLLoader.load(getClass().getResource("../views/mainmenuviews/HostingMenuScene.fxml"));
        Scene hostingMenuScene = new Scene(hostingMenuParent);


        // get the Stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(hostingMenuScene);
        window.show();
    }

    public void serverButtonPushed(javafx.event.ActionEvent event) throws IOException {
        /*Parent serverMenuParent = FXMLLoader.load(getClass().getResource("../views/mainmenuviews/ServerMenuScene.fxml"));
        Scene serverMenuScene = new Scene(serverMenuParent);*/

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/mainmenuviews/ServerMenuScene.fxml"));
        Parent serverMenuParent = loader.load();

        Scene serverMenuScene = new Scene(serverMenuParent);


        ServerMenuController controller = new ServerMenuController();


        // get the Stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(serverMenuScene);
        window.show();
    }

    public void passProductDbtoServer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("views/mainmenuviews/ServerMenuScene"));
        Parent serverMenuParent = loader.load();

        Scene serverMenuScene = new Scene(serverMenuParent);


        // access the controller and call the method

        ServerMenuController controller = loader.getController();

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

    }

    public void dbButtonPushed(javafx.event.ActionEvent event) throws IOException {
        Parent dbMenuParent = FXMLLoader.load(getClass().getResource("../views/mainmenuviews/DbMenuScene.fxml"));
        Scene dbMenuScene = new Scene(dbMenuParent);

        // get the Stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(dbMenuScene);
        window.show();
    }




}
