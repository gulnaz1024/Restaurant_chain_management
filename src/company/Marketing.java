package company;
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
        System.out.println("4 - Показать выделенный бюджет для маркетинга");
        System.out.println("5 - Потратить бюджет на продвижение");
        System.out.println("0 - Закончить работу");
        System.out.print("\nВаш выбор: ");
        return scanner.nextInt();
    }
    @Override
    public void showCoverageAreas() {
        super.showCoverageAreas();
    }

    public void showCategoriesMarketing(){
        System.out.println("\nInstagram: 3 000 000");
        System.out.println("Facebook: 500 000");
        System.out.println("Youtube: 1 000 000");
    }

    @Override
    public void showBudgetForEachCategory() {
        super.showBudgetForEachCategory();
    }

    @Override
    public void showBudgetMarketing() {
        super.showBudgetMarketing();
    }

    @Override
    public int spendingOnPromotion() {
        return super.spendingOnPromotion();
    }
}
