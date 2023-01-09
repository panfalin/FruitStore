package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request= (HttpServletRequest) req;


        //注册登录相关资源,直接放行
        String[] urls={"/index.jsp","/js/","/images/","/css/","/LoginServlet",
                "/CheckServlet","/register.jsp","/RegisterServlet","/successPage.jsp","errorPage.jsp"};
        String url = request.getRequestURL().toString();
        for (String u : urls) {
            if (url.contains(u)){
                chain.doFilter(req, resp);

                return;
            }
        }

        HttpSession session=request.getSession();
        Object username = session.getAttribute("username");
        if (username!=null){
            chain.doFilter(req, resp);
        }else {

            req.setAttribute("errorMsg","请先登录");
            req.getRequestDispatcher("/index.jsp").forward(req,resp);


        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}

