package frontend;

import backend.IQLCB;
import backend.QLCB;
import backend.controller.CanBoController;
import entity.CanBo;

import java.util.List;
import java.util.Scanner;

public class Function {
    private  CanBoController canBoController;
    Scanner sc = new Scanner(System.in);

    public Function(){
        this.canBoController = new CanBoController();
    }

    //Them moi
    //Hien thi
    public void hienThiToanBo(){
        List<CanBo> canBoList = canBoController.findAll();
        System.out.println("==== HIEN THI TOAN BO CAN BO ====");
        System.out.println("+--------------------+--------------------+--------------------+--------------------+--------------------+");
        System.out.printf("|%20s|%20s|%20s|%20s|%20s|\n","Ho ten", "Tuoi", "Gioi Tinh", "Dia Chi", "Loai");
        System.out.println("+--------------------+--------------------+--------------------+--------------------+--------------------+");
        for (CanBo cb : canBoList) {
            System.out.printf("|%20s|%20d|%20s|%20s|%20s|\n", cb.getHoTen(), cb.getTuoi(), cb.getGioiTinh(), cb.getDiaChi(), cb.getLoai());
        }
        System.out.println("+--------------------+--------------------+--------------------+--------------------+--------------------+");
    }
    //Tim kiem
    public void timKiemTheoTen(){
        System.out.println("==== TIM KIEM THEO TEN ====");
        System.out.print("Nhap ho ten can bo can tim: ");
        String ten = sc.nextLine();

        List<CanBo> canBoList = canBoController.findByName(ten);

        if(canBoList.isEmpty()){
            System.out.println("Chua co noi dung");
        } else {
            System.out.println("+--------------------+--------------------+--------------------+--------------------+--------------------+");
            System.out.printf("|%20s|%20s|%20s|%20s|%20s|\n","Ho ten", "Tuoi", "Gioi Tinh", "Dia Chi", "Loai ");
            System.out.println("+--------------------+--------------------+--------------------+--------------------+--------------------+");
            for (CanBo cb : canBoList) {
                System.out.printf("|%20s|%20d|%20s|%20s|%20s|\n", cb.getHoTen(), cb.getTuoi(), cb.getGioiTinh(), cb.getDiaChi(), cb.getLoai());
            }
            System.out.println("+--------------------+--------------------+--------------------+--------------------+--------------------+");
        }
    }
    //Xoa
    public void deleteByName(){
        System.out.println("==== XOA THEO TEN ====");
        System.out.println("Nhap ho ten can xoa: ");
        String ten = sc.nextLine();

        boolean check = canBoController.deleteByName(ten);

        if (check){
            System.out.println("xoa thanh cong");
        } else {
            System.out.println("xoa loi!");
        }
    }
    //Update
    public void menu(){
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("");
            System.out.println("=== MOI BAN CHON CHUC NANG ===");
            System.out.println("1. Thêm mới cán bộ.");
            System.out.println("2. Tìm kiếm theo họ tên.");
            System.out.println("3. Hiển thị toàn bộ các cán bộ.");
            System.out.println("4. Nhập vào tên của cán bộ và delete cán bộ đó.");
            System.out.println("5. Thoát khỏi chương trình.");
            String choice = sc.nextLine();
            switch (choice){
                case "1":
                    break;
                case "2":
                    this.timKiemTheoTen();
                    break;
                case "3":
                    this.hienThiToanBo();
                    break;
                case "4":
                    this.deleteByName();
                    break;
                case "6" :
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
