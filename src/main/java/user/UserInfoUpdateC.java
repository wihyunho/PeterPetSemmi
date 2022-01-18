package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/UserInfoUpdateC")
public class UserInfoUpdateC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(UserDAO.loginCheck(request)) {
			request.setAttribute("User", UserDAO.getUser(request));
			String type = request.getParameter("type");
			
			if(type.equals("profile")) {
				request.setAttribute("contentPage", "account/profileUpdate.jsp");
			}else if(type.equals("password")) {
				request.setAttribute("contentPage", "account/passwordUpdate.jsp");
			}else if(type.equals("nickname")) {
				request.setAttribute("contentPage", "account/nicknameUpdate.jsp");
			}
		}else {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "로그인을 해야 정보를 불러 올 수 있습니다.");
			response.sendRedirect("HC");
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		if(UserDAO.UserUpdate(request)) {
			request.getSession().setAttribute("messageType", "성공 메시지");
			request.getSession().setAttribute("messageContent", "회원 정보를 수정했습니다.");
		}
		
		response.sendRedirect("UserInfoC");
	}

}
