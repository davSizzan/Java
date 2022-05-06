package btl_backend.repository;

import btl_database.ConnectDataBase;
import entity.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository implements IBookRepository{
//    View
    @Override
    public List<NhaSanXuatSach> getListNSX() throws SQLException, IOException, ClassNotFoundException {
        List<NhaSanXuatSach> sanXuatSachList = new ArrayList<>();

        ConnectDataBase connectDataBase = new ConnectDataBase();
        Connection connection = connectDataBase.getConnection();

        Statement statement  = connection.createStatement();
        String query = "SELECT * FROM NhaSanXuatSach";
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()){
            NhaSanXuatSach sanXuatSach = new NhaSanXuatSach();
            sanXuatSach.setMaSach(resultSet.getString("MaSach"));
            sanXuatSach.setTenSach(resultSet.getNString("TenSach"));
            sanXuatSach.setTenNXB(resultSet.getNString("TenNXB"));

            sanXuatSachList.add(sanXuatSach);
        }
        connectDataBase.Disconnect();
        return sanXuatSachList;
    }

    @Override
    public List<ThongTinTacGia> getListTG() throws SQLException, IOException, ClassNotFoundException {
        List<ThongTinTacGia> thongTinTacGiaList = new ArrayList<>();

        ConnectDataBase connectDataBase = new ConnectDataBase();

        Connection connection = connectDataBase.getConnection();
        Statement statement  = connection.createStatement();

        String query = "SELECT * FROM ThongTinTacGia";

        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()){
            ThongTinTacGia thongTinTacGia = new ThongTinTacGia();
            thongTinTacGia.setMaSach(resultSet.getString("MaSach"));
            thongTinTacGia.setTenSach(resultSet.getNString("TenSach"));
            thongTinTacGia.setTacGia(resultSet.getNString("TenTacGia"));
            thongTinTacGia.setButDanh(resultSet.getNString("ButDanh"));

            thongTinTacGiaList.add(thongTinTacGia);
        }
        connectDataBase.Disconnect();

        return thongTinTacGiaList;

    }

    @Override
    public List<MaThe> getListMT() throws SQLException, IOException, ClassNotFoundException {
        List<MaThe> maTheList = new ArrayList<>();
        ConnectDataBase connectDataBase = new ConnectDataBase();
        Connection connection = connectDataBase.getConnection();

        Statement statement = connection.createStatement();

        String query = "SELECT * FROM CapThe";

        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()){
            MaThe maThe = new MaThe();

            maThe.setMaThe(resultSet.getInt("MaThe"));

            maTheList.add(maThe);
        }
        connectDataBase.Disconnect();
        return maTheList;
    }

    @Override
    public List<ChiTietMuon> getListCTM(int mathe) throws SQLException, IOException, ClassNotFoundException {
        List<ChiTietMuon> chiTietMuonList = new ArrayList<>();

        ConnectDataBase connectDataBase = new ConnectDataBase();
        Connection connection = connectDataBase.getConnection();

        String query = "EXEC ChiTietThongTinMuon ?";

//        PreparedStatement preparedStatement = connection.prepareStatement(query);
        CallableStatement callableStatement = connection.prepareCall(query);
        callableStatement.setInt(1,mathe);
//        preparedStatement.setInt(1,mathe);
        ResultSet resultSet = callableStatement.executeQuery();
        while (resultSet.next()){
            ChiTietMuon chiTietMuon = new ChiTietMuon();

            chiTietMuon.setMaSinhVien(resultSet.getString("MaSinhVien"));
            chiTietMuon.setTenSinhVien(resultSet.getNString("TenSinhVien"));
            chiTietMuon.setTenSach(resultSet.getNString("TenSach"));
            chiTietMuon.setTenLoai(resultSet.getNString("TenLoai"));

            chiTietMuonList.add(chiTietMuon);
        }
        connectDataBase.Disconnect();
        return chiTietMuonList;
    }
//  store procedure
    @Override
    public List<SoLuongNguoiMuonTungThang> getListDS1N() throws SQLException, IOException, ClassNotFoundException {
        List<SoLuongNguoiMuonTungThang> lst = new ArrayList<>();

        ConnectDataBase connectDataBase = new ConnectDataBase();
        Connection connection = connectDataBase.getConnection();

        String query = "EXEC SoLuongMuonTungThang";
//        PreparedStatement preparedStatement = connection.prepareStatement(query);
        CallableStatement callableStatement = connection.prepareCall(query);

        ResultSet resultSet = callableStatement.executeQuery();
        while (resultSet.next()){
            SoLuongNguoiMuonTungThang num = new SoLuongNguoiMuonTungThang();
            num.setThang(resultSet.getInt("MONTH"));
            num.setSoLuong(resultSet.getInt("SoLuongNguoiNMuon"));

            lst.add(num);
        }
        connectDataBase.Disconnect();
        return lst;
    }

    @Override
    public List<SoLuongNguoiMuonTungThang> getListDS6T() throws SQLException, IOException, ClassNotFoundException {
        List<SoLuongNguoiMuonTungThang> lst = new ArrayList<>();

        ConnectDataBase connectDataBase = new ConnectDataBase();
        Connection connection = connectDataBase.getConnection();

        String query = "EXEC SoLuongNguoiMuon6ThangGanNhat";
//        PreparedStatement preparedStatement = connection.prepareStatement(query);
        CallableStatement callableStatement = connection.prepareCall(query);

        ResultSet resultSet = callableStatement.executeQuery();
        while (resultSet.next()){
            SoLuongNguoiMuonTungThang num = new SoLuongNguoiMuonTungThang();
            num.setThang(resultSet.getInt("Thang"));
            num.setSoLuong(resultSet.getInt("SoLuongMuon"));

            lst.add(num);
        }
        connectDataBase.Disconnect();
        return lst;
    }

//    Functions
    @Override
    public List<NhaSanXuatSach> getListSX(String nxb) throws SQLException, IOException, ClassNotFoundException {
        List<NhaSanXuatSach> list = new ArrayList<>();

        ConnectDataBase connectDataBase = new ConnectDataBase();
        Connection connection = connectDataBase.getConnection();

        String query = "SELECT * FROM SanXuatSach (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setNString(1,nxb);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            NhaSanXuatSach nhaSanXuatSach = new NhaSanXuatSach();
            nhaSanXuatSach.setMaSach(resultSet.getString("MaSach"));
            nhaSanXuatSach.setTenSach(resultSet.getString("TenSach"));

            list.add(nhaSanXuatSach);
        }
        connectDataBase.Disconnect();

        return list;
    }

    @Override
    public List<TenLoai> getListSachMuon(int mathe) throws SQLException, IOException, ClassNotFoundException {
        List<TenLoai> lstLoaiSach = new ArrayList<>();

        ConnectDataBase connectDataBase = new ConnectDataBase();
        Connection connection = connectDataBase.getConnection();

        String sql = "SELECT * FROM LoaiSachTungMuon (?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,mathe);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            TenLoai tenLoai = new TenLoai();
            tenLoai.setTenSach(resultSet.getString("TenSach"));
            tenLoai.setTenLoai(resultSet.getString("TenLoai"));

            lstLoaiSach.add(tenLoai);
        }
        connectDataBase.Disconnect();
        return lstLoaiSach;
    }
}
