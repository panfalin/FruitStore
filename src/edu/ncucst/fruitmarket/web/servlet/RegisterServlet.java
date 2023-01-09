package edu.ncucst.fruitmarket.web.servlet;

import edu.ncucst.fruitmarket.web.beans.User;
import edu.ncucst.fruitmarket.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet",urlPatterns = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name").trim();
        String password = request.getParameter("password").trim();;
        String checkpwd = request.getParameter("checkpwd").trim();;
        String code = request.getParameter("code").trim();
        String saveCode = (String) request.getSession().getAttribute("check_code");

        UserService service = new UserService();
        if (saveCode.equals(code)) {
            try {
                if (name != null && name.length() > 0){
                    if (!checkpwd.equals(password)){
                        request.setAttribute("msg", "两次密码输入不一致，请重新输入");
                        request.getRequestDispatcher("errpage.jsp").forward(request, response);
                    } else {
                        //存入注册信息
                        User user = new User();
                        user.setUsername(name);
                        user.setPassword(password);
                        if (service.addUser(user)){
                            request.setAttribute("msg", "注册成功");
                            request.getRequestDispatcher("rgsuccpage.jsp").forward(request, response);
                        }else {
                            request.setAttribute("msg", "用户名不能重复，请重新输入");
                            request.getRequestDispatcher("errpage.jsp").forward(request, response);
                        }
                    }
                }else {
                    request.setAttribute("msg", "用户名不能为空，请重新输入");
                    request.getRequestDispatcher("errpage.jsp").forward(request, response);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("msg", "用户名格式错误，请重新输入");
                request.getRequestDispatcher("errpage.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("msg", "验证码错误，请检查后重新输入");
            request.getRequestDispatcher("errpage.jsp").forward(request, response);
        }

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

