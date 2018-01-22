package controllers;

import com.jfoenix.controls.JFXButton;
import database.dao.PersonDaoImpl;
import database.model.Person;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.Alerts;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

public class PersonOverviewController {

    public static final String PERSON_EDIT_DIALOG_FXML = "/PersonEditDialog.fxml";
    public static final String PERSON_ADD_DIALOG_FXML = "/PersonAddDialog.fxml";


    @FXML
    private TableView<Person> tableView;

    @FXML
    private TableColumn<Person, String> firstNameColumn;

    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstNameText;

    @FXML
    private Label lastNameText;

    @FXML
    private Label emailText;

    @FXML
    private Label cityText;

    @FXML
    private Label birthdayText;

    @FXML
    private JFXButton addButton;

    @FXML
    private JFXButton removeButton;

    @FXML
    private JFXButton editButton;


    //elementy tabeli
    private ObservableList<Person> masterData;
    private FilteredList<Person> filteredList;

    public PersonOverviewController() {
    }

    @FXML
    public void initialize() {
        initTableView();
        clearFields();
        initTextFields();
        initButtons();
    }

    private void initButtons() {
        this.removeButton.setDisable(true);
        this.editButton.setDisable(true);
        this.tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>() {
            @Override
            public void changed(ObservableValue<? extends Person> observable, Person oldValue, Person newValue) {

                if (oldValue == null) {
                    editButton.setDisable(false);
                    removeButton.setDisable(false);
                }
            }
        });
    }

    private void initTextFields() {
        this.tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>() {
            @Override
            public void changed(ObservableValue<? extends Person> observable, Person oldValue, Person newValue) {
                if (newValue != null) {
                    bindTextFields(newValue);
                } else {

                }
            }
        });
    }

    private void bindTextFields(Person person) {
        this.firstNameText.setText(person.firstNameProperty().get());
        this.lastNameText.setText(person.lastNameProperty().get());
        this.emailText.setText(person.emailProperty().get());
        this.cityText.setText(person.cityProperty().get());
        this.birthdayText.setText(person.birthdayProperty().getValue().toString());
    }

    private void initTableView() {
        PersonDaoImpl dao = new PersonDaoImpl();
        Set<Person> all = dao.getAll();
        this.masterData = FXCollections.observableArrayList();
        masterData.addAll(all);
        this.firstNameColumn.setCellValueFactory(cell -> cell.getValue().firstNameProperty());
        this.lastNameColumn.setCellValueFactory(cell -> cell.getValue().lastNameProperty());
        this.tableView.setItems(masterData);
    }

    @FXML
    void addOnAction() {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(PERSON_ADD_DIALOG_FXML));

        Stage stage = new Stage();
        Parent root;
        try {
            root = loader.load();

            PersonAddController personAddController = loader.getController();
            personAddController.setPersonOverviewController(this);

            stage.setScene(new Scene(root));
            stage.setTitle("Edit person");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {

        }

    }


    @FXML
    void editOnAction() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(PERSON_EDIT_DIALOG_FXML));

        Stage stage = new Stage();
        Parent root;
        try {
            root = loader.load();

            PersonEditController personEditController = loader.getController();
            personEditController.setPerson(this.tableView.getSelectionModel().getSelectedItem());
            personEditController.setPersonOverviewController(this);


            stage.setScene(new Scene(root));
            stage.setTitle("Edit person");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {

        }
    }

    @FXML
    void removeOnAction() {
        PersonDaoImpl dao = new PersonDaoImpl();
        int id = this.tableView.getSelectionModel().getSelectedItem().getId();
        if (masterData.size() >= 1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("INFO");
            alert.setContentText("Are you sure?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                dao.deletePerson(id);
                initialize();
            } else {
                return;
            }

        } else {
            Alerts.warningAlert("Table is empty, removing impossible");
        }
    }


    public void clearFields() {
        this.firstNameText.setText("");
        this.lastNameText.setText("");
        this.emailText.setText("");
        this.cityText.setText("");
        this.birthdayText.setText("");
    }
}
