import java.sql.*;
import java.util.Scanner;

public class Marketing extends Employee {
    public Marketing(String name, String surname, int login, String status, int salary) {
        super(name, surname, login, status, salary);
    }

    public int printMenuMarketing() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nВыберите действие:\n==================");
        System.out.println("1 - Показать список всех зон покрытия");
        System.out.println("2 - Показать список категорий для маркетинга");
        System.out.println("3 - Показать выделенный бюджет для определенной категорий мест для маркетинга");
        System.out.println("4 - Показать оставшийся бюджет для маркетинга");
        System.out.println("5 - Потратить бюджет на продвижение");
        System.out.println("6 - Изменить пароль");
        System.out.println("99 - Выйти из учетной записи");
        System.out.println("0 - Закончить работу");
        System.out.print("\nВаш выбор: ");
        return scanner.nextInt();
    }

    public void showCategoriesMarketing(){
        System.out.println("\nInstagram: 3 000 000");
        System.out.println("Facebook: 500 000");
        System.out.println("Youtube: 1 000 000");
    }

    public void spendingOnPromotion() throws SQLException {
        int budgetMarketing = 0,
            instagramBudget = 0,
            facebookBudget = 0,
            youtubeBudget = 0;

        Connection connection = DriverManager.getConnection(Main.DB_URL, Main.DB_USERNAME, Main.DB_PASSWORD);
        Statement statement = connection.createStatement();
        Scanner scanner = new Scanner(System.in);
        String SELECT_marketing = "SELECT money FROM budget WHERE name_category = 'marketing'";
        ResultSet resultM = statement.executeQuery(SELECT_marketing);
        resultM.next();
        budgetMarketing =resultM.getInt(1);
        System.out.println(budgetMarketing);

        String SELECT_instagram = "SELECT money FROM budget WHERE name_category = 'instagram'";
        ResultSet resultI = statement.executeQuery(SELECT_instagram);
        resultI.next();
        instagramBudget =resultI.getInt(1);

        String SELECT_facebook = "SELECT money FROM budget WHERE name_category = 'marketing'";
        ResultSet resultF = statement.executeQuery(SELECT_facebook);
        resultF.next();
        facebookBudget =resultF.getInt(1);

        String SELECT_youtube = "SELECT money FROM budget WHERE name_category = 'youtube'";
        ResultSet resultY = statement.executeQuery(SELECT_youtube);
        resultY.next();
        youtubeBudget =resultY.getInt(1);

        while (true) {
            System.out.println("\nВыберите название для продвижения:");
            System.out.println("    1- Instagram");
            System.out.println("    2- Facebook");
            System.out.println("    3- Youtube");
            System.out.print(">>> ");
            int choise = scanner.nextInt();
            System.out.print("\nНаберите сумму расхода, которую вы хотите потратить из бюджета: ");
            int account = scanner.nextInt();
            if (choise == 1 && account <= budgetMarketing) {
                instagramBudget += account;
                System.out.println("\n1- Instagram: " + instagramBudget);
                budgetMarketing -= account;
                break;
            } else if (choise == 2 && account <= budgetMarketing) {
                facebookBudget += account;
                System.out.println("\n1- Facebook: " + facebookBudget);
                budgetMarketing -= account;
                break;
            } else if (choise == 3 && account <= budgetMarketing) {
                youtubeBudget += account;
                System.out.println("\n1- Youtube: " + youtubeBudget);
                budgetMarketing -= account;
                break;
            } else {
                System.out.println("\nПревышение допустимой суммы!!!");
            }
        }
        String UPDATE_marketingBudget = "UPDATE budget SET money = \'" + budgetMarketing + "\' WHERE name_category = 'marketing' ";
        statement.executeUpdate(UPDATE_marketingBudget);

        String UPDATE_instagram = "UPDATE budget SET money = \'" + instagramBudget + "\' WHERE name_category = 'instagram' ";
        statement.executeUpdate(UPDATE_instagram);

        String UPDATE_facebook = "UPDATE budget SET money = \'" + facebookBudget + "\' WHERE name_category = 'facebook' ";
        statement.executeUpdate(UPDATE_facebook);

        String UPDATE_youtube = "UPDATE budget SET money = \'" + youtubeBudget + "\' WHERE name_category = 'youtube' ";
        statement.executeUpdate(UPDATE_youtube);
    }
}
