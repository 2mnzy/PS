package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberDAO;

public class MyNameAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberDAO dao = new MemberDAO();
		String result = dao.getName(request.getParameter("id"));
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(result);
		return null;
	}

}
