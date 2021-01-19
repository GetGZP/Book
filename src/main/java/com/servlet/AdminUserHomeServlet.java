package com.servlet;

import com.entity.Adminuser;
import com.service.AdminUserHomeService;
import com.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebServlet("/adminuserhomeservlet")
public class AdminUserHomeServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String op = req.getParameter("op");

        Class<? extends AdminUserHomeServlet> aClass = this.getClass();

        try {
            Method method = aClass.getDeclaredMethod(op, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据用户名去查询用户信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findMore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Adminuser adminuser;
        if(req.getSession().getAttribute("auser")!=null){
            String auser = (String) req.getSession().getAttribute("auser");
            adminuser = new AdminUserHomeService().findMore(auser);
        }else {
            adminuser = null;
        }
        System.out.println(adminuser);
        JsonUtil.writeToJSON(adminuser,resp);
    }

    /**
     * 注销session
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void remove(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        session.removeAttribute("auser");
        session.removeAttribute("uid");
    }

}
