package btl_backend.repository;

import entity.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IBookRepository {

    public List<NhaSanXuatSach> getListNSX() throws SQLException, IOException, ClassNotFoundException;
    public List<ThongTinTacGia> getListTG() throws SQLException, IOException, ClassNotFoundException;
    public List<MaThe> getListMT() throws SQLException, IOException, ClassNotFoundException;
    public List<ChiTietMuon> getListCTM(int mathe) throws SQLException, IOException, ClassNotFoundException;
    public List<SoLuongNguoiMuonTungThang> getListDS1N() throws SQLException, IOException, ClassNotFoundException;
    public List<SoLuongNguoiMuonTungThang> getListDS6T() throws SQLException, IOException, ClassNotFoundException;
    public List<NhaSanXuatSach> getListSX(String nxb) throws SQLException, IOException, ClassNotFoundException;
    public List<TenLoai> getListSachMuon(int mathe) throws SQLException, IOException, ClassNotFoundException;
}
