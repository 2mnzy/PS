package net.warn.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.warn.db.WarnDAO;

public class MyWarncountAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		WarnDAO dao = new WarnDAO();
		int result = dao.getWarnCount(request.getParameter("id"));
		response.getWriter().append(Integer.toString(result));
		return null;
	}

}
