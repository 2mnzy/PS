package net.comment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.comment.db.Comment;
import net.comment.db.CommentDAO;

public class CommentAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommentDAO commentdao = new CommentDAO();
		Comment commentdata = new Comment();
		ActionForward forward = new ActionForward();
		System.out.println(request.getParameter("comment_board"));
		//여기로 오니까 넣어주세요
		
		int num=Integer.parseInt(request.getParameter("num")); //String->int로 바꾸어 주시고
		
		boolean result = false;
		
		try {
			commentdata.setB_NUM(Integer.parseInt(request.getParameter("comment_board")));
			commentdata.setM_ID(request.getParameter("comment_id"));
			commentdata.setC_DATE(request.getParameter("comment_date"));
			commentdata.setC_CONTENT(request.getParameter("comment_content"));
			
			result = commentdao.commentInsert(commentdata);
			
			if(result==false) {
				System.out.println("result가 false입니다.");
				forward.setPath("error/error.jsp");
				request.setAttribute("message", "오류가 발생했습니다.");
				forward.setRedirect(false);
				return forward;
			}
			
			//글 등록 완료시
			forward.setRedirect(false);
			forward.setPath("/BoardDetailAction.bo?num="+ num);  //글의 내용을 보고자 하면 게시판 글 번호가 있어야 하는데 보내주지 않네요
			System.out.println("글 등록 완료 - 이동");
			return forward;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
