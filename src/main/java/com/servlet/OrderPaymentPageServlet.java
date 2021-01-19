package com.servlet;

import com.entity.Address;
import com.entity.OrderList;
import com.entity.User;
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
import java.util.List;

@WebServlet("/orderpayment")
public class OrderPaymentPageServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String op = req.getParameter("op");

        Class<? extends OrderPaymentPageServlet> aClass = this.getClass();

        try {
            Method method = aClass.getDeclaredMethod(op, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询订单信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findByMore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int uid = (int) req.getSession().getAttribute("uid");
        int Onumbers = Integer.parseInt(req.getParameter("Onumbers"));
        OrderList orderList = new OrderList();
        User user = new User();
        user.setUid(uid);
        orderList.setUser(user);
        orderList.setOnumbers(Onumbers);
        List<OrderList> list = new OrderListService().findByMore(orderList);
        JsonUtil.writeToJSON(list,resp);

    }

    public void updateMore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int uid = (int) req.getSession().getAttribute("uid");
        int Onumbers = Integer.parseInt(req.getParameter("Onumbers"));
        OrderList orderList = new OrderList();
        User user = new User();
        Address address = new Address();
        user.setUid(uid);
        orderList.setUser(user);
        orderList.setOnumbers(Onumbers);
        orderList.setOstatus("待发货");
        orderList.setAddress(address);
        boolean b = new OrderListService().updateMore(orderList);
        JsonUtil.writeToJSON(b,resp);
    }
}
