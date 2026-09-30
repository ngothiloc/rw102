package backend;

import entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QLCB implements IQLCB{
    private Scanner sc = new Scanner(System.in);
    private List<CanBo> canBoList = new ArrayList<>();

    public QLCB(){
        canBoList = new ArrayList<>();
        canBoList.add(new CanBo("canbo1", 20, GioiTinh.NAM, "HaNoi"));
        canBoList.add(new CanBo("canbo2", 21, GioiTinh.NU, "HaiPhong"));
        canBoList.add(new CanBo("canbo3", 22, GioiTinh.KHAC, "HCM"));
        canBoList.add(new CanBo("canbo4", 23, GioiTinh.NU, "DaNang"));
        canBoList.add(new CanBo("canbo5", 24, GioiTinh.NAM, "Hue"));
    }

    @Override
    public void themMoi() {
        System.out.println("==== THEM MOI CAN BO ====");
        System.out.print("Nhap ho ten: ");
        String hoten = sc.nextLine();
        System.out.print("Nhap tuoi: ");
        int tuoi = 0;

        while (true){
            if (!sc.hasNextInt()){
                sc.nextLine();
                System.err.println("Vui long nhap so nguyen duong!");
            } else {
                tuoi = sc.nextInt();
                sc.nextLine();
                if (tuoi <= 0){
                    System.err.println("Vui long nhap so nguyen duong!!!");
                } else {
                    break;
                }
            }
        }
        System.out.print("Nhap gioi tinh (1. NAM | 2. NU | 3. KHAC): ");
        String gt = sc.nextLine();
        GioiTinh gioiTinh;
        switch (gt){
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
        String diachi = sc.nextLine();

        System.out.println("Nhap loai can bo (1. Cong nhan | 2. Ky su | 3. Nhan vien): ");
        String choice = sc.nextLine();
        switch (choice){
            case "1":
                System.out.print("Nhap bac: ");
                int bac = sc.nextInt();;
                while(true){
                    if (!sc.hasNextInt()){
                        sc.nextLine();
                        System.err.println("Vui long nhap so!!");
                    }else {
                        bac = sc.nextInt();
                        if (bac < 1 || bac > 10){
                            System.err.println("Vui long nhap so >= 1 va <= 10");
                        } else {
                            break;
                        }
                    }
                }
                CanBo congNhan = new CongNhan(hoten, tuoi, gioiTinh, diachi, bac);
                canBoList.add(congNhan);
                System.out.println("them cong nhan thanh cong !!!");
                break;
            case "2":
                System.out.print("Nhap nganh dao tao: ");
                String nganhDaoTao = sc.nextLine();
                CanBo kySu = new KySu(hoten, tuoi, gioiTinh, diachi, nganhDaoTao);
                canBoList.add(kySu);
                System.out.println("them ky su thanh cong!!");
                break;
            default:
                System.out.print("Nhap cong viec: ");
                String congViec = sc.nextLine();
                CanBo nhanVien = new NhanVien(hoten, tuoi, gioiTinh , diachi, congViec);
                canBoList.add(nhanVien);
                System.out.println("Them nhan vien thanh cong!");
        }
    }

    @Override
    public void timKiemTheoTen() {
        System.out.println("==== TIM KIEM THEO TEN ====");
        System.out.print("Nhap ho ten can bo can tim: ");
        String ten = sc.nextLine();

        System.out.println("+--------------------+--------------------+--------------------+--------------------+");
        System.out.printf("|%20s|%20s|%20s|%20s|\n","Ho ten", "Tuoi", "Gioi Tinh", "Dia Chi");
        System.out.println("+--------------------+--------------------+--------------------+--------------------+");
        for (CanBo cb : canBoList) {
            if (cb.getHoTen().contains(ten)) {
                System.out.printf("|%20s|%20d|%20s|%20s|\n", cb.getHoTen(), cb.getTuoi(), cb.getGioiTinh(), cb.getDiaChi());
            }
        }
        System.out.println("+--------------------+--------------------+--------------------+--------------------+");


    }

    @Override
    public void hienThiToanBo() {
        System.out.println("==== HIEN THI TOAN BO CAN BO ====");
        System.out.println("+--------------------+--------------------+--------------------+--------------------+");
        System.out.printf("|%20s|%20s|%20s|%20s|\n","Ho ten", "Tuoi", "Gioi Tinh", "Dia Chi");
        System.out.println("+--------------------+--------------------+--------------------+--------------------+");
        for (CanBo cb : canBoList) {
            System.out.printf("|%20s|%20d|%20s|%20s|\n", cb.getHoTen(), cb.getTuoi(), cb.getGioiTinh(), cb.getDiaChi());
        }
        System.out.println("+--------------------+--------------------+--------------------+--------------------+");
    }

    @Override
    public void xoaTheoTen() {
        System.out.println("==== XOA THEO TEN ====");
        System.out.println("Nhap ho ten can xoa: ");
        String ten = sc.nextLine();
        //tim cac can bo co ten giong voi ten can xoa -> cho vao list
        List<CanBo> removes = new ArrayList<>();
        for (CanBo cb : canBoList){
            if (cb.getHoTen().equals(ten)){
                removes.add(cb);
            }
        }

        //xoa cac ptu trong list do ra khoai canBoList
        if(removes.isEmpty()){
            System.out.println("Khong co ten trong he thong!!!");
        } else {
            canBoList.removeAll(removes);//xoa list
            System.out.println("xoa thanh cong!!!");
        }

    }
}
