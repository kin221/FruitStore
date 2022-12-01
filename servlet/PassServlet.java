package edu.ncucst.fruitweb.servlet;

import edu.ncucst.fruitweb.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "PassServlet", urlPatterns = "/passServlet")
public class PassServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
             doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String US="admin";
        final int i=1;
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        UserService user=new UserService();
        try {
            //String username=request.getParameter("username");
            String password1=request.getParameter("mpass");
            String password=request.getParameter("password");
            if(password.equals(password1)==false){
                user.updataFruitItem(i,US,password);
                request.getRequestDispatcher("pass.jsp").forward(request,response);
            else {
                PrintWriter out=response.getWriter();
                out.println("<html><body>");
                out.println("<h2>"+"密码和上次的密码一致，请重新输入!"+"</h2><br>");
                out.println("<a href=pass.jsp>修改密码</a><br>");
                out.println("</body></html>");
                out.close();
            }
        }catch (Exception e){
            response.getWriter().println(e);
        }
    }
}
