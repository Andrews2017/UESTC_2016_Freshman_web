package com.freshman.Service;

import com.freshman.Dao.StudentDao;
import com.freshman.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tianxianglan on 2016/11/15.
 */
@Service
public class StudentService {

    @Autowired
    StudentDao studentDao;

    //根据学生id在工具库中查找学生信息
    public Student findStudentByStuId(String stuId){
        return studentDao.findStudentByStuId(stuId);
    }

}
