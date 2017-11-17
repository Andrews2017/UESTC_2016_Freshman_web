package com.freshman.Controller;

import com.freshman.Common.Response;
import com.freshman.Common.Status;
import com.freshman.Entity.Answer;
import com.freshman.Entity.Problem;
import com.freshman.Entity.TotalScore;
import com.freshman.Entity.User;
import com.freshman.Service.AnswerService;
import com.freshman.Service.ProblemService;
import com.freshman.Service.TotalScoreService;
import com.freshman.Util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.lang.management.BufferPoolMXBean;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zly on 2016/11/15.
 */
@Controller
public class CorrectController {
    @Autowired
    TotalScoreService scoreService;
    @Autowired
    AnswerService answerService;
    @Autowired
    ProblemService problemService;
    @Autowired
    TotalScoreService totalScoreService;


    /**
     * 如果用户最近提交的未被批改，则予以显示
     */
    @ResponseBody
    @RequestMapping(value = "/correct/showUncorrectList",method = RequestMethod.GET)
    public Response correctAnswer(HttpSession session){
        Map<String, Object> body = new HashMap<String, Object>();
        User user = (User)session.getAttribute("user");
        int level = user.getUserAuthority();
//        int level = 2;
        Map<Integer, String> map = Tools.getCategory();
        String category = map.get(level);
        List<Map> unCorrectList = answerService.findUncorrectList(category);
        body.put("unCorrectList",unCorrectList);
        return new Response(Status.SUCCESS,"改题人查看未批改试题",body);
    }

    /**
     * 显示每道题提交详情
     * @param userId
     * @param proId
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/correct/showSubmitDetail/{userId}/{proId}",method = RequestMethod.GET)
    public Response showAnswerDetail(@PathVariable int userId, @PathVariable int proId, HttpSession session){
        Map<String, Object> body = new HashMap<String, Object>();
        Problem problem = problemService.getProblemInfo(proId);
        body.put("problemInfo",problem);
        List<Map> submitInfo = answerService.findSubmitInfo(userId,proId);
        body.put("submitInfo",submitInfo);
        return new Response(Status.SUCCESS,"每题详情",body);
    }

    /**
     * 对未批改的题目进行打分、写评语
     * @param id
     * @param request
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/correct/checkPro/{answerId}",method = RequestMethod.GET)
    public Response giveScore(@PathVariable int answerId, int score, String comment, HttpSession session){
        User user = (User)session.getAttribute("user");
        Answer answer = answerService.findAnswer(answerId);

        int highScore = answerService.getHighScore(answer.getProId(), answer.getUserId());
        answerService.insertScoreAndComment(answerId, score, answer.getUserId(), comment);
        if (score > highScore) {
            //更新总分表
            int adder = score- highScore;
            answerService.updateSumScore(adder,user.getUserId(), answer.getCategory());
        }
        return new Response(Status.SUCCESS);

    }

    /**
     * 显示改题人方向的排名
     */
    @ResponseBody
    @RequestMapping(value = "/correct/rankListAll",method = RequestMethod.GET)
    public Response showRankListAll(HttpSession session){
        User user = (User)session.getAttribute("user");
        int level = user.getUserAuthority();
        Map<Integer, String> map = Tools.getCategory();
        String category = map.get(level);
        List<TotalScore> rankingList = scoreService.getRankingList(category);
        return new Response(Status.SUCCESS,"改题人查看排名",rankingList);
    }

    /*
    * 下载用户提交的答案
    * */
    @ResponseBody
    @RequestMapping(value = "correct/downLoadAnswer", method = RequestMethod.GET)
    public Response downLoad(String fileName, HttpServletResponse response, HttpServletRequest request) throws IOException {
        String path = request.getSession().getServletContext().getRealPath("/");
        int begin = path.indexOf("ROOT");
        path = path.substring(0, begin);
        String dir = path+ "freshman"+ File.separator+ "answer";
//        iso8859-1编码的编码表中，不包含汉字字符
//        fileName = new String(fileName.getBytes("iso8859-1"), "UTF-8");
        File file = new File(dir + File.separator + fileName);
        if (!file.exists()) {
            return new Response(Status.FAILURE, "您要下载的资源不存在！");
        } else {
            //http header头要求内容必须为ISO8859-1编码，但奇怪的是为什么URLEncoder.encode(fileName, "UTF-8")也可以
            response.setHeader("content-disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO8859-1"));
            FileInputStream in = new FileInputStream(dir + File.separator + fileName);
            OutputStream out = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            in.close();
            out.close();
        }
        return new Response(Status.SUCCESS);
    }
}
