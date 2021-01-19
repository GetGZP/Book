package com.servlet;

import com.entity.Adminuser;
import com.service.AdminUserService;
import com.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@WebServlet("/adminuser")
public class AdminUserServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String op = req.getParameter("op");

        Class<? extends AdminUserServlet> aClass = this.getClass();

        try {
            Method method = aClass.getDeclaredMethod(op, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 调用service层查询adminuser表中的所有数据发送给页面
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findMore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Adminuser> list = new AdminUserService().findMore();
        JsonUtil.writeToJSON(list,resp);
    }

    /**
     * 存储session对象,并跳转到商家后台管理页面
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String auser = req.getParameter("auser");
        req.getSession().setAttribute("auser",auser);
        boolean b = false;
        JsonUtil.writeToJSON(b,resp);
    }

    /**
     * 判定是否已经登录
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void determineSession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object auser = req.getSession(false).getAttribute("auser");
        boolean more = false;
        if(auser != null){
            more = true;
        }
        JsonUtil.writeToJSON(more,resp);
    }
}

