package backend;

import entity.*;
import utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QLCB implements IQLCB{
    private Scanner sc = new Scanner(System.in);
    private List<CanBo> canBoList = new ArrayList<>();

    public Connection getConnection(){
        String url = "jdbc:mysql://localhost:3306/qlcb";
        String username = "root";
        String password = "311004";
        Connection conn = null;
        try {
            return  DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } return conn;
    }

    @Override
    public void themMoi() {
        Connection con = null;
        String sql = null;
        PreparedStatement preparedStatement = null;

        String url = "jdbc:mysql://localhost:3306/qlcb";
        String username = "root";
        String password = "311004";

        System.out.println("==== THEM MOI CAN BO ====");

        System.out.print("Nhap ho ten: ");
        String hoten = sc.nextLine();

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
        String diachi = sc.nextLine();

        String column = null;
        String value = null;
        System.out.println("Nhap loai can bo (1. Cong nhan | 2. Ky su | 3. Nhan vien): ");
        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                int bac = 0;
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
                column = "bac";
                value = "'CN', " + bac;
                break;
            case "2":
                System.out.print("Nhap nganh dao tao: ");
                String nganhDaoTao = sc.nextLine();
                column = "nganh";
                value = "'KS', '" + nganhDaoTao + "'";
                break;
            case "3":
                System.out.print("Nhap cong viec: ");
                String congViec = sc.nextLine();
                column = "cong_viec";
                value = "'NV', '" + congViec + "'";
                break;

            default:
                System.out.println("Chon sai, chon lai!!!");
                return;
        }
        sql = String.format(
                "INSERT INTO can_bo (ho_ten, tuoi, gioi_tinh, dia_chi, loai, %s) VALUES (?, ?, ?, ?, %s)", column, value);
        try {
            con = JDBCUtils.getConnection();
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, hoten);
            preparedStatement.setInt(2, tuoi);
            preparedStatement.setString(3, gioiTinh.name());
            preparedStatement.setString(4, diachi);

            int c = preparedStatement.executeUpdate();

            if (c > 0) {
                System.out.println("Them thanh cong!");
            } else {
                System.out.println("Them that bai!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void timKiemTheoTen() {
        List<CanBo> canBoList = new ArrayList<>();
        System.out.println("==== TIM KIEM THEO TEN ====");
        System.out.print("Nhap ho ten can bo can tim: ");
        String ten = sc.nextLine();

        try {
//            //b1 lay du lieu
            // tao ket noi den database
            Connection connection = JDBCUtils.getConnection();
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
        } finally {
            JDBCUtils.closeConnection();
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
            // tao ket noi den database
            Connection connection = JDBCUtils.getConnection();
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
        } finally {
            JDBCUtils.closeConnection();
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

        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "DELETE FROM can_bo WHERE ho_ten like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ten);

            int c = preparedStatement.executeUpdate();
            if (c > 0) {
                System.out.println("Xoa thanh cong!!!");
            } else {
                System.out.println("Xoa that bai");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeConnection();
        }
    }
    @Override
    public void updateTheoTen() {
        System.out.println("==== CAP NHAT THEO TEN ====");
        System.out.println("Nhap ho ten can cap nhat: ");
        String ten = sc.nextLine();

        System.out.println("Nhap dia chi can update: ");
        String diaChi = sc.nextLine();

        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "update can_bo SET dia_chi = ? where ho_ten = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, diaChi);
            preparedStatement.setString(2, ten);

            int c = preparedStatement.executeUpdate();
            if(c > 0 ){
                System.out.println("update thanh cong!");
            } else {
                System.out.println("update that bai!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeConnection();
        }
    }
}
