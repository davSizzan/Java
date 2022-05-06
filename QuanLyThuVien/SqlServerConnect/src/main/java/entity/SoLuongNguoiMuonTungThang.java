package entity;

public class SoLuongNguoiMuonTungThang {

    private int thang;
    private int soLuong;

    public  SoLuongNguoiMuonTungThang(){

    }

    public SoLuongNguoiMuonTungThang(int thang, int soLuong) {
        this.thang = thang;
        this.soLuong = soLuong;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
