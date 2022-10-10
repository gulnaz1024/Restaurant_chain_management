package company;
import java.sql.*;
import java.util.Scanner;

import static company.Main.*;

public class Hr extends Employee {

    public Hr(String name, String surname, int login, String status, int salary) {
        super(name, surname, login, status, salary);
    }

    public int printMenuHR() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nВыберите действие:\n==================");
        System.out.println("1 - Регистрация нового сотрудника");
        System.out.println("0 - Закончить работу");
        System.out.print("\nВаш выбор: ");
        int choice = scanner.nextInt();
        return choice;
    }

    public String registrateEmployee() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        String INSERT_NEW = "INSERT INTO employee (name,surname,login,password,status, salary) VALUES(?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW);
        String name = "",
                surname = "",
                password = "",
                status = "";
        int login = 0,
                livingWageSalary = 6900;

        boolean wrongInput = false;

        Scanner scanner = new Scanner(System.in);
        do {
            if (wrongInput) {
                System.out.println("\nПароль или логин заданы не верно! Попробуйте еще раз.\n");
            }

            System.out.print("\nВведите имя: ");
            name = scanner.nextLine();

            System.out.print("Введите фамилию: ");
            surname = scanner.nextLine();

            System.out.print("Придумайте пароль: ");
            password = scanner.nextLine();

            System.out.println("\n1 - Директор");
            System.out.println("2 - Маркетолог");
            System.out.println("3 - Менеджер");
            System.out.println("4 - Рабочий");
            System.out.println("5 - HR");

            while (true) {
                System.out.print("\nВыберите статус: ");
                int choise = scanner.nextInt();
                if (choise == 1) {
                    status = "director";
                    break;
                } else if (choise == 2) {
                    status = "marketing";
                    break;
                } else if (choise == 3) {
                    status = "manager";
                    break;
                } else if (choise == 4) {
                    status = "worker";
                    break;
                } else if (choise == 5) {
                    status = "HR";
                    break;
                } else {
                    System.out.println("\nНе правильный выбор!");
                }
            }

            wrongInput = true;
        } while (name.trim().isEmpty() || surname.trim().isEmpty());


        //insert to BD
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, surname);
        preparedStatement.setInt(3, login);
        preparedStatement.setString(4, password);
        preparedStatement.setString(5, status);
        preparedStatement.setInt(6, livingWageSalary);
        preparedStatement.execute();

        String SELECT_id = "SELECT id FROM employee WHERE name = \'" + name + "\' AND surname = \'" + surname + "\'";
        ResultSet result = statement.executeQuery(SELECT_id);
        while (result.next()) {
            login = result.getInt(1);
            System.out.println("\nРегистрация прошла успешно! \nЛогин сотрудника: " + result.getInt(1));
            System.out.println("Пароль сотрудника: " + password + "\n");
        }
//            preparedStatement.executeUpdate();
        String UPDATE_LOGIN = "UPDATE employee SET login = \'" + login + "\' WHERE login = 0";
        statement.executeUpdate(UPDATE_LOGIN);

        return password;
    }

}

