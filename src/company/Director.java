package company;
import java.sql.*;
import java.util.Scanner;

import static company.Main.*;

public class Director extends Employee {

    public Director(String name, String surname, int login, String status, int salary) {
        super(name, surname, login, status, salary);
    }

    public void registrateNewEmployee() throws Exception{
        registrateEmployee();
    }

    public int printMenuDirector() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nВыберите действие:\n==================");
        System.out.println("1 - Показать список всех зон покрытия");
        System.out.println("2 - Показать список категорий бюджета");
        System.out.println("3 - Показать выделенный бюджет для определенной категории мест для маркетинга");
        System.out.println("4 - Показать общий бюджет для маркетинга");
        System.out.println("5 - Показать общий бюджет необходимый для зарплаты");
        System.out.println("6 - Повысить зарплату сотруднику: ");
        System.out.println("7 - Понизить зарплату сотруднику: ");

        System.out.println("48 - Выделить бюджет для маркетинга: ");
        System.out.println("49 - Регистрация нового сотрудника");
        System.out.println("50 - Изменить пароль");
        System.out.println("0 - Закончить работу");
        System.out.print("\nВаш выбор: ");
        int choice = scanner.nextInt();
        return choice;
    }
    public void showAllBudget() throws Exception{
//        System.out.println("бюджет для заработной платы,");
//        System.out.println("бюджет для маркетинга.");
        System.out.println("!!! UNDER CONSTRUCTION !!!");
    }

    public void showSalaryBudget() throws Exception{
        System.out.print("\nОбщий бюджет для заработной платы: ");
        System.out.println(salaryCount());
    }

    @Override
    public void showCoverageAreas() {
        super.showCoverageAreas();
    }

    @Override
    public void showBudgetForEachCategory() {
        super.showBudgetForEachCategory();
    }

    public void salaryIncrease() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Наберите имя сотрудника которому хотите повысить зарплату: ");
        String userName = scanner.nextLine();

        String SELECT_salary = "SELECT salary FROM employee WHERE name = \'" + userName + "\'";
        ResultSet resultS = statement.executeQuery(SELECT_salary);
        resultS.next();
        int userSalary = resultS.getInt(1);

        System.out.print("Наберите сумму надбавки к зарплате: ");
        int salarySupplement = scanner.nextInt() + userSalary;

        String UPDATE_salary = "UPDATE employee SET salary = \'" + salarySupplement + "\' WHERE name = \'" + userName + "\'" ;
        statement.executeUpdate(UPDATE_salary);

        String SELECT_S = "SELECT salary FROM employee WHERE name = \'" + userName + "\'";
        ResultSet result = statement.executeQuery(SELECT_S);
        result.next();
        System.out.println("Текущая зарплата сотрудника: " + result.getInt(1));

    }

    public void salaryDecrease() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Наберите имя сотрудника которому хотите понизить зарплату: ");
        String userName = scanner.nextLine();

        String SELECT_salary = "SELECT salary FROM employee WHERE name = \'" + userName + "\'";
        ResultSet resultS = statement.executeQuery(SELECT_salary);
        resultS.next();
        int userSalary = resultS.getInt(1);

        System.out.print("Наберите сумму понижения зарплаты: ");
        int salaryReduction = scanner.nextInt();
        salaryReduction= userSalary -salaryReduction;

        String UPDATE_salary = "UPDATE employee SET salary = \'" + salaryReduction + "\' WHERE name = \'" + userName + "\'" ;
        statement.executeUpdate(UPDATE_salary);

        String SELECT_S = "SELECT salary FROM employee WHERE name = \'" + userName + "\'";
        ResultSet result = statement.executeQuery(SELECT_S);
        result.next();
        System.out.println("Текущая зарплата сотрудника: " + result.getInt(1));
    }

    @Override
    public int allocateBudget() {
        return super.allocateBudget();
    }

    @Override
    public void showBudgetMarketing() {
        super.showBudgetMarketing();
    }
}
