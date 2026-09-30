package entity;

public class Sach extends TaiLieu{
    private String tenTacGia;
    private int soTrang;

    public Sach(){
    }

    public Sach(int maTaiLieu, String tenNXB, int soBanPhatHanh, LoaiTaiLieu loaiTaiLieu, String tenTacGia, int soTrang) {
        super(maTaiLieu, tenNXB, soBanPhatHanh, loaiTaiLieu);
        this.tenTacGia = tenTacGia;
        this.soTrang = soTrang;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public int getSoTrang() {
        return soTrang;
    }

    public void setSoTrang(int soTrang) {
        this.soTrang = soTrang;
    }
}
