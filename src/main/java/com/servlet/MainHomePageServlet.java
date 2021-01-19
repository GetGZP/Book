package com.servlet;

import com.entity.Book;
import com.entity.BookType;
import com.service.MainHomePageService;
import com.service.ShoppingCarService;
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

@WebServlet("/mainhomepage")
public class MainHomePageServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String op = req.getParameter("op");

        Class<? extends MainHomePageServlet> aClass = this.getClass();

        try {
            Method method = aClass.getDeclaredMethod(op, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据tid进行查询
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findMore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int tid = Integer.parseInt(req.getParameter("tid"));
        Book book = new Book();
        BookType bookType = new BookType();
        bookType.setTid(tid);
        book.setBookType(bookType);
        List<Book> list = new MainHomePageService().findMore(book);
        JsonUtil.writeToJSON(list,resp);
    }

    /**
     * 查询是否已经登录
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void getSession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uname = (String) req.getSession(false).getAttribute("uname");
        JsonUtil.writeToJSON(uname,resp);
    }

    /**
     * 注销session
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void removeSession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        session.removeAttribute("uname");
        boolean b = false;
        JsonUtil.writeToJSON(b,resp);
    }

    /**
     * 查询购物车中有几件商品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findNumber(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int number = 0;
        if(req.getSession().getAttribute("uid")!=null){
            int uid = (int) req.getSession().getAttribute("uid");
            number = new ShoppingCarService().findNumber(uid);
        }
        JsonUtil.writeToJSON(number,resp);
    }
}
