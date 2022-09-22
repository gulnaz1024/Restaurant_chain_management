package company;

import java.sql.*;
import java.util.Scanner;

public class Main {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "2001";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/shopme";


    private static final String INSERT_NEW = "INSERT INTO employee ( name,surname,login,password) VALUES(?,?,?,?)";

    private static final String DD = "DELETE FROM employee WHERE login = ?";


    public static void main(String[] args) throws Exception {

        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW);
        String name = "",
                surname = "",
                password = "";
        int login = 0;

        boolean wrongInput = false;

        try (Scanner scanner = new Scanner(System.in)) {
            do {
                if (wrongInput) {
                    System.out.println("Пароль или Логин заданы не верно! ");
                }

                System.out.print("Введите имя: ");
                name = scanner.nextLine();

                System.out.print("Введите фамилию: ");
                surname = scanner.nextLine();

                System.out.print("Придумайте пароль: ");
                password = scanner.nextLine();

                wrongInput = true;
            } while (name.trim().isEmpty() || surname.trim().isEmpty());

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setInt(3, login);
            preparedStatement.setString(4, password);
            preparedStatement.execute();

            String SELECT_id = "SELECT id FROM employee WHERE name = \'" + name + "\' AND surname = \'" + surname + "\'";
            ResultSet result = statement.executeQuery(SELECT_id);
            while (result.next()) {
                login = result.getInt(1);
                System.out.println("Регистрация прошла успешно! вот ваш логин: " + result.getInt(1));
            }
//            preparedStatement.executeUpdate();
            String UPDATE_LOGIN = "UPDATE employee SET login = \'" + login + "\' WHERE login = 0";
            statement.executeUpdate(UPDATE_LOGIN);


//        Employee a = new Employee("dim", "rr", 1, "password");
//        a.printInfo();
        }
    }
}