package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/UserRegisterC")
public class UserRegisterC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String path = request.getSession().getServletContext().getRealPath("images");
		
		MultipartRequest mr = new MultipartRequest(request, path, 30 * 1024 * 1204, "UTF-8", new DefaultFileRenamePolicy());
		String userID = mr.getParameter("userID");
		String userPassword1 = mr.getParameter("userPassword1");
		String userPassword2 = mr.getParameter("userPassword2");
		String userName = mr.getParameter("userName");
		String userNickname = mr.getParameter("nickname");
		String userProfile = mr.getFilesystemName("profile");
		
		//null 값을 받으면 반환
		if(userID == null || userID.equals("") || userPassword1 == null || userPassword1.equals("") ||
			userPassword2 == null || userPassword2.equals("") || userName == null || userName.equals("") ||
			userNickname == null || userNickname.equals("")) {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "항목을 다 입력해주세요.");
			response.sendRedirect("JoinC");
		}
		
		if(!userPassword1.equals(userPassword2)) {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "비밀번호가 일치하지 않습니다.");
			response.sendRedirect("JoinC");
		}
		
		int result = new UserDAO().register(userID, userPassword1, userName, userNickname, userProfile);
		
		if(result == 1) {
			request.getSession().setAttribute("userID", userID);
			request.getSession().setAttribute("messageType", "성공 메시지");
			request.getSession().setAttribute("messageContent", "회원가입에 성공하였습니다.");
			response.sendRedirect("HC");
		}
		else {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "이미 존제하는 회원입니다.");
			response.sendRedirect("JoinC");
		}
	}

}
