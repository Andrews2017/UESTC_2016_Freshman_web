
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
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zly on 2016/11/15.
 */
@Controller
public class RespondentController {
    @Autowired
    ProblemService problemService;
    @Autowired
    AnswerService answerService;
    @Autowired
    TotalScoreService totalScoreService;

    /**
     * 做题人显示自己在某一方向题目的提交详情以及最高分
     */
    @ResponseBody
    @RequestMapping(value = "/villager/showSubmitDetail/{category}",method = RequestMethod.GET)
    public Response showProblems(@PathVariable String category,HttpSession session){
        User user = (User)session.getAttribute("user");
        List<Map> list = new ArrayList<Map>();
        List<Problem> problemList = problemService.findAllProblemsByCate(category);
        for(Problem problem : problemList){
            Map<Object, Object> map = new HashMap<Object, Object>();
            Map detail = answerService.findSubmitDetail(problem.getProId(),user.getUserId());
            map.put("submitDetail",detail);
            map.put("problemInfo", problem);
            list.add(map);
        }
        return new Response(Status.SUCCESS,"获取题目列表",list);
    }

    /*
    * 显示某一方向所有题目
    * */
    @ResponseBody
    @RequestMapping(value = "villager/showAllProByCategory/{category}", method = RequestMethod.GET)
    public Response showAllPros(@PathVariable String category){
        List<Problem> proList = problemService.findAllProblemsByCate(category);
        return new Response(proList, Status.SUCCESS);
    }

    /**
     * 查看具体题目信息
     */
    @ResponseBody
    @RequestMapping(value = "/villager/showProDetail/{proId}",method = RequestMethod.GET)
    public Response problemInfo(@PathVariable int proId){
        Map<String, Object> body = new HashMap<String, Object>();
        Problem problem = problemService.getProblemInfo(proId);
        body.put("problemInfo", problem);
        return new Response(Status.SUCCESS, "", body);
    }

    /**
     * 答题,上传答案
     */
    @ResponseBody
    @RequestMapping(value = "/villager/{category}/{proId}/uploadanswer",method = RequestMethod.POST)
    public Response answerProblem(@PathVariable String category, @PathVariable int proId, HttpSession session, @RequestParam("answerFile") MultipartFile file, HttpServletRequest request) throws IOException {
        User user = (User)session.getAttribute("user");
        if (!file.isEmpty()){
            Problem problem = problemService.getProblemInfo(proId);
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            SimpleDateFormat df2 = new SimpleDateFormat("MMddhhmm");
            String currentTime = df.format(date);
            String path = request.getSession().getServletContext().getRealPath("/");
            int begin = path.indexOf("ROOT");
            path = path.substring(0, begin);
            String dir = path+ "freshman"+ File.separator+ "answer";
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf(".")+ 1);
            if (suffix.equals("zip")){
                String newFileName = df2.format(date)+ "-"+ user.getUserName() + "-"+ problem.getTitle()+ ".zip";
                File answerFile = new File(dir, newFileName);
                File oldFile = new File(dir, fileName);
                FileUtils.writeByteArrayToFile(oldFile, file.getBytes());
                oldFile.renameTo(answerFile);
                answerService.insertAnswer(user.getUserId(),proId,newFileName,category,currentTime);
                return new Response(Status.SUCCESS);
            }else {
                return  new Response(Status.FAILURE, "文件格式不对,请上传zip格式文件");
            }
        }else {
            return new Response(Status.FAILURE,"请选择要上传的文件");
        }
    }

    /**
     * 查看各个方向的排名
     */
    @ResponseBody
    @RequestMapping(value = "/villager/showRankList/{category}",method = RequestMethod.GET)
    public Response showRankList(@PathVariable String category,HttpSession session) {
        User user = (User) session.getAttribute("user");
        Map allRankingList = totalScoreService.getRankList(category, user);
        return new Response(Status.SUCCESS, "显示排名", allRankingList);
    }
}
