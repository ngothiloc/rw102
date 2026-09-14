import java.time.LocalDate;

public class Person {

    // thuoc tinh
    int id;
    String fullname;
    Main.GioiTinh gender;
    LocalDate birthdate;
    String cccd;


    //phuong thuc
    void an(){
        System.out.println("person dang an: ");
    }

    void ngu(){
        System.out.println("person dang ngu:");
    }
    void inThongTin(){
        System.out.println("ID:" + id);
        System.out.println("Fullname: " + fullname);
        System.out.println("Gioitinh: " + gender);
        System.out.println("CCCD" + cccd);
        System.out.println("Birthday" + birthdate);
    }
}
