package com.freshman.Entity;

import org.apache.ibatis.type.Alias;

/**
 * Created by zly on 2016/11/8.
 */
@Alias("totalscore")
public class TotalScore {
    //总分数Id
    private int id;
    //答题者姓名
    private String name;
    //答题者Id
    private int nameId;
    //年级
    private String grade;
    //题目方向
    private String category;
    //分数
    private int score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNameId() {
        return nameId;
    }

    public void setNameId(int nameId) {
        this.nameId = nameId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
