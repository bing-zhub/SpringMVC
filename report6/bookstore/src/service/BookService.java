package service;

import dao.BookDao;
import database.BookDetails;

public class BookService {
    private BookDao bookDao;


    public BookDao getBookDao() {
        return bookDao;
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void updateBook(BookDetails bookDetails){
        this.bookDao.updateBook(bookDetails);
    }
}
