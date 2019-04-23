package service;

import dao.BookDao;
import bean.BookDetails;
import exception.BookNotFoundException;
import exception.BooksNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookService {

    @Autowired
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

    public List<BookDetails> listAllBooks() throws BooksNotFoundException {
        List<BookDetails> books =  this.bookDao.listAllBooks();
        if(books.isEmpty())
            throw new BooksNotFoundException("Books not found");
        return books;
    }

    public BookDetails getBookDetails(String bookid) throws BookNotFoundException {
        BookDetails bookDetails = this.bookDao.getBookDetails(bookid);
        if(bookDetails==null)
            throw new BookNotFoundException("Book not found");
        return bookDetails;
    }
}
