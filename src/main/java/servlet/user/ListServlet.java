package servlet.user;

import database.service.UserDataBaseService;
import database.service.DataBaseServiceException;
import database.dataset.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({"/", "/index", "/user/list"})
public class ListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");

        try {
            List<User> users = UserDataBaseService.getInstance().getUserList();
            request.setAttribute("users", users);
            request.getRequestDispatcher("/view/user/list.jsp").forward(request, response);
        } catch (DataBaseServiceException e) {
            e.printStackTrace();
        }
    }
}
