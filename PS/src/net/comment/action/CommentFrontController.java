package net.comment.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class CommentFrontController
 */
@WebServlet("*.com")
public class CommentFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	/*
    	 * 요청된 전체 URL 중에서 포트번호 다음 부터 마지막 문자열까지 반환됩니다.
    	 * 예) http://localhost:8088/JspProject/login.net인 경우 
    	 * "/JspProject/login.net" 반환됩니다.
    	 * */
    	String RequestURI = request.getRequestURI();
    	System.out.println("RequestURI= "+RequestURI);
    	
    	//getContextPath(): 컨텍스트 경로가 반환됩니다.
    	//contextPath는 "/JspProject"가 반환됩니다.
    	String contextPath= request.getContextPath();
    	System.out.println("contextPath= "+contextPath);
    	
    	//RequestURI에서 컨텍스트 경로 길이 값의 인덱스 위치의 문자부터
    	//마지막 위치 문자까지 추출합니다.
    	//command는 "\login.net"반환됩니다.
    	String command= RequestURI.substring(contextPath.length());
    	System.out.println("command="+ command);
    	
    	//초기화
    	ActionForward forward=null;
    	Action action = null;
    	
    	if(command.equals("/mycomment.com")) {
    		action = new MyCommentAction();
    		try {
    			forward= action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/mycommentcount.com")) {
    		action = new MyCommentcountAction();
    		try {
    			forward= action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}if(command.equals("/CommentAddAction.com")) {
    		action = new CommentAddAction();
    		try {
    			forward = action.execute(request, response);
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	}if(command.equals("/ImageCommentAddAction.com")) {
    		action = new ImageCommentAddAction();
    		try {
    			forward = action.execute(request, response);
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/CommentDeleteAction.com")) {
    		action = new CommentDeleteAction();
    		try {
    			forward = action.execute(request, response);
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    	
    	if(forward != null) {
    		if(forward.isRedirect()) { //�����̷�Ʈ �˴ϴ�.
    			response.sendRedirect(forward.getPath());
    		}else { //������ �˴ϴ�.
    			RequestDispatcher dispatcher =
    					request.getRequestDispatcher(forward.getPath());
    			dispatcher.forward(request, response);
    		}
    	}
	}
    
    
    //doProcess(request, response)�޼ҵ带 �����Ͽ� ��û�� get����̵�
    //post ����̵� ���۵Ǿ� ��� ���� �޼ҵ忡�� ��û�� ó���� �� �ֵ��� �Ͽ����ϴ�.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doProcess(request, response);
	}
}
