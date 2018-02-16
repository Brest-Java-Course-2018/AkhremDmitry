package com.epam.brest.cource;


import org.junit.Assert;
import org.junit.Test;

import java.sql.*;

public class DBUtilsTest {

    @Test
    public void getConnection() throws SQLException, ClassNotFoundException {
        DBUtils dbUtils = new DBUtils();
        Assert.assertNotNull(dbUtils.getConnection());

    }

    @Test
    public void addUser() throws SQLException, ClassNotFoundException {
        // create connection
        String batabaseURL="jdbc:h2:mem:test_db;MODE=MYSQL;DB_CLOSE_DELAY=-1";
        Class.forName("org.h2.Driver");
        try(Connection connection= DriverManager.getConnection(batabaseURL,"sa","")) {

            // create table
            String createTable =
                    "CREATE TABLE app_user(" +
                            "user_id INT NOT NULL AUTO_INCREMENT," +
                            "login VARCHAR (255) NOT NULL ," +
                            "password VARCHAR (255) NOT NULL ," +
                            "description VARCHAR (255) NULL," +
                            "PRIMARY KEY (user_id))";

            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(createTable);
            }


            String testSQL = "SELECT count(*) AS row_count " +
                    "FROM app_user " +
                    "WHERE login = 'uzver' " +
                    "AND password = 'somepass' " +
                    "AND description = 'simpledescription'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(testSQL);
            resultSet.next();

            int expected = resultSet.getInt("row_count")+1;

            DBUtils dbUtils = new DBUtils();
            dbUtils.addUser(connection, "uzver", "somepass", "simpledescription");

            resultSet = statement.executeQuery(testSQL);
            resultSet.next();
            int actual = resultSet.getInt("row_count");

            Assert.assertEquals(expected,actual);
        }
    }
}