package entity;

public class Position {
    private int id;
    private String positionName;

    public Position(){
    }

    public Position(String positionName) {
        this.positionName = positionName;
    }

    public Position(int id, String positionName) {
        this.id = id;
        this.positionName = positionName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
}
