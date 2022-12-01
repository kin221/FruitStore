package edu.ncucst.fruitweb.servlet;

import edu.ncucst.fruitweb.bean.FruitItem;
import edu.ncucst.fruitweb.service.AdminService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "SortFruitServlet", urlPatterns = "/sortFruitServlet")
public class SortFruitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                request.setCharacterEncoding("utf-8");
                String sort=request.getParameter("sort");
        AdminService service= new AdminService();
        ArrayList<FruitItem> fruitList = service.showFruitlistOrderby(sort);
        request.setAttribute("fruitList",fruitList);
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                    doGet(request,response);
    }
}
