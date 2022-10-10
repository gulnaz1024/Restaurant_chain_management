package company;

import java.sql.*;
import java.util.Scanner;

import static company.Main.*;

public class Worker extends Employee {

    public Worker(String name, String surname, int login, String status, int salary) {
        super(name, surname, login, status, salary);
    }

    public int printMenuWorker() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nВыберите действие:\n==================");
        System.out.println("1 - Показать список порученных мне дел");
        System.out.println("2 - Показать список завершенных указаний");
        System.out.println("3 - Показать список дел над, которым я работаю");
        System.out.println("4 - Показать зарплату");
        System.out.println("99 - Выйти из учетной записи");
        System.out.println("0 - Закончить работу");
        System.out.print("\nВаш выбор: ");
        return scanner.nextInt();
    }
    public void showTasksToDo() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        String SELECT_tasks = "SELECT task FROM tasks WHERE userid = \'" + this.login + "\' AND progress = 'to do'";
        ResultSet result = statement.executeQuery(SELECT_tasks);
        System.out.println();
        while(result.next()){
            System.out.println(result.getString(1));
        }
    }

    public void showTasksDone() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        String SELECT_tasks = "SELECT task FROM tasks WHERE userid = \'" + this.login + "\' AND progress = 'done'";
        ResultSet result = statement.executeQuery(SELECT_tasks);
        System.out.println();
        while(result.next()){
            System.out.println(result.getString(1));
        }
    }

    public void showTasksINProgress() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        String SELECT_tasks = "SELECT task FROM tasks WHERE userid = \'" + this.login + "\' AND progress = 'in progress'";
        ResultSet result = statement.executeQuery(SELECT_tasks);
        System.out.println();
        while(result.next()){
            System.out.println(result.getString(1));
        }
    }

    public void showEmployeeSalary() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        String SELECT_salary = "SELECT salary FROM employee WHERE login = \'" + this.login + "\'";
        ResultSet result = statement.executeQuery(SELECT_salary);
        System.out.println();
        result.next();
        System.out.println("Твоя текущая зарплата: " + result.getInt(1));
    }
}