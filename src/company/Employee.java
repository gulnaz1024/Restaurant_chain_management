package company;

import java.sql.*;
import java.util.Scanner;

import static company.Main.*;

public class Employee {

    private String name;
    private String surname;
    private int login;
    public String status;
    public int wage = 1000000;

    public int marketingBudget = 3000000;


    public Employee(String name, String surname, int login, String status) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.status = status;
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
        //String SELECT_ID = "SELECT id FROM employee WHERE login = \'" + login + "\' AND password = \'" + password + "\'";
        System.out.print("\nВведите новый пароль: ");
        String password = scanner.nextLine();
        String UPDATE_password = "UPDATE employee SET password = \'" + password + "\' WHERE name = \'" + this.name + "\' AND surname = \'" + this.surname + "\' AND login = \'" + this.login + "\'"   ;
        statement.executeUpdate(UPDATE_password);
        System.out.println("Ваш пароль успешно изменен.");
    }

    public void printWage() {
        System.out.println(wage);
    }

    public void printMarketingBudget() {
        System.out.println(marketingBudget);
    }
}
