package board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.CommentDAO;
import comment.CommentDTO;
import user.UserDAO;
import user.UserDTO;

@WebServlet("/BoardViewC")
public class BoardViewC extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDTO b = BoardDAO.getBdao().getBoard(request);
		
		if(UserDAO.loginCheck(request)) {			
			UserDTO u = UserDAO.getUser(request);
			request.setAttribute("userNickname", u.getUserNickname());
		}
		ArrayList<CommentDTO> commentList=CommentDAO.getCommentList(request);
		
		request.setAttribute("commentList", commentList);
		request.setAttribute("board", b);
		
		if(b.type.equals("free")) {
			request.setAttribute("contentPage", "board/free/board_view.jsp");
		} else if(b.type.equals("QA")) {
			request.setAttribute("contentPage", "board/QA/board_view.jsp");
		} else if(b.type.equals("tip")) {
			//팁 게시판	
			request.setAttribute("contentPage", "board/tip/board_view.jsp");
		}else if(b.type.equals("share")) {
			//나눔 게시판
			request.setAttribute("contentPage", "board/share/board_view.jsp");
		}else if(b.type.equals("temp")) {
			//임시보호 게시판
			request.setAttribute("contentPage", "board/temp/board_view.jsp");
		}else if(b.type.equals("other")) {
			//그외동물 게시판	
			request.setAttribute("contentPage", "board/other/board_view.jsp");
		}
		
		//자신이 있던 페이지 목록
		String curPageNo = request.getParameter("p");
		request.setAttribute("curPageNo", curPageNo);
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
