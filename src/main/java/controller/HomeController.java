package controller;

import database.model.user.User;
import service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home")
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        User user = null;

        if ((boolean) request.getAttribute("loggedIn")) {
            user = (User) request.getSession().getAttribute("user");
        }

        request.setAttribute("user", user);
        request.getRequestDispatcher("/view/home.jsp")
                .forward(request, response);
    }
}
