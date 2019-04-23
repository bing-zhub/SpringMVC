package mapper;

import bean.BookDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class BookDetailMapper implements RowMapper<BookDetails> {

    @Override
    public BookDetails mapRow(ResultSet resultSet, int i) throws SQLException {
        BookDetails bookDetails = new BookDetails();
        bookDetails.setBookId(resultSet.getString(1));
        bookDetails.setSurname(resultSet.getString(2));
        bookDetails.setFirstName(resultSet.getString(3));
        bookDetails.setTitle(resultSet.getString(4));
        bookDetails.setPrice(resultSet.getFloat(5));
        bookDetails.setYear(resultSet.getInt(6));
        bookDetails.setDescription(resultSet.getString(7));
        bookDetails.setInventory(resultSet.getInt(8));
        return bookDetails;
    }
}
