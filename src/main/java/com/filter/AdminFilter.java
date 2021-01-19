package com.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        ArrayList<String> list = new ArrayList<>();
        list.add("/BusinessManagementPage/MerchantLoginPage.html");
        String s = req.getServletPath();
        if(s.equals("/BusinessManagementPage/MerchantLoginPage.html") && (session.getAttribute("auser"))!=null){
            resp.sendRedirect("/Book/BusinessManagementPage/BusinessBackgroundHomePage.html");
        }else if(session.getAttribute("auser")!=null || list.contains(s)){
            chain.doFilter(request,response);
        }else {
            resp.sendRedirect("/Book/BusinessManagementPage/MerchantLoginPage.html");
        }
    }
}
