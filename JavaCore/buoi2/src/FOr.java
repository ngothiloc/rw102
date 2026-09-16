public class FOr {
    public static void main(String[] args) {
        String[] hocSinhs = new String[]{"A", "B", "C", "D"};
        //for i xu ly theo vi tri cua ptu trong ds
        for (int i = 0; i < hocSinhs.length; i++) {
            System.out.println(hocSinhs[i]);
        }

        for (int i = 1; i < 10; i++) {
            System.out.println(i);
        }
        //in theo object
        // gan lan luot cac ptu trong mang voi 1 object, sau khi xu ly xong thi voi phan tu tiep theo
        // gan cho den khi het ds thi moi dung vong for
        for (String hs : hocSinhs) {
            System.out.println(hs);
        }
    }
}
