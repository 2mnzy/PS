package net.image.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.image.db.ImageDAO;

public class MyImagelikecountAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ImageDAO dao= new ImageDAO();
		int result = dao.getImageHeartCount(request.getParameter("id"));
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(Integer.toString(result));
		return null;
	}

}
