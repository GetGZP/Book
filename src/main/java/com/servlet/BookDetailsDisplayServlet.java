package com.servlet;

import com.entity.Book;
import com.entity.ShoppingCar;
import com.entity.User;
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
@WebServlet("/bookdetailsdisplay")
public class BookDetailsDisplayServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String op = req.getParameter("op");

        Class<? extends BookDetailsDisplayServlet> aClass = this.getClass();

        try {
            Method method = aClass.getDeclaredMethod(op, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 新增一条购物车并查找sid
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void ADD(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer sid;
        int bid = Integer.parseInt(req.getParameter("bid"));
        double bprice = Double.parseDouble(req.getParameter("bprice"));
        if(req.getSession(false).getAttribute("uid")!=null){
            int uid = (int) req.getSession().getAttribute("uid");
            Book book = new Book();
            book.setBid(bid);
            book.setBprice(bprice);
            User user = new User();
            user.setUid(uid);
            ShoppingCar shoppingCar = new ShoppingCar();
            shoppingCar.setBook(book);
            shoppingCar.setUser(user);
            sid = new ShoppingCarService().ADDCar1(shoppingCar);
        }
        else {
            sid = 0;
        }

        JsonUtil.writeToJSON(sid,resp);
    }
}
