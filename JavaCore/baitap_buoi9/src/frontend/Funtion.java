package frontend;
import backend.controller.AccountController;
import backend.controller.DepartmentController;
import entity.Account;
import entity.Department;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Funtion {
    private AccountController accountController;
    private DepartmentController departmentController;

    Scanner sc = new Scanner(System.in);

    public Funtion(){
        this.accountController = new AccountController();
        this.departmentController = new DepartmentController();
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
//        4. Xóa acccount theo username
    public void xoaAccTheoUsername(){
        System.out.println("==== XOA ACC THEO USERNAME ====");
        System.out.print("Nhap username can xoa: ");
        String username = sc.nextLine();

        boolean check = accountController.xoaAccTheoUsername(username);

        if (check){
            System.out.println("Xoa acc thanh cong !!!");
        } else {
            System.out.println("Xoa acc loi !!!");
        }
    }
//        5. Update fullname theo username

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
