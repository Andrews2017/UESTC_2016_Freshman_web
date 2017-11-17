package com.freshman.Dao;

import com.freshman.Common.Response;
import com.freshman.Entity.Student;
import com.freshman.Entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by tianxianglan on 2016/11/13.
 */
@Repository
public interface UserDao {

    public User findUserByMail(String userMail);

    public void addUser(String userName, String userPasswd, String userNo, String userEmail, String sex, String salty, String grade, String major);

    public User findUserById(int userId);

    public void updateUserPasswd(int userId, String newPasswd);

    public void insertPhoto(int userId, String photoName);

}
