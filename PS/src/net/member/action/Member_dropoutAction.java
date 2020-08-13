package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberDAO;

public class Member_dropoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		MemberDAO mdao = new MemberDAO();
		String id = request.getParameter("id");
		if (id.equals("admin")) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('관리자는 삭제하지 않습니다. ');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}else {
			mdao.delete(id);
			forward.setPath("logout.net");
			forward.setRedirect(false);
		}
		return forward;
	}

}
