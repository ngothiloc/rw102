package backend;

import entity.*;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QLTL implements IQLTL{
    private Scanner sc = new Scanner(System.in);
    private List<TaiLieu> taiLieuList = new ArrayList<>();

    public QLTL(){
        taiLieuList = new ArrayList<>();
        taiLieuList.add(new TaiLieu(1, "loc", 1, LoaiTaiLieu.BAO));
        taiLieuList.add(new TaiLieu(2, "loc1", 12, LoaiTaiLieu.SACH));
        taiLieuList.add(new TaiLieu(3, "loc2", 13, LoaiTaiLieu.TAP_CHI));
        taiLieuList.add(new TaiLieu(4, "loc3", 14, LoaiTaiLieu.SACH));
        taiLieuList.add(new TaiLieu(5, "loc4", 15, LoaiTaiLieu.BAO));
    }

    @Override
    public void themMoiTaiLieu() {
        System.out.println("=== THEM MOI TAI LIEU ===");
        System.out.print("Chon loai tai lieu (1: Sach | 2: Bao | 3. Tap chi): ");
        String ltl = sc.nextLine();

        //=== Nhap thong tin co ban ====
        System.out.print("Nhap ma tai lieu: ");
        int maTaiLieu = sc.nextInt();
        sc.nextLine();

        System.out.print("Nhap ten nha xuat ban: ");
        String tenNhaXuatBan = sc.nextLine();

        System.out.print("Nhap so ban phat hanh: ");
        int soBanPhatHanh = sc.nextInt();
        sc.nextLine();

        //=== chon loai tai lieu va nhap thong tin theo tung loai ===
        LoaiTaiLieu loaiTaiLieu;
        switch (ltl){
            case "1" :
                System.out.print("Nhap ten tac gia: ");
                String tenTacGia = sc.nextLine();

                System.out.print("Nhap so trang: ");
                int soTrang = sc.nextInt();
                sc.nextLine();

                loaiTaiLieu = LoaiTaiLieu.SACH;

                TaiLieu sach = new Sach(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, loaiTaiLieu, tenTacGia, soTrang);
                taiLieuList.add(sach);
                System.out.println("Them sach thanh cong!!!");
                break;
            case "2":
                System.out.print("Nhap ngay phat hanh: ");
                int ngayPhatHanh = sc.nextInt();
                sc.nextLine();

                loaiTaiLieu = LoaiTaiLieu.BAO;
                TaiLieu bao = new Bao(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, loaiTaiLieu, ngayPhatHanh);
                taiLieuList.add(bao);
                System.out.println("Them bao thanh cong");
                break;
            case "3":
                System.out.print("Nhap so phat hanh: ");
                int soPhatHanh = sc.nextInt();
                sc.nextLine();

                System.out.print("Nhap thang phat hanh: ");
                int thangPhatHanh = sc.nextInt();
                sc.nextLine();

                loaiTaiLieu = LoaiTaiLieu.TAP_CHI;
                TaiLieu tapChi = new TapChi(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, loaiTaiLieu, soPhatHanh);
                taiLieuList.add(tapChi);
                System.out.println("Them tap chi thanh cong!!!");
                break;
            default:
                System.out.println("Chon sai, chon lai!!!");
                return;
        }
    }

    @Override
    public void xoaTaiLieuTheoMa() {
        System.out.println("=== XOA TAI LIEU THEO MA ===");
        System.out.print("Nhap ma tai lieu can xoa: ");
        int maTaiLieu = sc.nextInt();
        sc.nextLine();

        List<TaiLieu> removes = new ArrayList<>();
        for (TaiLieu tl : taiLieuList){
            if(tl.getMaTaiLieu() == maTaiLieu){
                removes.add(tl);
            }
        }

        if(removes.isEmpty()){
            System.out.println("Khong co ma tai lieu can xoa!!!");
        } else {
            taiLieuList.removeAll(removes);
            System.out.println("Xoa thanh cong !!!");
        }
    }

    @Override
    public void hienThiThongTin() {
        System.out.println("=== HIEN THI THONG TIN ===");
        System.out.println("+---------------+------------------------------+------------------------------+------------------------------+");
        System.out.printf("|%15s|%30s|%30s|%30s|\n","Ma tai lieu", "Ten Nha Xuat Ban", "So ban phat hanh", "Loai tai lieu");
        System.out.println("+---------------+------------------------------+------------------------------+------------------------------+");
        for (TaiLieu tl : taiLieuList){
            System.out.printf("|%15d|%30s|%30d|%30s|\n",tl.getMaTaiLieu(), tl.getTenNXB(), tl.getSoBanPhatHanh(), tl.getLoaiTaiLieu());
        }
        System.out.println("+---------------+------------------------------+------------------------------+------------------------------+");
    }

    @Override
    public void timKiemTheoLoai() {
        System.out.println("=== TIM KIEM THEO LOAI");
        System.out.println("Nhap loai can tim: ");
        String loai = sc.nextLine();
        System.out.println("+---------------+------------------------------+------------------------------+------------------------------+");
        System.out.printf("|%15s|%30s|%30s|%30s|\n","Ma tai lieu", "Ten Nha Xuat Ban", "So ban phat hanh", "Loai tai lieu");
        System.out.println("+---------------+------------------------------+------------------------------+------------------------------+");
        for (TaiLieu tl : taiLieuList){
            if (tl.getLoaiTaiLieu().toString().equalsIgnoreCase(loai)) {
                System.out.printf("|%15d|%30s|%30d|%30s|\n", tl.getMaTaiLieu(), tl.getTenNXB(), tl.getSoBanPhatHanh(), tl.getLoaiTaiLieu());
            }
        }
        System.out.println("+---------------+------------------------------+------------------------------+------------------------------+");
    }
}
