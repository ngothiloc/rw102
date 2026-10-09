package backend.repository.impl;

import backend.repository.ICanBoRepository;
import entity.*;
import utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CanBoRepositoryImpl implements ICanBoRepository {
    @Override
    public List<CanBo> findAll() {
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
        return canBoList;
    }

    @Override
    public List<CanBo> findByName(String ten) {
        List<CanBo> canBoList = new ArrayList<>();
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
        return canBoList;
    }

    @Override
    public boolean deleteByName(String ten) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "DELETE FROM can_bo WHERE ho_ten like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ten);

            int c = preparedStatement.executeUpdate();
//            if (c > 0) {
//                return true;
//            } else {
//                return false;
//            }
            return c > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeConnection();
        }
    }

    @Override
    public boolean save(CanBo canBo) {
        String column = null;
        String value = null;
        if (canBo instanceof CongNhan) {
            column = "bac";
            value = "'CN', " + ((CongNhan) canBo).getBac();
        } else if (canBo instanceof KySu) {
            column = "nganh";
            value = "'KS', " + ((KySu) canBo).getNganhDaoTao();
        } else {
            column = "cong_viec";
            value = "'NV', " + ((NhanVien) canBo).getCongViec();
        }

        String sql = String.format(
                "INSERT INTO can_bo (ho_ten, tuoi, gioi_tinh, dia_chi, loai, %s) VALUES (?, ?, ?, ?, %s)", column, value);
        try {
            Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, canBo.getHoTen());
            preparedStatement.setInt(2, canBo.getTuoi());
            preparedStatement.setString(3, canBo.getGioiTinh().name());
            preparedStatement.setString(4, canBo.getDiaChi());

            int c = preparedStatement.executeUpdate();

            if (c > 0) return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeConnection();
        }
        return false;
    }

    @Override
    public boolean existByName(String hoten) {
        try {
            // tạo kết nối đến Database
            Connection connection = JDBCUtils.getConnection();
            String sql = "select * from can_bo where ho_ten like ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, hoten);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {// next dc là có dữ liệu  -> tòn tại
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {// cụm này luôn thực hien cuối cùng
            JDBCUtils.closeConnection();
        }
        return false;
    }
}
