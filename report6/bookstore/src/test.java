import bean.BookDetails;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.BookService;

public class test {

    @Test
    public void test01(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = (BookService) ctx.getBean("bookService");

//        for(BookDetails d: bookService.listAllBooks()){
//            System.out.println(d.getTitle());
//        }
    }
}
