package com.servlet;

import com.entity.User;
import com.service.UserService;
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

@WebServlet("/userservlet")
public class UserServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String op = req.getParameter("op");

        Class<? extends UserServlet> aClass = this.getClass();

        try {
            Method method = aClass.getDeclaredMethod(op, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判定登录,如果正确保存id和用户名
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findMore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String auser = req.getParameter("auser");
        String apsw = req.getParameter("apsw");
        List<User> list = new UserService().findMore(null);
        boolean b = false;
        for (User user: list) {
            if(auser.equals(user.getUname()) && apsw.equals(user.getUpsw())){
                b = true;
                req.getSession().setAttribute("uid",user.getUid());
                req.getSession().setAttribute("uname",user.getUname());
                break;
            }
        }
        JsonUtil.writeToJSON(b,resp);
    }

    /**
     * 新增一条用户信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void insertADD(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uname = req.getParameter("uname");
        String upsw = req.getParameter("upsw");
        String uemail = req.getParameter("uemail");
        User user = new User();
        user.setUname(uname);
        user.setUpsw(upsw);
        user.setUemail(uemail);
        boolean b = new UserService().insertADD(user);
//        if(b){
//            req.getSession().setAttribute("uid",user.getUid());
//            req.getSession().setAttribute("uname",user.getUname());
//        }
        JsonUtil.writeToJSON(b,resp);
    }
}
