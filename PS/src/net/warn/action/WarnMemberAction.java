package net.warn.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


import net.warn.db.WarnDAO;
import net.warn.db.WarnMember;

public class WarnMemberAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		List<WarnMember> warnMemberlist = new ArrayList<WarnMember>();
		WarnDAO warndao = new WarnDAO();
		String warnlinkpage ="";
		
		int page=1; 
		int limit =10; 
		if(request.getParameter("page")!= null) {
			page= Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지="+page);
		
		if(request.getParameter("limit")!= null) {
			limit= Integer.parseInt(request.getParameter("limit"));
		}
		System.out.println("넘어온 limit="+limit);
		
			warnlinkpage=(String) request.getAttribute("WarnLinkPage");
	
		
		
		
		
		System.out.println("WarnLinkPage ="+warnlinkpage );
		
		
		int listcount= warndao.getMemberListCount();
		
		
		warnMemberlist = warndao.getWarnMemberList(page,limit);
		System.out.println("warnmemberlist size = "+warnMemberlist.size());
		
		int maxpage= (listcount+limit -1)/limit;
		System.out.println("넘어온 최대 페이지="+maxpage);
		
		int startpage= ((page-1)/10)*10+1;
		System.out.println("시작 페이지 ="+startpage);
		
		
		
		
		//endpage: ���� ������ �׷쿡�� ������ ������ ������ ��([10],[20],[30]��..)
		int endpage= startpage+10-1;
		System.out.println("끝나는 페이지 ="+endpage);
		
		
		if(endpage>=maxpage) {
			endpage=maxpage;
		}
		String state= request.getParameter("state");
		List<WarnMember> warnmember = new ArrayList<WarnMember>();//modal��
		warnmember = warndao.getWarnMember(page,limit);
		ActionForward forward = new ActionForward();
		
		if(state==null) {
			System.out.println("state=null");
			request.setAttribute("page", page);
			request.setAttribute("maxpage", maxpage); 
			
			
			request.setAttribute("startpage", startpage);
			
			
			request.setAttribute("endpage", endpage);
			
			request.setAttribute("listcount", listcount);
			
			
			request.setAttribute("warnmemberlist", warnMemberlist);
			request.setAttribute("warnmember", warnmember);
			request.setAttribute("limit", limit);
			
			forward.setRedirect(false);
			
			
		forward.setPath("board/member_warn_board_list.jsp");
		forward.setRedirect(false);
		return forward;
		}else {
			System.out.println("state=ajx");
			
			
			JsonObject object = new JsonObject();
			object.addProperty("page", page); 
			object.addProperty("maxpage", maxpage);
			object.addProperty("startpage", startpage);
			object.addProperty("endpage", endpage);
			object.addProperty("listcount", listcount);
			object.addProperty("limit", limit);
			
			
			JsonElement je= new Gson().toJsonTree(warnMemberlist);
			System.out.println("warnmemberlist"+je);
			object.add("warnmemberlist", je);
			
			Gson gson = new Gson();
			String json= gson.toJson(object);
			
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().append(json);
			System.out.println(json);
			return null;
			
		}
	}
}