package entity;

public class ThongTinTacGia{

    private String maSach;
    private String tenSach;
    private String tenNXB;
    private String tacGia;
    private String butDanh;

    public ThongTinTacGia(){}

    public ThongTinTacGia(String maSach, String tenSach, String tenNXB, String tacGia, String butDanh) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tenNXB = tenNXB;
        this.tacGia = tacGia;
        this.butDanh = butDanh;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTenNXB() {
        return tenNXB;
    }

    public void setTenNXB(String tenNXB) {
        this.tenNXB = tenNXB;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getButDanh() {
        return butDanh;
    }

    public void setButDanh(String butDanh) {
        this.butDanh = butDanh;
    }
}
