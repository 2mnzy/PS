package net.image.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;



import net.image.db.*;


public class ImageUploadAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ImageDAO Imagedao = new ImageDAO();
		Image imagedata = new Image();
		ActionForward forward = new ActionForward();
		
		String realFolder="";
		
		//WebContent아래에 꼭 폴더 생성하세요
		String saveFolder="uploadedimage";
		
		int fileSize= 5*1024*1024; //업로드할 파일의 최대 사이즈 입니다. 5MB
		
		//실제 저장경로를 저장합니다.
		ServletContext sc= request.getServletContext();
		realFolder= sc.getRealPath(saveFolder);
		/*
		 * realFolder= request.getSession().getServletContext().getRealPath(saveFolder);
		 * */
		
		System.out.println("realFolder= "+realFolder);
		boolean result= false;
		
		try {
			MultipartRequest multi= null;
			multi = new MultipartRequest(request, 
					realFolder,
					fileSize,
					"utf-8",
					new DefaultFileRenamePolicy());
			
			//imagedata 객체에 글등록  폼에서 입력 받은 정보들을 저장합니다.
			System.out.println("이미지 파일명: "+multi.getFilesystemName("IMAGE_FILE"));
			System.out.println("위도 : "+multi.getParameter("map_lat"));
			System.out.println("경도 : "+multi.getParameter("map_lng"));
			imagedata.setM_ID(multi.getParameter("M_ID"));
			imagedata.setI_CONTENT(multi.getParameter("IMAGE_CONTENT"));
			imagedata.setI_LATITUDE(Double.parseDouble(multi.getParameter("map_lat")));
			imagedata.setI_LONGTITUDE(Double.parseDouble(multi.getParameter("map_lng")));
			imagedata.setFILENAME(multi.getFilesystemName("IMAGE_FILE"));
			String [] tag = multi.getParameterValues("tag");
			
			for (int i = 0; i < tag.length; i++) {
		         if(tag[i].contentEquals("1")) {
		        	 imagedata.setI_TAG1("1");
		         }else if (tag[i].contentEquals("2")) {
		        	 imagedata.setI_TAG2("1");
		         }else if (tag[i].contentEquals("3")) {
		        	 imagedata.setI_TAG3("1");
		         }else if (tag[i].contentEquals("4")) {
		        	 imagedata.setI_TAG4("1");
		         }else if (tag[i].contentEquals("5")) {
		        	 imagedata.setI_TAG5("1");
		         }
		      }
			
			
			//업로드도니 파일의 시스템 상에 업로드된 실제 파일명을 얻어 옵니다.
			//글 등록 처리를 위해 DAO의 boardInsert()메소드를 호출합니다.
			//글 등록 폼에서 입력한 정보가 저장되어 있는 boarddata 객체를 전달합니다.
			result= Imagedao.imageInsert(imagedata);
			
			//글 등록에 실패할 경우 false를 반환합니다.
			if(result==false) {
				System.out.println("이미지 등록 실패");
				forward.setPath("error/error.jsp");
				request.setAttribute("message", "이미지 등록 실패입니다.");
				forward.setRedirect(false);
				return forward;
			}
			System.out.println("이미지 등록 성공");
			
			//글 등록이 완료되면 글 목록을 단순히 보여주기만 할 것이므로 Redirect 여부를 true로 설정합니다.
			forward.setRedirect(true);
			
			forward.setPath("index.jsp");
			return forward;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
