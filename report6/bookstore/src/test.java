import database.BookDetails;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.BookService;

public class test {

    @Test
    public void test01(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = (BookService) ctx.getBean("bookService");

        BookDetails bookDetails = new BookDetails();
        bookDetails.setBookId("209");
        bookDetails.setInventory(1);
        bookService.updateBook(bookDetails);
    }
}
