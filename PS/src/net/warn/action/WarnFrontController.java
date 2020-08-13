package net.warn.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





@WebServlet("*.wa")
public class WarnFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public void doProcess(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException{
    	
    	String RequestURI =request.getRequestURI();
    	System.out.println("RequestURI = " + RequestURI);
    	
    	//getContextPath(): 컨텍스트 경로가 반환됩니다.
    	//contextPath는 "/JspProject"가 반환됩니다.
    	String contextPath =request.getContextPath();
    	System.out.println("contextPath = "+ contextPath);
    	
    	//RequestURI에서 컨텍스트 경로 길이 값의 인덱스 위치의 문자부터
    	//마지막 위치 문자까지 추출합니다.
    	//command는 "\login.net"반환됩니다.
    	String command=RequestURI.substring(contextPath.length());
    	System.out.println("command = "+ command);
    	
    	//초기화
    	ActionForward forward =null;
    	Action action =null;
    	
    	if(command.equals("/board_warn_board_list.wa")) {
    		action = new WarnBoardAction();
    		 try {
    			 forward =action.execute(request,response);
    		 }catch(Exception e){
    			 e.printStackTrace();
    		 }
    	}if(command.equals("/comment_warn_board_list.wa")) {
    		action = new WarnCommentAction();
   		 try {
   			 forward =action.execute(request,response);
   		 }catch(Exception e){
   			 e.printStackTrace();
   		 }
    	}if(command.equals("/image_warn_board_list.wa")) {
		action = new WarnImageAction();
		 try {
			 forward =action.execute(request,response);
		 }catch(Exception e){
			 e.printStackTrace();
		 }
    	}if(command.equals("/member_warn_board_list.wa")) {
		action = new WarnMemberAction();
		 try {
			 forward =action.execute(request,response);
		 }catch(Exception e){
			 e.printStackTrace();
		 }
    	}if(command.equals("/mywarncount.wa")) {
    		action = new MyWarncountAction();
   		 try {
   			 forward =action.execute(request,response);
   		 }catch(Exception e){
   			 e.printStackTrace();
   		 }
       	}else if(command.equals("/reportimg.wa")) {
    		action = new ReportimgAction();
   		 try {
   			 forward =action.execute(request,response);
   		 }catch(Exception e){
   			 e.printStackTrace();
   		 }
       	}else if(command.equals("/warnProcess.wa")) {
    		action = new InsertImgWarnAction();
   		 try {
   			 forward =action.execute(request,response);
   		 }catch(Exception e){
   			 e.printStackTrace();
   		 }
       	}else if(command.equals("/reportcom.wa")) {
    		action = new ReportcomAction();
   		 try {
   			 forward =action.execute(request,response);
   		 }catch(Exception e){
   			 e.printStackTrace();
   		 }
       	}else if(command.equals("/reportbo.wa")) {
    		action = new ReportboAction();
   		 try {
   			 forward =action.execute(request,response);
   		 }catch(Exception e){
   			 e.printStackTrace();
   		 }
       	}else if(command.equals("/warnComProcess.wa")) {
    		action = new InsertComWarnAction();
   		 try {
   			 forward =action.execute(request,response);
   		 }catch(Exception e){
   			 e.printStackTrace();
   		 }
       	}else if(command.equals("/bowarnProcess.wa")) {
    		action = new InsertBoWarnAction();
   		 try {
   			 forward =action.execute(request,response);
   		 }catch(Exception e){
   			 e.printStackTrace();
   		 }
       	}
    	
    	
    	if( forward != null) {
    		if(forward.isRedirect()){ //리다이렉트 됩니다.
    			response.sendRedirect(forward.getPath());	
    		}else { //포워딩 됩니다.
    			RequestDispatcher dispatcher =
    					request.getRequestDispatcher(forward.getPath());
    			dispatcher.forward(request, response);
    			
    		}
    	}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doProcess(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
