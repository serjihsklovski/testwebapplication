package servlet.user;

import database.service.DataBaseServiceException;
import database.service.UserDataBaseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/delete")
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        long id = -1;

        if (idParam != null) {
            try {
                UserDataBaseService.getInstance().deleteUser(Long.valueOf(idParam));
            } catch (NumberFormatException | DataBaseServiceException e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect("/user/list");
    }
}
