package com.freshman.Entity;

import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * Created by zly on 2016/11/8.
 */
@Alias("answer")
public class Answer {
    //分数Id
    private int id;
    //做题者Id
    private int userId;
    //题目Id
    private int proId;
    //每题分数
    private int score;
    //上传答案
    private String uploadAnswer;
    //题目方向
    private String category;
    //提交时间
    private Date submitTime;
    //改题者Id
    private int correctpersonId;
    //评语
    private String comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUploadAnswer() {
        return uploadAnswer;
    }

    public void setUploadAnswer(String uploadAnswer) {
        this.uploadAnswer = uploadAnswer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public int getCorrectpersonId() {
        return correctpersonId;
    }

    public void setCorrectpersonId(int correctpersonId) {
        this.correctpersonId = correctpersonId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}