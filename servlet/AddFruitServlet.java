package edu.ncucst.fruitweb.servlet;

import edu.ncucst.fruitweb.service.AdminService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddFruitServlet", urlPatterns = "/addFruitServlet")
public class AddFruitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.setCharacterEncoding("utf-8");
       String number,fruitname,price,unit;
        number = request.getParameter("number");
        fruitname = request.getParameter("name");
        price = request.getParameter("price");
        unit = request.getParameter("unit");
        AdminService service = new AdminService();
        try{
        if(service.addFruitItem(number,fruitname,price,unit)){
            request.getRequestDispatcher("listFruitServlet").forward(request,response);
        }else {
            String msg="水果编号不能重复，请验证后重新输入";
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer= response.getWriter();
            writer.println( "<h2>"+msg+"</h2>");
            writer.println("<a href=add.jsp>添加水果<a/><br/>");
            writer.println("<a href=listFruitServlet>查看水果<a/>");
            writer.close();
        }}catch (Exception e){
            String msg="您输入的数据格式有错误，请验证后重新输入";
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer= response.getWriter();
            writer.println( "<h2>"+msg+"</h2>");
            writer.println("<a href=add.jsp>添加水果<a/><br/>");
            writer.println("<a href=listFruitServlet>查看水果<a/>");
            writer.close();
        }
    }
}
