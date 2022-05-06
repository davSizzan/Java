package entity;

public class ChiTietMuon {

    private String maSinhVien;
    private String tenSinhVien;
    private String tenSach;
    private String tenLoai;

    public ChiTietMuon(){}

    public ChiTietMuon(String maSinhVien, String tenSinhVien, String tenSach, String tenLoai) {
        this.maSinhVien = maSinhVien;
        this.tenSinhVien = tenSinhVien;
        this.tenSach = tenSach;
        this.tenLoai = tenLoai;
    }

    public String getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public String getTenSinhVien() {
        return tenSinhVien;
    }

    public void setTenSinhVien(String tenSinhVien) {
        this.tenSinhVien = tenSinhVien;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
