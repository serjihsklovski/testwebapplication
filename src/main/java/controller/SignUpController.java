package controller;

import database.model.user.User;
import database.model.user.UserBuilder;
import service.AccountService;
import service.ServiceException;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/sign-up")
public class SignUpController extends HttpServlet {

    private static final String SIGN_UP_PAGE_PATH = "/view/sign-up.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        request.getRequestDispatcher(SIGN_UP_PAGE_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String loginParam = request.getParameter("login");
        String emailParam = request.getParameter("email");
        String passwordParam = request.getParameter("password");

        if ((boolean) request.getAttribute("loggedIn")) {
            AccountService.getInstance().removeHttpSession(request.getSession().getId());
            Cookie sessionIdCookie = new Cookie("sessionId", null);
            response.addCookie(sessionIdCookie);
        }

        try {
            User user = new UserBuilder()
                    .setLogin(loginParam)
                    .setEmail(emailParam)
                    .setPassword(passwordParam)
                    .buildUser();

            UserService.getInstance().addUser(user);

            HttpSession httpSession = request.getSession();

            httpSession.setAttribute("user", user);
            AccountService.getInstance().putHttpSession(httpSession);

            Cookie sessionIdCookie = new Cookie("sessionId", httpSession.getId());

            sessionIdCookie.setMaxAge(60 * 60 * 24 * 365);

            response.addCookie(sessionIdCookie);
            response.sendRedirect("/profile");
        } catch (ServiceException e) {
            e.printStackTrace();
            response.sendRedirect("/sign-up");
        }
    }
}
