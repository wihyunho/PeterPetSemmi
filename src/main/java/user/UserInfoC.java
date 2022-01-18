package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/UserInfoC")
public class UserInfoC extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(UserDAO.loginCheck(request)) {
			request.setAttribute("User", UserDAO.getUser(request));
			request.setAttribute("contentPage", "account/info.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "로그인을 해야 정보를 불러 올 수 있습니다.");
			response.sendRedirect("HC");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
