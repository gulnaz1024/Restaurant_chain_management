package company;

public class Employee {

    private String name;
    private String surname;
    private int login;
    private String password;

    public Employee(String name, String surname, int login, String password) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }

    public void printInfo() {
        System.out.print(this.name);
    }
}
