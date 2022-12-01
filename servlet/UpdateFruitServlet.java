package edu.ncucst.fruitweb.servlet;

import edu.ncucst.fruitweb.service.AdminService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UpdateFruitServlet", urlPatterns = "/updateFruitServlet")
public class UpdateFruitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String number, fruitname, price, unit;
        number = request.getParameter("number").trim();
        fruitname = request.getParameter("name").trim();
        price = request.getParameter("price").trim();
        unit = request.getParameter("unit").trim();
        AdminService service = new AdminService();
        try {
            boolean flag = service.updataFruitItem(number, fruitname, price, unit);
            if (flag) {
                request.setAttribute("msg", "修改成功!");

            } else {
                request.setAttribute("msg", "修改失败!");
            }
            request.getRequestDispatcher("listFruitServlet").forward(request, response);

        }catch (Exception e){
            String msg="您输入的数据格式有错误，请验证后重新输入";
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer= response.getWriter();
            writer.println( "<h2>"+msg+"</h2>");
            writer.println("<a href=listFruitServlet>查看水果<a/>");
            writer.close();

        }

    }
    }
