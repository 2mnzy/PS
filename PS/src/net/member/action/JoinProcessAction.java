package net.member.action;



import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.Member;
import net.member.db.MemberDAO;

public class JoinProcessAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id= request.getParameter("id");	
		String pass= request.getParameter("pass");	
		String name= request.getParameter("name");	
		String email= request.getParameter("email");
		int age = Integer.parseInt(request.getParameter("age"));
		String gender= request.getParameter("gender");
		String [] tag = request.getParameterValues("tag");
		
		Member m = new Member();
		m.setAGE(age);
		m.setEMAIL(email);
		m.setGENDER(gender);
		m.setID(id);
		m.setNAME(name);
		m.setPASSWORD(pass);
		
		for (int i = 0; i < tag.length; i++) {
			if(tag[i].contentEquals("1")) {
				m.setTAG1("1");
			}else if (tag[i].contentEquals("2")) {
				m.setTAG2("1");
			}else if (tag[i].contentEquals("3")) {
				m.setTAG3("1");
			}else if (tag[i].contentEquals("4")) {
				m.setTAG4("1");
			}else if (tag[i].contentEquals("5")) {
				m.setTAG5("1");
			}
		}
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		MemberDAO mdao = new MemberDAO();
		
		int result = mdao.insert(m);
		out.println("<script>");
		if(result == 1) {
			out.println("alert('회원가입 축하드립니다');");
			out.println("location.href='index.jsp';");
		}else if(result == -1) {
			out.println("alert('아이디가 중복되었습니다. 다시 입력하세요');");
			//out.println("location.href='./join.net';"); //새로 고침되어 이전에 입력한 데이터가 나타나지 않습니다.
			out.println("history.back()");//비밀번호를 제외한 다른 데이터는 유지 되어 있습니다.
		}
		out.println("</script>");
		out.close();
		return null;
	}
	
}