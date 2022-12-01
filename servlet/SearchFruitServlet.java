package edu.ncucst.fruitweb.servlet;

import edu.ncucst.fruitweb.bean.FruitItem;
import edu.ncucst.fruitweb.service.AdminService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "SearchFruitServlet", urlPatterns = "/searchFruitServlet")
public class SearchFruitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                 doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String cond;
        String number = null;
        String fruitname = null;
        String price = null;
        String unit = null;
        cond = request.getParameter("cond");
        if (cond.equals("1")){
            number = request.getParameter("keywords");
        }
        if (cond.equals("2")){
            fruitname = request.getParameter("keywords");
        }
        if (cond.equals("3")){
            price = request.getParameter("keywords");
        }
        if (cond.equals("4")){
            unit = request.getParameter("keywords");
        }
        AdminService service =new AdminService();
        ArrayList<FruitItem> fruitItems=service.queryFruitItemByCon(number,fruitname,price,unit);
        request.setAttribute("fruitList",fruitItems);
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }
}
