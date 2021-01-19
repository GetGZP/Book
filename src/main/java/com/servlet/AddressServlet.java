package com.servlet;

import com.entity.Address;
import com.entity.User;
import com.service.AddressService;
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

@WebServlet("/address")
public class AddressServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String op = req.getParameter("op");

        Class<? extends AddressServlet> aClass = this.getClass();

        try {
            Method method = aClass.getDeclaredMethod(op, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有地址信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findMore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Address address = new Address();
        User user = new User();
        int uid = (int) req.getSession().getAttribute("uid");
        if(req.getParameter("adid")!=null){
            int adid = Integer.parseInt(req.getParameter("adid"));
            address.setAdid(adid);
        }
        user.setUid(uid);
        address.setUser(user);
        List<Address> list = new AddressService().findMore(address);
        JsonUtil.writeToJSON(list,resp);
    }

    /**
     * 新增一条数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void insertADD(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int uid = (int) req.getSession().getAttribute("uid");
        Address address = new Address();
        User user = new User();
        user.setUid(uid);
        address.setUser(user);
        String adname = req.getParameter("adname");
        String q1 = req.getParameter("op1");
        String q2 = req.getParameter("op2");
        String q3 = req.getParameter("op3");
        String ad = req.getParameter("adress");
        String adphone = req.getParameter("adphone");
        String adress = q1 + q2 + q3 + ad;
        address.setAdress(adress);
        address.setAdphone(adphone);
        address.setAdname(adname);
        boolean b = new AddressService().insertADD(address);
        JsonUtil.writeToJSON(b,resp);
    }

    /**
     * 保存修改的数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void updateID(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            int upadid = Integer.parseInt(req.getParameter("upadid"));
            String upadname = req.getParameter("upadname");
            String upadphone = req.getParameter("upadphone");
            String upadress = req.getParameter("upadress");
            Address address = new Address(upadid,upadname,upadphone,upadress);
        boolean b = new AddressService().updateID(address);
        JsonUtil.writeToJSON(b,resp);
    }

    /**
     * 删除单条数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void deleteID(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int adid = Integer.parseInt(req.getParameter("adid"));
        boolean b = new AddressService().deleteID(adid);
        JsonUtil.writeToJSON(b,resp);
    }
}
