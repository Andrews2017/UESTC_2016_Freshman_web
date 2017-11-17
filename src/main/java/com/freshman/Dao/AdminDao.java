package com.freshman.Dao;

import com.freshman.Entity.TotalScore;
import com.freshman.Entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by tianxianglan on 2016/11/16.
 */
@Repository
public interface AdminDao {
    public void modifyAuth(int uId, int uAuth);

    public void publishProblem(String title, String category, String content, int score, String level);

    public List<TotalScore> showRankingDesc(String category, int lastPageNo);

    public int showUserNum();

    public List<User> showAllUsers();

    public List<TotalScore> showRankingByScoreAndCategory(String category);

}
