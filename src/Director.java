import java.sql.*;
import java.util.Scanner;

public class Director extends Hr {

    public Director(String name, String surname, int login, String status, int salary) {
        super(name, surname, login, status, salary);
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
        System.out.println("8 - Показать список оборудований для строительства объектов ");
        System.out.println("9 - Выделить бюджет для маркетинга: ");
        System.out.println("10 - Регистрация нового сотрудника");
        System.out.println("11 - Изменить пароль");
        System.out.println("12 - Показать список всех сотрудников");
        System.out.println("99 - Выйти из учетной записи");
        System.out.println("0 - Закончить работу");
        System.out.print("\nВаш выбор: ");
        int choice = scanner.nextInt();
        return choice;
    }
    public void showAllBudget() throws SQLException{
        Connection connection = DriverManager.getConnection(Main.DB_URL, Main.DB_USERNAME, Main.DB_PASSWORD);
        Statement statement = connection.createStatement();
        Scanner scanner = new Scanner(System.in);

        String SELECT_salary = "SELECT money FROM budget WHERE name_category = 'salary'";
        ResultSet resultS = statement.executeQuery(SELECT_salary);
        resultS.next();
       System.out.println("\nбюджет для заработной платы: " + resultS.getInt(1));

        String SELECT_marketing = "SELECT money FROM budget WHERE name_category = 'marketing'";
        ResultSet resultM = statement.executeQuery(SELECT_marketing);
        resultM.next();
        System.out.println("бюджет для маркетинга: " + resultM.getInt(1));
//        System.out.println("бюджет для маркетинга.");
    }

    public void showSalaryBudget() throws Exception{
        System.out.print("\nОбщий бюджет для заработной платы: ");
        System.out.println(salaryCount());
    }

    public void increaseSalary() throws SQLException {
        Connection connection = DriverManager.getConnection(Main.DB_URL, Main.DB_USERNAME, Main.DB_PASSWORD);
        Statement statement = connection.createStatement();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите ID сотрудника, которому хотите повысить зарплату: ");
        int userID = scanner.nextInt();

        String SELECT_salary = "SELECT salary FROM employee WHERE id = \'" + userID + "\'";
        ResultSet resultS = statement.executeQuery(SELECT_salary);
        resultS.next();
        int userSalary = resultS.getInt(1);

        System.out.print("Введите сумму надбавки к зарплате: ");
        int salarySupplement = scanner.nextInt() + userSalary;

        String UPDATE_salary = "UPDATE employee SET salary = \'" + salarySupplement + "\' WHERE id = \'" + userID + "\'" ;
        statement.executeUpdate(UPDATE_salary);

        String SELECT_S = "SELECT salary FROM employee WHERE id = \'" + userID + "\'";
        ResultSet result = statement.executeQuery(SELECT_S);
        result.next();
        System.out.println("Текущая зарплата сотрудника: " + result.getInt(1));

    }

    public void decreaseSalary() throws SQLException {
        Connection connection = DriverManager.getConnection(Main.DB_URL, Main.DB_USERNAME, Main.DB_PASSWORD);
        Statement statement = connection.createStatement();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите ID сотрудника, которому хотите понизить зарплату: ");
        int userID = scanner.nextInt();

        String SELECT_salary = "SELECT salary FROM employee WHERE id = \'" + userID + "\'";
        ResultSet resultS = statement.executeQuery(SELECT_salary);
        resultS.next();
        int userSalary = resultS.getInt(1);

        System.out.print("Введите сумму понижения зарплаты: ");
        int salaryReduction = scanner.nextInt();
        salaryReduction= userSalary -salaryReduction;

        String UPDATE_salary = "UPDATE employee SET salary = \'" + salaryReduction + "\' WHERE id = \'" + userID + "\'" ;
        statement.executeUpdate(UPDATE_salary);

        String SELECT_S = "SELECT salary FROM employee WHERE id = \'" + userID + "\'";
        ResultSet result = statement.executeQuery(SELECT_S);
        result.next();
        System.out.println("Текущая зарплата сотрудника: " + result.getInt(1));
    }

    public void showEquipment() {
        System.out.println("\nCписок оборудований для строительства объектов:\n");
        System.out.println("плиты");
        System.out.println("жарочные шкафы");
        System.out.println("пароконвектоматы");
        System.out.println("опциональное оборудование");
    }
}
