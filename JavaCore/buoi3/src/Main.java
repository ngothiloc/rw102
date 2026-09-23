import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //for -- su dung khi biet truoc duoc gioi han va so lan lap
        //while la su dung khi chua biet duoc so lan lap lai

        // nếu t+ đứng sau biến thì thứ tự thực hiện: gan roi moi tang
        // nếu ++ đứng trước biến thì thứ tự thực hiện: tang roi moi gan

        Scanner sc = new Scanner(System.in);
        System. out.println("Nhâp tuổi:");
        while (true) {
            if (sc.hasNextInt() == true) {
                int age = sc.nextInt();
                System.out.println("Tuổi vừa nhâp là: " + age);
                break;
            } else {
                System.out.println("Nhâp sai đinh dạng");
            }
           sc.nextLine();
        }
    }
}