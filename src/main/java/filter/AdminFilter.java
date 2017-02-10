package filter;

import database.model.user.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter extends AbstractFilter {

    @Override
    public void doFilter(HttpServletRequest request,
                         HttpServletResponse response,
                         FilterChain chain)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        String loginUri = request.getContextPath() + "/login";
        User user;

        boolean loggedIn = (session != null) &&
                (session.getAttribute("user") != null);

        if (loggedIn) {
            user = (User) session.getAttribute("user");

            if (user.getRole().equals(User.ROLE_ADMIN)) {
                chain.doFilter(request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                request.getRequestDispatcher("/view/unauthorized.jsp")
                        .forward(request, response);
            }
        } else {
            response.sendRedirect(loginUri);
        }
    }
}
