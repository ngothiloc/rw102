package frontend;

import backend.HTTH;
import backend.IHTTH;

import java.util.Scanner;

public class Program {

    public static void main(String[] args){
        menu();
    }

    public static void menu(){
        Scanner sc = new Scanner(System.in);
        IHTTH ihtth = new HTTH();

//        void hienThiToanBoAccount();
//        void timKiemAccount_TheoUser();
//        void hienThiDepartment();
//        void timKiemDepartment_TheoTen();

        while (true) {
            System.out.println("");
            System.out.println("=== MOI BAN CHON CHUC NANG ===");
            System.out.println("1. Hien thi toan bo account");
            System.out.println("2. Tim kiem account - theo user.");
            System.out.println("3. Hien thi toan bo department");
            System.out.println("4. Tim kiem department - theo ten");
            System.out.println("5. Thoát khỏi chương trình.");
            String choice = sc.nextLine();
            switch (choice){
                case "1":
                    ihtth.hienThiToanBoAccount();
                    break;
                case "2":
                    ihtth.timKiemAccount_TheoUser();
                    break;
                case "3":
                    ihtth.hienThiDepartment();
                    break;
                case "4":
                    ihtth.timKiemDepartment_TheoTen();
                    break;
                case "5":
                    System.out.println("Thoat");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Nhap chua dung, nhap lai!!");
            }
        }
    }
}
