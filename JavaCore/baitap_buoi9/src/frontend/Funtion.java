package frontend;
import backend.controller.AccountController;
import backend.controller.DepartmentController;
import backend.controller.PositionController;
import entity.Account;
import entity.Department;
import entity.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Funtion {
    private AccountController accountController;
    private DepartmentController departmentController;
    private PositionController positionController;

    Scanner sc = new Scanner(System.in);

    public Funtion(){
        this.accountController = new AccountController();
        this.departmentController = new DepartmentController();
        this.positionController = new PositionController();
    }

//    ======= Account =======
//        1. Hiển thị toàn bộ account
    public void hienThiToanBo(){
        List<Account> accountList = accountController.hienThiToanBo();

        System.out.println("=== HIEN THI TOAN BO ACCOUNT ===");
        System.out.println("+--------------------+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+");
        System.out.printf("|%20s|%35s|%35s|%35s|%35s|%35s|\n", "AccountID", "Email", "Username", "Full Name", "Department Name", "Position Name");
        System.out.println("+--------------------+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+");
        for (Account acc : accountList) {
            System.out.printf("|%20s|%35s|%35s|%35s|%35s|%35s|\n", acc.getId(), acc.getEmail(), acc.getUsername(), acc.getFullName(), acc.getDepartment().getDeparrmentName(), acc.getPosition().getPositionName());
        }
        System.out.println("+--------------------+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+");
    }
//        2. Tìm kiêm account theo username
    public void timKiemAccount_TheoUser(){
        System.out.println("=== TIM KIEM ACCOUNT - THEO TEN ===");
        System.out.print("Nhap ten can tim kiem: ");
        String ten = sc.nextLine();
        List<Account> accountList = accountController.timKiemAccount_TheoUser(ten);

        System.out.println("=== HIEN THI DEPARTMENT ===");
        System.out.println("+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+");
        System.out.printf("|%35s|%35s|%35s|%35s|%35s|%35s|\n", "AccountID", "Email", "Username", "Full Name", "Department Name", "Position Name");
        System.out.println("+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+");
        for (Account acc : accountList) {
            System.out.printf("|%35s|%35s|%35s|%35s|%35s|%35s|\n", acc.getId(), acc.getEmail(), acc.getUsername(), acc.getFullName(), acc.getDepartment(), acc.getPosition());
        }
        System.out.println("+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+-----------------------------------+");
    }
//        3. Thêm mới account
    public void themMoiAcc() {
        System.out.println("==== THEM MOI CAN BO ====");

        System.out.print("Nhap email: ");
        String email;
        while (true){
            email = sc.nextLine();
            if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                System.err.println("Email khong dung dinh dang! Vui long nhap lai.");
                continue;
            }
            boolean check = accountController.existByEmail(email);
            if (check) {
                System.err.println("email nay da ton tai !!! Chon email khac");
                continue;
            } break;
        }

        System.out.print("Nhap username: ");
        String username;
        while (true){
            username = sc.nextLine();
            if (username.length() < 5 || username.length() > 50){
                System.err.println("ho ten phai tu 5 - 50 ky tu nhap lai!!!");
                continue;
            }
            boolean check = accountController.existByUsername(username);
            if (check) {
                System.err.println("username nay da ton tai !!! Chon username khac");
                continue;
            } break;
        }

        System.out.print("Nhap ho va ten: ");
        String fullname;
        while (true) {
            fullname = sc.nextLine();
            if (fullname.length() < 5 || fullname.length() > 50){
                System.err.println("ho ten phai tu 5 - 50 ky tu nhap lai!!!");
                continue;
            } break;
        }

        System.out.println("---Chon Department---");
        departmentController.hienThiDep();
        List<Department> departmentList = departmentController.hienThiDep();
        System.out.println("+----------+---------------+");
        System.out.printf("|%10s|%15s|\n", "DepID", "DepName");
        System.out.println("+----------+---------------+");
        for (Department dep : departmentList) {
            System.out.printf("|%10d|%15s|\n", dep.getId(), dep.getDeparrmentName());
        }
        System.out.println("+----------+---------------+");
        while (true) {
            System.out.println("Nhap department: ");
            int depID;
            if (!sc.hasNextInt()) {
                System.out.println("Vui long nhap so !!!");
                continue;
            }

            depID = sc.nextInt();
            sc.nextLine();
            boolean check = false;

            for (Department dep : departmentList) {
                if (dep.getId() == depID) {
                    check = true;
                    break;
                }
            }
            if (check) {
                break;
            } else {
                System.out.println("DepID khong ton tai !!!");
            }
        }

        System.out.println("---Chon Position---");
        List<Position> positionList = positionController.hienThiPos();
        System.out.println("+----------+---------------+");
        System.out.printf("|%10s|%15s|\n", "PosID", "PosName");
        System.out.println("+----------+---------------+");
        for (Position pos : positionList) {
            System.out.printf("|%10d|%15s|\n", pos.getId(), pos.getPositionName());
        }
        System.out.println("+----------+---------------+");
        while (true) {
            System.out.println("Nhap position: ");
            int posID;
            if (!sc.hasNextInt()) {
                System.out.println("Vui long nhap so !!!");
                continue;
            }

            posID = sc.nextInt();
            sc.nextLine();
            boolean check = false;

            for (Position pos : positionList) {
                if (pos.getId() == posID) {
                    check = true;
                    break;
                }
            }
            if (check) {
                break;
            } else {
                System.out.println("DepID khong ton tai !!!");
            }
        }

    }
