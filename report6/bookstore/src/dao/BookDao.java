package dao;

import bean.BookDetails;
import bean.OrderDetail;
import cart.ShoppingCart;
import cart.ShoppingCartItem;
import exception.OrderException;
import mapper.BookDetailMapper;
import mapper.OrderDetailsMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class BookDao{
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void updateBook(BookDetails bookDetails) {

        String sql = "update books set surname=?,first_name=?,title=?,price=?,yr=?,description=?,inventory=? where id = ?";
        Object args[] = {bookDetails.getSurname(), bookDetails.getFirstName(), bookDetails.getTitle(),
                bookDetails.getPrice(), bookDetails.getYear(), bookDetails.getDescription(),bookDetails.getInventory(),
                bookDetails.getBookId()};
        jdbcTemplate.update(sql, args);

    }

    public List<BookDetails> listAllBooks(){
        String sql = "select * from books";
        return this.jdbcTemplate.query(sql, new BookDetailMapper());
    }

    public BookDetails getBookDetails(String bookId){
        String sql = "select * from books where id=?";
        return this.jdbcTemplate.queryForObject(sql, new BookDetailMapper(), bookId);
    }


}


