package net.comment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.comment.db.CommentDAO;


public class MyCommentcountAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommentDAO dao = new CommentDAO();
		int result = dao.getCommentCount(request.getParameter("id"));
		response.getWriter().append(Integer.toString(result));
		return null;
	}

}
