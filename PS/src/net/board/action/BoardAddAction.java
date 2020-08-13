package net.board.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.board.db.*;


public class BoardAddAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDAO boarddao = new BoardDAO();
		BoardBean boarddata = new BoardBean();
		ActionForward forward = new ActionForward();
		//String[] B_TAG = request.getParameterValues("B_TAG");
	
		boolean result= false;
		
		try {
			//BoardBean 객체에 글등록  폼에서 입력 받은 정보들을 저장합니다.
			boarddata.setB_PASS(request.getParameter("B_PASS"));
			boarddata.setB_TAG(request.getParameter("B_TAG"));
			boarddata.setB_ID(request.getParameter("B_ID"));
			boarddata.setB_TITLE(request.getParameter("B_TITLE"));
			boarddata.setB_CONTENT(request.getParameter("B_CONTENT"));
			boarddata.setB_DATE(request.getParameter("B_DATE"));
			
			//업로드도니 파일의 시스템 상에 업로드된 실제 파일명을 얻어 옵니다.
			//글 등록 처리를 위해 DAO의 boardInsert()메소드를 호출합니다.
			//글 등록 폼에서 입력한 정보가 저장되어 있는 boarddata 객체를 전달합니다.
			result= boarddao.boardInsert(boarddata);
			
			//글 등록에 실패할 경우 false를 반환합니다.
			if(result==false) {
				System.out.println("�Խ��� ��� ����");
				forward.setPath("error/error.jsp");
				request.setAttribute("message", "�Խ��� ��� �����Դϴ�.");
				forward.setRedirect(false);
				return forward;
			}
			System.out.println("�Խ��� ��� �Ϸ�");
			
			//글 등록이 완료되면 글 목록을 단순히 보여주기만 할 것이므로 Redirect 여부를 true로 설정합니다.
			forward.setRedirect(true);
			
			forward.setPath("BoardList.bo");
			return forward;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
