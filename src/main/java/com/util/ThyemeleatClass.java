package com.util;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ThyemeleatClass {
    public static void  writeData(HttpServletRequest req, HttpServletResponse resp, String htmlName) throws IOException, IOException {
        TemplateEngine te=new TemplateEngine();//模板引擎对象的创建

        ServletContextTemplateResolver r=new ServletContextTemplateResolver(req.getServletContext());//上下文解析器
        r.setPrefix("static/");//前置
        r.setSuffix(".html");//后置

        te.setTemplateResolver(r);//添加到模板引擎中
        resp.setCharacterEncoding("UTF-8");//设置响应的编码格式
        WebContext ctx = new WebContext(req, resp, req.getServletContext()) ;

        //模板引擎开始运行
        te.process(htmlName, ctx,resp.getWriter());//第一个参数是模板文件，第二个参数：包含请求，响应，application对象   ；第三个参数是一个输出流


    }
}
