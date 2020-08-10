package aspects;

import controllers.hostingadministration.wphosting.CreateWpHostingController;
import controllers.serveradministration.vps.CreateVpsController;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public aspect CreateProductValidator {
	
	void around(CreateVpsController controller): execution( * controllers.serveradministration.vps.CreateVpsController.createButtonPushed(..)) && this(controller) {
		if(controller.getDistributionComboBox().getSelectionModel().getSelectedIndex() == -1 || 
				controller.getTypeOfStorageComboBox().getSelectionModel().getSelectedIndex() == -1 ||
				controller.getRamSizeTextField().getText().isEmpty() ||
				controller.getStorageSizeTextField().getText().isEmpty())
			displayError();
		else proceed(controller);
	}
	
	void around(CreateWpHostingController controller): execution( * controllers.hostingadministration.wphosting.CreateWpHostingController.createButtonPushed(..)) && this(controller) {
		if(controller.getDbmsTypeComboBox().getSelectionModel().getSelectedIndex() == -1 ||
				controller.getDbNameTextField().getText().isEmpty() || 
				controller.getUserNameTextField().getText().isEmpty()) displayError();
		else {
			if(controller.getDomainTextField().getText().equalsIgnoreCase("<domain>.<tld>"))
				controller.getDomainTextField().setText("");
			
			proceed(controller);
		}
	}
	
	private void displayError() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setContentText("Vypln vsetky polia");
		alert.setTitle("Chyba");
		alert.showAndWait();
	}

}
