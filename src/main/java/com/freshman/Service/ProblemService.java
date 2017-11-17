package com.freshman.Service;

import com.freshman.Dao.ProblemDao;
import com.freshman.Entity.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zly on 2016/11/10.
 */
@Service
public class ProblemService {
    @Autowired
    private ProblemDao problemDao;

    public ArrayList<Problem> findAllProblemsByCate(String category){
        return problemDao.findAllProblemsByCate(category);
    }

    public Problem getProblemInfo(int proId){
        return problemDao.selectProblemInfo(proId);
    }

}
