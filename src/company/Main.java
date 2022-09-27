package company;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Main {
    static final String DB_USERNAME = "postgres";
    static final String DB_PASSWORD = "2001";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/shopme";
    private static final String INSERT_NEW = "INSERT INTO employee (name,surname,login,password) VALUES(?,?,?,?)";
    private static final String DD = "DELETE FROM employee WHERE login = ?";

    public static String registrateNewEmployee() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW);
        String name = "",
                surname = "",
                password = "";
        int login = 0;

        boolean wrongInput = false;

        Scanner scanner = new Scanner(System.in);
            do {
                if (wrongInput) {
                    System.out.println("\nПароль или логин заданы не верно! Попробуйте еще раз.\n");
                }

                System.out.print("Введите имя: ");
                name = scanner.nextLine();

                System.out.print("Введите фамилию: ");
                surname = scanner.nextLine();

                System.out.print("Придумайте пароль: ");
                password = scanner.nextLine();

                wrongInput = true;
            } while (name.trim().isEmpty() || surname.trim().isEmpty());


            //insert to BD
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setInt(3, login);
            preparedStatement.setString(4, password);
            //preparedStatement.setString(5, employeeStatus);
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
        /*Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();

        Director a = new Director("yio", "qewrw",89,"ghjk","director");
        a.changePassword();*/

        registrateNewEmployee();
        authorize();




//        String SELECT_id = "SELECT surname FROM employee WHERE name = 'Gulnaz'";
//        ResultSet result = statement.executeQuery(SELECT_id);
//        result.next();
//        System.out.println(result.getString(1));
//        while (result.next()) {
//            System.out.println("Регистрация прошла успешно! вот ваш логин: " + result.getString(2));
//        }


    }
}
