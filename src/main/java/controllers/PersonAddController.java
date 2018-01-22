package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import database.dao.PersonDaoImpl;
import database.model.Person;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import utils.Alerts;
import utils.DateConverter;
import utils.Validators;

public class PersonAddController implements BaseController {

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
        validateFields();
    }

    private void validateFields() {


        this.firstNameField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    firstNameField.setText(Validators.validateName(firstNameField.getText()));
                }
            }
        });

        this.lastNameField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    lastNameField.setText(Validators.validateName(lastNameField.getText()));
                }
            }
        });

        this.cityField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    cityField.setText(Validators.validateName(cityField.getText()));
                }
            }
        });


        this.emailField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    emailField.setText(Validators.validateEmail(emailField.getText()));
                }
            }
        });

    }


    @FXML
    void saveOnAction() {
        String fN = this.firstNameField.getText();
        String lN = this.lastNameField.getText();
        String email = this.emailField.getText();
        String city = this.cityField.getText();
        org.joda.time.LocalDate localDate = DateConverter.convertToDate(this.birthdayField.getText());

        Person p = new Person();
        p.setFirstName(fN);
        p.setLastName(lN);
        p.setEmail(email);
        p.setCity(city);
        p.setBirthday(localDate);

        PersonDaoImpl dao = new PersonDaoImpl();
        dao.addPerson(p);
        Alerts.informationAlert("Person " + p.getFirstName() + " added!");

        exitWindow();
        personOverviewController.initialize();

    }

    @FXML
    void cancelOnAction() {
        exitWindow();
    }

    private void exitWindow() {
        Stage stage = (Stage) this.cancelButton.getScene().getWindow();
        stage.close();
    }

    public void setPersonOverviewController(PersonOverviewController personOverviewController) {
        this.personOverviewController = personOverviewController;
    }
}
