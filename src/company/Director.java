package company;
import java.sql.*;
import java.util.Scanner;

import static company.Main.*;

public class Director extends Employee {




    public Director(String name, String surname, int login, String status) {
        super(name, surname, login, status);
    }



    public void printStatus() {

        System.out.println(this.status);
    }


    public void registrateNewEmployee() throws Exception{
        registrateEmployee();
    }

    public int printMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nВыберите действие:\n==================");
        System.out.println("1 - Показать список всех зон покрытия");
        System.out.println("2 - Показать список категорий бюджета");

        System.out.println("49 - Регистрация нового сотрудника");
        System.out.println("50 - Изменить пароль");
        System.out.println("0 - Закончить работу");
        System.out.print("\nВаш выбор: ");
        int choice = scanner.nextInt();
        return choice;
    }
    public void showMarketingSalaryBudget() throws Exception{
//        System.out.println("бюджет для заработной платы,");
//        System.out.println("бюджет для маркетинга.");
        System.out.println("!!! UNDER CONSTRUCTION !!!");
    }

    public void showCoverageAreas() {
        System.out.println("Зона охвата клиентами для Бишкека: 13%");
        System.out.println("Зона охвата клиентами для Джаллабада: 17%");
        System.out.println("Зона охвата клиентами для Оша: 21%");
        System.out.println("Зона охвата клиентами для Нарына: 54%");
        System.out.println("Зона охвата клиентами для Иссык Куля: 5%");
        System.out.println("Зона охвата клиентами для Баткена: 8%");
    }
}
