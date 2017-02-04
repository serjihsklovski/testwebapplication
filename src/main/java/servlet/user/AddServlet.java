package servlet.user;

import database.dataset.user.User;
import database.dataset.user.UserBuilder;
import service.ServiceException;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/add")
public class AddServlet extends HttpServlet {

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

        boolean done = false;

        if ((userLogin != null) && (userEmail != null) && (userPassword != null)) {
            User user = new UserBuilder()
                    .setLogin(userLogin)
                    .setEmail(userEmail)
                    .setPassword(userPassword)
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
