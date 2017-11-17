package com.freshman.Dao;

import com.freshman.Entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tianxianglan on 2016/11/15.
 */
@Repository
public interface StudentDao {
    public Student findStudentByStuId(String stuId);

}