//        4. Xóa acccount theo username
    public void xoaAccTheoUsername(){
        System.out.println("==== XOA ACC THEO USERNAME ====");
        System.out.print("Nhap username can xoa: ");
        String username;
        while (true){
            username = sc.nextLine();
            boolean check = accountController.existByUsername(username);
            if (!check) {
                System.err.println("username nay khong ton tai");
                continue;
            } break;
        }

        boolean check = accountController.xoaAccTheoUsername(username);

        if (check){
            System.out.println("Xoa acc thanh cong !!!");
        } else {
            System.out.println("Xoa acc loi !!!");
        }
    }
//        5. Update fullname theo username
    public void updateFullname_theoUser(){
        System.out.println("==== CAP NHAT THEO USERNAME ====");
        System.out.print("Nhap username can cap nhat: ");
        String username;
        while (true){
            username = sc.nextLine();
            boolean check = accountController.existByUsername(username);
            if (!check) {
                System.err.println("username nay khong ton tai");
                continue;
            } break;
        }

        System.out.print("Nhap fullname can update: ");
        String fullname;
        while (true) {
            fullname = sc.nextLine();
            if (fullname.length() < 5 || fullname.length() > 50){
                System.err.println("ho ten phai tu 5 - 50 ky tu nhap lai!!!");
                continue;
            } break;
        }

        boolean check = accountController.updateFullname_theoUser(username, fullname);

        if (check) {
            System.out.println("cap nhat thanh cong !!!");
        } else {
            System.out.println("Cap nhat that bai !!!");
        }
    }
//    6. Xóa acccount theo id
public void xoaAccTheoId(){
    System.out.println("==== XOA ACC THEO ID ====");
    System.out.print("Nhap id can xoa: ");
    String accId;
    while (true){
        accId = sc.nextLine();
        boolean check = accountController.existById(accId);
        if (!check) {
            System.err.println("ID account nay khong ton tai");
            continue;
        } break;
    }

    boolean check = accountController.xoaAccTheoId(accId);

    if (check){
        System.out.println("Xoa acc thanh cong !!!");
    } else {
        System.out.println("Xoa acc loi !!!");
    }
}
//    7. Update fullname theo id
public void updateFullname_theoAccId(){
    System.out.println("==== CAP NHAT THEO USERNAME ====");
    System.out.print("Nhap account id can cap nhat: ");
    String accId;
    while (true){
        accId = sc.nextLine();
        boolean check = accountController.existById(accId);
        if (!check) {
            System.err.println("username nay khong ton tai");
            continue;
        } break;
    }

    System.out.print("Nhap fullname can update: ");
    String fullname;
    while (true) {
        fullname = sc.nextLine();
        if (fullname.length() < 5 || fullname.length() > 50){
            System.err.println("ho ten phai tu 5 - 50 ky tu nhap lai!!!");
            continue;
        } break;
    }

    boolean check = accountController.updateFullname_theoAccId(accId, fullname);

    if (check) {
        System.out.println("cap nhat thanh cong !!!");
    } else {
        System.out.println("Cap nhat that bai !!!");
    }
}


//    ======= Department =======
//        1. Hiến thị department
    public void hienThiDep(){
        List<Department> departmentList = departmentController.hienThiDep();

        System.out.println("+----------+---------------+");
        System.out.printf("|%10s|%15s|\n", "DepID", "DepName");
        System.out.println("+----------+---------------+");
        for(Department dep : departmentList) {
            System.out.printf("|%10d|%15s|\n", dep.getId(), dep.getDeparrmentName());
        }
        System.out.println("+----------+---------------+");
    }
