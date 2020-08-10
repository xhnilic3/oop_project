package controllers.serveradministration.vps;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import products.components.Os;
import products.components.Storage;
import products.databases.DatabaseAgregator;
import products.servers.Vps;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateVpsController implements Initializable {

    ObservableList<String> distrosList = FXCollections.observableArrayList("CLEAR", "UBUNTU");

    @FXML private ComboBox<products.components.ServerComponents.Os.Distributions> distributionComboBox;

    @FXML private TextField ramSizeTextField;
    @FXML private ComboBox<Storage.TypeOfStorage> typeOfStorageComboBox;
    @FXML private TextField storageSizeTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       distributionComboBox.getItems().addAll(products.components.ServerComponents.Os.Distributions.CLEAR_INSTALL,
               products.components.ServerComponents.Os.Distributions.DEBIAN,
               products.components.ServerComponents.Os.Distributions.CENTOS,
               products.components.ServerComponents.Os.Distributions.UBUNTU);
       typeOfStorageComboBox.getItems().addAll(Storage.TypeOfStorage.SSD, Storage.TypeOfStorage.HDD);
    }

    /**
     * check whether given string is numeric. If it is not, it will promt user to enter one
     * @param str
     * @return true if given string is numeric, false otherwise
     */
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            System.out.println("Enter an integer, please..");
            return false;
        }
    }


    public void createButtonPushed(ActionEvent event) throws IOException {
        int ramSize = 0;
        int storageSize = 0;
        products.components.ServerComponents.Os.Distributions distro =  distributionComboBox.getValue();
        Storage.TypeOfStorage typeOfStorage = typeOfStorageComboBox.getValue();
        System.out.println(distro);
        System.out.println(typeOfStorage);
        if (isNumeric(String.valueOf(this.ramSizeTextField.getCharacters())) &&
                isNumeric(String.valueOf(this.storageSizeTextField.getCharacters()))) {
            ramSize = Integer.parseInt((String.valueOf(this.ramSizeTextField.getCharacters())));
            storageSize = Integer.parseInt((String.valueOf(this.storageSizeTextField.getCharacters())));
        }

        Vps vps = new Vps(DatabaseAgregator.getVpsDb(), ramSize, typeOfStorage, storageSize, distro);
        vps.setOs(distro);


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

	public ComboBox<Storage.TypeOfStorage> getTypeOfStorageComboBox() {
		return typeOfStorageComboBox;
	}

	public TextField getStorageSizeTextField() {
		return storageSizeTextField;
	}

	public ComboBox<products.components.ServerComponents.Os.Distributions> getDistributionComboBox() {
		return distributionComboBox;
	}

	public TextField getRamSizeTextField() {
		return ramSizeTextField;
	}
}
