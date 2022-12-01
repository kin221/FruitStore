package edu.ncucst.fruitweb.servlet;

import edu.ncucst.fruitweb.bean.FruitItem;
import edu.ncucst.fruitweb.service.AdminService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "FruitInfoServlet", urlPatterns = "/fruitInfoServlet")
public class FruitInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                     request.setCharacterEncoding("utf-8");
                     String number=request.getParameter("number");
        AdminService service=new AdminService();
        ArrayList<FruitItem> fruitList=service.queryFruitItemByCon(number,null,null,null);
        FruitItem fruitItem = fruitList.get(0);
        if (fruitItem==null){
            throw new  RuntimeException("找不到该水果信息！");

        }
        request.setAttribute("fruitItem",fruitItem);
        request.getRequestDispatcher("/update.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                   doGet(request,response);
    }
}