//        2. Tìm kiếm department theo tên
    public void timKiemDep_theoTen(){
        System.out.println("=== TIM KIEM DEPARTMENT - THEO TEN ===");
        System.out.print("Nhap ten dep: ");
        String depName = sc.nextLine();

        List<Department> departmentList = departmentController.timKiemDep_theoTen(depName);

        System.out.println("+----------+---------------+");
        System.out.printf("|%10s|%15s|\n", "DepID", "DepName");
        System.out.println("+----------+---------------+");
        for(Department dep : departmentList) {
            System.out.printf("|%10d|%15s|\n", dep.getId(), dep.getDeparrmentName());
        }
        System.out.println("+----------+---------------+");

    }
//        3. Thêm mới department
//        4. Xóa department theo id
    public void xoaDep_theoID(){
        System.out.println("==== XOA DEP THEO ID ====");
        System.out.print("Nhap ID can xoa: ");
        int depID = sc.nextInt();
        sc.nextLine();

       boolean check = departmentController.xoaDep_theoID(depID);

       if (check) {
           System.out.println("Xoa thanh cong !!!");
        } else {
           System.out.println("Xoa that bai !!!");
       }
    }
//        5. Update tên phòng ban theo id
    public void update_Ten_PhongBan_TheoID(){
        System.out.println("==== CAP NHAT THEO ID ====");
        System.out.print("Nhap ID phong ban can cap nhat: ");
        int depID = sc.nextInt();
        sc.nextLine();

        System.out.print("Nhap ten phong ban can update: ");
        String depName = sc.nextLine();

        boolean check = departmentController.update_Ten_PhongBan_TheoID(depID, depName);

        if (check) {
            System.out.println("Cap nhat thanh cong !!!");
        } else {
            System.out.println("Xoa thanh cong!!!");
        }
    }

    public void menu(){
        while (true) {
            System.out.println("");
            System.out.println("=== MOI BAN CHON CHUC NANG ===");
            System.out.println("1. Account");
            System.out.println("2. Department");
            System.out.println("3. Thoát chương trình");
            String choice = sc.nextLine();
            switch (choice){
                case "1":
                    boolean accountMenu = true;

                    while(accountMenu) {
                        System.out.println("");
                        System.out.println("=== MOI BAN CHON CHUC NANG ===");
                        System.out.println("1. Hiển thị toàn bộ account");
                        System.out.println("2. Tìm kiêm account theo username");
                        System.out.println("3. Thêm mới account");
                        System.out.println("4. Xóa acccount theo username");
                        System.out.println("5. Update fullname theo username");
                        System.out.println("6. Quay lại");
                        String choiceAccount = sc.nextLine();
                        switch (choiceAccount){
                            case "1":
                                this.hienThiToanBo();
                                break;
                            case "2":
                                this.timKiemAccount_TheoUser();
                                break;
                            case "3":
//                                iqlAccount.themMoiAcc();
                                break;
                            case "4":
                                this.xoaAccTheoUsername();
                                break;
                            case "5" :
//                                iqlAccount.updateFullname_theoUser();
                                break;
                            case "6":
                                accountMenu = false;
                                break;
                            default:
                                System.out.println("Nhap chua dung, nhap lai!!");
                        }
                    }
                    break;
                case "2":
                    boolean departmentMenu = true;

                    while(departmentMenu) {
                        System.out.println("");
                        System.out.println("=== MOI BAN CHON CHUC NANG ===");
                        System.out.println("1. Hiến thị department");
                        System.out.println("2. Tìm kiếm department theo tên");
                        System.out.println("3. Thêm mới department");
                        System.out.println("4. Xóa department theo id");
                        System.out.println("5. Update fullname theo username");
                        System.out.println("6. Quay lại");
                        String choiceAccount = sc.nextLine();
                        switch (choiceAccount){
                            case "1":
                                this.hienThiDep();
                                break;
                            case "2":
                                this.timKiemDep_theoTen();
                                break;
                            case "3":
//                                iqlDepartment.themMoiDep();
                                break;
                            case "4":
                                this.xoaDep_theoID();
                                break;
                            case "5" :
//                                iqlDepartment.update_Ten_PhongBan_TheoID();
                                break;
                            case "6":
                                departmentMenu = false;
                                break;
                            default:
                                System.out.println("Nhap chua dung, nhap lai!!");
                        }
                    }
                    break;
                case "3":
                    System.out.println("Thoat");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Nhap chua dung, nhap lai!!");
            }
        }
    }
}
