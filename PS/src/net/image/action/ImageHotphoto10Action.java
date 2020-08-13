package net.image.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.image.db.ImageDAO;
import net.image.db.ImageTotal;

public class ImageHotphoto10Action implements Action{

	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		ImageDAO idao =new ImageDAO();
		List<ImageTotal> totalList = new ArrayList<ImageTotal>();
		totalList = idao.get10HotPhoto();//객체 받아온고
		
		request.setAttribute("totalList", totalList);
		request.setAttribute("hot_tag", "10th");
		forward.setPath("hotphoto/hotphoto.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
