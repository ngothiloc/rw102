package frontend;

import backend.IQLCB;
import backend.QLCB;
import backend.controller.CanBoController;
import entity.*;

import java.util.List;
import java.util.Scanner;

public class Function {
    private  CanBoController canBoController;
    Scanner sc = new Scanner(System.in);

    public Function(){
        this.canBoController = new CanBoController();
    }

    //Them moi
    public void themMoi(){
        System.out.println("==== THEM MOI CAN BO ====");

        System.out.print("Nhap ho ten: ");
        String hoten;
        while (true) {
            hoten = sc.nextLine();
            if (hoten.length() < 5 || hoten.length() > 50){
                System.err.println("ho ten phai tu 5 - 50 ky tu nhap lai!!!");
                continue;
            }

            boolean check = canBoController.existByName(hoten);
            if (check) {
                System.err.println("Ho ten nay da ton tai !!! Chon ten khac");
                continue;
            } break;
        }

        System.out.print("Nhap tuoi: ");
        int tuoi = 0;

        while (true) {
            if (!sc.hasNextInt()) {
                sc.nextLine();
                System.err.println("Vui long nhap so nguyen duong!");
            } else {
                tuoi = sc.nextInt();
                sc.nextLine();

                if (tuoi <= 0) {
                    System.err.println("Vui long nhap so nguyen duong!!!");
                } if(tuoi > 150) {
                    System.out.println("Vui long nhap nho hon 150! ");
                } else {
                    break;
                }
            }
        }
        System.out.print("Nhap gioi tinh (1. NAM | 2. NU | 3. KHAC): ");
        String gt = sc.nextLine();
        GioiTinh gioiTinh;
        switch (gt) {
            case "1":
                gioiTinh = GioiTinh.NAM;
                break;
            case "2":
                gioiTinh = GioiTinh.NU;
                break;
            case "3":
                gioiTinh = GioiTinh.KHAC;
                break;
            default:
                System.out.println("Chon sai, chon lai!!!");
                return;
        }

        System.out.print("Nhap dia chi: ");
        String diachi;
        while (true) {
            diachi = sc.nextLine();
            if (diachi.length() < 5 || diachi.length() > 100){
                System.err.println("Dia chi phai tu 5 - 100 ky tu nhap lai!!!");
                continue;
            } break;
        }

        String congViec = null;
        int bac = 0;
        String nganhDaoTao = null;
        CanBo canBo = null;
        System.out.println("Nhap loai can bo (1. Cong nhan | 2. Ky su | 3. Nhan vien): ");
        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                while (true) {
                    System.out.print("Nhap bac: ");
                    if (!sc.hasNextInt()) {
                        sc.nextLine();
                        System.err.println("Vui long nhap so!!");
                    } else {
                        bac = sc.nextInt();
                        sc.nextLine();
                        if (bac < 1 || bac > 10) {
                            System.err.println(
                                    "Vui long nhap so >= 1 va <= 10"
                            );
                        } else {
                            break;
                        }
                    }
                }
                canBo = new CongNhan(hoten, tuoi, gioiTinh, diachi, Loai.CN, bac);
                break;
            case "2":
                System.out.print("Nhap nganh dao tao: ");
                while (true) {
                    nganhDaoTao = sc.nextLine();
                    if (nganhDaoTao.length() < 5 || nganhDaoTao.length() > 50){
                        System.err.println("Nganh dao tao phai tu 5 - 50 ky tu nhap lai!!!");
                        continue;
                    } break;
                }
                canBo = new KySu(hoten, tuoi, gioiTinh, diachi, Loai.KS, nganhDaoTao);
                break;
            case "3":
                System.out.print("Nhap cong viec: ");
                while (true) {
                    congViec = sc.nextLine();
                    if (congViec.length() < 5 || congViec.length() > 50){
                        System.err.println("Cong viec phai tu 5 - 50 ky tu nhap lai!!!");
                        continue;
                    } break;
                }
                canBo = new NhanVien(hoten, tuoi, gioiTinh, diachi, Loai.NV, congViec);
                break;
            default:
                System.out.println("Chon sai, chon lai!!!");
                return;
        }
        boolean check = canBoController.save(canBo);

        if (check) {
            System.out.println("Thêm mới thành công!");
        } else {
            System.out.println("Thêm mới thất bại!");
        }
    }
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
                    this.themMoi();
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
