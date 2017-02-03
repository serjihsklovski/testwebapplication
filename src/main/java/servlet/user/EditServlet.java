package servlet.user;

import database.dataset.user.User;
import database.service.DataBaseServiceException;
import database.service.UserDataBaseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/edit")
public class EditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");

        String userIdParam = request.getParameter("id");

        if (userIdParam != null) {
            try {
                User user = UserDataBaseService.getInstance().getUser(Long.valueOf(userIdParam));
                request.setAttribute("user", user);
                request.getRequestDispatcher("/view/user/edit.jsp").forward(request, response);
            } catch (NumberFormatException | DataBaseServiceException e) {
                e.printStackTrace();
            }
        } else {
            response.sendRedirect("/user/list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");

        String idParam = request.getParameter("id");
        String loginParam = request.getParameter("login");
        String emailParam = request.getParameter("email");
        String passwordParam = request.getParameter("password");

        long id = -1;
        boolean done = false;

        if (idParam != null) {
            try {
                id = Long.valueOf(idParam);
                User user = UserDataBaseService.getInstance().getUser(id);

                System.out.println(user);
                System.out.println("new login = " + loginParam);
                System.out.println("new email = " + emailParam);
                System.out.println("new password = " + passwordParam);

                if ((loginParam != null) && (emailParam != null) && (passwordParam != null)) {
                    user.setLogin(loginParam);
                    user.setEmail(emailParam);
                    user.setPassword(passwordParam);

                    done = UserDataBaseService.getInstance().updateUser(user);
                }
            } catch (DataBaseServiceException e) {
                e.printStackTrace();
            }
        }

        if (!done || (id == -1)) {
            response.sendRedirect("/user/edit?id=" + id);
        } else {
            response.sendRedirect("/user/list");
        }
    }
}
