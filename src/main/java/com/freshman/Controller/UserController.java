package com.freshman.Controller;

import com.freshman.Common.Response;
import com.freshman.Common.Status;
import com.freshman.Entity.Student;
import com.freshman.Entity.User;
import com.freshman.Service.StudentService;
import com.freshman.Service.UserService;
import com.freshman.Util.Tools;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

/**
 * Created by tianxianglan on 2016/11/13.
 */
@Controller
public class UserController {

    short status = Status.FAILURE;
    @Autowired
    UserService userService;
    @Autowired
    StudentService studentService;


    /*
    * 登录
    * */
    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Response login(String userEmail, String userPasswd, HttpSession httpSession){
        User findUser = userService.findUserByMail(userEmail);
        if (findUser == null){
            String errmsg = "请注册！";
            return new Response(status, errmsg);
        }else {
            String mdPasswd = Tools.saltiedPasswd(userPasswd, findUser.getSalty());
            if (mdPasswd.equals(findUser.getUserPassword())){
                httpSession.setAttribute("user", findUser);
                status = Status.SUCCESS;
                Object authority = findUser.getUserAuthority();
                return new Response(authority, status);
            }else {
                String errmsg = "密码错误！";
                return new Response(status, errmsg);
            }
        }
    }

    /*
    * 注册
    * */
    @ResponseBody
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public Response userRegister(String userEmail, String passwd, String rePasswd, String stdNo, String idNum, String checkedWords, HttpSession session){
        if (passwd.equals(rePasswd)){
            Student student = studentService.findStudentByStuId(stdNo);
            String checkNum = (String) session.getAttribute("capText");
            if (checkedWords.equals(checkNum)){
                if (idNum.equals(student.getIdNum())){
                    User user = userService.findUserByMail(userEmail);
                    System.out.println(user);
                    if (user == null){
                        String salty = Tools.getFourWords();
                        String mdPasswd = Tools.saltiedPasswd(passwd, salty);
                        String grade = Tools.getGrade(student.getEdu(),student.getStuId());
                        userService.addUser(student.getName(),mdPasswd,stdNo,userEmail,student.getSex(),salty,grade,student.getMajor());
                        status = Status.SUCCESS;
                        return new Response(status);
                    }else {
                        String errmsg = "此邮箱已注册，请登录！";
                        return new Response(status, errmsg);
                    }
                }else {
                    String errmsg = "学号与身份证号不相符，请重输！";
                    return new Response(status, errmsg);
                }
            }else {
                String errmsg = "验证码错误！";
                return new Response(status, errmsg);
            }
        }else {
            String errmsg = "两次密码输入不一致,请重输!";
            return new Response(status, errmsg);
        }
    }

    /*
    * 用户上传照片,
    * */
    @ResponseBody
    @RequestMapping(value = "user/uploadPic", method = RequestMethod.POST)
    public Response modifyInfo( @RequestParam("photo") MultipartFile file, HttpServletRequest request, HttpSession session) throws IOException {
           if (!file.isEmpty()){
               User user = (User) session.getAttribute("user");
               String path = request.getSession().getServletContext().getRealPath("/");
               int begin = path.indexOf("ROOT");
               path = path.substring(0, begin);
               String dir = path+ "freshman"+ File.separator+ "pic";
               String fileName = file.getOriginalFilename();
               String suffix = fileName.substring(fileName.lastIndexOf(".")+ 1);

               if (suffix.equals("bmp") || suffix.equals("jpg") || suffix.equals("jpeg")){
                   //避免重复上传照片时无法显示最新的照片而加了个随机数
                   String newFileName = user.getUserName()+ (int)(Math.random()*100)+ "."+ suffix;
//                   String newFileName = "kk."+(int)(Math.random()*100)+ suffix;
                   File newFile = new File(dir, newFileName);
                   File oldFile = new File(dir, fileName);
                   FileUtils.writeByteArrayToFile(oldFile, file.getBytes());
                   oldFile.renameTo(newFile);
                   userService.insertPhoto(user.getUserId(),newFileName);
                   return new Response(Status.SUCCESS);
               }else {
                   return new Response(Status.FAILURE, "文件格式不对");
               }
           } else {
               return new Response(Status.FAILURE, "文件为空");
           }
    }
}
