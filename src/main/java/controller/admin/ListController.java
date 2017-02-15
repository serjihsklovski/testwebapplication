package controller.admin;

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

@WebServlet("/admin")
public class ListController extends HttpServlet {

    private static final String VIEW_ADMIN_LIST = "/view/admin/list.jsp";
    private static final String VIEW_UNEXPECTED_ERROR = "/view/error/unexpected.jsp";
    private static final String ATTR_USERS = "users";

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<User> users = UserService.getInstance().getUserList();
            request.setAttribute(ATTR_USERS, users);
            request.getRequestDispatcher(VIEW_ADMIN_LIST)
                    .forward(request, response);
        } catch (ServiceException e) {
            e.printStackTrace();    // todo: print stack trace in log
            request.getRequestDispatcher(VIEW_UNEXPECTED_ERROR)
                    .forward(request, response);
        }
    }
}
