package entity;

public class TapChi extends TaiLieu{
    private int soPhatHanh;
    private int thangPhatHanh;

    public TapChi(){
    }

    public TapChi(int maTaiLieu, String tenNXB, int soBanPhatHanh, LoaiTaiLieu loaiTaiLieu, int soPhatHanh) {
        super(maTaiLieu, tenNXB, soBanPhatHanh, loaiTaiLieu);
        this.soPhatHanh = soPhatHanh;
    }

    public int getSoPhatHanh() {
        return soPhatHanh;
    }

    public void setSoPhatHanh(int soPhatHanh) {
        this.soPhatHanh = soPhatHanh;
    }

    public int getThangPhatHanh() {
        return thangPhatHanh;
    }

    public void setThangPhatHanh(int thangPhatHanh) {
        this.thangPhatHanh = thangPhatHanh;
    }
}
