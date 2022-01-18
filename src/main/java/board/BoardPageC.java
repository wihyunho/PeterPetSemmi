package board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.UserDAO;

@WebServlet("/BoardPageC")
public class BoardPageC extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		UserDAO.loginCheck(request);
		
		int p = Integer.parseInt(request.getParameter("p"));

		BoardDAO.getBdao().paging(p, request);
		
		String board_type = request.getParameter("type");
		
		if(board_type.equals("free")) {
			//자랑 게시판
			request.setAttribute("contentPage", "board/free/board_list.jsp");
		}else if(board_type.equals("QA")) {
			//질문 게시판
			request.setAttribute("contentPage", "board/QA/board_list.jsp");
		}else if(board_type.equals("tip")) {
			//팁 게시판	
			request.setAttribute("contentPage", "board/tip/board_list.jsp");
		}else if(board_type.equals("share")) {
			//나눔 게시판
			request.setAttribute("contentPage", "board/share/board_list.jsp");
		}else if(board_type.equals("temp")) {
			//임시보호 게시판
			request.setAttribute("contentPage", "board/temp/board_list.jsp");
		}else if(board_type.equals("other")) {
			//그외동물 게시판	
			request.setAttribute("contentPage", "board/other/board_list.jsp");
		}
			
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
