import java.util.Scanner;

public class Ex4 {


    Scanner scanner = new Scanner(System.in);
//    Question 1:
//    Nhập một xâu kí tự, đếm số lượng các từ trong xâu kí tự đó (các từ có thể cách nhau bằng nhiều khoảng trắng );
    public void question1() {
        System.out.print("Nhập chuỗi: ");
        String s = scanner.nextLine();
        String[] words = s.trim().split("[space]+");
        System.out.println("Số lượng từ trong chuỗi là: " + words.length);
    }

//    Question 2:
//    Nhập hai xâu kí tự s1, s2 nối xâu kí tự s2 vào sau xâu s1;
    public void question2() {
        System.out.print("Nhập chuỗi s1: ");
        String s1 = scanner.nextLine();
        System.out.print("Nhập chuỗi s2: ");
        String s2 = scanner.nextLine();
        String result = s1 + s2;
        System.out.println("Chuỗi sau khi nối là: " + result);
    }

//    Question 3:
//    Viết chương trình để người dùng nhập vào tên và kiểm tra, nếu tên chữ viết hoa chữ cái đầu thì viết hoa lên.
    public void question3() {
        System.out.print("Nhập tên: ");
        String name = scanner.nextLine();
        String newName = name.substring(0, 1).toUpperCase() + name.substring(1);
        System.out.println("Tên sau khi viết hoa: " + newName);
    }

//    Question 4:
//    Viết chương trình để người dùng nhập vào tên in từng ký tự trong tên của người dùng ra
//    VD:
//    Người dùng nhập vào "Nam", hệ thống sẽ in ra
//        "Ký tự thứ 1 là: N"
//        "Ký tự thứ 1 là: A"
//        "Ký tự thứ 1 là: M"
    public void question4() {
        System.out.print("Nhập tên: ");
        String name = scanner.nextLine();
        for (int i = 0; i < name.length(); i++) {
            System.out.println("Ký tự thứ " + (i + 1) + " là: " + name.charAt(i));
        }
    }

//    Question 5:
//    Viết chương trình để người dùng nhập vào họ, sau đó yêu cầu người dùng nhập vào tên và hệ thống sẽ in ra họ và tên đầy đủ.
    public void question5() {
        System.out.print("Nhập họ: ");
        String ho = scanner.nextLine();
        System.out.print("Nhập tên: ");
        String ten = scanner.nextLine();
        String hoTen = ho + " " + ten;
        System.out.println("Họ và tên đầy đủ: " + hoTen);
    }

//    Question 6:
//    Viết chương trình yêu cầu người dùng nhập vào họ và tên đầy đủ và sau đó hệ thống sẽ tách ra họ, tên , tên đệm
//    VD:
//    Người dùng nhập vào "Nguyễn Văn Nam"
//    Hệ thống sẽ in ra
//            "Họ là: Nguyễn"
//            "Tên đệm là: Văn"
//            "Tên là: Nam"
//
//
//    Question 7:
//    Viết chương trình yêu cầu người dùng nhập vào họ và tên đầy đủ và chuẩn hóa họ và tên của họ như sau:
//    a) Xóa dấu cách ở đầu và cuối và giữa của chuỗi người dùng nhập vào
//    VD: Nếu người dùng nhập vào " nguyễn văn nam " thì sẽ chuẩn hóa thành "nguyễn văn   nam"
//    b) Viết hoa chữ cái mỗi từ của người dùng
//    VD: Nếu người dùng nhập vào " nguyễn văn nam " thì sẽ chuẩn hóa thành "Nguyễn Văn Nam"
//
//
//    Question 8:
//    In ra tất cả các group có chứa chữ "Java"
//
//
//    Question 9:
//    In ra tất cả các group "Java"
//
//
//    Question 10:
//    Kiểm tra 2 chuỗi có là đảo ngược của nhau hay không.
//    Nếu có xuất ra “OK” ngược lại “KO”.
//    Ví dụ “word” và “drow” là 2 chuỗi đảo ngược nhau.
//
//
//    Question 11: Count special Character
//    Tìm số lần xuất hiện ký tự "a" trong chuỗi
    public void question11() {
        System.out.print("Nhập chuỗi: ");
        String s = scanner.nextLine();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a') {
                count++;
            }
        }
        System.out.println("Số lần xuất hiện ký tự a là: " + count);
    }
//
//    Question 12: Reverse String
//    Đảo ngược chuỗi sử dụng vòng lặp
    public void question12() {
        System.out.print("Nhập chuỗi: ");
        String s = scanner.nextLine();
        String reverse = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            reverse = reverse + s.charAt(i);
        }
        System.out.println("Chuỗi đảo ngược là: " + reverse);
    }

//    Question 13:
//    String not contains digit
//    Kiểm tra một chuỗi có chứa chữ số hay không, nếu có in ra false ngược lại true.
//    Ví dụ:
//            "abc" => true
//            "1abc", "abc1", "123", "a1bc", null => false
//
//
//    Question 14: Replace character
//    Cho một chuỗi str, chuyển các ký tự được chỉ định sang một ký tự khác cho trước.
//    Ví dụ:
//            "VTI Academy" chuyển ký tự 'e' sang '*' kết quả " VTI Acad*my"
//
//
//    Question 15: Revert string by word
//    Đảo ngược các ký tự của chuỗi cách nhau bởi dấu cách mà không dùng thư viện.
//    Ví dụ: " I am developer " => "developer am I".
//    Các ký tự bên trong chỉ cách nhau đúng một dấu khoảng cách.
//    Gợi ý: Các bạn cần loại bỏ dấu cách ở đầu và cuối câu, thao tác cắt chuỗi theo dấu cách
//
//    Question 16:
//    Cho một chuỗi str và số nguyên n >= 0. Chia chuỗi str ra làm các phần bằng nhau với n    ký tự. Nếu chuỗi không chia được thì xuất ra màn hình “KO”.




}
