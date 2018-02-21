package com.epam.brest.cource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Class for integration with database.
 *
 */

public class DBUtils {
    /**
     * Connection to database.
     *
     * @return Connection connection to database
     * @throws ClassNotFoundException wrong database Driver
     * @throws SQLException some exceptions
     */
    public final Connection getConnection()
            throws ClassNotFoundException, SQLException {

        System.out.println("Connect to db");
        String databaseURL = "jdbc:h2:mem:test_db;MODE=MYSQL;DB_CLOSE_DELAY=-1";
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(databaseURL,
                "sa", "");
        return connection;
    }

    /**
     * The method creates table app_user.
     *
     * @param connection connection to database
     * @throws SQLException some exception
     */
    public final void createUserTable(final Connection connection)
            throws SQLException {
        System.out.println("create app_user table");
        String createTable =
                "CREATE TABLE app_user("
                        + "user_id INT NOT NULL AUTO_INCREMENT,"
                        + "login VARCHAR (255) NOT NULL ,"
                        + "password VARCHAR (255) NOT NULL ,"
                        + "description VARCHAR (255) NULL,"
                        + "PRIMARY KEY (user_id))";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTable);
        }

    }

    /**
     * The method adds user to app_user.
     *
     * @param connection connection to database
     * @param login user's login
     * @param password users' password
     * @param description some description
     * @throws SQLException some exception
     */
    public final void addUser(final Connection connection,
                              final String login,
                              final String password,
                              final String description)
            throws SQLException {
        System.out.println(String.format("Add user: %s", login));
        String newUser = "INSERT INTO app_user (login, password, description)"
                + " VALUES(?,?,?)";

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(newUser)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(2 + 1, description);
            preparedStatement.executeUpdate();
        }


    }

    /**
     * The method displays all users from the app_user.
     *
     * @param connection connection to database.
     * @throws SQLException some exception
     */
    public final void getUsers(final Connection connection)
            throws SQLException {

        System.out.println("\nОтображаем список всех пользователей:");
        String getRecords =
                "SELECT user_id, login, description "
                        + "FROM app_user "
                        + "ORDER BY user_id";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getRecords);
            while (resultSet.next()) {
                System.out.println(String.format("User: %s, %s, %s",
                                resultSet.getInt("user_id"),
                        resultSet.getString("login"),
                        resultSet.getString("description")));
            }
            System.out.println();
        }


    }

    /**
     * The method updates the table entries.
     *
     * @param connection connection to database
     * @param id user's id
     * @param login user's login
     * @param password user's password
     * @param description some description
     * @throws SQLException some exception
     */
    public final void updateUserById(final Connection connection,
                               final int id,
                               final String login,
                               final String password,
                               final String description)
            throws SQLException {

        System.out.println(String.format("Обновляем "
                + "данные пользователя с id = %s", id));
        String getById =
                "UPDATE app_user "
                        + "SET login=?, password=?, description=? "
                        + "WHERE user_id = ?";

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(getById)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(2 + 1, description);
            preparedStatement.setInt(2 + 2, id);

            preparedStatement.executeUpdate();
        }

    }

    /**
     * The method removes user by id.
     *
     * @param connection connection to database
     * @param id user's id
     * @throws SQLException some exceptions
     */
    public final void removeUserById(final Connection connection, final int id)
            throws SQLException {
        System.out.println(String.format("Удаляем пользователя с id = %s", id));
        String delRecord = "DELETE FROM app_user WHERE user_id = ?";
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(delRecord)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }



    }

}
