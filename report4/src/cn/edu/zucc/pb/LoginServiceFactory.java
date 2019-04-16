package cn.edu.zucc.pb;

public class LoginServiceFactory {
    private String level = "L1";



    public static void main(String[] args) {
        try {
            ApplicationContext applicationContext = new ApplicationContextImpl("application.xml");
            LoginServiceFactory loginServiceFactory = (LoginServiceFactory) applicationContext.getBean("LoginSessionFactory");
            System.out.println(loginServiceFactory.getLevel());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ILogin createService(){
        switch (this.level){
            case "L1":
                return new LoginServiceV1();
            case "L2":
                return new LoginServiceV2();
            case "L3":
                return new LoginServiceV3();
            default:
                System.out.println("no such service");
        }

        return null;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }
}
