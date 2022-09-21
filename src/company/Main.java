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
        String name = "";
        String surname = "";
        boolean wrongInput = false;
        try (Scanner scanner = new Scanner(System.in)) {
            do {
                if (wrongInput) {
                    System.out.println("Пароль или Логин введены не верно! ");
                }

                System.out.print("Введите имя: ");
                name = scanner.nextLine();

                System.out.print("Введите фамилию: ");
                surname = scanner.nextLine();
                wrongInput = true;
            } while (name.trim().isEmpty() || surname.trim().isEmpty());

            preparedStatement.setString(1, "dgfg");
            preparedStatement.setString(2, "dgfg");
            preparedStatement.setInt(3, 1);
            preparedStatement.setString(4, "dgfg");
            preparedStatement.execute();
        }


        Employee a = new Employee("dim", "rr", 1, "password");
        a.printInfo();
    }
}