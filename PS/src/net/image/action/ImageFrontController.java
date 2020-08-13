package net.image.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.im")
public class ImageFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ImageFrontController() {
        super();
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    	ActionForward forward= null;
    	Action action= null;
    	
    	if(command.equals("/Imagemain.im")) {
    		action = new ImageMainAction(); //다형성에 의한 업 캐스팅
    		try {
    			forward = action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/Imageview.im")){
    		action = new ImageViewAction();
    		try {
    			forward = action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/Imagelocation.im")){
    		action = new ImageLocationAction();
    		try {
    			forward = action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/Imageuploadview.im")){
    		action = new ImageUploadViewAction();
    		try {
    			forward = action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/myimagecount.im")){
    		action = new MyImagecountAction();
    		try {
    			forward = action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/myimagelikecount.im")){
    		action = new MyImagelikecountAction();
    		try {
    			forward = action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/Imageupload.im")){
    		action = new ImageUploadAction();
    		try {
    			forward = action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/Imagelike.im")){
    		action = new ImageLikeAction();
    		try {
    			forward = action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/getmain_image.im")){
    		action = new Getmain_imageAction();
    		try {
    			forward = action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/total_hot_image_list.im")){
    		action = new ImageHotphotoTotalAction();
    		try {
    			forward = action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/male_hot_image_list.im")){
    		action = new ImageHotphotoMaleAction();
    		try {
    			forward = action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/female_hot_image_list.im")){
    		action = new ImageHotphotoFemaleAction();
    		try {
    			forward = action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/tag1_hot_image_list.im")){
    		action = new ImageHotphotoTag1Action();
    		try {
    			forward = action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/tag2_hot_image_list.im")){
    		action = new ImageHotphotoTag2Action();
    		try {
    			forward = action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/tag3_hot_image_list.im")){
    		action = new ImageHotphotoTag3Action();
    		try {
    			forward = action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/tag4_hot_image_list.im")){
    		action = new ImageHotphotoTag4Action();
    		try {
    			forward = action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/tag5_hot_image_list.im")){
    		action = new ImageHotphotoTag5Action();
    		try {
    			forward = action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/10th_hot_image_list.im")){
    		action = new ImageHotphoto10Action();
    		try {
    			forward = action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/20th_hot_image_list.im")){
    		action = new ImageHotphoto20Action();
    		try {
    			forward = action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/30th_hot_image_list.im")){
    		action = new ImageHotphoto30Action();
    		try {
    			forward = action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/40th_hot_image_list.im")){
    		action = new ImageHotphoto40Action();
    		try {
    			forward = action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/50th_hot_image_list.im")){
    		action = new ImageHotphoto50Action();
    		try {
    			forward = action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/image_view_hotphoto_form.im")){
    		
    		action = new ImageHotphotoViewAction();
    		try {
    			forward = action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/imagelikecount.im")){
    		
    		action = new ImageLikeCountAction();
    		try {
    			forward = action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/imagecontent.im")){
    		
    		action = new ImagecontentAction();
    		try {
    			forward = action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    	
    	
    	if(forward != null) {
    		if(forward.isRedirect()) {//리다이렉트 됩니다.
    			response.sendRedirect(forward.getPath());
    		}else { //������ �˴ϴ�.
    			RequestDispatcher dispatcher =
    					request.getRequestDispatcher(forward.getPath());
    			dispatcher.forward(request, response);
    		}
    	}
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doProcess(request, response);
	}

}
