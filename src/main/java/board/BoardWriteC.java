package board;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.UserDAO;

@WebServlet("/BoardWriteC")
public class BoardWriteC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(UserDAO.loginCheck(request)) {
			String board_type = request.getParameter("type");
			if(board_type.equals("free")) {
				//자랑 게시판
				request.setAttribute("contentPage", "board/free/board_Write.jsp");
			}else if(board_type.equals("QA")) {
				//질문 게시판
				request.setAttribute("contentPage", "board/QA/board_Write.jsp");
			}else if(board_type.equals("tip")) {
				//팁 게시판	
				request.setAttribute("contentPage", "board/tip/board_Write.jsp");
			}else if(board_type.equals("share")) {
				//나눔 게시판
				request.setAttribute("contentPage", "board/share/board_Write.jsp");
			}else if(board_type.equals("temp")) {
				//임시보호 게시판
				request.setAttribute("contentPage", "board/temp/board_Write.jsp");
			}else if(board_type.equals("other")) {
				//그외동물 게시판	
				request.setAttribute("contentPage", "board/other/board_Write.jsp");
			}
		}else {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "회원만 글을 작성할 수 있습니다.");
			request.setAttribute("contentPage", "home.jsp");
		}
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
	     response.setContentType("text/html; charset=UTF-8");

		if(BoardDAO.getBdao().include(request) != -1) {
			UserDAO.loginCheck(request);
			request.getSession().setAttribute("messageType", "성공 메시지");
			request.getSession().setAttribute("messageContent", "글 등록 성공.");
			
		}else {
			UserDAO.loginCheck(request);
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "글 등록 실패.");		
		}	
		
		String type = request.getParameter("type");
		String url = "BoardListC?type="+type;
		response.sendRedirect(url);

	}

}
