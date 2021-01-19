package com.servlet;

import com.entity.User;
import com.service.UserService;
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
import java.util.List;

@WebServlet("/userchangepassword")
public class UserChangePasswordServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String op = req.getParameter("op");

        Class<? extends UserChangePasswordServlet> aClass = this.getClass();

        try {
            Method method = aClass.getDeclaredMethod(op, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询出此用户的所有信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findMore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int uid = (int) req.getSession().getAttribute("uid");
        User user = new User();
        user.setUid(uid);
        List<User> list = new UserService().findMore(user);
        JsonUtil.writeToJSON(list,resp);
    }

    /**
     * 执行修改密码,如果成功注销session
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newpsw = req.getParameter("ps");
        int uid = (int) req.getSession().getAttribute("uid");
        User user = new User();
        user.setUpsw(newpsw);
        user.setUid(uid);
        boolean b = new UserService().updateUser(user);
        if(b){
            HttpSession session = req.getSession(false);
            session.removeAttribute("uname");
            session.removeAttribute("uid");
        }
        JsonUtil.writeToJSON(b,resp);
    }
}
