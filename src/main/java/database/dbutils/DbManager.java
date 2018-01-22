package database.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Patryk on 2017-08-10.
 */
public class DbManager {
    private static DbManager instance = null;

    public static DbManager getInstance() {
        if (instance == null) {
            instance = new DbManager();
        }
        return instance;
    }

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE = "jdbc:mysql://localhost:3306/kruczek";

/*    private static final String USER = "admin";
    private static final String PASSWORD = "admin";*/

    private static final String USER2 = "root";
    private static final String PASSWORD2 = "root";
    private Connection connection;


    private DbManager() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DATABASE, USER2, PASSWORD2);
            System.out.println("Database connection successed!");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        createTables();
    }

    private void createTables() {
        String tableName = "Persons";
        String personsTable =
                "CREATE TABLE IF NOT EXISTS " + tableName + "  ( " +
                        "id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                        "firstName VARCHAR(50) NOT NULL, " +
                        "lastName VARCHAR(50) NOT NULL, " +
                        "email VARCHAR(50) NOT NULL, " +
                        "city VARCHAR(50) NOT NULL, " +
                        "birthday DATE NOT NULL " +
                        " )";
        try {
//            connection.createStatement().execute("DROP TABLE IF EXISTS Products");
            connection.createStatement().execute(personsTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Table created!");
    }

    public Connection getConnection() {
        return connection;
    }
}
