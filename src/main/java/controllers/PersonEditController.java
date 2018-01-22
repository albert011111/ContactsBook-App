package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import database.dao.PersonDaoImpl;
import database.model.Person;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import utils.DateConverter;

public class PersonEditController implements BaseController {
    private Person person;

    @FXML
    private JFXTextField firstNameField;

    @FXML
    private JFXTextField lastNameField;

    @FXML
    private JFXTextField emailField;

    @FXML
    private JFXTextField cityField;

    @FXML
    private JFXTextField birthdayField;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXButton cancelButton;

    private PersonOverviewController personOverviewController;

    @FXML
    public void initialize() {
    }

    @FXML
    void saveOnAction() {
        PersonDaoImpl dao = new PersonDaoImpl();
        System.out.println(person);
        this.person.setFirstName(this.firstNameField.getText());
        this.person.setLastName(this.lastNameField.getText());
        this.person.setEmail(this.emailField.getText());
        this.person.setCity(this.cityField.getText());
        this.person.setBirthday(DateConverter.convertToDate(this.birthdayField.getText()));
        dao.updatePerson(person);

        personOverviewController.initialize();
        exitWindow();


    }

    @FXML
    void cancelOnAction() {
        exitWindow();
    }

    private void exitWindow() {
        Stage stage = (Stage) this.cancelButton.getScene().getWindow();
        stage.close();
    }


    public void setPerson(Person person) {
        this.person = person;
        this.firstNameField.setText(person.getFirstName());
        this.lastNameField.setText(person.getLastName());
        this.emailField.setText(person.getEmail());
        this.cityField.setText(person.getCity());
        this.birthdayField.setText(person.getBirthday().toString());
    }

    public void setPersonOverviewController(PersonOverviewController personOverviewController) {
        this.personOverviewController = personOverviewController;
    }
}
