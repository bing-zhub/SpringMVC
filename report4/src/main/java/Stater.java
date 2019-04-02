public class Stater {

    public static void main(String[] args) {
        BeanFactory factory = new BeanFactory("src/config.xml");
        System.out.println(factory.getLoginServiceLevel());
    }
}
