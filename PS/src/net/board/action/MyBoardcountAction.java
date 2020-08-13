package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardDAO;


public class MyBoardcountAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDAO dao = new BoardDAO();
		int result = dao.getBoardCount(request.getParameter("id"));
		response.getWriter().append(Integer.toString(result));
		return null;
	}

}
