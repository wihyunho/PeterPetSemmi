package home;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDAO;
import user.UserDAO;

	
@WebServlet("/HC")
public class HC extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO.loginCheck(request);
		
		String type = "free";
		request.setAttribute("freeBoards",  BoardDAO.getBdao().getfreeList(type));
		type = "QA";
		request.setAttribute("QABoards",  BoardDAO.getBdao().getfreeList(type));
		type = "share";
		request.setAttribute("shareBoards",  BoardDAO.getBdao().getfreeList(type));
		
		
		request.setAttribute("contentPage", "home.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
