package btl_fondend;

import btl_backend.controller.BookController;
import btl_backend.repository.BookRepository;
import btl_backend.service.BookService;

import java.io.IOException;
import java.sql.SQLException;

public class Test {

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {

        BookRepository bookRepository = new BookRepository();
        BookService bookService = new BookService(bookRepository);
        BookController bookController = new BookController(bookService);

//        bookController.getListNXB();
        bookController.getListTG();
//        bookController.getListCTM(1000111);
//        bookController.getList1Nam();
//        bookController.getList6Thang();
//        bookController.getListSX("NXB Kim Đồng");
       /* bookController.getLstLoaiSachMuon(10009);
*/

    }
}
