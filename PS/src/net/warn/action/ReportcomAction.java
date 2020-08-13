package net.warn.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import net.warn.db.WarnComment;
import net.warn.db.WarnDAO;


public class ReportcomAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ReportcomAction 이동");
		int comno = Integer.parseInt(request.getParameter("C_NUM"));
		ActionForward forward = new ActionForward();
		WarnDAO wdao = new WarnDAO();
		WarnComment comid = wdao.com_info(comno);
		
		forward.setPath("form/comment_warn_form.jsp");
		forward.setRedirect(false);
		request.setAttribute("cominfo", comid);
		return forward;
	}
}
