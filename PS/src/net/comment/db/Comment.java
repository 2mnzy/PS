package net.comment.db;

public class Comment {
	private int C_NUM;
	private int B_NUM;
	private int I_NUM;
	private String M_ID;
	private String C_CONTENT;
	private String C_DATE;
	
	public int getC_NUM() {
		return C_NUM;
	}
	public void setC_NUM(int c_NUM) {
		C_NUM = c_NUM;
	}
	public int getB_NUM() {
		return B_NUM;
	}
	public void setB_NUM(int b_NUM) {
		B_NUM = b_NUM;
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
	public String getC_CONTENT() {
		return C_CONTENT;
	}
	public void setC_CONTENT(String c_CONTENT) {
		C_CONTENT = c_CONTENT;
	}
	public String getC_DATE() {
		return C_DATE;
	}
	public void setC_DATE(String c_DATE) {
		C_DATE = c_DATE;
	}
}
