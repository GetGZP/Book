package com.servlet;

import com.entity.User;
import com.service.UserService;
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
import java.util.List;
import java.util.UUID;

@WebServlet("/usermodifies")
public class UserModifiesAvatarServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String op = req.getParameter("op");

        Class<? extends UserModifiesAvatarServlet> aClass = this.getClass();

        try {
            Method method = aClass.getDeclaredMethod(op, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    /**
     * 修改头像
     * @param req
     * @param resp
     * @throws Exception
     */
    public void updateUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int uid = (int) req.getSession().getAttribute("uid");
        User user = new User();
        user.setUid(uid);
        //判断表单属性enctype,值为multipart/form-data
        if (ServletFileUpload.isMultipartContent(req)){
            //调用封装的方法,将所有解析结果封装到ParamDto对象中
            ParamDto dto = RequestUtil.parseParam(req);
            FileItem item = dto.getFileMap().get("xdaTanFileImg");
            UUID uuid = UUID.randomUUID();
            if(item != null){
                String name = item.getName();
                String realPath = this.getServletContext().getRealPath("/img");
                item.write(new File(realPath,uuid + name));
                user.setUphoto("/Book/img/"+ uuid + name);
            }
        }
        boolean b = new UserService().updateUser(user);
        JsonUtil.writeToJSON(b,resp);
    }

    /**
     * 根据UID查询头像信息
     * @param req
     * @param resp
     * @throws Exception
     */
    public void findMore(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int uid = (int) req.getSession().getAttribute("uid");
        System.out.println(uid);
        User user = new User();
        user.setUid(uid);
        List<User> list = new UserService().findMore(user);
        JsonUtil.writeToJSON(list,resp);
    }
}
