package tech.zxuuu.hotel24h.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object id = session.getAttribute("empId");
        System.out.println(id);
        if (id != null) {
            return true;
        }
        request.getRequestDispatcher("/login").forward(request, response);
        return false;
    }
}
