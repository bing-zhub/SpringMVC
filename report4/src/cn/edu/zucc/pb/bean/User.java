package cn.edu.zucc.pb.bean;

public class User {
    private String userid;
    private String name;
    private String pwd;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return name;
    }

    public void setUsername(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String password) {
        this.pwd = password;
    }
}
