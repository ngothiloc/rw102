//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        double diem = 4.5;
//        (neu diem >= 5 thi in ra 'qua mon')
        if (diem >= 5) {
            System.out.println("qua mon");
        } else {
            System.out.println("tach");
        }

//        (neu diem >= 8 thi in ra GIOI
//         neu >= 6 va < 8 thi in ra kha
//         neu >= 5 va < 6 thi in ra trung binh
//         duoi 5 thi yeu)

        int number = 0;
        System.out.println(number == 0 ? "Khong" : "Khong xac dinh"); // toan tu benari
    }
}