package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/NicknameCheckServlet")
public class NicknameCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String nickname = request.getParameter("nickname");
		
		if(nickname == null || nickname.equals("")) response.getWriter().write("-1");
		
		response.getWriter().write(UserDAO.nicknameCheck(nickname) + "");
	}

}
