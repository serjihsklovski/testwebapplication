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

@WebServlet("/profile")
public class ProfileController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String userIdParam = request.getParameter("id");
        User user = null;

        if (userIdParam == null) {
            if ((boolean) request.getAttribute("loggedIn")) {
                user = (User) request.getSession().getAttribute("user");
                request.setAttribute("user", user);
                request.getRequestDispatcher("/view/my-profile.jsp")
                    .forward(request, response);
            } else {
                response.sendRedirect("/login");
            }
        } else {
            long userId = Long.valueOf(userIdParam);

            try {
                user = UserService.getInstance().getUser(userId);
                request.setAttribute("user", user);

                if (user.equals(request.getSession().getAttribute("user"))) {
                    request.getRequestDispatcher("/view/my-profile.jsp")
                            .forward(request, response);
                } else {
                    request.getRequestDispatcher("/view/profile.jsp")
                            .forward(request, response);
                }
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
    }
}
