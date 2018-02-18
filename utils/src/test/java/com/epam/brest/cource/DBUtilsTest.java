package com.epam.brest.cource;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

import static org.mockito.Mockito.*;

public class DBUtilsTest {

    public Connection connection;
    public DBUtils dbUtils;

    @Before
    public void init() throws ClassNotFoundException, SQLException {
        // create connection
        String databaseURL = "jdbc:h2:mem:test_db;MODE=MYSQL;DB_CLOSE_DELAY=-1";
        Class.forName("org.h2.Driver");
        connection = DriverManager.getConnection(databaseURL, "sa", "");
        dbUtils = new DBUtils();

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


    }

    @Test
    public void getConnectionTest() throws SQLException, ClassNotFoundException {

        Assert.assertNotNull(dbUtils.getConnection());

    }

    @Test
    public void addUserTest() throws SQLException {
        int expected = 1;
        int actual;
        String testSQL = "SELECT count(*) AS row_count " +
                "FROM app_user " +
                "WHERE login = 'uzver' " +
                "AND password = 'somepass' " +
                "AND description = 'simpledescription'";

        try (Statement statement = connection.createStatement()) {

            dbUtils.addUser(connection, "uzver", "somepass", "simpledescription");

            ResultSet resultSet = statement.executeQuery(testSQL);
            resultSet.next();
            actual = resultSet.getInt("row_count");
        }

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void addUserMockitoTest() throws SQLException {
        final String newUser = "INSERT INTO app_user (login, password, description) VALUES(?,?,?)";
        final String login = "Vasia";
        final String password = "Boss";
        final String description = "superBoss";

        final Connection mockConnection = mock(Connection.class);
        final PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);

        when(mockConnection.prepareStatement(newUser)).thenReturn(mockPreparedStatement);

        dbUtils.addUser(mockConnection, login, password, description);

        verify(mockConnection, times(1)).prepareStatement(newUser);
        verify(mockPreparedStatement, times(1)).setString(1, login);
        verify(mockPreparedStatement, times(1)).setString(2, password);
        verify(mockPreparedStatement, times(1)).setString(3, description);
        verify(mockPreparedStatement, times(1)).executeUpdate();


    }

    @After
    public void closeAll() throws SQLException {
        String testSQL = "DROP TABLE app_user";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(testSQL);
        }

        dbUtils = null;
        if (connection != null) connection.close();
    }
}