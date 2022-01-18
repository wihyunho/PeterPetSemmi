package comment;

import java.sql.Date;

public class CommentDTO {
	int level;
	int c_no;
	int b_no;
	String c_writer;
	Date c_date;
	int c_parent;
	int c_ischange;
	String c_comment;
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getC_no() {
		return c_no;
	}
	public void setC_no(int c_no) {
		this.c_no = c_no;
	}
	public int getB_no() {
		return b_no;
	}
	public void setB_no(int b_no) {
		this.b_no = b_no;
	}
	public String getC_writer() {
		return c_writer;
	}
	public void setC_writer(String c_writer) {
		this.c_writer = c_writer;
	}
	public Date getC_date() {
		return c_date;
	}
	public void setC_date(Date c_date) {
		this.c_date = c_date;
	}
	public int getC_parent() {
		return c_parent;
	}
	public void setC_parent(int c_parent) {
		this.c_parent = c_parent;
	}
	public String getC_comment() {
		return c_comment;
	}
	public void setC_comment(String c_comment) {
		this.c_comment = c_comment;
	}
	public int getC_ischange() {
		return c_ischange;
	}
	public void setC_ischange(int c_ischange) {
		this.c_ischange = c_ischange;
	}
	
}
