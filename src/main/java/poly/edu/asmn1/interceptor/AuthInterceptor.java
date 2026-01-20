package poly.edu.asmn1.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.*;
import poly.edu.asmn1.entity.Account;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        HttpSession session = req.getSession();
        Account user = (Account) session.getAttribute("user");

        if (user == null) {
            // Nếu chưa đăng nhập, lưu URL đang định vào để sau khi login thì quay lại trang đó
            session.setAttribute("secureUri", req.getRequestURI());
            resp.sendRedirect("/account/login?message=Vui lòng đăng nhập!");
            return false;
        }
        return true;
    }
}