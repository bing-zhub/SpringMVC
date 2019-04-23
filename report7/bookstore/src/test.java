import bean.BookDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;
import service.BookService;

public class test {

    @Autowired
    private BookService bookService;

    @Test
    public void test01(){
        System.out.println(bookService);
    }
}
