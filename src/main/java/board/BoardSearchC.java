package board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.UserDAO;

@WebServlet("/BoardSearchC")
public class BoardSearchC extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//네비게이션 바 생성 작업
		UserDAO.loginCheck(request);
		
		if(request.getParameter("field").equals("title")) {	
			//서치 목록 적제
			BoardDAO.getBdao().getTitleSearch(request);
		}else {
			//서치 목록 적제
			BoardDAO.getBdao().getWriterSearch(request);
		}
		
		String board_type = request.getParameter("type");
		if(board_type.equals("free")) {
			request.setAttribute("contentPage", "board/free/board_list.jsp");
		}else if(board_type.equals("QA")) {
			request.setAttribute("contentPage", "board/QA/board_list.jsp");
		}else if(board_type.equals("tip")) {
			request.setAttribute("contentPage", "board/tip/board_list.jsp");
		}else if(board_type.equals("share")) {
			request.setAttribute("contentPage", "board/share/board_list.jsp");
		}else if(board_type.equals("temp")) {
			request.setAttribute("contentPage", "board/temp/board_list.jsp");
		}else if(board_type.equals("other")) {
			request.setAttribute("contentPage", "board/other/board_list.jsp");
		}
		
		//검색 1페이지에 해당하는 각각의 보드들에게 할당
		BoardDAO.getBdao().paging(1, request);
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
