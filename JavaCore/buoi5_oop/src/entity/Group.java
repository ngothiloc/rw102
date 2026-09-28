package entity;

import java.time.LocalDate;

public class Group {

    public int id;
    public String name;
    LocalDate createDate;

    // Constructor không tham số
    public Group() {
    }

    // Constructor đầy đủ tham số
    public Group(int id, String name, LocalDate createDate) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
    }

}