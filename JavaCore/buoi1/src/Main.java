import java.time.LocalDate;
import java.util.Date;

//cac quy tắc đặt tên cần tuân thủ khi code Java
//- các keyword trong java phân biệt hoa thường
//- tên class phải viết hoa chữ cái đầu của từng từ
//- thuộc tính phải là danh từ, phương thức là động từ
//- với thuộc tính, phương thức thì chữ cái đầu viết thường, viết hoa các chữ cái đầu của các từ phía sau VD: createDate, fullName, ...
//        - với enum thì các gtri phải viết hoa hết
//        VD: DEV, TEST, PM, SCRUM_MASTER
//- với các thuộc tính boolean thfi bắt đầu bằng chữ "is"

public class Main {
    public enum GioiTinh {
        Male, Female, other
    };
    public static void main(String[] args) {
        System.out.println("Hello world");

        String fullname = "Ngô Tiến Lộc"; // like varchar: bao nhieue  ky tu cung duoc
        int Age = 22;
        float Point = 7.5f;
        LocalDate birthday = LocalDate.of(2004, 10, 31); // date
        Date data = new Date();
        GioiTinh gioiTinh = GioiTinh.Male;

        System.out.println("Fullname: " + fullname);
        System.out.println("Age:" + Age);
        System.out.println("Point:" + Point);
        System.out.println("Birthday:" + birthday);
        System.out.println(data);
        System.out.println("Gioi Tinh: " + GioiTinh.Male);

        //Mang array Khai bao loat cac du lieu
        double[] diems = new double[]{10, 9 , 8 , 7 , 6, 5};
        String[] hocsinh = new String[]{"An", "Phuc", "Binh", "Ha"};

        //kieu dung sai
        Boolean checkTrue = true;
        Boolean checkFalse = false;

        //sosanh
        boolean check3 = (1 < 2);
        System.out.println("Check3: " + check3);

        //Run method inThongTinPosition
        Position p1 = new Position();
        p1.id = 1;
        p1.name = PositionName.DEV;

    }
}