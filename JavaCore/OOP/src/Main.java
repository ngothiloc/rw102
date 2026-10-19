//“OOP là phương pháp lập trình xây dựng chương trình dựa trên các đối tượng,
// trong đó mỗi đối tượng chứa dữ liệu và các phương thức xử lý dữ liệu đó.”

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
         //VD: cha họ Nguyên thì con cũng có toàn bộ quyền lợi của họ Nguyên
        //Giảm code, trùng lặp

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
        // contructor dung overload nhieu nhat

        //OVERRIDE (ghi đè): trong quan hệ kế thừa, Class con sửa lại
             // phương thức (giữ nguyên tên và tham số) của class cha thì de coi là ghi đè
            // subclass

    //- trừu tượng: che dấu đi quá tình xử lý bên trong của 1 hành động.
        //VD: Lớp đang học qua zoom, khi mình nói thì mọi người nghe được, khi mọi người nói thì mình nghe được
        // nhưng mình ko thể biết được zoom hoạt động như nào để truyền được âm thanh
        // điều khiển: ấn tăng âm lượng thì TV sẽ nói to hơn và ngược lại (ko bt được cách TV và điều khiển hoạt động như nào)

    //Phương thức trừu tượng (abstract method) là phương thức chỉ khai báo, không có phần thân, để lớp con tự triển khai.

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

//instanceof: trong Java thì nó dùng để kiểm tra một object có thuộc một kiểu/class/interface nào đó hay không.

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

//nguyên thuỷ: int, double, char, boolean -- so sánh giá trị ==
//Object: String, Integer, Student -- so sánh giá trị .equals()

// Statement: dùng để thực thi câu SQL cố định, không có tham số.

// PreparedStatement: dùng để thực thi câu SQL có tham số (?),
    // truyền dữ liệu động vào câu SQL, an toàn và hạn chế SQL Injection.

//static khi an run thì sẽ được cấp phát bọ nhớ luôn còn không có static thì sẽ phải khởi tạo rồi mới hiển thị
//-> khi nào dùng đi dùng lại nhiều thì nên dùng static còn nếu không thì sẽ gây lãng phí tài nguyên
// co static thi se khong phai khoi tao oject nua | con ko co thi se can phai khoi tao oject
//-- các biến hàm có STATIC thi thuoc ve class (di theo class)/ khong co static thi thuoc ve object

//finally: cụm cuối cùng thực hiện trong "try / catch "
//final(cuối cùng):"
        // biến final: không thể sửa giá trị được
        // -- khi nào dùng ?? Dùng với các giá trị mà không muốn người dùng sửa
        // phương thức final : không thể ghi đè được
        // class final: không thể kế thừa được nữa

//RUN: chạy 1 mạch đến khi có kết quả
//DEBUG: sẽ dừng ở những vị trí đạt breakpoint(vi trí code sẽ dừng khi chạy qua), stepover(code chạy qua từng row), stepinto(code sẽ chạy vào hàm mà code đang dừng)

//executeUpdate la co thay doi du lieu trong data tra
//    int = số dòng bị ảnh hưởng
//executeQuery la ko thay doi du lieu trong db
//    tra ve ResultSet

//* Dùng for khi:
//        * Cần vị trí/index i.
//        * Muốn lặp từ vị trí này đến vị trí khác.
//        * Muốn bỏ qua phần tử hoặc lặp ngược.
//* Dùng for-each khi:
    //    * Chỉ cần duyệt lần lượt tất cả phần tử.
    //    * Không quan tâm vị trí.
    //    * Thường dùng để hiển thị List, ví dụ List<Account>.

//* while: kiểm tra điều kiện trước → có thể không chạy lần nào.
//        * while → dùng khi phải kiểm tra điều kiện trước rồi mới chạy. Có thể không chạy lần nào.
//* do-while: chạy trước, kiểm tra điều kiện sau → luôn chạy ít nhất 1 lần.
//        * do-while → dùng khi muốn code chắc chắn chạy ít nhất 1 lần, sau đó mới kiểm tra có lặp tiếp không.

//for i là biết trước số lần lặp
//while là chưa biết trước được số lần  (lặp lại cho đến khi đúng thì thôi)

//break: Thoát khỏi vòng lặp ngay
//continue: Bỏ qua lần lặp hiện tại, sang lần tiếp theo
//return: Thoát khỏi cả method, có thể trả về giá trị

//* this → chỉ đến đối tượng/lớp hiện tại.
//* super → chỉ đến lớp cha.

//Constructor (hàm khởi tạo) trong Java dùng để khởi tạo đối tượng và gán giá trị ban đầu cho thuộc tính.

//exception (ngoại lệ, 1 loại lỗi): trong rất nhiều trường hợp đúng thì có 1 vài trường hợp sai - xảy ra lỗi
//-- có 2 loại:
//        -- checked: có thể biết trước được IDE sẽ cảnh báo phải thêm handle exception
//        -- VD: getConnection() của thư viện MySQLConnector có throws Exception nên muốn sử dụng thì phải handle exception
//                -- throws Exceptione
//                -- Try Catch
//        -- unchecked: không biết trước được IDE không thể biết trước được lỗi sẽ xảy ra
//                    Project vẫn chạy được mà không hề có cảnh báo trước
//
//linkedList: các phần tử được gọi là từng node
//        - node 3 thành phần : prev: địa chỉ node đứng trước - item: giá trị của object dó - next: địa chỉ node đứng trước

// Set không lưu được giá trị cùng nhau
// List lưu được giá trị cùng nhau

//List : get(), size(), remove(xoá 1 phần tử theo index), removeAll(xoá nhiều phần tử)

//Stack: pop(xoa top), push(them moi), peek(xem phan tu dau tien), isFull(ktra xem full stack chua), isEmpty(ktra xem co rong ko)

//queue (hàng đợi): enqueue(thêm vào hàng đợi), dequeue(xoá khỏi hàng đợi), peek(), isFull(ktra xem full queue chua), isEmpty(ktra xem co rong ko)

//map được quản lý bởi Key và Value

//equals và ==: equals là so sánh giá trị của object
//        == là ó sánh vị trí ô nhớ

//final dùng với static để khai báo hằng số



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