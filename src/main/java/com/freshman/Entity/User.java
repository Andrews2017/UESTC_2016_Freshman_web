package com.freshman.Entity;

import org.apache.ibatis.type.Alias;

/**
 * Created by zly on 2016/11/8.
 */
@Alias("user")
public class User {
    //用户Id
    private int userId;
    //用户名
    private String userName;
    //密码
    private String userPassword;
    //学号
    private String userNo;
    //邮箱
    private String userEmail;
    //权限
    private int userAuthority;
    //性别
    private String sex;
    //年级
    private String grade;
    //头像路径
    private String photo;
    //学历
    private String edu;
    //专业
    private String major;
    //加密所用的盐
    private String salty;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getUserAuthority() {
        return userAuthority;
    }

    public void setUserAuthority(int userAuthority) {
        this.userAuthority = userAuthority;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getSalty() {
        return salty;
    }

    public void setSalty(String salty) {
        this.salty = salty;
    }

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

}
