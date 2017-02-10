package controller.user;

import service.ServiceException;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/delete")
public class DeleteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String idParam = request.getParameter("id");
        long id = -1;

        if (idParam != null) {
            try {
                UserService.getInstance().deleteUser(Long.valueOf(idParam));
            } catch (NumberFormatException | ServiceException e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect("/user/list");
    }
}
