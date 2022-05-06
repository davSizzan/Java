package entity;

public class NhaSanXuatSach {

    private String maSach;
    private String tenSach;
    private String tenNXB;

    public NhaSanXuatSach(){};
    public NhaSanXuatSach(String maSach, String tenSach, String tenNXB) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tenNXB = tenNXB;
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
}
