package dao;

import database.BookDetails;
import org.springframework.jdbc.core.JdbcTemplate;

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
}
