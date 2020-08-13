package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

public class RememberAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id= "";
		Cookie[] cookies= request.getCookies();
		int result=0;
		if(cookies != null) {
			for(int i=0; i<cookies.length; i++) {
				if(cookies[i].getName().equals("id")) {
					id=cookies[i].getValue();
				}
			}
			result=1;
		}
		request.setAttribute("id", id);
		response.getWriter().append(id);
		System.out.println("id cookie="+id);
		return null;
	}

}
