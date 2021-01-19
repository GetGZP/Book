package com.filter;

import javax.servlet.*;
import java.io.IOException;

    /**
     * 此类需要实现Tomcat的Filter接口来实现过滤功能
     * Filter的包为Tomcat的javax里的servlet包
     * 此类的过滤为将所有的通信都规范字符集为UTF-8
     * 目的是搞定乱码
     */
    public class MainFilter implements Filter{

        /**
         * 此方法为实现Filter接口里的方法
         * 作用是控制放行所有的页面
         * @param servletRequest 服务器获取的请求对象
         * @param servletResponse 服务器响应的返回对象
         * @param filterChain 过滤器链包含了相同过滤条件的所有过滤器
         * @throws IOException IO流异常
         * @throws ServletException 服务器异常
         */
        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            //设定所有经过此过滤器的内容编码格式为UTF-8
            servletRequest.setCharacterEncoding("UTF-8");
            servletResponse.setCharacterEncoding("UTF-8");
//        HttpServletRequest s = (HttpServletRequest) servletRequest;
//        HttpServletResponse rs = (HttpServletResponse) servletResponse;
//        s.setCharacterEncoding("UTH-8");
//        rs.setCharacterEncoding("UTF-8");
            //设定完成后执行放行
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }


