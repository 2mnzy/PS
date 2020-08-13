package net.warn.db;

public class WarnComment {
	int ROWNUM;//리스트에서 번호
	int WC_NUM;//코맨트 고유번호
	String WM_ID;//작성자 아이디
	int WCB_NUM;// 참조된 게시판 번호
	int WCI_NUM;// 참조된 이미지 번호
	int WC_COUNT;// 신고된 횟수
	String WC_DATE;//작성 날짜
	String WC_CONTENT;//작성 내용 
	String WC_TAG1;//신고태그1
	String WC_TAG2;//신고태그2
	
	public int getROWNUM() {
		return ROWNUM;
	}
	public void setROWNUM(int rOWNUM) {
		ROWNUM = rOWNUM;
	}
	public int getWC_NUM() {
		return WC_NUM;
	}
	public void setWC_NUM(int wC_NUM) {
		WC_NUM = wC_NUM;
	}
	public String getWM_ID() {
		return WM_ID;
	}
	public void setWM_ID(String wM_ID) {
		WM_ID = wM_ID;
	}
	
	public int getWCB_NUM() {
		return WCB_NUM;
	}
	public void setWCB_NUM(int wCB_NUM) {
		WCB_NUM = wCB_NUM;
	}
	public int getWCI_NUM() {
		return WCI_NUM;
	}
	public void setWCI_NUM(int wCI_NUM) {
		WCI_NUM = wCI_NUM;
	}
	public int getWC_COUNT() {
		return WC_COUNT;
	}
	public void setWC_COUNT(int wC_COUNT) {
		WC_COUNT = wC_COUNT;
	}
	public String getWC_DATE() {
		return WC_DATE;
	}
	public void setWC_DATE(String wC_DATE) {
		WC_DATE = wC_DATE;
	}
	public String getWC_CONTENT() {
		return WC_CONTENT;
	}
	public void setWC_CONTENT(String wC_CONTENT) {
		WC_CONTENT = wC_CONTENT;
	}
	public String getWC_TAG1() {
		return WC_TAG1;
	}
	public void setWC_TAG1(String wC_TAG1) {
		WC_TAG1 = wC_TAG1;
	}
	public String getWC_TAG2() {
		return WC_TAG2;
	}
	public void setWC_TAG2(String wC_TAG2) {
		WC_TAG2 = wC_TAG2;
	}

	
	
	
}
