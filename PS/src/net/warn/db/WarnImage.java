package net.warn.db;

public class WarnImage {
	int ROWNUM;//리스트에서 rownum
	int WI_NUM;//받아온 이미지 넘버
	String WM_ID;//작성자 ID
	int WI_COUNT;//신고된 카운트
	String WI_DATE;//이미지 등록 날짜
	String WI_CONTENT;//이미지내용
	String WI_TAG1="0";//신고태그1
	String WI_TAG2="0";//신고태그2
	
	
	public int getROWNUM() {
		return ROWNUM;
	}
	public void setROWNUM(int rOWNUM) {
		ROWNUM = rOWNUM;
	}
	public int getWI_NUM() {
		return WI_NUM;
	}
	public void setWI_NUM(int wI_NUM) {
		WI_NUM = wI_NUM;
	}
	public String getWM_ID() {
		return WM_ID;
	}
	public void setWM_ID(String wM_ID) {
		WM_ID = wM_ID;
	}
	public int getWI_COUNT() {
		return WI_COUNT;
	}
	public void setWI_COUNT(int wI_COUNT) {
		WI_COUNT = wI_COUNT;
	}
	public String getWI_DATE() {
		return WI_DATE;
	}
	public void setWI_DATE(String wI_DATE) {
		WI_DATE = wI_DATE;
	}
	public String getWI_CONTENT() {
		return WI_CONTENT;
	}
	public void setWI_CONTENT(String wI_CONTENT) {
		WI_CONTENT = wI_CONTENT;
	}
	public String getWI_TAG1() {
		return WI_TAG1;
	}
	public void setWI_TAG1(String wI_TAG1) {
		WI_TAG1 = wI_TAG1;
	}
	public String getWI_TAG2() {
		return WI_TAG2;
	}
	public void setWI_TAG2(String wI_TAG2) {
		WI_TAG2 = wI_TAG2;
	}

	
	
}
