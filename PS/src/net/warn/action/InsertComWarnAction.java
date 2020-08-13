package net.warn.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.image.db.Image;
import net.warn.db.WarnComment;
import net.warn.db.WarnDAO;
import net.warn.db.WarnImage;

public class InsertComWarnAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");		
		int comno = Integer.parseInt(request.getParameter("comnum"));
		String[] tag = request.getParameterValues("wtag");
		
		WarnComment wc = new WarnComment();
		wc.setWM_ID(id);
		wc.setWC_NUM(comno);
		
		for (int i = 0; i < tag.length; i++) {
			if(tag[i].contentEquals("1")) {
				wc.setWC_TAG1("1");
			}else if(tag[i].contentEquals("2")){
				wc.setWC_TAG2("1");
			}
		}
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		WarnDAO wdao = new WarnDAO();
		int result = wdao.cominsert(wc);
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
