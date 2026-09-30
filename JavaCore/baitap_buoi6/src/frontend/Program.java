package frontend;
import backend.IQLTL;
import backend.QLTL;

import java.util.Scanner;

public class Program {

    public static void main(String[] args){
        menu();
    }

    public static void menu(){
        IQLTL iqlcb = new QLTL();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("");
            System.out.println("=== MOI BAN CHON CHUC NANG ===");
            System.out.println("1. Thêm mới tai lieu.");
            System.out.println("2. Xoa tai lieu theo ma");
            System.out.println("3. Hiển thị thong tin tai lieu.");
            System.out.println("4. Tiem kiem tai lieu theo loai.");
            System.out.println("5. Thoát khỏi chương trình.");
            String choice = sc.nextLine();
            switch (choice){
                case "1":
                    iqlcb.themMoiTaiLieu();
                    break;
                case "2":
                    iqlcb.xoaTaiLieuTheoMa();
                    break;
                case "3":
                    iqlcb.hienThiThongTin();
                    break;
                case "4":
                    iqlcb.timKiemTheoLoai();
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
