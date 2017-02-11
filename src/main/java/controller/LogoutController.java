package controller;

import service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession httpSession = request.getSession();
        String sessionId = httpSession.getId();

        AccountService.getInstance().removeHttpSession(sessionId);

        Cookie sessionIdCookie = new Cookie("sessionId", null);

        response.addCookie(sessionIdCookie);
        response.sendRedirect("/home");
    }
}
