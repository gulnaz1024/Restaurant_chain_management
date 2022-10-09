package company;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Main {
    static final String DB_USERNAME = "postgres";
    static final String DB_PASSWORD = "2001";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/shopme";

    public static String registrateEmployee() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        String INSERT_NEW = "INSERT INTO employee (name,surname,login,password,status, salary) VALUES(?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW);
        String name = "",
                surname = "",
                password = "",
                status = "";
        int login = 0,
            salary = 777;

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

            System.out.print("Введите статус: ");
            status = scanner.nextLine();



            wrongInput = true;
        } while (name.trim().isEmpty() || surname.trim().isEmpty());


        //insert to BD
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, surname);
        preparedStatement.setInt(3, login);
        preparedStatement.setString(4, password);
        preparedStatement.setString(5, status);
        preparedStatement.setInt(6, salary);
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

    public static int authorize() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        int userID = -1;
        String userPassword = "";

        while (true) {
            Scanner scanner = new Scanner(System.in);
            int login;
            while (true) {
                System.out.print("Введите логин: ");
                String strLogin = scanner.nextLine();

                if (isNumeric(strLogin)) {
                    login = Integer.parseInt(strLogin);
                    break;
                } else {
                    System.out.println("Ваш логин должен состоять только из цифр.\n");
                }
            }

            System.out.print("Введите пароль: ");
            String password = scanner.nextLine();

            String SELECT_ID = "SELECT id FROM employee WHERE login = \'" + login + "\' AND password = \'" + password + "\'";
            ResultSet resultID = statement.executeQuery(SELECT_ID);
            if (resultID.next()) {
                //if login or password wrong, userID doesn't change. Its remain -1.
                userID = resultID.getInt(1);
            }

            String SELECT_password = "SELECT password FROM employee WHERE login = \'" + login + "\'";
            ResultSet resultP = statement.executeQuery(SELECT_password);
            if (resultP.next()) {
                userPassword = resultP.getString(1);
            }

            if (login == userID && password.equals(userPassword)) {
                System.out.println("Вы успешно вошли в систему.");

                break;
            } else {
                System.out.println("\nПароль или логин введены не верно! Попробуйте еще раз.\n");
            }
        }
        return userID;
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public static void main(String[] args) throws Exception {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
//        Marketing a = new Marketing("dim", "shai", 67, "manager", 22000);
//        a.printS();


        int authorizedUserID = authorize();

        String SELECT_All = "SELECT * FROM employee WHERE id = \'" + authorizedUserID + "\'";
        ResultSet resultAll = statement.executeQuery(SELECT_All);
        resultAll.next();
        String userName = resultAll.getString(2);
        String userSurname = resultAll.getString(3);
        int userLogin = resultAll.getInt(4);
        String userStatus = resultAll.getString(6);
        int userSalary = resultAll.getInt(7);

        if (userStatus.equals("director")) {

            Director director = new Director(userName, userSurname, userLogin, userStatus, userSalary);

            int chosenAction = director.printMenuDirector();
            while (true) {
                switch (chosenAction) {
                    case 1:
                        director.showCoverageAreas();
                        break;
                    case 2:
                        director.showAllBudget();
                        break;
                    case 3:
                        director.showBudgetForEachCategory();
                        break;
                    case 4:
                        director.showBudgetMarketing();
                        break;
                    case 5:
                        director.showSalaryBudget();
                        break;
                    case 6:
                        director.salaryIncrease();
                        break;
                    case 7:
                        director.salaryDecrease();
                        break;
                    case 8:
                        director.showeQuipment();
                        break;
                    case 48:
                        director.allocateBudget();
                        break;
                    case 49:
                        director.registrateNewEmployee();
                        break;
                    case 50:
                        director.changePassword();
                        break;
                    case 0:
                        System.out.println("\nПрограмма завершена, мы будем рады вашему возвращению!");
                        System.exit(0);
                }

                Scanner s = new Scanner(System.in);
                System.out.print("\nНажмите Enter для продолжения...");
                s.nextLine();


                chosenAction = director.printMenuDirector();
            }

        } else if (userStatus.equals("marketing")) {
            Marketing marketing = new Marketing(userName, userSurname, userLogin, userStatus, userSalary);

            int chosenAction = marketing.printMenuMarketing();
            while (true) {
                switch (chosenAction) {
                    case 1:
                        marketing.showCoverageAreas();
                        break;
                    case 2:
                        marketing.showCategoriesMarketing();
                        break;
                    case 3:
                        marketing.showBudgetForEachCategory();
                        break;
                    case 4:
                        marketing.showBudgetMarketing();
                        break;
                    case 5:
                        marketing.spendingOnPromotion();
                        break;
                    case 0:
                        System.out.println("\nПрограмма завершена, мы будем рады вашему возвращению!");
                        System.exit(0);
                }

                Scanner s = new Scanner(System.in);
                System.out.print("\nНажмите Enter для продолжения...");
                s.nextLine();


                chosenAction = marketing.printMenuMarketing();
            }


        }else if (userStatus.equals("manager")) {
            Manager manager = new Manager(userName, userSurname, userLogin, userStatus, userSalary);

            int chosenAction = manager.printMenuManager();
            while (true) {
                switch (chosenAction) {
                    case 1:
                        manager.showEmployees();
                        break;
                    case 2:
                        manager.giveTasks();
                        break;
                    case 3:
                        manager.showTableTasks();
                        break;
                    case 4:
                        manager.showCoverageAreas();
                        break;

                    case 50:
                        manager.showTasks();
                        break;
                    case 0:
                        System.out.println("\nПрограмма завершена, мы будем рады вашему возвращению!");
                        System.exit(0);
                }

                Scanner s = new Scanner(System.in);
                System.out.print("\nНажмите Enter для продолжения...");
                s.nextLine();

                chosenAction = manager.printMenuManager();
            }
        } else if (userStatus.equals("worker")) {
            Worker worker = new Worker(userName, userSurname, userLogin, userStatus, userSalary);

            int chosenAction = worker.printMenuWorker();
            while (true) {
                switch (chosenAction) {
                    case 1:
                        worker.showTasksToDo();
                        break;
                    case 2:
                        worker.showTasksDone();
                        break;
                    case 3:
                        worker.showTasksINProgress();
                        break;
                    case 4:
                        worker.showEmployeeSalary();
                        break;
                    case 0:
                        System.out.println("\nПрограмма завершена, мы будем рады вашему возвращению!");
                        System.exit(0);
                }

                Scanner s = new Scanner(System.in);
                System.out.print("\nНажмите Enter для продолжения...");
                s.nextLine();

                chosenAction = worker.printMenuWorker();
            }
        }
    }
}

