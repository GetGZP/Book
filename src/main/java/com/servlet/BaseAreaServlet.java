package com.servlet;

import com.entity.BaseArea;
import com.service.BaseAreaService;
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

@WebServlet("/basearea")
public class BaseAreaServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String op = req.getParameter("op");

        Class<? extends BaseAreaServlet> aClass = this.getClass();

        try {
            Method method = aClass.getDeclaredMethod(op, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询省
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findProvince(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BaseArea> list = new BaseAreaService().findProvince();
        JsonUtil.writeToJSON(list,resp);
    }

    /**
     * 查询市
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findCity(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 11;
        if(req.getParameter("city")!=null){
            id = Integer.parseInt(req.getParameter("city"));
        }
        List<BaseArea> list = new BaseAreaService().findCity(id);
        JsonUtil.writeToJSON(list,resp);
    }

    /**
     * 查询地区
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findCounty(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 1101;
        if(req.getParameter("county")!=null){
            id = Integer.parseInt(req.getParameter("county"));
        }
        List<BaseArea> list = new BaseAreaService().findCounty(id);
        JsonUtil.writeToJSON(list,resp);
    }
}
