package com.servlet;

import com.entity.Address;
import com.entity.OrderList;
import com.entity.ShopOrderTable;
import com.entity.User;
import com.service.OrderListService;
import com.service.ShopOrderTableService;
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
@WebServlet("/myorderpage")
public class MyOrderPageServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String op = req.getParameter("op");

        Class<? extends MyOrderPageServlet> aClass = this.getClass();

        try {
            Method method = aClass.getDeclaredMethod(op, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有订单信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findByMore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderList orderList = new OrderList();
        if(req.getParameter("onumbers")==null){
            orderList.setOnumbers(0);
        }else {
            orderList.setOnumbers(Integer.parseInt(req.getParameter("onumbers")));
        }
        User user = new User();
        if(req.getParameter("II") != null && !req.getParameter("II").equals("guan")){
            int uid = (int) req.getSession().getAttribute("uid");
            user.setUid(uid);
        }
        orderList.setUser(user);
        List<OrderList> list = new OrderListService().findByMore(orderList);
        JsonUtil.writeToJSON(list,resp);
    }

    /**
     * 查询所有订单信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findByMore1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderList orderList = new OrderList();
        if(req.getParameter("onumbers")==null){
            orderList.setOnumbers(0);
        }else {
            orderList.setOnumbers(Integer.parseInt(req.getParameter("onumbers")));
        }
        User user = new User();
        if(!req.getParameter("II").equals("guan")){
            int uid = (int) req.getSession().getAttribute("uid");
            user.setUid(uid);
        }
        orderList.setUser(user);
        List<OrderList> list = new OrderListService().findByMore1(orderList);
        JsonUtil.writeToJSON(list,resp);
    }

    /**
     * 修改订单状态
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void updateMore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int uid = (int) req.getSession().getAttribute("uid");
        int onumbers = Integer.parseInt(req.getParameter("onumbers"));
        String ostatus = req.getParameter("ostatus");
        User user = new User();
//        user.setUid(uid);
        Address address = new Address();
        OrderList orderList = new OrderList();
        orderList.setUser(user);
        orderList.setAddress(address);
        orderList.setOnumbers(onumbers);
        orderList.setOstatus(ostatus);
        boolean b = new OrderListService().updateMore(orderList);
        JsonUtil.writeToJSON(b,resp);
    }

    /**
     * 查询订单详情
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findByMores(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int onumbers = Integer.parseInt(req.getParameter("onumbers"));
        List<ShopOrderTable> list = new ShopOrderTableService().findByMore(onumbers);
        JsonUtil.writeToJSON(list,resp);
    }
}
