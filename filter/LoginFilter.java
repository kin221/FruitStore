package edu.ncucst.fruitweb.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//过滤器
//必须登录才能进入网站，否则跳转至登录页面 注册页面除外

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //判断访问路径是否包含登录注册所需要的资源
        HttpServletRequest req = (HttpServletRequest) request;
        String[] urls = {"/index.jsp", "/loginServlet","/images/", "/css/", "/js/", "/checkServlet"};
        String url = req.getRequestURL().toString();

        //循环判断是否包含上述资源
        for (String s : urls) {
            if (url.contains(s)) {
                chain.doFilter(request,response);
                return;
            }
        }

        //判断session是否有user对象
        HttpSession session = req.getSession();
        Object user = session.getAttribute("User");

        //判断user是否为空
        if (user != null) {
            //有登录 放行
            chain.doFilter(request,response);

        }else {
            //未登录，跳转到登录界面
            req.setAttribute("msg","尚未登录，请先登录！");
            req.getRequestDispatcher("index.jsp").forward(request,response);
        }

    }
}
