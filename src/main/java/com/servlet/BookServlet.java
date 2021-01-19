package com.servlet;


import com.entity.Book;
import com.entity.BookType;
import com.github.pagehelper.PageInfo;
import com.service.BookService;
import com.util.FileIO.ParamDto;
import com.util.FileIO.RequestUtil;
import com.util.JsonUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

@WebServlet("/bookservlet")
public class BookServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String op = req.getParameter("op");

        Class<? extends BookServlet> aClass = this.getClass();

        try {
            Method method = aClass.getDeclaredMethod(op, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 带分页的数据展示以及模糊查询and id查询
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findMore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNum = 0;
        int pageSize = 0;
        if(req.getParameter("pageNum")!=null){
            pageNum = Integer.parseInt(req.getParameter("pageNum"));
        }
        if(req.getParameter("pageSize")!=null){
            pageSize = Integer.parseInt(req.getParameter("pageSize"));
        }
        Book book = new Book();
        BookType bookType = new BookType();
        if(req.getParameter("bid")!=null){
            int bid = Integer.parseInt(req.getParameter("bid"));
            book.setBid(bid);
        }
        if(req.getParameter("bname")!=null){
            String bname = req.getParameter("bname");
            book.setBname(bname);
        }
        if(req.getParameter("tname")!=null){
            String tname = req.getParameter("tname");
            bookType.setTname(tname);
        }
        book.setBookType(bookType);
        PageInfo<Book> info = new BookService().findMore(book, pageNum, pageSize);
        JsonUtil.writeToJSON(info,resp);
    }

    /**
     * 根据传入的数据修改一条数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void updateID(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Book book = new Book();
        BookType bookType = new BookType();

        //判断表单属性enctype,值为multipart/form-data
        if (ServletFileUpload.isMultipartContent(req)){
            //调用封装的方法,将所有解析结果封装到ParamDto对象中
            ParamDto dto = RequestUtil.parseParam(req);

            int bid = Integer.parseInt(dto.getParamMap().get("id"));
            String bname = dto.getParamMap().get("updatebname");
            int bnumbers = Integer.parseInt(dto.getParamMap().get("updatebnumbers"));
            String tname = dto.getParamMap().get("updatestate");
            int binventory = Integer.parseInt(dto.getParamMap().get("updatebinventory"));
            String bauthor = dto.getParamMap().get("updatebauthor");
            String bpress = dto.getParamMap().get("updatebpress");
            double bbaseprice = Double.parseDouble(dto.getParamMap().get("updatebbaseprice"));
            double bprice = Double.parseDouble(dto.getParamMap().get("updatebprice"));
            String bdetails = dto.getParamMap().get("updatebdetails");
            String bstate = dto.getParamMap().get("updatebstate");

            book.setBid(bid);
            book.setBname(bname);
            book.setBnumbers(bnumbers);

            bookType.setTname(tname);
            book.setBookType(bookType);
            book.setBinventory(binventory);
            book.setBauthor(bauthor);
            book.setBpress(bpress);
            book.setBbaseprice(bbaseprice);
            book.setBprice(bprice);
            book.setBdetails(bdetails);
            book.setBstate(bstate);

            FileItem item = dto.getFileMap().get("xdaTanFileImg");
            UUID uuid = UUID.randomUUID();
            if (item != null){
                String fileName = item.getName();
                String path = this.getServletContext().getRealPath("/img");
                item.write(new File(path,uuid + fileName));
                book.setPath("/Book/img/"+ uuid + fileName);
            }
        }
            boolean b = new BookService().updateID(book);
            JsonUtil.writeToJSON(b,resp);
    }

    /**
     * 修改单条数据状态
     * @param req
     * @param resp
     * @throws Exception
     */
    public void upbstate(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int bid = Integer.parseInt(req.getParameter("bid"));
        String bstate = req.getParameter("bstate");
        Book book = new Book();
        book.setBid(bid);
        book.setBstate(bstate);
        boolean b = new BookService().upbstate(book);
        JsonUtil.writeToJSON(b,resp);

    }

    /**
     * 批量修改书籍状态
     * @param req
     * @param resp
     * @throws Exception
     */
    public void stateupdates(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String bstate = "已下架";
        String[] bid = req.getParameterValues("bid");
        boolean b = new BookService().stateupdates(bstate, bid);
        JsonUtil.writeToJSON(b,resp);
    }

    /**
     * 新增一条数据
     * @param req
     * @param resp
     * @throws Exception
     */
    public void insertADD(HttpServletRequest req, HttpServletResponse resp) throws Exception {
         Book book = new Book();
        BookType bookType = new BookType();

        //判断表单属性enctype,值为multipart/form-data
        if (ServletFileUpload.isMultipartContent(req)){
            //调用封装的方法,将所有解析结果封装到ParamDto对象中
            ParamDto dto = RequestUtil.parseParam(req);

            String bname = dto.getParamMap().get("updatebname");
            int bnumbers = Integer.parseInt(dto.getParamMap().get("updatebnumbers"));
            int tid = Integer.parseInt(dto.getParamMap().get("updatestate"));
            int binventory = Integer.parseInt(dto.getParamMap().get("updatebinventory"));
            String bauthor = dto.getParamMap().get("updatebauthor");
            String bpress = dto.getParamMap().get("updatebpress");
            double bbaseprice = Double.parseDouble(dto.getParamMap().get("updatebbaseprice"));
            double bprice = Double.parseDouble(dto.getParamMap().get("updatebprice"));
            String bdetails = dto.getParamMap().get("updatebdetails");
            String bstate = dto.getParamMap().get("updatebstate");


            book.setBname(bname);
            book.setBnumbers(bnumbers);

            bookType.setTid(tid);
            book.setBookType(bookType);
            book.setBinventory(binventory);
            book.setBauthor(bauthor);
            book.setBpress(bpress);
            book.setBbaseprice(bbaseprice);
            book.setBprice(bprice);
            book.setBdetails(bdetails);
            book.setBstate(bstate);
            UUID uuid = UUID.randomUUID();
            FileItem item = dto.getFileMap().get("xdaTanFileImg");
            if (item != null){
                String fileName = item.getName();
                String path = this.getServletContext().getRealPath("/img");
                item.write(new File(path,uuid + fileName));
                book.setPath("/Book/img/"+ uuid + fileName);
            }
        }
        boolean b = new BookService().insertADD(book);
        JsonUtil.writeToJSON(b,resp);

    }


}
