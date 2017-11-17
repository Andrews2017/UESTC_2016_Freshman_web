package com.freshman.Dao;

import com.freshman.Entity.Problem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zly on 2016/11/9.
 */
@Repository
public interface ProblemDao {

    public ArrayList<Problem> findAllProblemsByCate(String category);


    //根据id获取题目详情
    public Problem selectProblemInfo(int proId);
    //根据标题获取题目详情
    public Problem getProblemInfoByTitle(String title);
}
