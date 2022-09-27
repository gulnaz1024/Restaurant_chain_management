package company;

import java.sql.*;
import java.util.Scanner;

import static company.Main.*;

public class Employee {

    private String name;
    private String surname;
    private int login;
    private String password;

    public Employee(String name, String surname, int login, String password) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }

    public void changePassword() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        Scanner scanner = new Scanner(System.in);
//        String SELECT_id = "SELECT surname FROM employee WHERE name = 'Gulnaz'";
//        ResultSet result = statement.executeQuery(SELECT_id);
//        result.next();
//        System.out.println(result.getString(1));
//        "UPDATE employee SET login = \'" + login + "\' WHERE login = 0";

        password = scanner.nextLine();
        String UPDATE_password = "UPDATE employee SET password = \'" + password + "\' WHERE name = 'Gulnaz'";
        statement.executeUpdate(UPDATE_password);
        System.out.println("Ваш пароль успешно изменен.");
    }

    public void printInfo() {
        System.out.println(this.name);
    }
}
