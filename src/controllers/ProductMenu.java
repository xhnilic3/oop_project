package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ProductMenu {
    public void mainMenuButtonPushed(javafx.event.ActionEvent event) throws IOException {
        Parent mainMenuParent = FXMLLoader.load(getClass().getResource("../views/MainMenuScene.fxml"));
        Scene mainMenuScene = new Scene(mainMenuParent);

        // get the Stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainMenuScene);
        window.show();
    }
}
