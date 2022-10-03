package company;

import java.sql.*;
import java.util.Scanner;

import static company.Main.*;

public class Employee {

    private String name;
    private String surname;
    private int login;
    public String status;
    public int salary;

    public int instagramBudget;
    public int facebookBudget;
    public int youtubeBudget;
    public int budgetMarketing;

    public Employee(String name, String surname, int login, String status, int salary) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.status = status;
        this.salary = salary;
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

    public int salaryCount() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        String SELECT_salary = "SELECT salary FROM employee";
        ResultSet result = statement.executeQuery(SELECT_salary);
        while(result.next()){
            this.salary += result.getInt(1);
        }
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

    public void showBudgetForEachCategory(){
        System.out.println("\n1- Instagram");
        System.out.println("2- Facebook");
        System.out.println("3- Youtube");
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nВыберите категорию места: ");
        int choise = scanner.nextInt();
        if(choise == 1){
            System.out.println("\n1- Instagram: " + instagramBudget);
        } else if (choise == 2) {
            System.out.println("\n2- Facebook: " + facebookBudget);
        }else {
            System.out.println("\n3- Youtube: " + youtubeBudget);
        }
    }

    public void showBudgetMarketing(){
        System.out.println("\nОбщий бюджет маркетинга: "+ budgetMarketing);
    }
    public int spendingOnPromotion(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nВыберите название для продвижения:");
        System.out.println("\n      1- Instagram");
        System.out.println("    2- Facebook");
        System.out.println("    3- Youtube");
        System.out.print("\n>>> ");
        int choise = scanner.nextInt();
        System.out.print("\nНаберите сумму расхода, которую вы хотите потратить из бюджета: ");
        int account = scanner.nextInt();
        if(choise == 1 && account <= budgetMarketing){
            instagramBudget = account;
            System.out.println("\n1- Instagram: " + instagramBudget);
            budgetMarketing -= account;
        } else if (choise == 2 && account <= budgetMarketing) {
            facebookBudget = account;
            System.out.println("\n1- Instagram: " + facebookBudget);
            budgetMarketing -= account;
        }else if(choise == 3 && account <= budgetMarketing) {
            youtubeBudget = account;
            System.out.println("\n1- Instagram: " + youtubeBudget);
            budgetMarketing -= account;
        }
        else{
            System.out.println("Превышение допустимой суммы!!!");
        }
        return budgetMarketing;
    }

    public int allocateBudget(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nВведите сумму, которая будет выделена на маркетинг: ");
        int budget = scanner.nextInt();
        budgetMarketing += budget;
        return budgetMarketing;
    }

}
