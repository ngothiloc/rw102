import java.time.LocalDate;

public class Program {

    public static void main(String[] args) {

        //tao ra department
        Department department1 = new Department();
        department1.id = 1;
        department1.name = "Bao ve";

        Department department2 = new Department();
        department2.id = 2;
        department2.name = "Sua chua";

        //tao ra position
        Position position1 = new Position();
        position1.id = 1;
        position1.name = PositionName.DEV;

        Position position2 = new Position();
        position2.id = 2;
        position2.name = PositionName.SCRUM_MASTER;

        //tao ra account
        Account account1 = new Account();
        account1.id = 1;
        account1.email = "locnt@gmail.com";
        account1.name = "Loc Ngo Tien";
        account1.date = LocalDate.now();
        account1.department = department1;
        account1.position = position2;

        System.out.println("Account ID: " + account1.id);
        System.out.println("FullName: " + account1.name);
        System.out.println("Email: " + account1.email);
        System.out.println("CreateDate: " + account1.date);
        System.out.println("Department ID: " + account1.department.id);
        System.out.println("Department Name: " + account1.department.name);
        System.out.println("Position ID: " + account1.position.id);
        System.out.println("Position Name: " + account1.position.name);
        System.out.println("=======================");


    }
}
