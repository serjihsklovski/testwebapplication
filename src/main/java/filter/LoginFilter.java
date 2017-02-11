package filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter extends AbstractFilter {

    @Override
    public void doFilter(HttpServletRequest request,
                         HttpServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        if (request.getRequestURI().endsWith(".css")) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = request.getSession(false);
        String loginUri = request.getContextPath() + "/login";

        boolean loggedIn = (session != null) &&
                (session.getAttribute("user") != null);

        boolean isLoginRequest = request.getRequestURI().equals(loginUri);

        if (loggedIn || isLoginRequest) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(loginUri);
        }
    }
}
