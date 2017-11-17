package com.freshman.Util;

import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tianxianglan on 2016/11/19.
 */
public class Tools {

    private Tools() {
        throw new IllegalStateException("no instance");
    }

    //获取四个随机的字母
    public static String getFourWords(){
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String words = "";
        for (int i = 0; i<4; i++){
            words += chars.charAt((int)(0+ Math.random()*25));
        }
        return words;
    }

    //获取加盐后的密码，形参为生成的四个随机的字母以及用户输入的密码
    public static String saltiedPasswd(String passwd, String fourWords){
        String unChangerPasswd = passwd+ fourWords;
        String changedPasswd = DigestUtils.md5DigestAsHex(unChangerPasswd.getBytes());
        return changedPasswd;
    }

    //根据student表中的数据形成user表中的学历字段
    public static String getGrade(String education, String stuid){
        Map<String, String> map = new HashMap<String, String>();
        map.put("本科生","大");
        map.put("本科","大");
        map.put("硕士","研");
        map.put("硕士研究生","研");
        map.put("研究生","研");
        map.put("博士研究生","博");
        map.put("博士","博");
        map.put("2016","一");
        map.put("2015","二");
        map.put("2014","三");
        map.put("2013","四");

        String year = stuid.substring(0,4);
        String grade = map.get(education)+ map.get(year);
        return grade;
    }

    public static Map<Integer, String> getCategory(){
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(2,"后台");
        map.put(3,"前端");
        map.put(4,"IOS");
        map.put(5,"安卓");
        map.put(6,"运维");
        map.put(7,"设计");
        map.put(8,"产品");
        return map;
    }
}
