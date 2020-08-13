package net.warn.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.warn.db.WarnBoard;
import net.warn.db.WarnDAO;

public class InsertBoWarnAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");		
		int imgno = Integer.parseInt(request.getParameter("bonum"));
		String[] tag = request.getParameterValues("wtag");
		
		WarnBoard wb = new WarnBoard();
		wb.setWM_ID(id);
		wb.setWB_NUM(imgno);
		
		for (int i = 0; i < tag.length; i++) {
			if(tag[i].contentEquals("1")) {
				wb.setWB_TAG1("1");
			}else if(tag[i].contentEquals("2")){
				wb.setWB_TAG2("1");
			}
		}
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		WarnDAO wdao = new WarnDAO();
		int result = wdao.boinsert(wb);
		out.println("<script>");
		if(result == 1) {
			out.println("alert('신고가 접수되었습니다.');");
			out.println("window.close();");
		}else if(result == -1) {
			out.println("alert('오류가 발생했습니다. 관리자에게 문의해주세요.');");
			out.println("history.back()");
		}
		out.println("</script>");
		out.close();
		return null;
	}
}
