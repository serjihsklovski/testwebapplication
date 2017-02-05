package controller.user;

import service.UserService;
import service.ServiceException;
import database.model.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/list")
public class ListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");

        try {
            List<User> users = UserService.getInstance().getUserList();
            request.setAttribute("users", users);
            request.getRequestDispatcher("/view/user/list.jsp").forward(request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
