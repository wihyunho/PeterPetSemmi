package board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.UserDAO;

@WebServlet("/BoardUpdateC")
public class BoardUpdateC extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		UserDAO.loginCheck(request);
		
		
		String page = request.getParameter("p");
		request.setAttribute("board", BoardDAO.getBdao().getBoard(request)); 
		request.setAttribute("page", page); 
		
		String board_type = request.getParameter("type");
		if(board_type.equals("free")) {
			request.setAttribute("contentPage", "board/free/board_Update.jsp");
		}else if(board_type.equals("QA")) {
			request.setAttribute("contentPage", "board/QA/board_Update.jsp");
		}else if(board_type.equals("tip")) {
			request.setAttribute("contentPage", "board/tip/board_Update.jsp");
		}else if(board_type.equals("share")) {
			request.setAttribute("contentPage", "board/share/board_Update.jsp");
		}else if(board_type.equals("temp")) {
			request.setAttribute("contentPage", "board/temp/board_Update.jsp");
		}else if(board_type.equals("other")) {
			request.setAttribute("contentPage", "board/other/board_Update.jsp");
		}
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		UserDAO.loginCheck(request);
		if(BoardDAO.getBdao().getUpdate(request)) {
			request.getSession().setAttribute("messageType", "성공 메시지");
			request.getSession().setAttribute("messageContent", "글수정을 성공했습니다.");
		}else {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "글수정을 실패했습니다.");
		}

		String url = "BoardViewC?p=" + request.getParameter("page") + "&no=" + request.getParameter("no");
		response.sendRedirect(url);
	}

}
