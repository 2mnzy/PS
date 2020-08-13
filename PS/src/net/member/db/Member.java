package net.member.db;

public class Member {

	private String ID;
	private String NAME;
	private String BIRTH;
	private int AGE;
	private String GENDER;
	private String PASSWORD;
	private String EMAIL;
	private String TAG1="0";
	private String TAG2="0";
	private String TAG3="0";
	private String TAG4="0";
	private String TAG5="0";
	private int LIKECOUNT;
	
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getBIRTH() {
		return BIRTH;
	}
	public void setBIRTH(String bIRTH) {
		BIRTH = bIRTH;
	}
	public int getAGE() {
		return AGE;
	}
	public void setAGE(int aGE) {
		AGE = aGE;
	}
	public String getGENDER() {
		return GENDER;
	}
	public void setGENDER(String gENDER) {
		GENDER = gENDER;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public String getTAG1() {
		return TAG1;
	}
	public void setTAG1(String tAG1) {
		TAG1 = tAG1;
	}
	public String getTAG2() {
		return TAG2;
	}
	public void setTAG2(String tAG2) {
		TAG2 = tAG2;
	}
	public String getTAG3() {
		return TAG3;
	}
	public void setTAG3(String tAG3) {
		TAG3 = tAG3;
	}
	public String getTAG4() {
		return TAG4;
	}
	public void setTAG4(String tAG4) {
		TAG4 = tAG4;
	}
	public String getTAG5() {
		return TAG5;
	}
	public void setTAG5(String tAG5) {
		TAG5 = tAG5;
	}
	public int getLIKECOUNT() {
		return LIKECOUNT;
	}
	public void setLIKECOUNT(int lIKECOUNT) {
		LIKECOUNT = lIKECOUNT;
	}
	
}