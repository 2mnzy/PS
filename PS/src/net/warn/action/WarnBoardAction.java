package net.warn.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;



import net.warn.db.WarnBoard;
import net.warn.db.WarnDAO;

public class WarnBoardAction implements Action{
   
   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
   
      List<WarnBoard> warnboardlist = new ArrayList<WarnBoard>();
   
      WarnDAO warndao = new WarnDAO();
      String warnlinkpage ="";
      
      int page=1; //        page
      int limit =10; //                    Խ            
      
      if(request.getParameter("page")!= null) {
         page= Integer.parseInt(request.getParameter("page"));
      }
      System.out.println(" Ѿ         ="+page);
      
      if(request.getParameter("limit")!= null) {
         limit= Integer.parseInt(request.getParameter("limit"));
      }
      System.out.println("받아온 최대 표시할 페이지="+limit);
      
         warnlinkpage=(String) request.getAttribute("WarnLinkPage");
      
      
      
      
      
      System.out.println("WarnLinkPage ="+warnlinkpage );
      //       Ʈ       ޾ƿɴϴ .
      int listcount= warndao.getBoardListCount();
      
      //    Ʈ    ޾ƿɴϴ .
      warnboardlist = warndao.getWarnBoardList(page,limit);
      System.out.println("warnboardlist size = "+warnboardlist.size());
      
      int maxpage= (listcount+limit -1)/limit;
      System.out.println("최대 페이지="+maxpage);
      
      int startpage= ((page-1)/10)*10+1;
      System.out.println("                                   ="+startpage);
      
      
      
      
      //endpage:              ׷쿡                          ([10],[20],[30]  ..)
      int endpage= startpage+10-1;
      System.out.println("                                    ="+endpage);
      
      
      if(endpage>=maxpage) {
         endpage=maxpage;
      }
      String state= request.getParameter("state");
      List<WarnBoard> warnboard = new ArrayList<WarnBoard>();//modal  
      warnboard = warndao.getWarnBoard(page,limit);
      
      ActionForward forward = new ActionForward();
      
      if(state==null) {
         System.out.println("state=null");
         request.setAttribute("page", page);//              
         request.setAttribute("maxpage", maxpage); // ִ           
         
         //              ǥ     ù          
         request.setAttribute("startpage", startpage);
         
         //              ǥ                
         request.setAttribute("endpage", endpage);
         
         request.setAttribute("listcount", listcount);//          
         
         // ش                          ִ      Ʈ
         request.setAttribute("warnboardlist", warnboardlist);
         request.setAttribute("warnboard", warnboard);
         request.setAttribute("limit", limit);
         
         forward.setRedirect(false);
         
         //                 ̵  ϱ         θ       մϴ .
      forward.setPath("board/board_warn_board_list.jsp");
      forward.setRedirect(false);
      return forward;
      }else {
         System.out.println("state=ajx");
         
         //       request     Ҵ       JsonObject       ϴ .
         JsonObject object = new JsonObject();
         object.addProperty("page", page); //{"page":      page     }              
         object.addProperty("maxpage", maxpage);
         object.addProperty("startpage", startpage);
         object.addProperty("endpage", endpage);
         object.addProperty("listcount", listcount);
         object.addProperty("limit", limit);
         
         //JsonObject   List                ִ  addProperty()           ʽ  ϴ .
         //void.com.google.gson.JsonObject.add(String property, JsonElement value)
         //List       JsonElement    ٲ  ־   object              ֽ  ϴ .
         //List=> JsonElement
         
         JsonElement je= new Gson().toJsonTree(warnboardlist);
         System.out.println("warnboardlist"+je);
         object.add("warnboardlist", je);
         
         Gson gson = new Gson();
         String json= gson.toJson(object);
         
         response.setContentType("text/html; charset=utf-8");
         response.getWriter().append(json);
         System.out.println(json);
         return null;
         
      }
   }
}