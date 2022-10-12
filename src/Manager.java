import java.sql.*;
import java.util.Scanner;

public class Manager extends Employee{
    private static final String INSERT_NEW = "INSERT INTO tasks (userid, task, progress) VALUES(?,?,?)";

    public Manager(String name, String surname, int login, String status, int salary) {
        super(name, surname, login, status, salary);
    }

    public int printMenuManager() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nВыберите действие:\n==================");
        System.out.println("1 - Показать список работников");
        System.out.println("2 - Распределить дела по работникам");
        System.out.println("3 - Показать список указаний к работникам");
        System.out.println("4 - Показать список зон покрытия");
        System.out.println("5 - Показать список дел");
        System.out.println("6 - Изменить пароль");
        System.out.println("99 - Выйти из учетной записи");
        System.out.println("0 - Закончить работу");
        System.out.print("\nВаш выбор: ");
        return scanner.nextInt();
    }

    public void showAllWorkersList() throws SQLException {
        Connection connection = DriverManager.getConnection(Main.DB_URL, Main.DB_USERNAME, Main.DB_PASSWORD);
        Statement statement = connection.createStatement();

        String SELECT_worker = "SELECT id, name, surname FROM employee WHERE status = 'worker' ORDER BY id";
        ResultSet result = statement.executeQuery(SELECT_worker);
        System.out.println();
        while(result.next()){
            System.out.println(result.getInt(1) + ". " + result.getString(2) + " " + result.getString(3));
        }
    }

    public void giveTasks() throws SQLException {
        Connection connection = DriverManager.getConnection(Main.DB_URL, Main.DB_USERNAME, Main.DB_PASSWORD);
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

    public void showToDoTasks() throws SQLException{
        Connection connection = DriverManager.getConnection(Main.DB_URL, Main.DB_USERNAME, Main.DB_PASSWORD);
        Statement statement = connection.createStatement();
        String SELECT_tasks = "SELECT task FROM tasks WHERE progress = 'to do'";
        ResultSet result = statement.executeQuery(SELECT_tasks);
        System.out.println();
        while(result.next()){
            System.out.println(result.getString(1));
        }
    }

    public void showTasksTable() throws SQLException {
        Connection connection = DriverManager.getConnection(Main.DB_URL, Main.DB_USERNAME, Main.DB_PASSWORD);
        Statement statement = connection.createStatement();

        String SELECT_tasks_by_user = "SELECT tasks.userid, employee.name, employee.surname, tasks.task, tasks.progress FROM employee JOIN tasks ON employee.id = tasks.userid ORDER BY employee.id";
        ResultSet resultUserID = statement.executeQuery(SELECT_tasks_by_user);
        System.out.printf("\n%-5s %-10s %-12s %-17s %-10s\n", "ID", "Имя", "Фамилия", "Задание", "Прогресc");
        while (resultUserID.next()) {
            System.out.printf("%-5s %-10s %-12s %-15s %-10s\n", resultUserID.getInt(1), resultUserID.getString(2),
                    resultUserID.getString(3), resultUserID.getString(4), resultUserID.getString(5));
        }
    }
}
