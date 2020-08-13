package net.image.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.image.db.*;

public class ImageLikeAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		int I_NUM= Integer.parseInt(request.getParameter("I_NUM"));
		String id = request.getParameter("id");
		ImageDAO dao= new ImageDAO();
		int result=0;
		
		result= dao.AddImageLike(id, I_NUM);
		
		
		System.out.println("I_NUM= "+I_NUM);
		System.out.println("id= "+id);
		
		forward.setRedirect(false);
		System.out.println("ImageLkeAction 완료");
		forward.setPath("Imageview.im?I_NUM="+I_NUM);
		return forward;
	}

}
