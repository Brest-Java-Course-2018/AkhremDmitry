package com.epam.brest.cource;

import java.sql.*;

public class DBUtils {
    public Connection getConnection() throws ClassNotFoundException, SQLException {

        System.out.println("Connect to db");
        String batabaseURL="jdbc:h2:mem:test_db;MODE=MYSQL;DB_CLOSE_DELAY=-1";
        Class.forName("org.h2.Driver");
        Connection connection= DriverManager.getConnection(batabaseURL,"sa","");
        return connection;
    }

    //create
    public void createUserTable(Connection connection) throws SQLException {
        System.out.println("create app_user table");
        String createTable =
                "CREATE TABLE app_user(" +
                        "user_id INT NOT NULL AUTO_INCREMENT," +
                        "login VARCHAR (255) NOT NULL ," +
                        "password VARCHAR (255) NOT NULL ," +
                        "description VARCHAR (255) NULL," +
                        "PRIMARY KEY (user_id))";

        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(createTable);
        }

    }

    public void addUser(Connection connection, String login, String password, String description) throws SQLException {
        System.out.println(String.format("Add user: %s", login));
        String newUser = "INSERT INTO app_user (login, password, description) VALUES(?,?,?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(newUser)){
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,description);
            preparedStatement.executeUpdate();
        }


    }

    //read
    public void getUsers(Connection connection) throws SQLException {

        System.out.println("\nОтображаем список всех пользователей:");
        String getRecords =
                "SELECT user_id, login, description FROM app_user ORDER BY user_id";
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(getRecords);
            while (resultSet.next()){
                System.out.println(String.format("User: %s, %s, %s", resultSet.getInt("user_id"),
                        resultSet.getString("login"),
                        resultSet.getString("description")));
            }
            System.out.println();
        }


    }

    //update
    public void udateUserById(Connection connection, int id, String login, String password, String description) throws SQLException {

        System.out.println(String.format("Обновляем данные пользователя с id = %s",id));
        String getById =
                "UPDATE app_user SET login=?, password=?, description=? WHERE user_id = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(getById)){
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3, description);
            preparedStatement.setInt(4,id);

            preparedStatement.executeUpdate();
        }

    }

    //delete
    public void removeUserById (Connection connection, int id) throws SQLException {
        System.out.println(String.format("Удаляем пользователя с id = %s", id));
        String delRecord = "DELETE FROM app_user WHERE user_id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(delRecord)){
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }



    }

}
