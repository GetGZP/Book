package com.servlet;

import com.entity.BookType;
import com.service.BookTypeService;
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

@WebServlet("/booktype")
public class BookTypeServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String op = req.getParameter("op");

        Class<? extends BookTypeServlet> aClass = this.getClass();

        try {
            Method method = aClass.getDeclaredMethod(op, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 调用此方法会查询书籍类型表中的所有信息并将信息放入前端
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findMore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<BookType> list = new BookTypeService().findMore();
        JsonUtil.writeToJSON(list,resp);

    }

    /**
     * 调用此方法会根据ID去查询一条数据发送到前端
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findBY(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int tid = Integer.parseInt(req.getParameter("tid"));
        BookType bookType = new BookType();
        bookType.setTid(tid);
        List<BookType> list = new BookTypeService().findBY(bookType);
        JsonUtil.writeToJSON(list,resp);
    }

    /**
     * 根据传入的数据去修改一条信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void updateID(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int tid = Integer.parseInt(req.getParameter("hdtid"));
        String tname = req.getParameter("tname");
        String tstate = req.getParameter("state");
        BookType bookType = new BookType(tid,tname,tstate);
        boolean b = new BookTypeService().updateID(bookType);
        JsonUtil.writeToJSON(b,resp);
    }

    /**
     * 根据传入的ID删除一条数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void  deleteID(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int tid = Integer.parseInt(req.getParameter("tid"));
        boolean b = new BookTypeService().deleteID(tid);
        JsonUtil.writeToJSON(b,resp);
    }

    /**
     * 根据传入的数据新增一条数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void insertADD(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tname = req.getParameter("intname");
        String tstate = req.getParameter("instate");
        BookType bookType = new BookType(tname,tstate);
        boolean b = new BookTypeService().insertADD(bookType);
        JsonUtil.writeToJSON(b,resp);
    }


}
