package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ch.makery.address.model.Person;
import ch.makery.address.util.DateUtil;

/*
 * Dialog to edit details of a person.
 */
public class PersonEditDialogController {

	@FXML private TextField firstNameField;
	@FXML private TextField lastNameField;
	@FXML private TextField streetField;
	@FXML private TextField postalCodeField;
	@FXML private TextField cityField;
	@FXML private TextField birthdayField;
	
	private Stage dialogStage;
	private Person person;
	private boolean okClicked = false;
	
	/*
	 * Initializes the controller class.
	 * This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML private void initialize() {
		
	}
	
	/*
	 * Sets the stage of this dialog.
	 * It is used to close the dialogStage.
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	/*
	 * Sets the person to be edited in the dialog.
	 */
	public void setPerson(Person person) {
		this.person = person;
		
		firstNameField.setText(person.getFirstName());
		lastNameField.setText(person.getLastName());
		streetField.setText(person.getStreet());
		postalCodeField.setText(Integer.toString(person.getPostalCode()));
		cityField.setText(person.getCity());
		birthdayField.setText(DateUtil.format(person.getBirthday()));
		birthdayField.setPromptText("yyyy.mm.dd");
	}
	
	/*
	 * Returns true if the user clicked OK, false otherwise.
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}
	
	/*
	 * Called when the user clicks ok.
	 */
	@FXML private void handleOK() {
		if (isInputValid()) {
			person.setFirstname(firstNameField.getText());
			person.setLastName(lastNameField.getText());
			person.setStreet(streetField.getText());
			person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
			person.setCity(cityField.getText());
			person.setBirthday(DateUtil.parse(birthdayField.getText()));
			
			okClicked = true;
			dialogStage.close();
		}
	}
	
	/*
	 * Called when the user clicks cancel.
	 */
	@FXML private void handleCancel() {
		dialogStage.close();
	}
	
	/*
	 * Validates the user input in the text fields.
	 * @return true if the input is valid
	 */
	private boolean isInputValid() {
		String errorMessage = "";
		
		if (firstNameField.getText() == null || firstNameField.getText().length() == 0) errorMessage += "No valid first name!\n";
		if (lastNameField.getText() == null || lastNameField.getText().length() == 0) errorMessage += "No valid last name!\n";
		if (streetField.getText() == null || streetField.getText().length() == 0) errorMessage += "No valid street!\n";
		
		if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) errorMessage += "No valid postal code!\n";
		else {
			//try to parse the postal code into a int.
			try {
				Integer.parseInt(postalCodeField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid postal code (must be an integer)!\n";
			}
		}
		
		if (cityField.getText() == null || cityField.getText().length() == 0) errorMessage += "No valid city!\n";
		
		if (birthdayField.getText() == null || birthdayField.getText().length() == 0) errorMessage += "No valid birthay!\n";
		else {
			if (!DateUtil.validDate(birthdayField.getText())) {
				errorMessage += "No valid birthday. Use the format yyyy.mm.dd!\n";
			}
		}
		
		if (errorMessage.length() == 0) return true;
		else {
			//Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);
			
			alert.showAndWait();
			
			return false;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
