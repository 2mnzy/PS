package net.image.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.image.db.ImageDAO;

public class ImagecontentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ImageDAO dao= new ImageDAO();
		int I_NUM = Integer.parseInt(request.getParameter("I_NUM"));
		String result = dao.getImagecontent(I_NUM);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(result);
		return null;
	}

}
