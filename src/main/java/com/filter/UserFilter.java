package com.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class UserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        ArrayList<String> list = new ArrayList<>();
        list.add("/UserPersonalCenter/MallHomePageDetailsDisplay.html");
        list.add("/UserPersonalCenter/UserloginPage.html");
        list.add("/UserPersonalCenter/BookDetailsDisplay.html");
        String s = req.getServletPath();
        if(s.equals("/UserPersonalCenter/UserloginPage.html") && session.getAttribute("uname")!=null){
            resp.sendRedirect("/Book/MainHomePage.html");
        }else if(session.getAttribute("uname")!=null || list.contains(s)){
            chain.doFilter(request,response);
        }else {
            resp.sendRedirect("/Book/UserPersonalCenter/UserloginPage.html");
        }
    }
}
