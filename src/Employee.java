import java.sql.*;
import java.util.Scanner;

public class Employee {

    private String name;
    private String surname;
    public int login;
    public String status;
    public int salary;


    public Employee(String name, String surname, int login, String status, int salary) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.status = status;
        this.salary = salary;
    }

    public void changePassword() throws SQLException {
        Connection connection = DriverManager.getConnection(Main.DB_URL, Main.DB_USERNAME, Main.DB_PASSWORD);
        Statement statement = connection.createStatement();
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nВведите новый пароль: ");
        String password = scanner.nextLine();
        String UPDATE_password = "UPDATE employee SET password = \'" + password + "\' WHERE name = \'" + this.name + "\' AND surname = \'" + this.surname + "\' AND login = \'" + this.login + "\'";
        statement.executeUpdate(UPDATE_password);
        System.out.println("Ваш пароль успешно изменен.");
    }

    public int salaryCount() throws SQLException {
        Connection connection = DriverManager.getConnection(Main.DB_URL, Main.DB_USERNAME, Main.DB_PASSWORD);
        Statement statement = connection.createStatement();
        String SELECT_salary = "SELECT salary FROM employee";
        ResultSet result = statement.executeQuery(SELECT_salary);
        while(result.next()){
            this.salary += result.getInt(1);
        }
        String UPDATE_salary = "UPDATE budget SET money = \'" + this.salary + "\' WHERE name_category = 'salary'";
        statement.executeUpdate(UPDATE_salary);

        return this.salary;
    }

    public void printWage() {
        System.out.println(this.salary);
    }


    public void showCoverageAreas() {
        System.out.println("\nЗона охвата клиентами для Бишкека: 13%");
        System.out.println("Зона охвата клиентами для Джаллабада: 17%");
        System.out.println("Зона охвата клиентами для Оша: 21%");
        System.out.println("Зона охвата клиентами для Нарына: 54%");
        System.out.println("Зона охвата клиентами для Иссык Куля: 5%");
        System.out.println("Зона охвата клиентами для Баткена: 8%");
    }



    public void showBudgetMarketing() throws SQLException{
        Connection connection = DriverManager.getConnection(Main.DB_URL, Main.DB_USERNAME, Main.DB_PASSWORD);
        Statement statement = connection.createStatement();
        String SELECT_marketingBudget = "SELECT money FROM budget WHERE name_category = 'marketing'";
        ResultSet resultB = statement.executeQuery(SELECT_marketingBudget);
        resultB.next();
        System.out.println("\nОбщий бюджет маркетинга: "+ resultB.getInt(1));
    }


    public void allocateBudget() throws SQLException{
        Connection connection = DriverManager.getConnection(Main.DB_URL, Main.DB_USERNAME, Main.DB_PASSWORD);
        Statement statement = connection.createStatement();
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nВведите сумму, которая будет выделена на маркетинг: ");
        int budget = scanner.nextInt();
        String UPDATE_budget = "UPDATE budget SET money = \'" + budget + "\' WHERE name_category = 'marketing'";
        statement.executeUpdate(UPDATE_budget);

    }

    public void showBudgetForEachCategory() throws SQLException {
        Connection connection = DriverManager.getConnection(Main.DB_URL, Main.DB_USERNAME, Main.DB_PASSWORD);
        Statement statement = connection.createStatement();
        System.out.println("\n1- Instagram");
        System.out.println("2- Facebook");
        System.out.println("3- Youtube");
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nВыберите категорию места: ");
        int choise = scanner.nextInt();
        if(choise == 1){
            String SELECT_instagram = "SELECT money FROM budget WHERE name_category = 'instagram'";
            ResultSet resultI = statement.executeQuery(SELECT_instagram);
            resultI.next();
            System.out.println("\n1- Instagram: " + resultI.getInt(1));
        } else if (choise == 2) {
            String SELECT_facebook = "SELECT money FROM budget WHERE name_category = 'facebook'";
            ResultSet resultF = statement.executeQuery(SELECT_facebook);
            resultF.next();
            System.out.println("\n1- Facebook: " + resultF.getInt(1));
        }else {
            String SELECT_youtube = "SELECT money FROM budget WHERE name_category = 'youtube'";
            ResultSet resultY = statement.executeQuery(SELECT_youtube);
            resultY.next();
            System.out.println("\n1- Youtube: " + resultY.getInt(1));
        }
    }

}
