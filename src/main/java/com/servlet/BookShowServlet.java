package com.servlet;

import com.entity.Book;
import com.entity.BookType;
import com.service.BookShowService;
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

@WebServlet("/bookshow")
public class BookShowServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String op = req.getParameter("op");

        Class<? extends BookShowServlet> aClass = this.getClass();

        try {
            Method method = aClass.getDeclaredMethod(op, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询正常状态的所有商品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findMore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = new Book();
        BookType bookType = new BookType();
        if(req.getParameter("tname") != null){
            String tname = req.getParameter("tname");
            bookType.setTname(tname);
        }
        if(req.getParameter("bid")!=null){
            int bid = Integer.parseInt(req.getParameter("bid"));
            book.setBid(bid);
        }
        if (req.getParameter("tid")!=null){
            int tid = Integer.parseInt(req.getParameter("tid"));
            bookType.setTid(tid);
        }
        book.setBookType(bookType);
        book.setBstate("正常");
        List<Book> list = new BookShowService().findMore(book);
        JsonUtil.writeToJSON(list,resp);
    }

    /**
     * 查询热卖商品根据库存排序
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void  findegroup(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> list = new BookShowService().findegroup();
        JsonUtil.writeToJSON(list,resp);
    }

}
