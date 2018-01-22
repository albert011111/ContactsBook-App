package database.dao;

import database.dbutils.DbManager;
import database.model.Person;
import javafx.scene.control.Alert;
import org.joda.time.LocalDate;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Patryk on 2017-08-10.
 */
public class PersonDaoImpl implements PersonDao {

    @Override
    public void updatePerson(Person person) {
        String updateSql = "UPDATE Persons SET firstName = ?, lastName =?, email = ?, city=? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = DbManager.getInstance().getConnection().prepareStatement(updateSql);
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.setString(4, person.getCity());

//            System.out.println(person.getId());
            preparedStatement.setInt(5, person.getId());
//            System.out.println(person.getId());


            preparedStatement.execute();
            preparedStatement.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("INFO");
            alert.setContentText("Element updated!");
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addPerson(Person person) {
        String stmt = "INSERT INTO Persons (firstName,lastName,email,city,birthday) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = DbManager.getInstance().getConnection().prepareStatement(stmt);
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.setString(4, person.getCity());
            preparedStatement.setDate(5, Date.valueOf(person.getBirthday().toString()));
            preparedStatement.execute();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePerson(int id) {
        String deleteSql = "DELETE FROM Persons WHERE id = ?";
        try {
            PreparedStatement preparedStatement = DbManager.getInstance().getConnection().prepareStatement(deleteSql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Set<Person> getAll() {
        Set<Person> persons = null;
        String sql = "SELECT * FROM Persons";
        try {
            ResultSet resultSet = DbManager.getInstance().getConnection().createStatement().executeQuery(sql);
            persons = new LinkedHashSet<>();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String email = resultSet.getString(4);
                String city = resultSet.getString(5);
                LocalDate date = LocalDate.parse(resultSet.getString(6));

                Person person = new Person();
                person.setId(id);
                person.setFirstName(firstName);
                person.setLastName(lastName);
                person.setEmail(email);
                person.setCity(city);
                person.setBirthday(date);

//                System.out.println(person);
                persons.add(person);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persons;
    }

    @Override
    public Person getPersonById(int id) {
        return null;
    }
}
