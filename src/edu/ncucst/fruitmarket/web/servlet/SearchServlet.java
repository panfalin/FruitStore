package edu.ncucst.fruitmarket.web.servlet;

import edu.ncucst.fruitmarket.web.beans.Fruit;
import edu.ncucst.fruitmarket.web.service.FruitService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "SearchServlet",urlPatterns = "/SearchServlet")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        FruitService service = new FruitService();
        String number =null;
        String name = null;
        String price = null;
        String unit = null;
        Fruit fruit = new Fruit();
        try {
        String cond = request.getParameter("cond");
        if ("1".equals(cond)) {
            number = request.getParameter("keywords");
            fruit.setNumber(number);
        }
        if ("2".equals(cond)) {
            name = request.getParameter("keywords");
            fruit.setName(name);
        }
        if ("3".equals(cond)) {
            price = request.getParameter("keywords");
            fruit.setPrice(Double.parseDouble(price));
        }
        if ("4".equals(cond)) {
            unit = request.getParameter("keywords");
            fruit.setUnit(unit);
        }

            ArrayList<Fruit> fruits=service.queryFruitByCond(fruit);
            request.setAttribute("fruits",fruits);
            request.getRequestDispatcher("/list.jsp").forward(request,response);
        }catch (NumberFormatException e){
            request.setAttribute("msg", "水果单价查询条件格式错误请重新输入");
            request.getRequestDispatcher("fail.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
