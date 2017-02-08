package controller.user;

import database.model.user.User;
import database.model.user.UserBuilder;
import service.ServiceException;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/add")
public class AddController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        request.getRequestDispatcher("/view/user/add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");

        String userLogin = request.getParameter("login");
        String userEmail = request.getParameter("email");
        String userPassword = request.getParameter("password");
        String userIsAdmin = request.getParameter("is-admin");
        String userRole = User.ROLE_USER;

        if ((userIsAdmin != null) && userIsAdmin.equals("on")) {
            userRole = User.ROLE_ADMIN;
        }

        boolean done = false;

        if ((userLogin != null) && (userEmail != null) && (userPassword != null)) {
            User user = new UserBuilder()
                    .setLogin(userLogin)
                    .setEmail(userEmail)
                    .setPassword(userPassword)
                    .setRole(userRole)
                    .buildUser();

            try {
                UserService.getInstance().addUser(user);
                done = true;
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }

        if (done) {
            response.sendRedirect("/user/list");
        } else {
            response.sendRedirect("/user/add");
        }
    }
}
