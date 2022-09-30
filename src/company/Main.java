package company;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Main {
    static final String DB_USERNAME = "postgres";
    static final String DB_PASSWORD = "2001";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/shopme";
    private static final String INSERT_NEW = "INSERT INTO employee (name,surname,login,password,status) VALUES(?,?,?,?,?)";
    private static final String DD = "DELETE FROM employee WHERE login = ?";

    public static String registrateEmployee() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW);
        String name = "",
                surname = "",
                password = "",
                status = "";
        int login = 0;

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
            if(resultID.next()){
                //if login or password wrong, userID doesn't change. Its remain -1.
                userID = resultID.getInt(1);
            }

            String SELECT_password = "SELECT password FROM employee WHERE login = \'" + login + "\'";
            ResultSet resultP = statement.executeQuery(SELECT_password);
            if(resultP.next()) {
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
//        Director a = new Director("dim","shai", 64,"HR");
//        a.showMarketingSalaryBudget();

        int authorizedUserID = authorize();

        String SELECT_status = "SELECT status FROM employee WHERE id = \'" + authorizedUserID + "\'";
        ResultSet result = statement.executeQuery(SELECT_status);
        String status = "";
        result.next();
        status = result.getString(1);

        if (status.equals("director")) {
            String SELECT_All = "SELECT * FROM employee WHERE id = \'" + authorizedUserID + "\'";
            ResultSet resultAll = statement.executeQuery(SELECT_All);
            resultAll.next();
            String nameDirector = resultAll.getString(2);
            String surnameDirector = resultAll.getString(3);
            int loginDirector = resultAll.getInt(4);
            Director director = new Director(nameDirector, surnameDirector, loginDirector, status);

            int chosenAction = director.printMenu();
            while(true) {
                switch (chosenAction) {
                    case 1: director.showCoverageAreas();
                        break;
                    case 2: director.showMarketingSalaryBudget();
                        break;
                    case 49: director.registrateNewEmployee();
                        break;
                    case 50: director.changePassword();
                        break;
                    case 0:
                        System.out.println("\nПрограмма завершена, мы будем рады вашему возвращению!");
                        System.exit(0);
                }

                Scanner s = new Scanner(System.in);
                System.out.print("\nНажмите Enter для продолжения...");
                s.nextLine();


                chosenAction = director.printMenu();
            }







        }







    }
}
