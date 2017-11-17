package com.freshman.Service;

import com.freshman.Dao.UserDao;
import com.freshman.Entity.Student;
import com.freshman.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by tianxianglan on 2016/11/13.
 */
@Service
public class UserService {
    @Autowired
    UserDao userDao;

    //根据邮箱查找用户
    public User findUserByMail(String userMail){
        return userDao.findUserByMail(userMail);
    }
    //往数据库中添加用户
    public void addUser(String userName, String userPasswd, String userNo, String userEmail, String sex, String salty, String grade, String major){
        userDao.addUser(userName, userPasswd, userNo, userEmail, sex, salty, grade, major);
    }

    public User findUserById(int userId){
        return userDao.findUserById(userId);
    }

    public void updateUserPasswd(int userId, String newPasswd){ userDao.updateUserPasswd(userId, newPasswd);}

    public void insertPhoto(int userId, String photoName){ userDao.insertPhoto(userId, photoName);}

}
