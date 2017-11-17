package com.freshman.Service;

import com.freshman.Dao.AnswerDao;
import com.freshman.Dao.UserDao;
import com.freshman.Entity.Answer;
import com.freshman.Entity.Problem;
import com.freshman.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zly on 2016/11/14.
 */
@Service
public class AnswerService {
    @Autowired
    private AnswerDao answerDao;
    @Autowired
    private UserService userService;

    public void insertScoreAndComment(int answerId, int score, int correctPersonId, String comment){answerDao.insertScoreAndComment(answerId,score,correctPersonId,comment);}

    public void insertAnswer(int userId, int proId, String answerPath, String category, String submitTime){ answerDao.insertAnswer(userId,proId,answerPath,category,submitTime);}

    //获取答题者提交次数和最高分
    public Map findSubmitDetail(int problemId,int userId){
        Map<String,Integer> map = new HashMap();
        int submitCounts = answerDao.getSubmitCounts(problemId,userId);
        int highScore = answerDao.getHighScore(problemId,userId);
        map.put("submitCounts",submitCounts);
        map.put("highScore",highScore);
        return map;
    }

    public int getHighScore(int problemId,int userId){ return answerDao.getHighScore(problemId, userId);}

    public void updateSumScore(int adder, int userId, String category){ answerDao.updateSumScore(adder, userId, category);}


    //获取未批改试题
    public List<Map> findUncorrectList(String category){
        List<Map> uncorrectAnswerList = new ArrayList<Map>();
        //获取最近一次提交的答案
        List<Answer> lastSubmitList = answerDao.getLastSubmit(category);
        for (Answer lastAnswer:lastSubmitList) {
            Map<String, Object> map = new HashMap<String, Object>();
            //如果最近一次未批改，则加入显示列表
            User user = userService.findUserById(lastAnswer.getUserId());
            map.put("username",user.getUserName());
            map.put("userId",lastAnswer.getUserId());
            map.put("grade",user.getGrade());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dt = lastAnswer.getSubmitTime();
            String submitTimeStr = df.format(dt);
            map.put("submitTime",submitTimeStr);
            map.put("proId",lastAnswer.getProId());
            int submitCounts = answerDao.getSubmitCounts(lastAnswer.getProId(),lastAnswer.getUserId());
            map.put("submitCounts",submitCounts);
            int highScore = answerDao.getHighScore(lastAnswer.getProId(),lastAnswer.getUserId());
            map.put("highScore",highScore);
            uncorrectAnswerList.add(map);
        }
        return uncorrectAnswerList;
    }

    //获取每道未批改题的详情
    public List<Map> findSubmitInfo(int userId,int proId){
        List<Answer> list = answerDao.getAllSubmit(userId,proId);
        List<Map> mapList = new ArrayList<Map>();
        for (Answer submitAnswer : list){
            Map<String, Object> map = new HashMap<String, Object>();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dt = submitAnswer.getSubmitTime();
            String submitTimeStr = df.format(dt);
            map.put("submitTime", submitTimeStr);
            map.put("comment",submitAnswer.getComment());
            map.put("score",submitAnswer.getScore());
            map.put("answerUrl",submitAnswer.getUploadAnswer());
            map.put("correctId", submitAnswer.getCorrectpersonId());
            map.put("answerId", submitAnswer.getId());
            mapList.add(map);
        }
        return mapList;
    }

    //根据id获取答案
    public Answer findAnswer(int id){
        return answerDao.getAnswer(id);
    }

}
