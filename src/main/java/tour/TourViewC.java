package tour;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.UserDAO;


@WebServlet("/TourViewC")
public class TourViewC extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO.loginCheck(request);
		
		String ty = request.getParameter("ty");
		String co = request.getParameter("co");
		
		request.setAttribute("ty", ty);
		request.setAttribute("co", co);
		
		request.setAttribute("contentPage", "tour/TourApi2.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
