package controller.admin;

import database.model.user.User;
import service.ServiceException;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/edit")
public class EditController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String userIdParam = request.getParameter("id");

        if (userIdParam != null) {
            try {
                User user = UserService.getInstance()
                        .getUser(Long.valueOf(userIdParam));

                request.setAttribute("user", user);
                request.getRequestDispatcher("/view/admin/edit.jsp")
                        .forward(request, response);
            } catch (NumberFormatException | ServiceException e) {
                e.printStackTrace();
            }
        } else {
            response.sendRedirect("/admin");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String idParam = request.getParameter("id");
        String loginParam = request.getParameter("login");
        String emailParam = request.getParameter("email");
        String passwordParam = request.getParameter("password");
        String userIsAdmin = request.getParameter("is-admin");
        String userRole = User.ROLE_USER;

        System.out.println(userIsAdmin);

        if ((userIsAdmin != null) && userIsAdmin.equals("on")) {
            userRole = User.ROLE_ADMIN;
        }

        long id = -1;
        boolean done = false;

        if (idParam != null) {
            try {
                id = Long.valueOf(idParam);
                User user = UserService.getInstance().getUser(id);

                if ((loginParam != null) && (emailParam != null) &&
                        (passwordParam != null)) {

                    user.setLogin(loginParam);
                    user.setEmail(emailParam);
                    user.setPassword(passwordParam);
                    user.setRole(userRole);

                    done = UserService.getInstance().updateUser(user);
                }
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }

        if (!done || (id == -1)) {
            response.sendRedirect("/admin/edit?id=" + id);
        } else {
            response.sendRedirect("/admin");
        }
    }
}
