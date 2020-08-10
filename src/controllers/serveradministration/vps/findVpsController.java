package controllers.serveradministration.vps;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import products.databases.DatabaseAgregator;
import products.databases.UsedUuidException;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class findVpsController implements Initializable {

    @FXML TextField uuidTextField;
    @FXML TextField domainTextField;
    @FXML TextArea notificationTextArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void searchButtonPushed(ActionEvent event) throws IOException {
        try {
            DatabaseAgregator.getVpsDb().findMatch(uuidTextField.getText());
            notificationTextArea.setText("Not found!");
        } catch (UsedUuidException e) {
            notificationTextArea.setText("FOUND! \n In later implementation \n" +
                    "administration of given \n VPS will be opened");
        }
    }

    public void serverMenuButtonPushed(javafx.event.ActionEvent event) throws IOException {
        Parent serverMenuParent = FXMLLoader.load(getClass().getResource("../../../views/mainmenuviews/" +
                "ServerMenuScene.fxml"));
        Scene serverMenuScene = new Scene(serverMenuParent);

        // get the Stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(serverMenuScene);
        window.show();
    }
}
