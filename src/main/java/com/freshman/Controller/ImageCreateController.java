package com.freshman.Controller;

import com.freshman.Common.Response;
import com.freshman.Common.Status;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by tianxianglan on 2016/11/26.
 */
@Controller
public class ImageCreateController {
    @Autowired
    public Producer captchaProducer;

    @RequestMapping(value = "imageCreate", method = RequestMethod.GET)
    public Response createImage(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        response.setDateHeader("Expires",0);
        response.setHeader("Cache-Control","no-store,no cache,must-revalidate");
        response.addHeader("Cache-Control","post-check=0,pre-chech=0");
        response.setHeader("pragma", "no-cache");
        response.setContentType("image/jpeg");

        String capText = captchaProducer.createText();
        session.setAttribute("capText",capText);
        System.out.println(capText);
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        out.flush();
        out.close();
        return new Response(Status.SUCCESS);
    }
}
