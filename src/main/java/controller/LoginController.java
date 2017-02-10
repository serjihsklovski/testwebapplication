package controller;

import database.model.user.User;
import service.ServiceException;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private static final String LOGIN_PAGE_PATH = "/view/login.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher(LOGIN_PAGE_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String loginParam = request.getParameter("login");
        String passwordParam = request.getParameter("password");

        List<String> errors = new LinkedList<>();

        try {
            User user = UserService.getInstance().getUserByLogin(loginParam);

            if ((user != null) && user.getPassword().equals(passwordParam)) {
                request.getSession().setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/home");
                return;
            } else {
                errors.add("Wrong combination of Login-Password");
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        request.setAttribute("errors", errors);
        request.setAttribute("login", loginParam);
        request.getRequestDispatcher(LOGIN_PAGE_PATH).forward(request, response);
    }
}
