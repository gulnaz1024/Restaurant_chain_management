package company;
import java.sql.*;

import static company.Main.*;

public class Director extends Employee {
    private String status;



    public Director(String name, String surname, int login, String password, String status) {
        super(name, surname, login, password);
    }



    public void printStatus() {

        System.out.println(this.status);
    }


    public void b() throws Exception{
        registrateNewEmployee();
    }
}
