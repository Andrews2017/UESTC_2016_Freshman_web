package com.freshman.Service;

import com.freshman.Dao.AdminDao;
import com.freshman.Dao.UserDao;
import com.freshman.Entity.TotalScore;
import com.freshman.Entity.User;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by tianxianglan on 2016/11/16.
 */
@Service
public class AdminService {
    @Autowired
    AdminDao adminDao;
    @Autowired
    UserDao userDao;

    public void modifyAuth(int uId, int uAuth) {
        adminDao.modifyAuth(uId, uAuth);
    }

    public void publishProblem(String title, String category, String content, int score, String level) {
        adminDao.publishProblem(title, category, content, score,level);
    }

    public List<Map> showRankingPage(String category, int pageNo){
        int lastPageNo = pageNo* 20;
        List<TotalScore> totalScoresList = adminDao.showRankingDesc(category, lastPageNo);
        List list = new ArrayList();
        for (TotalScore totalScore : totalScoresList){
            Map map = new HashMap();
            int stuId = totalScore.getNameId();
            User stu = userDao.findUserById(stuId);
            map.put("user", stu);
            map.put("totalscore", totalScore);
            list.add(map);
        }
        return list;
    }

    public int showUserNum(){ return adminDao.showUserNum(); }

    public List<User> showAllUsers(){
        return adminDao.showAllUsers();
    }

    public List<TotalScore> showAllRankingByScore(){
        List<String> categoryList = new ArrayList<String>();
        categoryList.add("后台");
        categoryList.add("前端");
        categoryList.add("IOS");
        categoryList.add("安卓");
        categoryList.add("运维");
        categoryList.add("设计");
        categoryList.add("产品");
        List<TotalScore> list = new ArrayList<TotalScore>();

        for (String category : categoryList){
            List<TotalScore> listOfTotalScore = new ArrayList<TotalScore>();
            listOfTotalScore = adminDao.showRankingByScoreAndCategory(category);
            for (TotalScore totalScore : listOfTotalScore){
                list.add(totalScore);
            }
        }
        return list;
    }

    public void insertCell(HSSFRow row, int i, Object object){
        if (object == null){
            row.createCell(i).setCellValue("");
        }else {
            row.createCell(i).setCellValue(object.toString());
        }
    }

    public void exportExcel(HttpServletRequest request, HttpServletResponse response){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //创建一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //在表中添加一个sheet
        HSSFSheet sheet = workbook.createSheet();
        //在sheet中添加表头
        HSSFRow row = sheet.createRow(0);
        //创建单元格并设置表头居中
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置表头信息
        List<String> excelHead = new ArrayList<String>();
        excelHead.add("方向");
        excelHead.add("姓名");
        excelHead.add("年级");
        excelHead.add("成绩");
        excelHead.add("专业");
        sheet.setColumnWidth(4,7000);
        //excel头
        HSSFCell cell = null;
        for (int i = 0; i<excelHead.size(); i++){
            cell = row.createCell(i);
            cell.setCellValue(excelHead.get(i));
            cell.setCellStyle(style);
        }

        List<TotalScore> list = showAllRankingByScore();
        for (int i = 0; i< list.size(); i++){
            row = sheet.createRow(i+ 1);
            TotalScore totalScore = list.get(i);
            User user = userDao.findUserById(totalScore.getNameId());

            int j = 0;
            insertCell(row,j++,totalScore.getCategory());
            insertCell(row,j++,totalScore.getName());
            insertCell(row,j++,user.getGrade());
            insertCell(row,j++,totalScore.getScore());
            insertCell(row,j++,user.getMajor());
        }
        try {
            //将此工作簿写入输出流中
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = out.toByteArray();

        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = format.format(date);
        try {
            byte[] fileNameByte = ("所有成绩-"+ currentTime+ ".xls").getBytes("GBK");
            String fileName = new String(fileNameByte, "ISO8859-1");
//            String fileName = "所有成绩"+ currentTime+ ".xls";,不进行编码转换无法显示中文
            //下载文件，返回流信息
            response.setContentType("application/x-msdownload");//"application/octet-stream"
//            response.setContentLength(bytes.length);
            //通知客户端以下载的方式接受数据，设置响应头
            response.setHeader("Content-Disposition","attachment;filename="+ fileName);
            response.getOutputStream().write(bytes);
            out.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}