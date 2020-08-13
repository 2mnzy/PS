package net.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.*;

public class ListAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      ActionForward forward = new ActionForward();
      MemberDAO mdao = new MemberDAO();
      
      int page=1;
      int limit= 3;
      
      if(request.getParameter("page") != null) {
         page= Integer.parseInt(request.getParameter("page"));
      }
      System.out.println("넘어온 페이지 = "+page);
      
      //총 리스트 수를 받아옵니다.
      int listcount = mdao.getListCount();
     List<Member> list = mdao.getList(page, limit);
      
     int maxpage= (listcount+limit -1)/limit;
     System.out.println("총 페이지 수= "+maxpage);
     
     int startpage=((page-1)/10)*10 +1;
     int endpage = startpage+10-1;
     System.out.println("현재 페이지에 보여줄 마지막 페이지 수="+endpage);
     System.out.println("현재 페이지에 보여줄 시작 페이지 수="+startpage);
     
     if(endpage>maxpage)endpage=maxpage;
     request.setAttribute("page", page);//현재 페이지 수
     request.setAttribute("maxpage", maxpage);//최대 페이지 수
     
     //현재 페이지에 표시할 첫 페이지 수
     request.setAttribute("startpage", startpage);
     
   //현재 페이지에 표시할 끝 페이지 수
     request.setAttribute("endpage", endpage);
     
     request.setAttribute("listcount", listcount); //�� ���� ��
     
     //해당 페이지의 글 목록을 갖고 있는 리스트
     request.setAttribute("totallist", list);
     
     
     
    System.out.println(list.size());
    forward.setPath("member/member_list.jsp");
    forward.setRedirect(false);
    return forward;
     
     
   }

}