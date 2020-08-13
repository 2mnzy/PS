package net.image.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.image.db.*;
import net.member.db.MemberDAO;

public class ImageMainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ImageDAO imagedao= new ImageDAO();
		Image image= new Image();
		String id= request.getParameter("id");
		String pass= request.getParameter("pass");
		ActionForward forward = new ActionForward();
		
		List<Image> imagelist = new ArrayList<Image>();
		imagelist = imagedao.Getmain_image();
		
		request.setAttribute("imagelist", imagelist);
		
		forward.setRedirect(false);
		System.out.println("ImageMainAction 도착");
		
		forward.setPath("main/main.jsp");
		return forward;
		
	}


}
