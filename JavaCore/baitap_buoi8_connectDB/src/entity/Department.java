package entity;

public class Department {
    int id;
    String deparrmentName;

    public Department(){
    }

    public Department(String deparrmentName) {
        this.deparrmentName = deparrmentName;
    }

    public Department(int id, String deparrmentName) {
        this.id = id;
        this.deparrmentName = deparrmentName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeparrmentName() {
        return deparrmentName;
    }

    public void setDeparrmentName(String deparrmentName) {
        this.deparrmentName = deparrmentName;
    }
}
