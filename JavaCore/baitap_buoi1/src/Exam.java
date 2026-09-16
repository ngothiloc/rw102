import java.sql.Time;
import java.time.LocalDate;

public class Exam {
    int id;
    int code;
    String title;
    CategoryQuestion category;
    int duration;
    Account creator;
    LocalDate createDate;
}
