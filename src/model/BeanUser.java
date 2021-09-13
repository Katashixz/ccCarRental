package model;

import java.sql.Timestamp;
import java.util.Date;

public class BeanUser {
    public static BeanUser currentLoginUser=null;

    public static BeanUser getCurrentLoginUser() {
        return currentLoginUser;
    }

    public static void setCurrentLoginUser(BeanUser currentLoginUser) {
        BeanUser.currentLoginUser = currentLoginUser;
    }
    public Integer UserNum;
    public String User_id;
    public String sex;
    public String phonenum;
    public String email;
    public String city;
    public Timestamp Register_time;
    public String password;

    public Integer getUserNum() {
        return UserNum;
    }

    public void setUserNum(Integer userNum) {
        UserNum = userNum;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_id() {
        return User_id;
    }

    public void setUser_id(String user_id) {
        User_id = user_id;
    }

    public Date getRegister_time() {
        return Register_time;
    }

    public void setRegister_time(Timestamp register_time) {
        Register_time = register_time;
    }
}
