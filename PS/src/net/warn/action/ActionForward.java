package net.warn.action;

public class ActionForward {
private boolean redirect=false;
private String path=null;

//property Redirect의 is 메소드
public boolean isRedirect() {
	//프로퍼티 타입이 boolean일 경우 get 대신 is를 붙일 수 있습니다.
	return redirect;
}

//property Redirect의 set 메소드
public void setRedirect(boolean b) {
	redirect = b;
}


//property path의 get 메소드
public String getPath() {
	return path;
}

//property patht의 set 메소드
public void setPath(String s) {
	path = s;
}

}