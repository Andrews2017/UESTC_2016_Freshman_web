package com.freshman.Controller;

import com.freshman.Common.Response;
import com.freshman.Common.Status;
import com.freshman.Entity.TotalScore;
import com.freshman.Entity.User;
import com.freshman.Service.AdminService;
import com.freshman.Service.UserService;
import com.freshman.Util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by zly on 2016/11/15.
 */
@Controller
public class AdminController {

    @Autowired
    AdminService adminService;
    @Autowired
    UserService userService;

    /*
    * 显示所有用户
    * */
    @ResponseBody
    @RequestMapping(value = "admin/showAllUsers", method = RequestMethod.POST)
    public Response showAll(){
        List<User> list = adminService.showAllUsers();
        return new Response(list, Status.SUCCESS);
    }

    /*
    * 修改用户权限
    * */
    @ResponseBody
    @RequestMapping(value = "admin/modifyAuth/{userId}/{authority}", method = RequestMethod.GET)
    public Response modifyAuth(@PathVariable int userId, @PathVariable int authority){
        adminService.modifyAuth(userId, authority);
        short status = Status.SUCCESS;
        String errmsg = "修改成功";
        return new Response(status, errmsg);
    }

    /*
    * 查看用户信息
    * */
    @ResponseBody
    @RequestMapping(value = "admin/showUserInfo/{userId}", method = RequestMethod.GET)
    public Response showUserInformation(@PathVariable int userId){
        User user = userService.findUserById(userId);
        return new Response(user, Status.SUCCESS);
    }

    /*
    * 发布题目
    * */
    @ResponseBody
    @RequestMapping(value = "admin/publishProblem/{category}", method = RequestMethod.GET)
    public Response publishProblem(String title, @PathVariable  String category, String content, int score, String level){
        adminService.publishProblem(title, category, content, score,level);
        short status = Status.SUCCESS;
        String errmsg = "发布成功";
        return new Response(status, errmsg);
    }

    /*
    * 查看各方向排行榜,每页显示二十人
    * */
    @ResponseBody
    @RequestMapping(value = "admin/RankingDesc/{category}", method = RequestMethod.GET)
    public Response showRanking(@PathVariable String category, int pageNo){
        if ((adminService.showUserNum()/20)+ 1 < pageNo){
            return new Response(Status.FAILURE, "已经到底啦");
        }else {
            List list = adminService.showRankingPage(category, pageNo);
            return new Response(list,Status.SUCCESS);
        }
    }

    /*
    * 修改用户密码
    * */
    @ResponseBody
    @RequestMapping(value = "admin/modifyPasswd/{userId}", method = RequestMethod.GET)
    public Response modifyPasswd(@PathVariable int userId, String newPasswd){
        String salty = userService.findUserById(userId).getSalty();
        String newMdPasswd = Tools.saltiedPasswd(newPasswd, salty);
        userService.updateUserPasswd(userId, newMdPasswd);
        return new Response(Status.SUCCESS, "修改成功");
    }

    /*
    * 导出excel
    * */
    @ResponseBody
    @RequestMapping(value = "admin/exportExcel", method = RequestMethod.GET)
    public Response exportExcel(HttpServletRequest request, HttpServletResponse response){
        adminService.exportExcel(request, response);
        return new Response(Status.SUCCESS,"");
    }
}
