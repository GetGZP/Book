package com.servlet;

import com.service.OrderListService;
import com.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

@WebServlet("/personalhomepage")
public class PersonalHomepageServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String op = req.getParameter("op");

        Class<? extends PersonalHomepageServlet> aClass = this.getClass();

        try {
            Method method = aClass.getDeclaredMethod(op, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询特殊值
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void find(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int uid = (int) req.getSession().getAttribute("uid");
        HashMap<String, Object> map = new OrderListService().find(uid);
        Integer integer = new OrderListService().findBystate("待付款", uid);
        Integer integer2 = new OrderListService().findBystate("已发货", uid);
        map.put("STATU1",integer);
        map.put("STATU2",integer2);
        JsonUtil.writeToJSON(map,resp);
    }

//    /**
//     * 查询特定状态的件数
//     * @param req
//     * @param resp
//     * @throws ServletException
//     * @throws IOException
//     */
//    public void findBystate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        String ostatus = req.getParameter("ostatus");
//
//        JsonUtil.writeToJSON(integer,resp);
//    }
}
