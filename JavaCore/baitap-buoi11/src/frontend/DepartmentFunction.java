package frontend;

import backend.controller.AccountController;

import java.util.Scanner;

public class DepartmentFunction {

    private Scanner scanner;

    public DepartmentFunction() {
        this.scanner = new Scanner(System.in);
    }

    public void menu() {
        while (true) {
            System.out.println("==== Mời bạn chọn chức năng ====");
            System.out.println("1. Hiển thị toàn bộ department");
            System.out.println("2. Thêm department");
            System.out.println("3. Xóa department theo id");
            System.out.println("4. Update department theo id");
            System.out.println("5. Thoát");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    this.findAll();
                    break;
                case "2":
                    this.create();
                    break;
                case "3":
                    this.delete();
                    break;
                case "4":
                    this.update();
                    break;
                case "5":
                    return;
//                    System.exit(0);
                default:
                    System.out.println("Chọn sai! Chọn lại!");
            }
        }
    }

    private void update() {
        System.out.println("==== CHỨC NĂNG UPDATE DEPARTMENT ====");
    }

    private void delete() {
        System.out.println("==== CHỨC NĂNG XÓA DEPARTMENT ====");
    }

    private void create() {
        System.out.println("====CHỨC NĂNG TẠO MỚI DEPARTMENT ====");
    }

    private void findAll() {
        System.out.println("==== HIỂN THỊ TOÀN BỘ DEPARTMENT ====");

    }
}
