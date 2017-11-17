package com.freshman.Dao;

import com.freshman.Entity.Answer;
import com.freshman.Entity.TotalScore;
import com.freshman.Entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zly on 2016/11/15.
 */
@Repository
public interface TotalScoreDao {
    //查询某方向前几名排名
    public List<TotalScore> findTop5Score(@Param("category") String category);
    //查询某方向答题者排名
    public TotalScore findUserScore(@Param("category") String category,User user);
    //查询某方向排名
    public List<TotalScore> showScoreDesc(@Param("category") String category);
    //插入一条总分记录
    public void insertTotalScore(User user,Answer answer);
    //总分累加一道题的分数
    public void updateTotalScore(Answer answer);
    //总分更新一道题的最高分
    public void updateTotalScore2(Answer answer,int addScore);
}
