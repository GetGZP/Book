package com.servlet;

import com.entity.Book;
import com.entity.OrderList;
import com.entity.ShoppingCar;
import com.entity.User;
import com.service.OrderListService;
import com.service.ShoppingCarService;
import com.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@WebServlet("/shoppingcar")
public class ShoppingCarServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String op = req.getParameter("op");

        Class<? extends ShoppingCarServlet> aClass = this.getClass();

        try {
            Method method = aClass.getDeclaredMethod(op, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 给购物车新增一条数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void ADDCar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean b = false;
        if (req.getSession().getAttribute("uid")!=null){
            int bid = Integer.parseInt(req.getParameter("bid"));
            double bprice = Double.parseDouble(req.getParameter("bprice"));
            int uid = (int) req.getSession().getAttribute("uid");Book book = new Book();
            book.setBid(bid);
            book.setBprice(bprice);
            User user = new User();
            user.setUid(uid);
            ShoppingCar shoppingCar = new ShoppingCar();
            shoppingCar.setBook(book);
            shoppingCar.setUser(user);
            b = new ShoppingCarService().ADDCar(shoppingCar);
        }else {
           b = false;
        }

        JsonUtil.writeToJSON(b,resp);
    }

    /**
     * 查询该用户购物车中的所有数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findMore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int uid = (int) req.getSession().getAttribute("uid");
        ShoppingCar shoppingCar = new ShoppingCar();
        User user = new User();
        user.setUid(uid);
        shoppingCar.setUser(user);
        List<ShoppingCar> more = new ShoppingCarService().findMore(shoppingCar);
        JsonUtil.writeToJSON(more,resp);
    }

    /**
     * 更改数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findPanDing(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int uid = (int) req.getSession().getAttribute("uid");
        int snumber = Integer.parseInt(req.getParameter("snumber"));
        int bid = Integer.parseInt(req.getParameter("bid"));
        String panding = req.getParameter("panding");
        User user = new User();
        user.setUid(uid);
        Book book = new Book();
        book.setBid(bid);
        ShoppingCar shoppingCar = new ShoppingCar();
        shoppingCar.setUser(user);
        shoppingCar.setBook(book);
        shoppingCar.setSnumber(snumber);
        List<ShoppingCar> list = new ShoppingCarService().findPanDing(shoppingCar, panding);
        JsonUtil.writeToJSON(list,resp);
    }

    /**
     * 单击删除
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void deleteCar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int sid = Integer.parseInt(req.getParameter("sid"));
        int uid = (int) req.getSession().getAttribute("uid");
        User user = new User();
        user.setUid(uid);
        Book book = new Book();
        ShoppingCar shoppingCar = new ShoppingCar();
        shoppingCar.setUser(user);
        shoppingCar.setBook(book);
        shoppingCar.setSid(sid);
        boolean b = new ShoppingCarService().deleteCar(shoppingCar);
        JsonUtil.writeToJSON(b,resp);
    }

    /**
     * 生成订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findADDOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer Onumbers;
        if (req.getSession(false).getAttribute("uid")!=null){
            int uid = (int) req.getSession().getAttribute("uid");
            double omoney = Double.parseDouble(req.getParameter("omoney"));
            String[] sid = req.getParameterValues("sid");
            System.out.println("**************************");
            System.out.println(Arrays.toString(sid));
            OrderList orderList = new OrderList();
            orderList.setOstatus("待付款");
            User user = new User();
            user.setUid(uid);
            orderList.setOmoney(omoney);
            orderList.setUser(user);
            Onumbers = new OrderListService().findADDOrder(orderList, sid);
        }else {
            Onumbers = null;
        }
        JsonUtil.writeToJSON(Onumbers,resp);
    }
}
