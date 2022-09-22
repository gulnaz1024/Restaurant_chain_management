package company;

public class Director extends Employee {
    private String status;

    public Director(String name, String surname, int login, String password, String status) {
        super(name, surname, login, password);
        this.status = status;
    }

    public void printStatus() {
        System.out.println(this.status);
    }
}
