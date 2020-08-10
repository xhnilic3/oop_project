package controllers.hostingadministration.wphosting;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import products.databases.DatabaseAgregator;
import products.databases.UserDatabase;
import products.hostings.Hosting;
import products.hostings.WpHosting;

public class CreateWpHostingController implements Initializable {
    @FXML
    private ComboBox<UserDatabase.DbmsType> dbmsTypeComboBox;
    @FXML private TextField dbNameTextField;
    @FXML private TextField userNameTextField;
    @FXML private TextField domainTextField;
    @FXML private PasswordField passwordField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dbmsTypeComboBox.getItems().addAll(UserDatabase.DbmsType.POSTGRES, UserDatabase.DbmsType.MYSQL);
    }

    public void createButtonPushed(ActionEvent event) throws IOException {
        String dbName, userName, password, domain;

        UserDatabase.DbmsType dbms =  dbmsTypeComboBox.getValue();
        dbName = String.valueOf(dbNameTextField.getCharacters());
        userName = String.valueOf(userNameTextField.getCharacters());
        password = String.valueOf(passwordField.getCharacters());
        domain = String.valueOf(domainTextField.getCharacters());

        WpHosting hosting = new WpHosting(DatabaseAgregator.getHostingDb());
        hosting.addDomain(domain);
        hosting.addDb(dbms, dbName, userName, password);
        hosting.installCms(Hosting.Cms.WORDPRESS);

    }

    public void serverMenuButtonPushed(javafx.event.ActionEvent event) throws IOException {
        Parent serverMenuParent = FXMLLoader.load(getClass().getResource("../../../views/mainmenuviews/" +
                "HostingMenuScene.fxml"));
        Scene serverMenuScene = new Scene(serverMenuParent);

        // get the Stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(serverMenuScene);
        window.show();
    }

	public ComboBox<UserDatabase.DbmsType> getDbmsTypeComboBox() {
		return dbmsTypeComboBox;
	}

	public TextField getDbNameTextField() {
		return dbNameTextField;
	}

	public TextField getUserNameTextField() {
		return userNameTextField;
	}

	public TextField getDomainTextField() {
		return domainTextField;
	}

	public PasswordField getPasswordField() {
		return passwordField;
	}
}
