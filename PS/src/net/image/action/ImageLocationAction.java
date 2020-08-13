package net.image.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.image.db.Image;
import net.image.db.ImageDAO;

public class ImageLocationAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		ImageDAO idao = new ImageDAO();
		Image imagelocation = new Image();
		int I_NUM = Integer.parseInt(request.getParameter("I_NUM"));
		System.out.println(I_NUM);
		imagelocation =idao.getLocation(I_NUM);
		double lat = imagelocation.getI_LATITUDE();
		double lng = imagelocation.getI_LONGTITUDE();
		System.out.println("lat = " +lat);
		System.out.println("lng = " +lng);
		request.setAttribute("lat", lat);
		request.setAttribute("lng", lng);
		forward.setRedirect(false);
		System.out.println("ImageLocationAction 도착");
		forward.setPath("form/image_view_location.jsp");
		
		return forward;
	}

}
