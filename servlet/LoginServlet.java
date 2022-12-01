package edu.ncucst.fruitweb.servlet;

import edu.ncucst.fruitweb.bean.User;
import edu.ncucst.fruitweb.utils.JDBCTools;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "LoginServlet", urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Connection conn=null;
        ResultSet rs=null;

        String username,password,checkcode,savecode;
        username = request.getParameter("name").trim();
        password = request.getParameter("password").trim();
        checkcode =request.getParameter("code");
        savecode =(String) request.getSession().getAttribute("savecode");


        PreparedStatement pstmt=null;
        String sql="select * from user where username=? and password=?";


        if (savecode.equalsIgnoreCase(checkcode))
        {
        try{
            conn= JDBCTools.getConn();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            rs=pstmt.executeQuery();

            if(rs.next()){
                User user=new User();
                user.setUsername(username);
                user.setPassword(password);
                request.getSession().setAttribute("User",user);
                request.getRequestDispatcher("main.jsp").forward(request,response);
            }else {
                String msg="您输入的用户名或密码错误，请验证后重新输入";
                response.setContentType("text/html;charset=utf-8");
                PrintWriter writer= response.getWriter();
                writer.println( "<h2>"+username+"登录反馈<br/>"+msg+"</h2>");
                writer.println(" 返回登录页面重新登录<br/>");
                writer.println("<a href=index.jsp>返回登录页面<a/>");
                writer.close();
            }
        } catch (Exception e){

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer= response.getWriter();
            writer.println( "<h2>"+username+"登录反馈<br/>"+e.getMessage()+"</h2>");
            writer.println(" 返回登录页面重新登录<br/>");
            writer.println("<a href=index.jsp>返回登录页面<a/>");
            writer.close();

        } finally {
            JDBCTools.release(conn,pstmt,rs);
        }
    }else {
           String msg="您输入的验证码错误，请验证后重新输入";
           response.setContentType("text/html;charset=utf-8");
            PrintWriter writer= response.getWriter();
           writer.println( "<h2>"+msg+"</h2>");
            writer.println(" 返回登录页面重新登录<br/>");
            writer.println("<a href=index.jsp>返回登录页面<a/>");
           writer.close();
        }
   }


}
