package com.epam.brest.cource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Hello world!
 */
public class App {
    public static void main(final String[] args)
            throws SQLException, ClassNotFoundException {

        System.out.println("Hello World!");
        DBUtils dbUtils = new DBUtils();

        Connection connection = dbUtils.getConnection();
        dbUtils.createUserTable(connection);
        dbUtils.addUser(connection, "admin", "pass", "user_admin");
        dbUtils.addUser(connection, "admin2", "pass2", "user_admin2");
        dbUtils.addUser(connection, "admin3", "pass3", "user_admin3");

        dbUtils.getUsers(connection);

        dbUtils.removeUserById(connection, 2);

        dbUtils.getUsers(connection);

        dbUtils.udateUserById(connection, 1, "felix", "cat", "superCat");

        dbUtils.getUsers(connection);

    }


    public final int sum(final int a, final int b) {
        return a + b;
    }
}
