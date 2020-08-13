package net.image.db;

public class Image {
	private int I_NUM;//이미지 번호
	private String M_ID; //작성자 ID
	private String I_TAG1="0";//자연풍경
	private String I_TAG2="0";//인물
	private String I_TAG3="0";//음식
	private String I_TAG4="0";//동물
	private String I_TAG5="0";//기타
	private String I_CONTENT;//이미지 설명
	private String I_DATE;//업로드한 날짜
	private double I_LATITUDE;//이미지찍은 장소_ 위도
	private double I_LONGTITUDE;//이미지 찍은 장소_경도
	private int I_LIKECOUNT;//받은 좋아요 수
	private String FILENAME;
	
	public String getFILENAME() {
		return FILENAME;
	}
	public void setFILENAME(String fILENAME) {
		FILENAME = fILENAME;
	}
	public int getI_NUM() {
		return I_NUM;
	}
	public void setI_NUM(int i_NUM) {
		I_NUM = i_NUM;
	}
	public String getM_ID() {
		return M_ID;
	}
	public void setM_ID(String m_ID) {
		M_ID = m_ID;
	}
	public String getI_TAG1() {
		return I_TAG1;
	}
	public void setI_TAG1(String i_TAG1) {
		I_TAG1 = i_TAG1;
	}
	public String getI_TAG2() {
		return I_TAG2;
	}
	public void setI_TAG2(String i_TAG2) {
		I_TAG2 = i_TAG2;
	}
	public String getI_TAG3() {
		return I_TAG3;
	}
	public void setI_TAG3(String i_TAG3) {
		I_TAG3 = i_TAG3;
	}
	public String getI_TAG4() {
		return I_TAG4;
	}
	public void setI_TAG4(String i_TAG4) {
		I_TAG4 = i_TAG4;
	}
	public String getI_TAG5() {
		return I_TAG5;
	}
	public void setI_TAG5(String i_TAG5) {
		I_TAG5 = i_TAG5;
	}
	public String getI_CONTENT() {
		return I_CONTENT;
	}
	public void setI_CONTENT(String i_CONTENT) {
		I_CONTENT = i_CONTENT;
	}
	public String getI_DATE() {
		return I_DATE;
	}
	public void setI_DATE(String i_DATE) {
		I_DATE = i_DATE;
	}
	public double getI_LATITUDE() {
		return I_LATITUDE;
	}
	public void setI_LATITUDE(double i_LATITUDE) {
		I_LATITUDE = i_LATITUDE;
	}
	public double getI_LONGTITUDE() {
		return I_LONGTITUDE;
	}
	public void setI_LONGTITUDE(double i_LONGTITUDE) {
		I_LONGTITUDE = i_LONGTITUDE;
	}
	public int getI_LIKECOUNT() {
		return I_LIKECOUNT;
	}
	public void setI_LIKECOUNT(int i_LIKECOUNT) {
		I_LIKECOUNT = i_LIKECOUNT;
	}
}
