package net.warn.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


import net.warn.db.WarnDAO;
import net.warn.db.WarnImage;

public class WarnImageAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		List<WarnImage> warnimagelist = new ArrayList<WarnImage>();
		WarnDAO warndao = new WarnDAO();
		String warnlinkpage ="";
		
		int page=1; // ������ page
		int limit =10; //�� �������� ������ �Խ��� ����� ��
		if(request.getParameter("page")!= null) {
			page= Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("�Ѿ�� ������="+page);
		
		if(request.getParameter("limit")!= null) {
			limit= Integer.parseInt(request.getParameter("limit"));
		}
		System.out.println("�Ѿ�� limit="+limit);
		
			warnlinkpage=(String) request.getAttribute("WarnLinkPage");
		
		
		
		
		
		System.out.println("WarnLinkPage ="+warnlinkpage );
		
		//�� ����Ʈ ���� �޾ƿɴϴ�.
		int listcount= warndao.getImageListCount();
		
		//����Ʈ�� �޾ƿɴϴ�.
		warnimagelist = warndao.getWarnImageList(page,limit);
		System.out.println("warnimagelist size = "+warnimagelist.size());
		
		int maxpage= (listcount+limit -1)/limit;
		System.out.println("�� ��������="+maxpage);
		
		int startpage= ((page-1)/10)*10+1;
		System.out.println("���� �������� ������ ���������� �� ="+startpage);
		
		
		
		
		
		int endpage= startpage+10-1;
		System.out.println("���� ������ ������ ������ ������ �� ="+endpage);
		
		
		if(endpage>=maxpage) {
			endpage=maxpage;
		}
		String state= request.getParameter("state");
		List<WarnImage> warnimage = new ArrayList<WarnImage>();//modal��
		warnimage = warndao.getWarnImage(page,limit);
		
		ActionForward forward = new ActionForward();
		
		if(state==null) {
			System.out.println("state=null");
			request.setAttribute("page", page);
			request.setAttribute("maxpage", maxpage);
			
			
			request.setAttribute("startpage", startpage);
			
			
			request.setAttribute("endpage", endpage);
			
			request.setAttribute("listcount", listcount);
			
			
			request.setAttribute("warnimagelist", warnimagelist);
			request.setAttribute("warnimage", warnimage);
			request.setAttribute("limit", limit);
			
			forward.setRedirect(false);
			String image="image";
			request.setAttribute("WarnLinkPage", image);
			
		forward.setPath("board/image_warn_board_list.jsp");
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
			
			JsonElement je= new Gson().toJsonTree(warnimagelist);
			System.out.println("warnimagelist"+je);
			object.add("warnimagelist", je);
			
			Gson gson = new Gson();
			String json= gson.toJson(object);
			
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().append(json);
			System.out.println(json);
			return null;
			
		}
	}
}