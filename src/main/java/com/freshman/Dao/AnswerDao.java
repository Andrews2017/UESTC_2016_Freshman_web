package com.freshman.Dao;

import com.freshman.Entity.Answer;
import com.freshman.Entity.Problem;
import com.freshman.Entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 1234 on 2016/11/14.
 */
@Repository
public interface AnswerDao {

    public void insertScoreAndComment(int answerId, int score, int correctPersonId, String comment);

    public int getHighScore(int problemId,int userId);

    public void updateSumScore(int adder, int userId, String category);

    public void insertAnswer(int userId, int proId, String answerPath, String category, String submitTime);

    //查询提交次数
    public int getSubmitCounts(int problemId,int userId);
    //查询最高分

    //最近一次时间提交的列表
    public List<Answer> getLastSubmit(String category);
    //按时间升序获取每道题的列表
    public List<Answer> getAllSubmit(int userId,int problemId);
    //根据id查找答案
    public Answer getAnswer(int id);
    //插入打分信息,未批改过
}
