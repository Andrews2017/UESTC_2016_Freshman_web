package com.freshman.Entity;

import org.apache.ibatis.type.Alias;

/**
 * Created by zly on 2016/11/8.
 */
@Alias("problem")
public class Problem {
    //题目Id
    private int proId;
    //题目标题
    private String title;
    //题目方向
    private String category;
    //题目内容（包括文字、图片）
    private String content;
    //题目分值
    private int score;
    //题目难度
    private String level;

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
