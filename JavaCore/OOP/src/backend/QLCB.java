package backend;

import entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QLCB implements IQLCB{
    private Scanner sc = new Scanner(System.in);
    private List<CanBo> canBoList = new ArrayList<>();

    public QLCB(){
//        canBoList = new ArrayList<>();
//        canBoList.add(new CongNhan("canbo1", 20, GioiTinh.NAM, "HaNoi", Loai.CN, 1));
//        canBoList.add(new KySu("canbo2", 21, GioiTinh.NU, "HaiPhong", Loai.KS, "Java"));
//        canBoList.add(new NhanVien("canbo3", 22, GioiTinh.KHAC, "HCM", Loai.NV, "Lap trinh"));
//        canBoList.add(new CongNhan("canbo4", 23, GioiTinh.NU, "DaNang", Loai.CN, 1));
//        canBoList.add(new KySu("canbo5", 24, GioiTinh.NAM, "Hue", Loai.KS, "C#"));
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
                CanBo congNhan = new CongNhan(hoten, tuoi, gioiTinh, diachi, Loai.CN, bac);
                canBoList.add(congNhan);
                System.out.println("them cong nhan thanh cong !!!");
                break;
            case "2":
                System.out.print("Nhap nganh dao tao: ");
                String nganhDaoTao = sc.nextLine();
                CanBo kySu = new KySu(hoten, tuoi, gioiTinh, diachi, Loai.KS , nganhDaoTao);
                canBoList.add(kySu);
                System.out.println("them ky su thanh cong!!");
                break;
            default:
                System.out.print("Nhap cong viec: ");
                String congViec = sc.nextLine();
                CanBo nhanVien = new NhanVien(hoten, tuoi, gioiTinh , diachi, Loai.NV, congViec);
                canBoList.add(nhanVien);
                System.out.println("Them nhan vien thanh cong!");
        }
    }

    @Override
    public void timKiemTheoTen() {
        List<CanBo> canBoList = new ArrayList<>();
        System.out.println("==== TIM KIEM THEO TEN ====");
        System.out.print("Nhap ho ten can bo can tim: ");
        String ten = sc.nextLine();

        try {
            //b1 lay du lieu
            String url = "jdbc:mysql://localhost:3306/qlcb";
            String username = "root";
            String password = "311004";

            // tao ket noi den database
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "select * from can_bo where ho_ten like ?"; // ? la tham so
            PreparedStatement statement = connection.prepareStatement(sql); // prepare ho tro cau sql dong (co tham so)
            statement.setString(1, "%" + ten + "%"); // truyen gia tri cho tham so

            ResultSet resultSet = statement.executeQuery(); // thuc thi cau querry sau
            while (resultSet.next()){ //chuyen tu resultSet thanh list canbo
                String hoTen = resultSet.getString("ho_ten");// lay du lieu theo ten cot hoac vi tri

                int tuoi = resultSet.getInt("tuoi");

                String gt = resultSet.getString("gioi_tinh");
                GioiTinh gioiTinh = GioiTinh.valueOf(gt); // chuyen String thanh Enum

                String diaChi = resultSet.getString("dia_chi");

                String loaiString = resultSet.getString("loai");
                Loai loai = Loai.valueOf(loaiString); // chuyen String thanh Enum

                if (loai == Loai.CN){
                    int bac = resultSet.getInt("bac");
                    CanBo cn = new CongNhan(hoTen, tuoi, gioiTinh, diaChi, Loai.CN, bac);
                    canBoList.add(cn);
                } else if (loai == Loai.KS){
                    String nganhDaoTao = resultSet.getString("nganh");
                    CanBo ks = new KySu(hoTen, tuoi, gioiTinh, diaChi, Loai.KS, nganhDaoTao);
                    canBoList.add(ks);
                } else if (loai == Loai.NV) {
                    String congViec = resultSet.getString("cong_viec");
                    CanBo nv = new NhanVien(hoTen, tuoi, gioiTinh, diaChi, Loai.NV, congViec);
                    canBoList.add(nv);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("+--------------------+--------------------+--------------------+--------------------+--------------------+");
        System.out.printf("|%20s|%20s|%20s|%20s|%20s|\n","Ho ten", "Tuoi", "Gioi Tinh", "Dia Chi", "Loai ");
        System.out.println("+--------------------+--------------------+--------------------+--------------------+--------------------+");
        for (CanBo cb : canBoList) {
            System.out.printf("|%20s|%20d|%20s|%20s|%20s|\n", cb.getHoTen(), cb.getTuoi(), cb.getGioiTinh(), cb.getDiaChi(), cb.getLoai());
        }
        System.out.println("+--------------------+--------------------+--------------------+--------------------+--------------------+");


    }

    @Override
    public void hienThiToanBo() {
        List<CanBo> canBoList = new ArrayList<>();
        try {
            //b1 lay du lieu
            String url = "jdbc:mysql://localhost:3306/qlcb";
            String username = "root";
            String password = "311004";

            // tao ket noi den database
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "select * from can_bo";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql); // thuc thi cau querry sau
            while (resultSet.next()){
                String hoTen = resultSet.getString("ho_ten");

                int tuoi = resultSet.getInt("tuoi");

                String gt = resultSet.getString("gioi_tinh");
                GioiTinh gioiTinh = GioiTinh.valueOf(gt);

                String diaChi = resultSet.getString("dia_chi");

                String loaiString = resultSet.getString("loai");
                Loai loai = Loai.valueOf(loaiString);

                if (loai == Loai.CN){
                    int bac = resultSet.getInt("bac");
                    CanBo cn = new CongNhan(hoTen, tuoi, gioiTinh, diaChi, Loai.CN, bac);
                    canBoList.add(cn);
                } else if (loai == Loai.KS){
                    String nganhDaoTao = resultSet.getString("nganh");
                    CanBo ks = new KySu(hoTen, tuoi, gioiTinh, diaChi, Loai.KS, nganhDaoTao);
                    canBoList.add(ks);
                } else if (loai == Loai.NV) {
                    String congViec = resultSet.getString("cong_viec");
                    CanBo nv = new NhanVien(hoTen, tuoi, gioiTinh, diaChi, Loai.NV, congViec);
                    canBoList.add(nv);
                }
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("==== HIEN THI TOAN BO CAN BO ====");
        System.out.println("+--------------------+--------------------+--------------------+--------------------+--------------------+");
        System.out.printf("|%20s|%20s|%20s|%20s|%20s|\n","Ho ten", "Tuoi", "Gioi Tinh", "Dia Chi", "Loai");
        System.out.println("+--------------------+--------------------+--------------------+--------------------+--------------------+");
        for (CanBo cb : canBoList) {
            System.out.printf("|%20s|%20d|%20s|%20s|%20s|\n", cb.getHoTen(), cb.getTuoi(), cb.getGioiTinh(), cb.getDiaChi(), cb.getLoai());
        }
        System.out.println("+--------------------+--------------------+--------------------+--------------------+--------------------+");
    }

    @Override
    public void xoaTheoTen() {
        System.out.println("==== XOA THEO TEN ====");
        System.out.println("Nhap ho ten can xoa: ");
        String ten = sc.nextLine();
        //tim cac can bo co ten giong voi ten can xoa -> cho vo list
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
