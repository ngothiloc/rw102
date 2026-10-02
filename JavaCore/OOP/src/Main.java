
//chuyến các đối tượng thực tế thành các class trong code đế dê quản lý
//1 đối tượng 2 thành phần : thuộc tính, phương thức
//các đặc điểm của đối tượng tên, địa chỉ, tài khoản, email các hành động của đôi tượng an, uong
//4 tính chất
    //- đóng gói: các class khác nhau ko thế truy cập trực tiếp tới các thuộc tính của
        //class khác mà chỉ có thể truy cập gián tiếp thông qua getter, setter.
        //thuộc tính: private, muốn xem hoặc sửa thuộc tính đó thì phải dùng getter, setter
        //VD: A có ip18prm, B muốn dùng thì phải xin phép A rồi mới do dùng.
        //nếu ko xin phép mà lấy dùng thì sẽ bị gán cho tội ăn cắp.

    //- kế thừa: class con kê thừa toàn bộ phương thức t thuộc tính của class cha
         //VD: cha họ Nguyên thì con cũng có toàn bộ quyền lợi của họ Nguyên Giảm code, trùng lặp
        // this là gọi trong class
        // super là gọi lại thông tin ở class cha

    //- đa hình: cùng 1 phương thức, các class khác nhau sẽ thực hiện theo từng cách khác nhau
    //        VD: cùng 1 hành động kêu ()
                //con Chó: gâu gâu
                //con mèo: meow meow
                //con gà: Ò Ó O
    //tính đa hình dc thể hiện qua overload và override
        //OVERLOAD (ghi chồng lên nhau): trong 1 class các phương thức cùng tên
        //        , khác tham số thì do coi là overloading
        //OVERRIDE (ghi đè): trong quan hệ kế thừa, Class con sửa lại
        // phương thức (giữ nguyên tên và tham số) của class cha thì de coi là ghi đè

    //- trừu tượng: che dấu đi quá tình xử lý bên trong của 1 hành động.
        //VD: Lớp đang học qua zoom, khi mình nói thì mọi người nghe được, khi mọi người nói thì mình nghe được
        // nhưng mình ko thể biết được zoom hoạt động như nào để truyền được âm thanh
        // điều khiển: ấn tăng âm lượng thì TV sẽ nói to hơn và ngược lại (ko bt được cách TV và điều khiển hoạt động như nào)
    // tình trừu tượng được thể hiện qua "abstract class" và "interface"
        // abstract class: về cơ bản vẫn là 1 class (có phương thức và thuộc tính)
//            -- sẽ có thêm phương thức trừu tượng (phương thức abstract)
//                + là phương thức "ko có" phần thân
//                + Phần thân sẽ được triển khai ở class con kế thừa
//                + Class và abstract class ko hỡ trợ đa kế thừa
//             - Class nào kế thừa thì mặc định phải ghi đè phương thức trừu tượng đó
       //  interface: ko phải là class (ko có phương thức và thuộc tính)
//                 - chỉ có phương thức trừu tượng
//                 - Class nào kế thừa (triển khai) thì mặc định phải ghi đè phương thức trừu tượng đó
//                 - Hỗ trợ đa kế thừa


//???
//Vì sao interface hỗ trợ đa kế thừa còn abstract class và class lại ko ?
//Vì class và abstract class phải khai báo thuộc tính và phương thức ví dụ class A và B có cùng giá trị id. nhưng kiểu dữ liệu khác nhau
//        nên sẽ bị lỗi còn interface sẽ ko phải khai báo nên ko bị lỗi và hỗ trợ đa kế thừa

//??? list và array
//Array thì sẽ bị giời hạn sẽ theo kiểu
//private CanBo[] canbos = new CanBo[1000]
//        private List<CanBo> canBoList = new ArayList<>();

// 2 loai datatype: nguyen thuy (int, float, ...),
// nguyen thuy luu ở bộ nhớ stack
//object lưu ở bộ nhớ heap




// Statement : ho tro cai sql tinh nhu select
// PreparedStatement : Ho tro cau sql dong co tham so nhu where, like,...

public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
    }
}