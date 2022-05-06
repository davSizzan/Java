package btl_backend.controller;

import btl_backend.service.IBookService;
import entity.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class BookController {

    IBookService iBookService;

    List<NhaSanXuatSach> sanXuatSachList;
    List<ThongTinTacGia> thongTinTacGiaList;
    List<MaThe> maTheLst;
    List<ChiTietMuon> chiTietMuonList;
    List<SoLuongNguoiMuonTungThang> lstNum;
    List<TenLoai> maLoaiList;
    public BookController(IBookService iBookService) {
        this.iBookService = iBookService;
    }

    public void getListNXB() throws SQLException, IOException, ClassNotFoundException {
        sanXuatSachList = iBookService.getListNSX();
        if (sanXuatSachList.isEmpty()){
            System.out.println("Khong ton tai ban ghi nao!");

        }else {
            for (NhaSanXuatSach elem : sanXuatSachList){
                System.out.println("MaSach: " + elem.getMaSach() + "-" + "TenSach: " + elem.getTenSach() + "-" +"TenNXB: "+elem.getTenNXB());
            }
        }
    }

    public void getListTG() throws SQLException, IOException, ClassNotFoundException {
        thongTinTacGiaList = iBookService.getListTG();
        if (thongTinTacGiaList.isEmpty()){
            System.out.println("Khong ton tai ban ghi nao!");
        }else {
            for(ThongTinTacGia elem : thongTinTacGiaList){
                System.out.println("Ten Sach: " + elem.getTenSach() + "-" + "Ten Tac Gia: " + elem.getTacGia());
            }
        }
    }

    public void getListCTM(int mathe) throws SQLException, IOException, ClassNotFoundException {
        try{
            maTheLst = iBookService.getListMT();
            if (maTheLst.isEmpty()){
                System.out.println("Khong co ma the nao");
            }else {
                for(MaThe elem : maTheLst){
                    if (elem.getMaThe() == mathe) {
                        chiTietMuonList = iBookService.getListCTM(mathe);
                    }
                }
            }
            for (ChiTietMuon elem : chiTietMuonList){
                System.out.println("MSV: " + elem.getMaSinhVien() + " Ten: " + elem.getTenSinhVien() + " Ten Sach: " + elem.getTenSach() + " Ten Loai: " + elem.getTenLoai());
            }
        }catch (Exception e){
            System.out.println("Error!!");
        }
    }

    public void getList1Nam() throws SQLException, IOException, ClassNotFoundException {
        lstNum = iBookService.getListDS1N();
        if (lstNum.isEmpty()){
            System.out.println("Khong co ban ghi nao");
        }else {
            for (SoLuongNguoiMuonTungThang elem : lstNum){
                System.out.println("Thang: " + elem.getThang() + "-" + "SoLuong: " +elem.getSoLuong());
            }
        }
    }

    public void getList6Thang() throws SQLException, IOException, ClassNotFoundException {
        lstNum = iBookService.getListDS6T();
        if(lstNum.isEmpty()){
            System.out.println("Khong ton tai ban ghi nao!");
        }else {
            for (SoLuongNguoiMuonTungThang elem : lstNum){
                System.out.println("Thang: " + elem.getThang() + "-" + "SoLuong: " + elem.getSoLuong());
            }
        }
    }
    public void getListSX(String nxb) throws SQLException, IOException, ClassNotFoundException {
        sanXuatSachList = iBookService.getListSX(nxb);
        if (sanXuatSachList.isEmpty()){
            System.out.println("Khong co ban ghi nao");
        }else {
            for (NhaSanXuatSach elem : sanXuatSachList){
                System.out.println("Ma Sach: "  + elem.getMaSach() + "-" + "Ten Sach: " + elem.getTenSach());
            }
        }
    }

    public void getLstLoaiSachMuon(int mathe) throws SQLException, IOException, ClassNotFoundException {
        try {
            maTheLst = iBookService.getListMT();
            if (maTheLst.isEmpty()){
                System.out.println("Khong co ban ghi nao!!");
            }else {
                for (MaThe elem : maTheLst){
                    if (elem.getMaThe() == mathe) {
                        maLoaiList  = iBookService.getListSachMuon(mathe);

                    }
                }
            }

            for (TenLoai elem : maLoaiList){
                System.out.println("Ten sach: " +elem.getTenSach() + " - " + "TenLoai: " + elem.getTenLoai());
            }
        }catch (Exception e){
            System.out.println("Ma the khong dung!!!");
        }
    }

}
