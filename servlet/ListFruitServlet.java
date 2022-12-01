package edu.ncucst.fruitweb.servlet;

import edu.ncucst.fruitweb.bean.FruitItem;
import edu.ncucst.fruitweb.service.AdminService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ListFruitServlet", urlPatterns = "/listFruitServlet")
public class ListFruitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminService service = new AdminService();
        ArrayList<FruitItem> fruitList = service.queryAllFruitItem();
        request.setAttribute("fruitList",fruitList);
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }
}
