package net.comment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.comment.db.CommentDAO;

public class CommentDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean result = false;
		
		int c_num = Integer.parseInt(request.getParameter("num"));
		System.out.println(c_num);
		String id = request.getParameter("id");
		System.out.println(id);
		CommentDAO commentdao = new CommentDAO();
		
		result = commentdao.commentdelete(c_num);
		
		if(result==false) {
			System.out.println("댓글 삭제 실패");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "댓글 삭제를 하지 못했습니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		
		System.out.println("댓글 삭제 성공");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('삭제 완료');");
		out.println("history.back(-1);</script>");
		return null;
	}

}
