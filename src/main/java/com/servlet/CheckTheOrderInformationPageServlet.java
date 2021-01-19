package com.servlet;

import com.entity.Address;
import com.entity.OrderList;
import com.entity.ShopOrderTable;
import com.entity.User;
import com.service.AddressService;
import com.service.CheckTheOrderInformationPageService;
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

@WebServlet("/checktheorder")
public class CheckTheOrderInformationPageServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String op = req.getParameter("op");

        Class<? extends CheckTheOrderInformationPageServlet> aClass = this.getClass();

        try {
            Method method = aClass.getDeclaredMethod(op, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询地址根据uid
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findMore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int uid = (int) req.getSession().getAttribute("uid");
        User user = new User();
        user.setUid(uid);
        Address address = new Address();
        address.setUser(user);
        List<Address> list = new AddressService().findMore(address);
        JsonUtil.writeToJSON(list,resp);
    }

    /**
     * 根据订单编号查询订单详情
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findByMore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int Onumbers = Integer.parseInt(req.getParameter("Onumbers"));
        List<ShopOrderTable> list = new ShopOrderTableService().findByMore(Onumbers);
        JsonUtil.writeToJSON(list,resp);
    }

    /**
     * 付款订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void updateMore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int Onumbers = Integer.parseInt(req.getParameter("onumbers"));
        int adid = Integer.parseInt(req.getParameter("adid"));
        OrderList orderList = new OrderList();
        orderList.setOnumbers(Onumbers);
        Address address = new Address();
        address.setAdid(adid);
        orderList.setAddress(address);
//        orderList.setOstatus("待发货");
        boolean b = new CheckTheOrderInformationPageService().updateMore(orderList);
        JsonUtil.writeToJSON(b,resp);
    }
}
