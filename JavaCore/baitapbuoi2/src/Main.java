import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //department
        Department department1 = new Department();
        department1.id = 1;
        department1.name = "Sale";

        Department department2 = new Department();
        department2.id = 2;
        department2.name = "Marketing";

        Department department3 = new Department();
        department3.id = 3;
        department3.name = "Bảo vệ";

        //position
        Position position1 = new Position();
        position1.id = 1;
        position1.name = PositionName.DEV;

        Position position2 = new Position();
        position2.id = 2;
        position2.name = PositionName.TEST;

        Position position3 = new Position();
        position3.id = 3;
        position3.name = PositionName.SCRUM_MASTER;

        //group
        Group group1 = new Group();
        group1.id = 1;
        group1.name = "Java Fresher";

        Group group2 = new Group();
        group2.id = 2;
        group2.name = "C# Fresher";

        Group group3 = new Group();
        group3.id = 3;
        group3.name = "SQL Fresher";

        //account
        Account account1 = new Account();
        account1.id = 1;
        account1.email = "loc@gmail.com";
        account1.fullName = "Loc";
        account1.position = position1;
        account1.department = department2;
        account1.useName = "ngoloc";
        account1.createDate = LocalDate.of(2026, 1, 1);

        Account account2 = new Account();
        account2.id = 2;
        account2.email = "loc2@gmail.com";
        account2.fullName = "Loc2";
        account2.position = position1;
        account2.department = department1;
        account2.useName = "ngoloc2";
        account2.createDate = LocalDate.of(2026, 1, 1);

        //groupaccount
        GroupAccount groupAccount1 = new GroupAccount();
        groupAccount1.group = group1;
        groupAccount1.account = account1;
        groupAccount1.joinDate = LocalDate.now();

        GroupAccount groupAccount2 = new GroupAccount();
        groupAccount2.group = group2;
        groupAccount2.account = account1;
        groupAccount2.joinDate = LocalDate.now();

        GroupAccount groupAccount3 = new GroupAccount();
        groupAccount3.group = group3;
        groupAccount3.account = account1;
        groupAccount3.joinDate = LocalDate.now();

        GroupAccount[] groupAccounts = {
                groupAccount1,
                groupAccount2,
                groupAccount3,
        };

        System.out.println(position1.toString());


        //exam
//      Question 1:
//      Kiểm tra account thứ 2
//      Nếu không có phòng ban (tức là department == null) thì sẽ in ra text
//      "Nhân viên này chưa có phòng ban"
//      Nếu không thì sẽ in ra text "Phòng ban của nhân viên này là …"
        if (account2.department == null) {
            System.out.println("Nhân viên này chưa có phòng ban!");
        } else {
            System.out.println("Phong ban nhan vien nay la: ");
        }

//      Question 2:
//      Kiểm tra account thứ 2
//      Nếu không có group thì sẽ in ra text "Nhân viên này chưa có group"
//      Nếu có mặt trong 1 hoặc 2 group thì sẽ in ra text "Group của nhân viên này là Java Fresher, C# Fresher"
//      Nếu có mặt trong 3 Group thì sẽ in ra text "Nhân viên này là người quan trọng, tham gia nhiều group"
//      Nếu có mặt trong 4 group trở lên thì sẽ in ra text "Nhân viên này là người hóng chuyện, tham gia tất cả các group"
        int soGroup = 2;

        if (soGroup == 0) {
            System.out.println("Nhân viên này chưa có group");
        } else if (soGroup == 1) {
            System.out.println("Group của nhân viên này là "
                    + groupAccount1.group.name);
        } else if (soGroup == 2) {
            System.out.println("Group của nhân viên này là "
                    + groupAccount1.group.name + ", "
                    + groupAccount2.group.name);
        } else if (soGroup == 3) {
            System.out.println("Nhân viên này là người quan trọng, tham gia nhiều group");
        } else {
            System.out.println("Nhân viên này là người hóng chuyện, tham gia tất cả các group");
        }

//        Question 3:
//        Sử dụng toán tử ternary để làm Question 1
        System.out.println(account2.department == null ? "Nhân viên này chưa có phòng ban" : "Phòng ban của nhân viên này là " + account2.department.name);

//        Question 4:
//        Sử dụng toán tử ternary để làm yêu cầu sau:
//        Kiểm tra Position của account thứ 1
//        Nếu Position = Dev thì in ra text "Đây là Developer"
//        Nếu không phải thì in ra text "Người này không phải là Developer"
        System.out.println("=======Q3========");
        System.out.println(account2.position != null && account2.position.name == PositionName.DEV ? "Đây là Developer" : "Người này không phải là Developer");

        //SWITCH CASE
