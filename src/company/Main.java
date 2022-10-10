package company;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Main {
    static final String DB_USERNAME = "postgres";
    static final String DB_PASSWORD = "2001";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/shopme";

    public static int authorize() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        int idThatNeverAppearAtDB = -1;
        int userID = idThatNeverAppearAtDB;
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

        while (true) {

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
                    boolean logOut = false;
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
                            director.increaseSalary();
                            break;
                        case 7:
                            director.decreaseSalary();
                            break;
                        case 8:
                            director.showEquipment();
                            break;
                        case 48:
                            director.allocateBudget();
                            break;
                        case 49:
                            director.registrateEmployee();
                            break;
                        case 50:
                            director.changePassword();
                            break;
                        case 51:
                            director.showAllEmployeesList();
                            break;
                        case 99:
                            System.out.println("Вы вышли из учетной записи\n");
                            logOut = true;
                            break;
                        case 0:
                            System.out.println("\nПрограмма завершена, мы будем рады вашему возвращению!");
                            System.exit(0);
                    }

                    if (logOut) {
                        break;
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
                    boolean logOut = false;
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
                        case 99:
                            System.out.println("Вы вышли из учетной записи\n");
                            logOut = true;
                            break;
                        case 0:
                            System.out.println("\nПрограмма завершена, мы будем рады вашему возвращению!");
                            System.exit(0);
                    }

                    if (logOut) {
                        break;
                    }

                    Scanner s = new Scanner(System.in);
                    System.out.print("\nНажмите Enter для продолжения...");
                    s.nextLine();
                    chosenAction = marketing.printMenuMarketing();
                }
            } else if (userStatus.equals("manager")) {
                Manager manager = new Manager(userName, userSurname, userLogin, userStatus, userSalary);

                int chosenAction = manager.printMenuManager();
                while (true) {
                    boolean logOut = false;
                    switch (chosenAction) {
                        case 1:
                            manager.showAllWorkersList();
                            break;
                        case 2:
                            manager.giveTasks();
                            break;
                        case 3:
                            manager.showTasksTable();
                            break;
                        case 4:
                            manager.showCoverageAreas();
                            break;

                        case 50:
                            manager.showToDoTasks();
                            break;
                        case 99:
                            System.out.println("Вы вышли из учетной записи\n");
                            logOut = true;
                            break;
                        case 0:
                            System.out.println("\nПрограмма завершена, мы будем рады вашему возвращению!");
                            System.exit(0);
                    }

                    if (logOut) {
                        break;
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
                    boolean logOut = false;
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
                        case 99:
                            System.out.println("Вы вышли из учетной записи\n");
                            logOut = true;
                            break;
                        case 0:
                            System.out.println("\nПрограмма завершена, мы будем рады вашему возвращению!");
                            System.exit(0);
                    }

                    if (logOut) {
                        break;
                    }

                    Scanner s = new Scanner(System.in);
                    System.out.print("\nНажмите Enter для продолжения...");
                    s.nextLine();

                    chosenAction = worker.printMenuWorker();
                }
            } else if (userStatus.equals("HR")) {
                Hr HR = new Hr(userName, userSurname, userLogin, userStatus, userSalary);

                int chosenAction = HR.printMenuHR();
                while (true) {
                    boolean logOut = false;
                    switch (chosenAction) {
                        case 1:
                            HR.registrateEmployee();
                            break;
                        case 51:
                            HR.showAllEmployeesList();
                            break;
                        case 99:
                            System.out.println("Вы вышли из учетной записи\n");
                            logOut = true;
                            break;
                        case 0:
                            System.out.println("\nПрограмма завершена, мы будем рады вашему возвращению!");
                            System.exit(0);
                    }

                    if (logOut) {
                        break;
                    }

                    Scanner s = new Scanner(System.in);
                    System.out.print("\nНажмите Enter для продолжения...");
                    s.nextLine();

                    chosenAction = HR.printMenuHR();
                }
            }
        }
    }
}

