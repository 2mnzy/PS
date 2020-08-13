package net.board.db;
 
public class BoardBean {
	private int B_NUM; //글번호
	private String B_ID; //글 작성자
	private String B_PASS; //글 비밀번호
	private String B_TITLE; // 글 제목
	private String B_CONTENT;// 글 내용
	private int B_VIEW; // 글의 조회
	private String B_DATE;
	private String B_TAG;
	
	public int getB_NUM() {
		return B_NUM;
	}
	public void setB_NUM(int b_NUM) {
		B_NUM = b_NUM;
	}
	public String getB_ID() {
		return B_ID;
	}
	public void setB_ID(String b_ID) {
		B_ID = b_ID;
	}
	public String getB_PASS() {
		return B_PASS;
	}
	public void setB_PASS(String b_PASS) {
		B_PASS = b_PASS;
	}
	public String getB_TITLE() {
		return B_TITLE;
	}
	public void setB_TITLE(String b_TITLE) {
		B_TITLE = b_TITLE;
	}
	public String getB_CONTENT() {
		return B_CONTENT;
	}
	public void setB_CONTENT(String b_CONTENT) {
		B_CONTENT = b_CONTENT;
	}
	public int getB_VIEW() {
		return B_VIEW;
	}
	public void setB_VIEW(int b_VIEW) {
		B_VIEW = b_VIEW;
	}
	public String getB_DATE() {
		return B_DATE;
	}
	public void setB_DATE(String b_DATE) {
		B_DATE = b_DATE;
	}
	public String getB_TAG() {
		return B_TAG;
	}
	public void setB_TAG(String b_TAG) {
		B_TAG = b_TAG;
	}
	
	
}