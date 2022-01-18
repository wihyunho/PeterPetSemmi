package comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import board.BoardDTO;
import home.DBManager;
import user.UserDAO;
import user.UserDTO;

public class CommentDAO {

	public static int insertComment(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
	
		String SQL = "INSERT INTO board_comment VALUES(board_seq.nextval, ?, ?, sysdate, ?, 0, ?)";
		
		String comment = request.getParameter("comment");
		comment = comment.replace("\r\n", "<br>");
		
		UserDTO user = UserDAO.getUser(request);

		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, request.getParameter("boardnum"));
			pstmt.setString(2, user.getUserNickname());
			pstmt.setInt(3, Integer.parseInt(request.getParameter("commentnum")));
			pstmt.setString(4, comment);
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		} // finally end
		return -1;
	}

	public static ArrayList<CommentDTO> getCommentList(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select Level, c_no, b_no, c_writer, c_date, c_parent, c_ischange,c_comment "
					+"from BOARD_COMMENT "
					+"where  b_no = ? "
					+"start with c_parent = 0 "
					+"connect by prior c_no = c_parent";
		
		ArrayList<CommentDTO> data = new ArrayList<CommentDTO>();
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("no"));
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				CommentDTO c = new CommentDTO();
				c.setLevel(rs.getInt("Level"));
				c.setC_no(rs.getInt("c_no"));
				c.setB_no(rs.getInt("b_no"));
				c.setC_writer(rs.getString("c_writer"));
				c.setC_date(rs.getDate("c_date"));
				c.setC_parent(rs.getInt("c_parent"));
				c.setC_ischange(rs.getInt("c_ischange"));
				c.setC_comment(rs.getString("c_comment"));
				
				
				data.add(c);
			}
			
	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, rs);
		}
		return data;	
	}

	public static boolean chageComment(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String comment = request.getParameter("comment");
		comment = comment.replace("\r\n", "<br>");
		
		String sql = "update BOARD_COMMENT set c_ischange=1, c_comment=?, c_date=sysdate where c_no=?";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, comment);
			pstmt.setInt(2, Integer.parseInt(request.getParameter("commentnum")));
			
			if(pstmt.executeUpdate()==1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, null);
		}
		return false;
	}

	public static boolean chageDelComment(int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "update BOARD_COMMENT set c_writer='삭제된 코멘트', c_ischange=-1, c_comment='삭제된 코멘트입니다.' where c_no=?";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			if(pstmt.executeUpdate()==1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, null);
		}
		return false;
	}
	
	public static boolean haveChildComment(int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		String sql = "select * from BOARD_COMMENT where c_parent =?";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, null);
		}
		return false;
	}

	public static boolean deleteComment(int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		String sql = "delete from BOARD_COMMENT where c_no=?";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			if(pstmt.executeUpdate()==1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, null);
		}
		return false;
	}
	
	private static int getParrentNO(int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
	
		String sql = "select * from BOARD_COMMENT where c_no =?";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("c_parent");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, null);
		}
		return -1;
	}

	public static CommentDTO getComment(int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		CommentDTO c = new CommentDTO();
		String sql = "select * from BOARD_COMMENT where c_no =?";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				c.setC_no(rs.getInt("c_no"));
				c.setB_no(rs.getInt("b_no"));
				c.setC_writer(rs.getString("c_writer"));
				c.setC_date(rs.getDate("c_date"));
				c.setC_parent(rs.getInt("c_parent"));
				c.setC_ischange(rs.getInt("c_ischange"));
				c.setC_comment(rs.getString("c_comment"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, null);
		}
		return c;
	}

	public static boolean isDelete(int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;

		String sql = "select * from BOARD_COMMENT where c_no =?";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			
			
			
			//지웠는데 있다?
			if(rs.next()) {
				//지워져서 삭제된 코멘트로 변경 되어있으면 true
				if(rs.getInt("c_ischange") == -1) {
					return true;
				}
				return false;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, null);
		}
		return true;
	}
	
	//댓글 삭제 시나리오
	public static void senarioDelete(int no) {	
		//자식이 있는지 검사
		if(haveChildComment(no)) {
			//자식이 있다면 삭제 대신 대체
			chageDelComment(no);
		}else {
			//자신의 부모의 정보를 가져옴
			int parentNo = getParrentNO(no);
			CommentDTO parent = getComment(parentNo);
			
			//자신을 삭제
			deleteComment(no);
	
			if(parent.getC_ischange() == -1) {
				//부모가 삭제 되어 있다면
				senarioDelete(parent.getC_no());
			}
		}
	}

	
}
