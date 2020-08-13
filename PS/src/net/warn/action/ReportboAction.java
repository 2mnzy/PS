package net.warn.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.warn.db.WarnBoard;
import net.warn.db.WarnDAO;

public class ReportboAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int bno = Integer.parseInt(request.getParameter("B_NUM"));
		ActionForward forward = new ActionForward();
		WarnDAO wdao = new WarnDAO();
		WarnBoard board = wdao.board_info(bno);
		
		forward.setPath("board/board_warn_form.jsp");
		forward.setRedirect(false);
		request.setAttribute("boardinfo", board);
		return forward;
	}
}
