package backend;

import entity.CanBo;
import entity.GioiTinh;

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
    }

    @Override
    public void timKiemTheoTen() {
        System.out.println("==== TIM KIEM THEO TEN ====");
        System.out.println("Nhap ho ten can bo can tim: ");
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
    }
}
