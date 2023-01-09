package edu.ncucst.fruitmarket.web.servlet;

import edu.ncucst.fruitmarket.web.beans.Fruit;
import edu.ncucst.fruitmarket.web.beans.User;
import edu.ncucst.fruitmarket.web.service.FruitService;
import edu.ncucst.fruitmarket.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "PassUpdateServlet",urlPatterns = "/PassUpdateServlet")
public class PassUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //获取存储的用户名和密码
        HttpSession session=request.getSession();
        Object username = session.getAttribute("username");
        Object password = session.getAttribute("password");

        UserService service = new UserService();

        String  mpass= request.getParameter("mpass").trim();
        String newpass = request.getParameter("newpass").trim();
        String renewpass = request.getParameter("renewpass").trim();

        if (mpass.equals(password)){
            if(renewpass.equals(newpass)){
                User user = new User();
                user.setUsername((String) username);
                user.setPassword(newpass);
                if(service.updateUser(user)){
                    //修改存储的密码
                    session.setAttribute("password",newpass);
                    request.setAttribute("msg", "修改成功");
                    request.getRequestDispatcher("msg.jsp").forward(request, response);
                }else {
                    request.setAttribute("msg", "修改失败，请重新输入");
                    request.getRequestDispatcher("fail.jsp").forward(request, response);
                }
            }else {
                request.setAttribute("msg", "两次密码输入不一致，请重新输入");
                request.getRequestDispatcher("fail.jsp").forward(request, response);
            }
        }else {
            request.setAttribute("msg", "原密码输入错误，请重新输入");
            request.getRequestDispatcher("fail.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request,response);
    }
}
