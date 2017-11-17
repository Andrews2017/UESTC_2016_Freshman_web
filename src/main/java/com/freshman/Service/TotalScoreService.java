package com.freshman.Service;

import com.freshman.Dao.TotalScoreDao;
import com.freshman.Entity.Answer;
import com.freshman.Entity.TotalScore;
import com.freshman.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zly on 2016/11/15.
 */
@Service
public class TotalScoreService {
    @Autowired
    TotalScoreDao totalScoreDao;
    @Autowired
    UserService userService;
    @Autowired
    AnswerService answerService;

    //做题人获取排名
    public Map getRankList(String category, User user){
        Map<String,Object> list = new HashMap<String, Object>();
        List<TotalScore> top5Num = totalScoreDao.findTop5Score(category);
        List<User> top5User = new ArrayList<User>();
        //答题者是否是前五
        boolean isTop5 = false;
        //如果答题人在前五名，直接返回
        for (TotalScore score : top5Num) {
            top5User.add(userService.findUserById(score.getNameId()));
            if( user.getUserId() == score.getNameId() ){
                isTop5 = true;
                break;
            }
        }
        //答题人不在前五，将答题人加入列表
        if(false==isTop5) {
            TotalScore answerScore = totalScoreDao.findUserScore(category, user);
            top5Num.add(answerScore);
            top5User.add(user);
        }
        list.put("namelist",top5User);
        list.put("scorelist",top5Num);
        return list;
    }

    //改题人获取排名（姓名和分数）
    public List<TotalScore> getRankingList(String category){
        return totalScoreDao.showScoreDesc(category);
    }
}
