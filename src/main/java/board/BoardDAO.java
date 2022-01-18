package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import home.DBManager;
import user.UserDAO;
import user.UserDTO;
import board.BoardDTO;

public class BoardDAO {
	ArrayList<BoardDTO> boards;
	
	// 클래스 내부에서 객체 만들기
	private static final BoardDAO BDAO = new BoardDAO();
	
	// 외부에서 생성자 호출 못하게
	private BoardDAO() {
		
	}
	
	//객체 가져올 수 있도록
	public static BoardDAO getBdao() {
		return BDAO;
	}


	//보드 글 생성
	public int include(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String SQL = "INSERT INTO BOARD VALUES(board_seq.nextval, ?, ?, ?, sysdate, ?, ?)";
		
		String content = request.getParameter("content");
		String thumbnail="";
		if(content.indexOf("<img ") != -1) {
			//이미지가 있다
			int start,end;
			
			start = content.indexOf("/PeterPet");
			thumbnail =  content.substring(start);
			
			end = thumbnail.indexOf("\"");
			thumbnail= thumbnail.substring(0, end);
		}else {
			thumbnail = "/PeterPet/images/noimage.png";
		}
		
		UserDTO user = UserDAO.getUser(request);

		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, request.getParameter("type"));
			pstmt.setString(2, request.getParameter("title"));
			pstmt.setString(3, content);
			pstmt.setString(4, thumbnail);
			pstmt.setString(5, user.getUserNickname());

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		} // finally end
		return -1;
	}

	//보드 리스트 가져오기
	public ArrayList<BoardDTO> getList(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from board where b_type = ? order by b_no";
		
		boards = new ArrayList<BoardDTO>();
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("type"));
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardDTO b = new BoardDTO();
				b.setNo(rs.getInt("b_no"));
				b.setTitle(rs.getString("b_title"));
				b.setContent(rs.getString("b_content"));
				b.setDate(rs.getDate("b_date"));
				b.setThumbnail(rs.getString("b_thumbnail"));
				b.setWriter(rs.getString("b_writer"));
				b.setType(rs.getString("b_type"));
				boards.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, rs);
		}
		return boards;
	}

	//글 자세히 보기
	public BoardDTO getBoard(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		BoardDTO b = new BoardDTO();
		
		String sql = "select * from board where b_no=?";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(request.getParameter("no")));
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				b.setNo(rs.getInt("b_no"));
				b.setTitle(rs.getString("b_title"));
				b.setContent(rs.getString("b_content"));
				b.setDate(rs.getDate("b_date"));
				b.setThumbnail(rs.getString("b_thumbnail"));
				b.setWriter(rs.getString("b_writer"));
				b.setType(rs.getString("b_type"));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, rs);
		}
		return b;
	}

	//글 수정하기
	public boolean getUpdate(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		String thumbnail="";
		if(content.indexOf("<img ") != -1) {
			//이미지가 있다
			int start,end;
			
			start = content.indexOf("/PeterPet");
			thumbnail =  content.substring(start);
			
			end = thumbnail.indexOf("\"");
			thumbnail= thumbnail.substring(0, end);
		}else {
			thumbnail = "/PeterPet/images/noimage.png";
		}
		
		
		String sql = "update board set b_title=?, b_content=?, b_date=sysdate, b_thumbnail = ? where b_no=?";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, thumbnail);
			pstmt.setInt(4, Integer.parseInt(request.getParameter("no")));
			
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

	//보드 삭제
	public boolean getDel(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from board where b_no=?";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(request.getParameter("no")));
			
			if(pstmt.executeUpdate()==1) { return true; }
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, null);
		}
		return false;
	}

	//보드 제목으로 검색하기
	public ArrayList<BoardDTO> getTitleSearch(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from board where b_title like ? and b_type = ? order by b_no";
		
		boards = new ArrayList<BoardDTO>();
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, "%"+request.getParameter("search")+"%");
			pstmt.setString(2, request.getParameter("type"));
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardDTO b = new BoardDTO();
				b.setNo(rs.getInt("b_no"));
				b.setTitle(rs.getString("b_title"));
				b.setContent(rs.getString("b_content"));
				b.setDate(rs.getDate("b_date"));
				b.setThumbnail(rs.getString("b_thumbnail"));
				b.setWriter(rs.getString("b_writer"));
				b.setType(rs.getString("b_type"));
				boards.add(b);
			}
			
	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, rs);
		}
		return boards;
	}

	//보드 작성자로 검색하기
	public ArrayList<BoardDTO> getWriterSearch(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from board where b_writer like ? and b_type = ? order by b_no";
		
		boards = new ArrayList<BoardDTO>();
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, "%"+request.getParameter("search")+"%");
			pstmt.setString(2, request.getParameter("type"));
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardDTO b = new BoardDTO();
				b.setNo(rs.getInt("b_no"));
				b.setTitle(rs.getString("b_title"));
				b.setContent(rs.getString("b_content"));
				b.setDate(rs.getDate("b_date"));
				b.setThumbnail(rs.getString("b_thumbnail"));
				b.setWriter(rs.getString("b_writer"));
				b.setType("b_type");
				boards.add(b);
			}
			
	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, rs);
		}
		return boards;
	}
	
	public void paging(int page, HttpServletRequest request) {
		
		request.setAttribute("curPageNo", page);
		
		// 전체 페이지 수 계산
		int cnt = 9; //한 페이지당 보여줄 개수
		
		String type = request.getParameter("type");
		if(type.equals("QA") || type.equals("tip")) {
			cnt = 15;
		}
		
		int total = boards.size();//총 데이터 개수
		
		int pageCount = (int) Math.ceil(total/(double)cnt);
		request.setAttribute("pageCount", pageCount);
		
		int start = total - (cnt * (page-1));
		int end = (page == pageCount) ? -1 : start - (cnt + 1);
		
		ArrayList<BoardDTO> items = new ArrayList<BoardDTO>();
		if(!boards.isEmpty()) {
			for (int i = start-1; i > end; i--) {
				items.add(boards.get(i));
			}
		}
		request.setAttribute("boards", items);
	}

	public ArrayList<BoardDTO> getfreeList(String type) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from board where b_type = ? order by b_no desc";
		int maxcount = 3;
		if(type.equals("QA")) {
			maxcount=7;
		}
		boards = new ArrayList<BoardDTO>();
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, type);
			rs=pstmt.executeQuery();
			int counter = 0;
			while(rs.next()) {
					counter++;
					BoardDTO b = new BoardDTO();
					b.setNo(rs.getInt("b_no"));
					b.setTitle(rs.getString("b_title"));
					b.setContent(rs.getString("b_content"));
					b.setDate(rs.getDate("b_date"));
					b.setThumbnail(rs.getString("b_thumbnail"));
					b.setWriter(rs.getString("b_writer"));
					b.setType(rs.getString("b_type"));
					boards.add(b);
					
					if(counter == maxcount) {
						break;
					}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, rs);
		}
		return boards;
	}
}
