package frontend;
import entity.Group;

public class Main {
    public static void main(String[] args) {
        Group g1 = new Group();
        g1.id = 4;
        g1.name = "Group4";
        System.out.println(g1);

        Group g2 = new Group();
        g2.id = 5;
        g2.name = "Group1";
        System.out.println(g2);
    }
}