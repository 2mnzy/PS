package net.image.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.comment.db.*;

public class ImageViewAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		List<Comment> commentlist = new ArrayList<Comment>();
		Comment comment = new Comment();
		CommentDAO commentdao = new CommentDAO();
		int I_NUM = Integer.parseInt(request.getParameter("I_NUM"));
		
		commentlist= commentdao.getcommentlist(I_NUM);
		
		
		int listcount= commentdao.getImageCommentCount(I_NUM);
		
		
		request.setAttribute("listcount", listcount);//총 글의 수
		request.setAttribute("commentlist", commentlist);
		
		forward.setRedirect(false);
		System.out.println("ImageMainAction 도착");
		forward.setPath("form/image_view_form.jsp");
		return forward;
	}

}
