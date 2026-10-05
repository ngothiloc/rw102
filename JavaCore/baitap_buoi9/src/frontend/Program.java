package frontend;

import backend.IQLAccount;
import backend.IQLDepartment;
import backend.QLAccount;
import backend.QLDepartment;

import java.util.Scanner;

public class Program {

    public static void main(String[] args){
        menu();
    }

    public static void menu(){
        Scanner sc = new Scanner(System.in);
        IQLAccount iqlAccount = new QLAccount();
        IQLDepartment iqlDepartment = new QLDepartment();

//        1. Hiển thị toàn bộ account
//        2. Tìm kiêm account theo username
//        3. Thêm mới account
//        4. Xóa acccount theo username
//        5. Update fullname theo username
//        1. Hiến thị department
//        2. Tìm kiếm department theo tên
//        3. Thêm mới department
//        4. Xóa department theo id
//        5. Update tên phòng ban theo id

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
                                iqlAccount.hienThiToanBoAccount();
                                break;
                            case "2":
                                iqlAccount.timKiemAccount_TheoUser();
                                break;
                            case "3":
                                iqlAccount.themMoiAcc();
                                break;
                            case "4":
                                iqlAccount.xoaAccTheoUsername();
                                break;
                            case "5" :
                                iqlAccount.updateFullname_theoUser();
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
                                iqlDepartment.hienThiDep();
                                break;
                            case "2":
                                iqlDepartment.timKiemDep_theoTen();
                                break;
                            case "3":
                                iqlDepartment.themMoiDep();
                                break;
                            case "4":
                                iqlDepartment.xoaDep_theoID();
                                break;
                            case "5" :
                                iqlDepartment.update_Ten_PhongBan_TheoID();
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
