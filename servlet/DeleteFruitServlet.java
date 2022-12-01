package edu.ncucst.fruitweb.servlet;

import edu.ncucst.fruitweb.service.AdminService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteFruitServlet", urlPatterns = "/deleteFruitServlet")
public class DeleteFruitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           request.getRequestDispatcher("utf-8");
           String number;
           number=request.getParameter("number");
        AdminService service=new AdminService();
String[] numbers = number.split(",");
    for(String id:numbers){
      service.delFruitItem(id);

    }
    request.getRequestDispatcher("listFruitServlet").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                doGet(request,response);
    }
}
