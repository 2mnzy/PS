package net.warn.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.image.db.Image;
import net.warn.db.WarnDAO;
import net.warn.db.WarnImage;

public class ReportimgAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int imgno = Integer.parseInt(request.getParameter("I_NUM"));
		ActionForward forward = new ActionForward();
		WarnDAO wdao = new WarnDAO();
		WarnImage imgid = wdao.img_info(imgno);
		
		forward.setPath("form/image_warn_form.jsp");
		forward.setRedirect(false);
		request.setAttribute("imginfo", imgid);
		return forward;
	}
}
