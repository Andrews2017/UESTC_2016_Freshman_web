package com.freshman.Entity;

import org.apache.ibatis.type.Alias;

/**
 * Created by tianxianglan on 2016/11/15.
 */
@Alias("student")
public class Student {
    //学生姓名
    private String name;
    //身份证号
    private String idNum;
    //学号
    private String stuId;
    //根据身份证后几位生成的密码
    private String password;
    //性别
    private String sex;
    //学历
    private String edu;
    //专业
    private String major;
    //班级号
    private String classId;
    //学院
    private String college;

    public Student() {
    }

    public Student(String name, String idNum, String stuId, String password, String sex, String edu, String major, String classId, String college) {

        this.name = name;
        this.idNum = idNum;
        this.stuId = stuId;
        this.password = password;
        this.sex = sex;
        this.edu = edu;
        this.major = major;
        this.classId = classId;
        this.college = college;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }
}
