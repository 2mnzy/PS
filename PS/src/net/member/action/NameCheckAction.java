package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberDAO;

public class NameCheckAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberDAO dao = new MemberDAO();
		int result = dao.isName(request.getParameter("name"));
		response.getWriter().append(Integer.toString(result));
		System.out.println("result="+result);
		return null;
	}

}
