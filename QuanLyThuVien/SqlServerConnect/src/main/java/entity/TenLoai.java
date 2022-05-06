package entity;

public class TenLoai extends NhaSanXuatSach{


    private String tenLoai;

    public TenLoai(String maSach, String tenSach, String tenNXB,String tenLoai) {
        super(maSach, tenSach, tenNXB);
        this.tenLoai = tenLoai;
    }

    public TenLoai() {

    }


    @Override
    public String getTenSach() {
        return super.getTenSach();
    }

    @Override
    public void setTenSach(String tenSach) {
        super.setTenSach(tenSach);
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String maLoai) {
        this.tenLoai = maLoai;
    }
}
