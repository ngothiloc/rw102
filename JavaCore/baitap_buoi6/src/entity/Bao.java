package entity;
import java.time.LocalDate;

public class Bao extends TaiLieu{
    private int ngayPhatHanh;

    public Bao(){}

    public Bao(int maTaiLieu, String tenNXB, int soBanPhatHanh, LoaiTaiLieu loaiTaiLieu, int ngayPhatHanh) {
        super(maTaiLieu, tenNXB, soBanPhatHanh, loaiTaiLieu);
        this.ngayPhatHanh = ngayPhatHanh;
    }

    public int getNgayPhatHanh() {
        return ngayPhatHanh;
    }

    public void setNgayPhatHanh(int ngayPhatHanh) {
        this.ngayPhatHanh = ngayPhatHanh;
    }
}
