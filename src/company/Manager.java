package company;

import java.sql.*;
import java.util.Scanner;

import static company.Main.*;

public class Manager extends Employee{
    private static final String INSERT_NEW = "INSERT INTO tasks (userid, task, progress) VALUES(?,?,?)";

    public Manager(String name, String surname, int login, String status, int salary) {
        super(name, surname, login, status, salary);
    }

    public int printMenuManager() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nВыберите действие:\n==================");
        System.out.println("1 - Показать список сотрудников");
        System.out.println("2 - Распределить дела по сотрудникам");
        System.out.println("3 - Показать список указаний к сотрудникам");
        System.out.println("4 - Показать список зон покрытия");

        System.out.println("50 - Показать список дел");
        System.out.println("0 - Закончить работу");
        System.out.print("\nВаш выбор: ");
        return scanner.nextInt();
    }

    public void showAllWorkersList() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();

        String SELECT_worker = "SELECT id, name, surname FROM employee WHERE status = 'worker'";
        ResultSet result = statement.executeQuery(SELECT_worker);
        System.out.println();
        while(result.next()){
            System.out.println(result.getInt(1) + ". " + result.getString(2) + " " + result.getString(3));
        }
    }

    public void giveTasks() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW);

        Scanner scanner = new Scanner(System.in);

        System.out.print("\nВведите дело, которое хотите поручить сотруднику: ");
        String task = scanner.nextLine();

        System.out.print("Введите id сотрудника: ");
        int userid = scanner.nextInt();

        preparedStatement.setInt(1, userid);
        preparedStatement.setString(2, task);
        preparedStatement.setString(3, "to do");
        preparedStatement.execute();

    }

    public void showTasks() throws SQLException{
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        String SELECT_tasks = "SELECT task FROM tasks WHERE progress = 'to do'";
        ResultSet result = statement.executeQuery(SELECT_tasks);
        System.out.println();
        while(result.next()){
            System.out.println(result.getString(1));
        }
    }

    public void showTableTasks() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();

        String SELECT_tasks_by_user = "SELECT tasks.userid, employee.name, tasks.task, tasks.progress FROM employee JOIN tasks ON employee.id = tasks.userid ORDER BY employee.id";
        ResultSet resultUserID = statement.executeQuery(SELECT_tasks_by_user);
        System.out.println();
        while (resultUserID.next()) {
            System.out.println(resultUserID.getInt(1) + " // " + resultUserID.getString(2) + " // " + resultUserID.getString(3) + " // " + resultUserID.getString(4));
        }
    }
}
