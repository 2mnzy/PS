package net.image.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageHotphotoViewAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		System.out.println("ImageMainHotphotoAction 도착");
		forward.setPath("hotphoto/image_view_hotphoto_form.jsp");

		return forward;
	}

}
