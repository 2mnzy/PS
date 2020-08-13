package net.board.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;


public class BoardModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDAO boarddao = new BoardDAO();
		BoardBean boarddata = new BoardBean();
		ActionForward forward = new ActionForward();
		boolean result= false;
		
		try {
			int num= Integer.parseInt(request.getParameter("b_num"));
			String pass= request.getParameter("b_pass");
			//글쓴이인지 확인하기 위해 저장된 비밀번호와 입력한 비밀번호를 비교합니다.
			boolean usercheck= boarddao.isBoardWriter(num, pass);
			//비밀번호가 다른 경우
			if(usercheck == false){
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out= response.getWriter();
				out.println("<script>");
				out.println("alert('비밀번호가 다릅니다.');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
				return null;
			}
			
			//비밀번호가 일치하는 경우 수정 내용을 설정합니다.
			//BoardBean 객체에 글 등록 폼에서 입력 받은 정보들을 저장합니다.
			boarddata.setB_NUM(num);
			boarddata.setB_ID(request.getParameter("B_ID"));
			boarddata.setB_PASS(pass);
			boarddata.setB_TITLE(request.getParameter("B_TITLE"));
			System.out.println(request.getParameter("B_TITLE"));
			boarddata.setB_CONTENT(request.getParameter("B_CONTENT"));
			System.out.println(request.getParameter("B_CONTENT"));
			boarddata.setB_TAG(request.getParameter("B_TAG"));
			System.out.println(request.getParameter("B_TAG"));
			
			
			//DAO에서 수정 메소드 호출하여 수정합니다.
			result= boarddao.boardModify(boarddata);
			
			//수정에 실패한 경우
			if(result==false) {
				System.out.println("게시판 수정 실패");
				forward= new ActionForward();
				forward.setRedirect(false);
				request.setAttribute("message", "게시판 수정이 되지 않았습니다.");
				forward.setPath("error/error.jsp");
				return forward;
			}
			//수정 성공의 경우
			
			System.out.println("게시판 수정 완료");
			//수정한 글 내용을 보여주기 위해 글 내용 보기 페이지로 이동하기위해 경로를 설정합니다.
			forward.setRedirect(true);
			forward.setPath("BoardDetailAction.bo?num="+boarddata.getB_NUM());
			return forward;
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
}