//        Question 5:
//        Lấy ra số lượng account trong nhóm thứ 1 và in ra theo format sau: Nếu số lượng account = 1 thì in ra "Nhóm có một thành viên"
//        Nếu số lượng account = 2 thì in ra "Nhóm có hai thành viên"
//        Nếu số lượng account = 3 thì in ra "Nhóm có ba thành viên"
//        Còn lại in ra "Nhóm có nhiều thành viên"
        int soAccount = 0;
        for (int i = 0; i < groupAccounts.length; i++) {
            if (groupAccounts[i].group == group1) {
                soAccount++;
            }
        }
        switch (soAccount) {
            case 1:
                System.out.println("Nhóm có một thành viên");
                break;
            case 2:
                System.out.println("Nhóm có hai thành viên");
                break;
            case 3:
                System.out.println("Nhóm có ba thành viên");
                break;
            default:
                System.out.println("Nhóm có nhiều thành viên");
                break;
        }

        // Question 6
        int soGroupQ6 = 0;
        String tenGroup = "";

        for (int i = 0; i < groupAccounts.length; i++) {
            if (groupAccounts[i].account == account2) {
                soGroupQ6++;
                tenGroup = tenGroup + groupAccounts[i].group.name + ", ";
            }
        }
        switch (soGroupQ6) {
            case 0:
                System.out.println("Nhân viên này chưa có group");
                break;
            case 1:
            case 2:
                tenGroup = tenGroup.substring(0, tenGroup.length() - 2);
                System.out.println("Group của nhân viên này là " + tenGroup);
                break;
            case 3:
                System.out.println("Nhân viên này là người quan trọng, tham gia nhiều group");
                break;
            default:
                System.out.println("Nhân viên này là người hóng chuyện, tham gia tất cả các group");
                break;
        }

        // Question 7
        switch (account1.position.name) {
            case DEV:
                System.out.println("Đây là Developer");
                break;
            default:
                System.out.println("Người này không phải là Developer");
                break;
        }

        // Question 8
        Account[] accounts = {account1, account2};
        for (Account account : accounts) {
            System.out.println("Email: " + account.email);
            System.out.println("FullName: " + account.fullName);
            System.out.println("Department: " + account.department.name);
            System.out.println("--------------------");
        }

//        Question 9:
//        In ra thông tin các phòng ban bao gồm: id và name

        Department[] departments = {department1, department2, department3};
        for (Department department : departments) {
            System.out.println("ID: " + department.id);
            System.out.println("Name: " + department.name);
            System.out.println("--------------------");
        }

//        Question 11:
//        In ra thông tin các phòng ban bao gồm: id và name theo định dạng sau:
//        Thông tin department thứ 1 là:
//        Id: 1
//        Name: Sale
//        Thông tin department thứ 2 là:
//        Id: 2
//        Name: Marketing
        for (int i = 0; i < departments.length; i++) {
            System.out.println("Thông tin department thứ " + (i + 1) + " là:");
            System.out.println("Id: " + departments[i].id);
            System.out.println("Name: " + departments[i].name);
        }

//        Quesstion 12:
//        Chỉ in ra thông tin 2 department đầu tiên theo định dạng như Question 10
// Question 12
        for (int i = 0; i < 2; i++) {
            System.out.println("Thông tin department thứ " + (i + 1) + " là:");
            System.out.println("Id: " + departments[i].id);
            System.out.println("Name: " + departments[i].name);
        }
        
//       Question 13:
//       In ra thông tin tất cả các account ngoại trừ account thứ 2
        for (int i = 0; i < accounts.length; i++) {
            if (i != 1) {
                System.out.println("Email: " + accounts[i].email);
                System.out.println("Full name: " + accounts[i].fullName);
            }
        }

//        Question 14:
//        In ra thông tin tất cả các account có id < 4
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].id < 4) {
                System.out.println("ID: " + accounts[i].id);
                System.out.println("Email: " + accounts[i].email);
                System.out.println("Full name: " + accounts[i].fullName);
            }
        }

//        Question 15:
//        In ra các số chẵn nhỏ hơn hoặc bằng 20
        for (int i = 2; i <= 20; i += 2) {
            System.out.println(i);
        }

        System.out.println("+-----+--------------------+");
        System.out.printf("|%5s|%20s|\n", "ID", "Department Name");
        System.out.println("+-----+--------------------+");
        for (Department department : departments) {
            System.out.printf("|%5d|%20s|\n", department.id, department.name);
        }
        System.out.println("+-----+--------------------+");
    }
}