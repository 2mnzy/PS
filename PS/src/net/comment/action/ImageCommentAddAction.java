package net.comment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.comment.db.Comment;
import net.comment.db.CommentDAO;

public class ImageCommentAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommentDAO commentdao = new CommentDAO();
		Comment commentdata = new Comment();
		ActionForward forward = new ActionForward();
		
		int I_NUM=Integer.parseInt(request.getParameter("I_NUM")); //String->int로 바꾸어 주시고
		String FILENAME= request.getParameter("FILENAME");
		System.out.println(request.getParameter("I_NUM"));
		
		boolean result = false;
		
		try {
			commentdata.setI_NUM(I_NUM);
			commentdata.setM_ID(request.getParameter("id"));
			commentdata.setC_CONTENT(request.getParameter("C_CONTENT"));
			
			result = commentdao.ImagecommentInsert(commentdata);
			
			if(result==false) {
				System.out.println("result가 false입니다.");
				forward.setPath("error/error.jsp");
				request.setAttribute("message", "오류가 발생했습니다.");
				forward.setRedirect(false);
				return forward;
			}
			
			//글 등록 완료시
			forward.setRedirect(false);
			forward.setPath("/Imageview.im?I_NUM="+ I_NUM+"&FILENAME="+FILENAME);  //글의 내용을 보고자 하면 게시판 글 번호가 있어야 하는데 보내주지 않네요
			System.out.println("이미지 댓글 등록 완료");
			return forward;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
