package btl_backend.service;

import btl_backend.repository.IBookRepository;
import entity.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class BookService implements IBookService{

    IBookRepository iBookRepository;

    public BookService(IBookRepository iBookRepository) {
        this.iBookRepository = iBookRepository;
    }

    @Override
    public List<NhaSanXuatSach> getListNSX() throws SQLException, IOException, ClassNotFoundException {
        return iBookRepository.getListNSX();
    }

    @Override
    public List<ThongTinTacGia> getListTG() throws SQLException, IOException, ClassNotFoundException {
        return iBookRepository.getListTG();
    }

    @Override
    public List<MaThe> getListMT() throws SQLException, IOException, ClassNotFoundException {
        return iBookRepository.getListMT();
    }

    @Override
    public List<ChiTietMuon> getListCTM(int mathe) throws SQLException, IOException, ClassNotFoundException {
        return iBookRepository.getListCTM(mathe);
    }

    @Override
    public List<SoLuongNguoiMuonTungThang> getListDS1N() throws SQLException, IOException, ClassNotFoundException {
        return iBookRepository.getListDS1N();
    }

    @Override
    public List<SoLuongNguoiMuonTungThang> getListDS6T() throws SQLException, IOException, ClassNotFoundException {
        return iBookRepository.getListDS6T();
    }

    @Override
    public List<NhaSanXuatSach> getListSX(String nxb) throws SQLException, IOException, ClassNotFoundException {
        return iBookRepository.getListSX(nxb);
    }

    @Override
    public List<TenLoai> getListSachMuon(int mathe) throws SQLException, IOException, ClassNotFoundException {
        return iBookRepository.getListSachMuon(mathe);
    }
}
