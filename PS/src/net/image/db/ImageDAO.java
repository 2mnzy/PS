package net.image.db;	

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.board.db.BoardBean;

public class ImageDAO {
	//데이터베이스 작업에 필요한 인터페이스들의 레퍼런스 변수를 선언합니다.
		DataSource ds;
		
		public ImageDAO() {
		      try {
		         Context init = new InitialContext();
		         ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		      }catch(Exception ex) {
		         System.out.println("DB 연결 실패 : " + ex);
		         return;
		      }
		   }
	
	public int getImageCount(String id) {
		Connection con= null;
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		int count = 0;
		
		try {
			con= ds.getConnection();
			String sql = "select count(*) from image where M_ID= ?";
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				count= rs.getInt(1);
			}else {
				return count;
			}
			
		} catch(Exception ex) {
			System.out.println("getImageCount() 에러: "+ex);
		}finally {
			if(rs!= null)
	               try {
	                  rs.close();
	               }catch(SQLException ex) {
	                  ex.printStackTrace();
	               }
	            if(pstmt != null)
	               try {
	                  pstmt.close();
	               }catch(SQLException ex) {
	                  ex.printStackTrace();
	               }
	            if(con != null)
	               try {
	                  con.close();
	               }catch(SQLException ex) {
	                  ex.printStackTrace();
	               }
		}
		return count;
	}

	public int getImageHeartCount(String id) {
		Connection con= null;
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		int count = 0;
		
		try {
			con= ds.getConnection();
			String sql = "select I_LIKECOUNT from IMAGE where M_ID= ?";
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				count= rs.getInt(1);
			}else {
				return count;
			}
			
		} catch(Exception ex) {
			System.out.println("getImageHeartCount() 에러: "+ex);
		}finally {
			if(rs!= null)
	               try {
	                  rs.close();
	               }catch(SQLException ex) {
	                  ex.printStackTrace();
	               }
	            if(pstmt != null)
	               try {
	                  pstmt.close();
	               }catch(SQLException ex) {
	                  ex.printStackTrace();
	               }
	            if(con != null)
	               try {
	                  con.close();
	               }catch(SQLException ex) {
	                  ex.printStackTrace();
	               }
		}
		return count;
	}

	public boolean imageInsert(Image imagedata) {
		 Connection con=null;
	      PreparedStatement pstmt=null, pstmt2= null;
	      ResultSet rs =null;
	      int i_num=1;
	      String sql="";
	      boolean result = false;
	      try {
	         con = ds.getConnection();
	         
	       //board 테이블의 board_num 필드의 최대값을 구해와서 글을 등록할 때 글 번호를 순차적으로 지정하기 위함입니다.
	         
	         String max_sql = "select max(i_num) from image";
	         pstmt = con.prepareStatement(max_sql);
	         rs= pstmt.executeQuery();
	         
	         /*
	          * select max(board_num) from board
	          * 처음 글쓰기 하는 경우 rs.getInt(1)은 0입니다.
	          * if the value is SQL NULL, the value returned is 0
	          * 
	          * */
	         
	         if(rs.next()) {
	        	 

	        	 i_num= rs.getInt(1)+1;// 최대값보다 	1만큼 큰값을 지정합니다.
	         }
	         
	         sql= "insert into image"
	 	        	+" values(?,?,?,?,?,?,?,?,?,sysdate,?,?,?)";
	         // 새로운 글을 등록하는 부분입니다.
	         pstmt2= con.prepareStatement(sql);
	         System.out.println("I_num = "+i_num);
	         pstmt2.setInt(1, i_num);
	         pstmt2.setString(2, imagedata.getM_ID());
	         pstmt2.setString(3,imagedata.getFILENAME());
	         pstmt2.setString(4, imagedata.getI_TAG1());
	         pstmt2.setString(5, imagedata.getI_TAG2());
	         pstmt2.setString(6, imagedata.getI_TAG3());
	         pstmt2.setString(7, imagedata.getI_TAG4());
	         pstmt2.setString(8, imagedata.getI_TAG5());
	         pstmt2.setString(9, imagedata.getI_CONTENT());
	         // 데이트
	         pstmt2.setDouble(10, imagedata.getI_LATITUDE());
	         pstmt2.setDouble(11, imagedata.getI_LONGTITUDE());
	         pstmt2.setInt(12, 0);
	         
	         int r= pstmt2.executeUpdate();
	         
	         System.out.println("데이터 삽입이 모두 완료되었습니다.");
	         if(r==1)
	        	 result= true;
	         
	         }catch(Exception ex) {
	        	 System.out.println("ImageInsert() 에러: "+ex);
	        	 ex.printStackTrace();
	         }finally {
	        	if(rs != null)
		              try {
		                 rs.close();
		              }catch(SQLException ex) {
		                 ex.printStackTrace();
		              }
	            if(pstmt != null)
	               try {
	                  pstmt.close();
	               }catch(SQLException ex) {
	                  ex.printStackTrace();
	               }
	            if(con != null)
	               try {
	                  con.close();
	               }catch(SQLException ex) {
	                  ex.printStackTrace();
	               }
	         }//finally
	               return result;
	}

	public int AddImageLike(String id, int i_NUM) {
		 Connection con=null;
	     PreparedStatement pstmt=null, pstmt2= null, pstmt3=null, pstmt4=null, pstmt5=null,pstmt6=null, pstmt7=null,pstmt8=null,pstmt9=null;
	     PreparedStatement pstmt10=null;
	     ResultSet rs =null, rs2=null, rs3=null, rs4=null, rs5=null,rs6=null;
	     int m_age;
	     int YEAR_10=0;
	     int YEAR_20=0;
	     int YEAR_30=0;
	     int YEAR_40=0;
	     int YEAR_50=0;
	     int MALE_COUNT=0;
	     int FEMALE_COUNT=0;
	     String m_gender;
	     int result=0;
	     int I_LIKECOUNT=0;
	     
	     try {
	         con = ds.getConnection();
	         
	         String check_INUM = "SELECT COUNT(I_NUM) FROM IMAGE_LIKE WHERE I_NUM=?";
	         pstmt2 = con.prepareStatement(check_INUM);
	         pstmt2.setInt(1, i_NUM);
	         rs2= pstmt2.executeQuery();
	         
	         if(rs2.next()) {
	        	 System.out.println("rs2.getInt(1)="+rs2.getInt(1));
	         }
	         
	         
	         if(rs2.getInt(1)>0) {
	        	 System.out.println("image_like테이블에 i_num 있음");
	        	 String select_all ="SELECT MALE_COUNT, FEMALE_COUNT, YEAR_10, YEAR_20, YEAR_30, YEAR_40, YEAR_50 FROM IMAGE_LIKE WHERE I_NUM=?";
	        	 pstmt4 = con.prepareStatement(select_all);
	        	 pstmt4.setInt(1, i_NUM);
	        	 rs3= pstmt4.executeQuery();
	        	 if(rs3.next()) {
		        	 System.out.println("rs3.getInt(1)="+rs3.getInt(1));
		         }
	        	 MALE_COUNT= rs3.getInt("MALE_COUNT");
	        	 FEMALE_COUNT= rs3.getInt("FEMALE_COUNT");
	        	 YEAR_10= rs3.getInt("YEAR_10");
	        	 YEAR_20= rs3.getInt("YEAR_20");
	        	 YEAR_30= rs3.getInt("YEAR_30");
	        	 YEAR_40= rs3.getInt("YEAR_40");
	        	 YEAR_50= rs3.getInt("YEAR_50");
	        	 
	        	 
	        	 
	        	 String mem_sql = "select M_AGES, M_GENDER from MEMBER WHERE M_ID= ?";
		         pstmt = con.prepareStatement(mem_sql);
		         pstmt.setString(1, id);
		         rs= pstmt.executeQuery();
		         
		         if(rs.next()) {
		        	 System.out.println("M_AGES="+rs.getInt("M_AGES"));
		        	 System.out.println("M_GENDER="+rs.getString("M_GENDER"));
		        	 m_age= rs.getInt("M_AGES");
		        	 m_gender = rs.getString("M_GENDER");
		        	 
		        	 String check_click= "select Click from LIKE_COUNT WHERE ID=? and I_NUM=?";
		        	 pstmt8= con.prepareStatement(check_click);
		        	 pstmt8.setString(1, id);
		        	 pstmt8.setInt(2, i_NUM);
		        	 rs5 = pstmt8.executeQuery();
		        	 if(rs5.next()) {
		        		 System.out.println("이미 좋아요 눌렀습니다.");
		        		 result = 0;
		        		 return result;
		        	 }
		        	 else {
		        		 System.out.println("해당 게시물에 좋아요를 누른 적이 없군요- 좋아요 가능");
		        		 String insert_click= "insert into LIKE_COUNT VALUES(?, ?, 1)";
		        		 pstmt9=con.prepareStatement(insert_click);
		        		 pstmt9.setString(1, id);
		        		 pstmt9.setInt(2, i_NUM);
		        		 int r4= pstmt9.executeUpdate();
		        		 if(r4==1) {
		 	        		System.out.println("LIKE_COUNT에 삽입 완료");
		 	        		result= 1;
		 	        	}
		 	        	else {
		 	        		System.out.println("LIKE_COUNT에 삽입 실패");
		 	        		result=1;
		 	        	}
		        		 
		        	 }
		        	 
		         }
		         else {
		        	 System.out.println("사용자정보가 없습니다.");
		        	 result= 0;
		        	 return result;
		         }
		         
		         String check_click= "select Click from LIKE_COUNT WHERE ID=? and I_NUM=?";
	        	 pstmt10= con.prepareStatement(check_click);
	        	 pstmt10.setString(1, id);
	        	 pstmt10.setInt(2, i_NUM);
	        	 rs6 = pstmt10.executeQuery();
	        	 if(rs6.next()) {
	        		 System.out.println("이미 좋아요 눌렀습니다.");
	        		 result = 0;
	        		 return result;
	        	 }
		         
		         if(m_age/10==1) {
		        	 YEAR_10 +=1;
		         }
		         if(m_age/10==2) {
		        	 YEAR_20 +=1;
		        	 System.out.println("20대입니다");
		        	 System.out.println(YEAR_20);
		         }
		         if(m_age/10==3) {
		        	 YEAR_30 +=1;
		         }
		         if(m_age/10==4) {
		        	 YEAR_40 +=1;
		         }
		         if(m_age/10==5) {
		        	 YEAR_50 +=1;
		         }
		         
		         if(m_gender.equals("M")){
		        	 MALE_COUNT +=1;
		         }
		         if(m_gender.equals("F")){
		        	 FEMALE_COUNT +=1;
		        	 System.out.println("여자입니다");
		        	 System.out.println(FEMALE_COUNT);
		         }
	        	 
	        	 
	        	 
	        	 
	        	 String update = "UPDATE IMAGE_LIKE SET "
	        	 		+ "MALE_COUNT =?, "
	        	 		+ "FEMALE_COUNT=?,"
	        	 		+ "YEAR_10=?, "
	        	 		+ "YEAR_20=?, "
	        	 		+ "YEAR_30=?, "
	        	 		+ "YEAR_40=?, "
	        	 		+ "YEAR_50=? "
	        	 		+ "WHERE I_NUM=?";
	        	pstmt5 = con.prepareStatement(update);
	        	pstmt5.setInt(1, MALE_COUNT);
	        	pstmt5.setInt(2, FEMALE_COUNT);
	        	pstmt5.setInt(3, YEAR_10);
	        	pstmt5.setInt(4, YEAR_20);
	        	pstmt5.setInt(5, YEAR_30);
	        	pstmt5.setInt(6, YEAR_40);
	        	pstmt5.setInt(7, YEAR_50);
	        	pstmt5.setInt(8, i_NUM);
	        	
	        	int r= pstmt5.executeUpdate();  
	        	
	        	if(r==1) {
	        		System.out.println("update문 변경 완료");
	        		result= 1;
	        	}
	        	else {
	        		System.out.println("update문 결과값 없음");
	        		result=1;
	        	}
	        		
	        	String select_likecount = "SELECT I_LIKECOUNT FROM IMAGE WHERE I_NUM=?";
	        	pstmt6 = con.prepareStatement(select_likecount);
	        	pstmt6.setInt(1, i_NUM);
	        	rs4= pstmt6.executeQuery();
	        	if(rs4.next()) {
		        	 System.out.println("rs4.getInt(1)="+rs4.getInt(1));
		         }
	        	I_LIKECOUNT= rs4.getInt("I_LIKECOUNT")+1;
	        	System.out.println(I_LIKECOUNT);
	        	String add_likecount = "UPDATE IMAGE SET I_LIKECOUNT = ? WHERE I_NUM= ?";
	        	pstmt7= con.prepareStatement(add_likecount);
	        	pstmt7.setInt(1, I_LIKECOUNT);
	        	pstmt7.setInt(2, i_NUM);
	        	int r2= pstmt7.executeUpdate();  
	        	if(r2==1) {
	        		System.out.println("add_likecount update문 변경 완료");
	        		result= 1;
	        	}
	        	else {
	        		System.out.println("add_likecount update문 결과값 없음");
	        		result=1;
	        	}
	        	 
	         }
	         else {
	        	 System.out.println("image_like테이블에 i_num 없음");
	        	 
	        	 String mem_sql = "select M_AGES, M_GENDER from MEMBER WHERE M_ID= ?";
		         pstmt = con.prepareStatement(mem_sql);
		         pstmt.setString(1, id);
		         rs= pstmt.executeQuery();
		         
		         if(rs.next()) {
		        	 System.out.println("M_AGES="+rs.getInt("M_AGES"));
		        	 System.out.println("M_GENDER="+rs.getString("M_GENDER"));
		        	 m_age= rs.getInt("M_AGES");
		        	 m_gender = rs.getString("M_GENDER");
		         }
		         else {
		        	 System.out.println("사용자정보가 없습니다.");
		        	 result= 0;
		        	 return result;
		         }
		         
		         if(m_age/10==1) {
		        	 YEAR_10 +=1;
		         }
		         if(m_age/10==2) {
		        	 YEAR_20 +=1;
		         }
		         if(m_age/10==3) {
		        	 YEAR_30 +=1;
		         }
		         if(m_age/10==4) {
		        	 YEAR_40 +=1;
		         }
		         if(m_age/10==5) {
		        	 YEAR_50 +=1;
		         }
		         
		         if(m_gender.equals("M")){
		        	 MALE_COUNT +=1;
		         }
		         if(m_gender.equals("F")){
		        	 FEMALE_COUNT +=1;
		         }
		         
	        	 String insert_INUM= "INSERT INTO IMAGE_LIKE VALUES (?,?,?,?,?,?,?,?)";
	        	 pstmt3 = con.prepareStatement(insert_INUM);
	        	 pstmt3.setInt(1, i_NUM);
	        	 pstmt3.setInt(2, MALE_COUNT);
	        	 pstmt3.setInt(3, FEMALE_COUNT);
	        	 pstmt3.setInt(4, YEAR_10);
	        	 pstmt3.setInt(5, YEAR_20);
	        	 pstmt3.setInt(6, YEAR_30);
	        	 pstmt3.setInt(7, YEAR_40);
	        	 pstmt3.setInt(8, YEAR_50);
	        	 int r3= pstmt3.executeUpdate();  
		        	if(r3==1) {
		        		System.out.println("insert_INUM update문 변경 완료");
		        		result= 1;
		        	}
		        	else {
		        		System.out.println("insert_INUM update문 결과값 없음");
		        		result=1;
		        	}
		        	String select_likecount = "SELECT I_LIKECOUNT FROM IMAGE WHERE I_NUM=?";
		        	pstmt6 = con.prepareStatement(select_likecount);
		        	pstmt6.setInt(1, i_NUM);
		        	rs4= pstmt6.executeQuery();
		        	if(rs4.next()) {
			        	 System.out.println("rs4.getInt(1)="+rs4.getInt(1));
			         }
		        	I_LIKECOUNT= rs4.getInt("I_LIKECOUNT")+1;
		        	
		        	String add_likecount = "UPDATE IMAGE SET I_LIKECOUNT = ? WHERE I_NUM= ?";
		        	pstmt7= con.prepareStatement(add_likecount);
		        	pstmt7.setInt(1, I_LIKECOUNT);
		        	pstmt7.setInt(2, i_NUM);
		        	int r2= pstmt7.executeUpdate();  
		        	if(r2==1) {
		        		System.out.println("add_likecount update문 변경 완료");
		        		result= 1;
		        	}
		        	else {
		        		System.out.println("add_likecount update문 결과값 없음");
		        		result=1;
		        	}
	        	 
	         }
	        
	         
	         }catch(Exception ex) {
	        	 System.out.println("AddImageLike() 에러: "+ex);
	        	 ex.printStackTrace();
	         }finally {
	        	if(rs != null)
		              try {
		                 rs.close();
		              }catch(SQLException ex) {
		                 ex.printStackTrace();
		              }
	        	if(rs2 != null)
		              try {
		                 rs.close();
		              }catch(SQLException ex) {
		                 ex.printStackTrace();
		              }
	        	if(rs3 != null)
		              try {
		                 rs.close();
		              }catch(SQLException ex) {
		                 ex.printStackTrace();
		              }
	            if(pstmt != null)
	               try {
	                  pstmt.close();
	               }catch(SQLException ex) {
	                  ex.printStackTrace();
	               }
	            if(pstmt2 != null)
		               try {
		                  pstmt2.close();
		               }catch(SQLException ex) {
		                  ex.printStackTrace();
		               }
	            if(pstmt3 != null)
		               try {
		                  pstmt3.close();
		               }catch(SQLException ex) {
		                  ex.printStackTrace();
		               }
	            if(pstmt4 != null)
		               try {
		                  pstmt4.close();
		               }catch(SQLException ex) {
		                  ex.printStackTrace();
		               }
	            if(con != null)
	               try {
	                  con.close();
	               }catch(SQLException ex) {
	                  ex.printStackTrace();
	               }
	         }//finally
	     
	     
	     
		return 1;
	}

	public List<Image> Getmain_image() {
		Connection con= null;
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		
		//page: 페이지
		//limit: 페이지당 목록의 수
		//BOARD_RE_REF desc, BOARD_RE_SEQ_asc에 의해 정렬할 것을 조건절에 맞는 rnum의 범위 만큼 가져오는 쿼리문 입니다.
				
		
		String mainimagelist_sql = 
		           "select I_NUM, FILENAME FROM IMAGE";
		
		List<Image> list= new ArrayList<Image>();
		
		try {
			con= ds.getConnection();
			pstmt= con.prepareStatement(mainimagelist_sql);
			rs= pstmt.executeQuery();
			
			//DB에서 가져온 데이터를 VO 객체에 담습니다.
			while(rs.next()) {
				Image image= new Image();
				image.setI_NUM(rs.getInt("I_NUM"));
				image.setFILENAME(rs.getString("FILENAME"));
				list.add(image);//값을 담은 객체를 리스트에 저장합니다.
				}
		}catch(Exception ex) {
			System.out.println("Getmain_image() 에러: "+ex);
		}finally {
			if(rs!= null)
	               try {
	                  rs.close();
	               }catch(SQLException ex) {
	                  ex.printStackTrace();
	               }
	            if(pstmt != null)
	               try {
	                  pstmt.close();
	               }catch(SQLException ex) {
	                  ex.printStackTrace();
	               }
	            if(con != null)
	               try {
	                  con.close();
	               }catch(SQLException ex) {
	                  ex.printStackTrace();
	               }
		}
		
		
		return list;
	
	}
	
	public List<ImageTotal> getTotalHotPhoto(){
		   List<ImageTotal> list = new ArrayList<ImageTotal>();
		   Connection con=null;
	       PreparedStatement pstmt=null;
	       ResultSet rs =null;
	       
	       try{
	    	   con=ds.getConnection();
	    	   String total_hotphoto_sql ="select*from IMAGE order by I_LIKECOUNT desc";
	    	   							
	    	   pstmt =con.prepareStatement(total_hotphoto_sql);
	    	   
	    	   rs = pstmt.executeQuery();
	    	   int i =0;
	    	   while(rs.next()){
	    		   i++;
	    		   ImageTotal total = new ImageTotal();
	    		   System.out.println(rs.getInt("I_NUM"));
	    		   total.setI_NUM(rs.getInt("I_NUM"));
	    		   total.setFILENAME(rs.getString("FILENAME"));
	    		   list.add(total);
	    		   System.out.println("hotphotototal 입력완료 "+i );
	    	   }
	    	   return list;
	       }catch(Exception ex){
	    	   System.out.println("getTotalHotPhoto() error");
	       }finally {
	           if(rs != null)
	               try {
	                  rs.close();
	               }catch(SQLException ex) {
	                  ex.printStackTrace();
	               }
	          if(pstmt != null)
	             try {
	                pstmt.close();
	             }catch(SQLException ex) {
	                ex.printStackTrace();
	             }
	          if(con != null)
	             try {
	                con.close();
	             }catch(SQLException ex) {
	                ex.printStackTrace();
	             }
	       }//finally
		   
		   return list;
	   }
	   public List<ImageTotal> getMaleHotPhoto(){
		   List<ImageTotal> list = new ArrayList<ImageTotal>();
		   Connection con=null;
	       PreparedStatement pstmt=null;
	       ResultSet rs =null;
	       
	       try{
	    	   con=ds.getConnection();
	    	   String total_hotphoto_sql ="select IMAGE.I_NUM, IMAGE.FILENAME from image join image_like on image.I_NUM=image_LIKE.I_NUM where MALE_COUNT > 0 order by I_LIKECOUNT";
	    	   							
	    	   pstmt =con.prepareStatement(total_hotphoto_sql);
	    	   
	    	   rs = pstmt.executeQuery();
	    	   int i =0;
	    	   while(rs.next()){
	    		   i++;
	    		   ImageTotal total = new ImageTotal();
	    		   System.out.println(rs.getInt("I_NUM"));
	    		   total.setI_NUM(rs.getInt("I_NUM"));
	    		   total.setFILENAME(rs.getString("FILENAME"));
	    		   list.add(total);
	    		   System.out.println("hotphotoman 입력완료 "+i );
	    	   }
	    	   return list;
	       }catch(Exception ex){
	    	   System.out.println("getMaleHotPhoto() error");
	       }finally {
	           if(rs != null)
	               try {
	                  rs.close();
	               }catch(SQLException ex) {
	                  ex.printStackTrace();
	               }
	          if(pstmt != null)
	             try {
	                pstmt.close();
	             }catch(SQLException ex) {
	                ex.printStackTrace();
	             }
	          if(con != null)
	             try {
	                con.close();
	             }catch(SQLException ex) {
	                ex.printStackTrace();
	             }
	       }//finally
		   
		   return list;
	   }
	   
	   public List<ImageTotal> getFemaleHotPhoto(){
		   List<ImageTotal> list = new ArrayList<ImageTotal>();
		   Connection con=null;
	       PreparedStatement pstmt=null;
	       ResultSet rs =null;
	       
	       try{
	    	   con=ds.getConnection();
	    	   String total_hotphoto_sql ="select IMAGE.I_NUM, IMAGE.FILENAME  from image join image_like on image.I_NUM=image_LIKE.I_NUM where FEMALE_COUNT > 0 order by I_LIKECOUNT";
	    	   							
	    	   pstmt =con.prepareStatement(total_hotphoto_sql);
	    	   
	    	   rs = pstmt.executeQuery();
	    	   int i =0;
	    	   while(rs.next()){
	    		   i++;
	    		   ImageTotal total = new ImageTotal();
	    		   System.out.println(rs.getInt("I_NUM"));
	    		   total.setI_NUM(rs.getInt("I_NUM"));
	    		   total.setFILENAME(rs.getString("FILENAME"));
	    		   list.add(total);
	    		   System.out.println("hotphotofemale 입력완료 "+i );
	    	   }
	    	   return list;
	       }catch(Exception ex){
	    	   System.out.println("getFemaleHotPhoto() error");
	       }finally {
	           if(rs != null)
	               try {
	                  rs.close();
	               }catch(SQLException ex) {
	                  ex.printStackTrace();
	               }
	          if(pstmt != null)
	             try {
	                pstmt.close();
	             }catch(SQLException ex) {
	                ex.printStackTrace();
	             }
	          if(con != null)
	             try {
	                con.close();
	             }catch(SQLException ex) {
	                ex.printStackTrace();
	             }
	       }//finally
		   
		   return list;
	   }
	   
	   public List<ImageTotal> getTag1HotPhoto(){
		   List<ImageTotal> list = new ArrayList<ImageTotal>();
		   Connection con=null;
	       PreparedStatement pstmt=null;
	       ResultSet rs =null;
	       
	       try{
	    	   con=ds.getConnection();
	    	   String total_hotphoto_sql ="select IMAGE.I_NUM, IMAGE.FILENAME from image join image_like on image.I_NUM=image_LIKE.I_NUM where I_TAG1 > 0 order by I_LIKECOUNT";
	    	   							
	    	   pstmt =con.prepareStatement(total_hotphoto_sql);
	    	   
	    	   rs = pstmt.executeQuery();
	    	   int i =0;
	    	   while(rs.next()){
	    		   i++;
	    		   ImageTotal total = new ImageTotal();
	    		   System.out.println(rs.getInt("I_NUM"));
	    		   total.setI_NUM(rs.getInt("I_NUM"));
	    		   total.setFILENAME(rs.getString("FILENAME"));
	    		   list.add(total);
	    		   System.out.println("hotphotototal 입력완료 "+i );
	    	   }
	    	   return list;
	       }catch(Exception ex){
	    	   System.out.println("getTag1HotPhoto() error");
	       }finally {
	           if(rs != null)
	               try {
	                  rs.close();
	               }catch(SQLException ex) {
	                  ex.printStackTrace();
	               }
	          if(pstmt != null)
	             try {
	                pstmt.close();
	             }catch(SQLException ex) {
	                ex.printStackTrace();
	             }
	          if(con != null)
	             try {
	                con.close();
	             }catch(SQLException ex) {
	                ex.printStackTrace();
	             }
	       }//finally
		   
		   return list;
	   }
	   
	   public List<ImageTotal> getTag2HotPhoto(){
		   List<ImageTotal> list = new ArrayList<ImageTotal>();
		   Connection con=null;
	       PreparedStatement pstmt=null;
	       ResultSet rs =null;
	       
	       try{
	    	   con=ds.getConnection();
	    	   String total_hotphoto_sql ="select IMAGE.I_NUM, IMAGE.FILENAME from image join image_like on image.I_NUM=image_LIKE.I_NUM where I_TAG2 > 0 order by I_LIKECOUNT";
	    	   							
	    	   pstmt =con.prepareStatement(total_hotphoto_sql);
	    	   
	    	   rs = pstmt.executeQuery();
	    	   int i =0;
	    	   while(rs.next()){
	    		   i++;
	    		   ImageTotal total = new ImageTotal();
	    		   System.out.println(rs.getInt("I_NUM"));
	    		   total.setI_NUM(rs.getInt("I_NUM"));
	    		   total.setFILENAME(rs.getString("FILENAME"));
	    		   list.add(total);
	    		   System.out.println("hotphotototal 입력완료 "+i );
	    	   }
	    	   return list;
	       }catch(Exception ex){
	    	   System.out.println("getTag2HotPhoto() error");
	       }finally {
	           if(rs != null)
	               try {
	                  rs.close();
	               }catch(SQLException ex) {
	                  ex.printStackTrace();
	               }
	          if(pstmt != null)
	             try {
	                pstmt.close();
	             }catch(SQLException ex) {
	                ex.printStackTrace();
	             }
	          if(con != null)
	             try {
	                con.close();
	             }catch(SQLException ex) {
	                ex.printStackTrace();
	             }
	       }//finally
		   
		   return list;
	   }public List<ImageTotal> getTag3HotPhoto(){
		   List<ImageTotal> list = new ArrayList<ImageTotal>();
		   Connection con=null;
	       PreparedStatement pstmt=null;
	       ResultSet rs =null;
	       
	       try{
	    	   con=ds.getConnection();
	    	   String total_hotphoto_sql ="select IMAGE.I_NUM, IMAGE.FILENAME from image join image_like on image.I_NUM=image_LIKE.I_NUM where I_TAG3 > 0 order by I_LIKECOUNT";
	    	   							
	    	   pstmt =con.prepareStatement(total_hotphoto_sql);
	    	   
	    	   rs = pstmt.executeQuery();
	    	   int i =0;
	    	   while(rs.next()){
	    		   i++;
	    		   ImageTotal total = new ImageTotal();
	    		   System.out.println(rs.getInt("I_NUM"));
	    		   total.setI_NUM(rs.getInt("I_NUM"));
	    		   total.setFILENAME(rs.getString("FILENAME"));
	    		   list.add(total);
	    		   System.out.println("hotphotototal 입력완료 "+i );
	    	   }
	    	   return list;
	       }catch(Exception ex){
	    	   System.out.println("getTag3HotPhoto() error");
	       }finally {
	           if(rs != null)
	               try {
	                  rs.close();
	               }catch(SQLException ex) {
	                  ex.printStackTrace();
	               }
	          if(pstmt != null)
	             try {
	                pstmt.close();
	             }catch(SQLException ex) {
	                ex.printStackTrace();
	             }
	          if(con != null)
	             try {
	                con.close();
	             }catch(SQLException ex) {
	                ex.printStackTrace();
	             }
	       }//finally
		   
		   return list;
	   }public List<ImageTotal> getTag4HotPhoto(){
		   List<ImageTotal> list = new ArrayList<ImageTotal>();
		   Connection con=null;
	       PreparedStatement pstmt=null;
	       ResultSet rs =null;
	       
	       try{
	    	   con=ds.getConnection();
	    	   String total_hotphoto_sql ="select IMAGE.I_NUM, IMAGE.FILENAME from image join image_like on image.I_NUM=image_LIKE.I_NUM where I_TAG4 > 0 order by I_LIKECOUNT";
	    	   							
	    	   pstmt =con.prepareStatement(total_hotphoto_sql);
	    	   
	    	   rs = pstmt.executeQuery();
	    	   int i =0;
	    	   while(rs.next()){
	    		   i++;
	    		   ImageTotal total = new ImageTotal();
	    		   System.out.println(rs.getInt("I_NUM"));
	    		   total.setI_NUM(rs.getInt("I_NUM"));
	    		   total.setFILENAME(rs.getString("FILENAME"));
	    		   list.add(total);
	    		   System.out.println("hotphotototal 입력완료 "+i );
	    	   }
	    	   return list;
	       }catch(Exception ex){
	    	   System.out.println("getTag4HotPhoto() error");
	       }finally {
	           if(rs != null)
	               try {
	                  rs.close();
	               }catch(SQLException ex) {
	                  ex.printStackTrace();
	               }
	          if(pstmt != null)
	             try {
	                pstmt.close();
	             }catch(SQLException ex) {
	                ex.printStackTrace();
	             }
	          if(con != null)
	             try {
	                con.close();
	             }catch(SQLException ex) {
	                ex.printStackTrace();
	             }
	       }//finally
		   
		   return list;
	   }public List<ImageTotal> getTag5HotPhoto(){
		   List<ImageTotal> list = new ArrayList<ImageTotal>();
		   Connection con=null;
	       PreparedStatement pstmt=null;
	       ResultSet rs =null;
	       
	       try{
	    	   con=ds.getConnection();
	    	   String total_hotphoto_sql ="select IMAGE.I_NUM, IMAGE.FILENAME from image join image_like on image.I_NUM=image_LIKE.I_NUM where I_TAG5 > 0 order by I_LIKECOUNT";
	    	   							
	    	   pstmt =con.prepareStatement(total_hotphoto_sql);
	    	   
	    	   rs = pstmt.executeQuery();
	    	   int i =0;
	    	   while(rs.next()){
	    		   i++;
	    		   ImageTotal total = new ImageTotal();
	    		   System.out.println(rs.getInt("I_NUM"));
	    		   total.setI_NUM(rs.getInt("I_NUM"));
	    		   total.setFILENAME(rs.getString("FILENAME"));
	    		   list.add(total);
	    		   System.out.println("hotphotototal 입력완료 "+i );
	    	   }
	    	   return list;
	       }catch(Exception ex){
	    	   System.out.println("getTag5HotPhoto() error");
	       }finally {
	           if(rs != null)
	               try {
	                  rs.close();
	               }catch(SQLException ex) {
	                  ex.printStackTrace();
	               }
	          if(pstmt != null)
	             try {
	                pstmt.close();
	             }catch(SQLException ex) {
	                ex.printStackTrace();
	             }
	          if(con != null)
	             try {
	                con.close();
	             }catch(SQLException ex) {
	                ex.printStackTrace();
	             }
	       }//finally
		   
		   return list;
	   }
	   
	   public List<ImageTotal> get10HotPhoto(){
		   List<ImageTotal> list = new ArrayList<ImageTotal>();
		   Connection con=null;
	       PreparedStatement pstmt=null;
	       ResultSet rs =null;
	       
	       try{
	    	   con=ds.getConnection();
	    	   String total_hotphoto_sql ="select IMAGE.I_NUM, IMAGE.FILENAME from image join image_like on image.I_NUM=image_LIKE.I_NUM where YEAR_10 > 0 order by I_LIKECOUNT";
	    	   							
	    	   pstmt =con.prepareStatement(total_hotphoto_sql);
	    	   
	    	   rs = pstmt.executeQuery();
	    	   int i =0;
	    	   while(rs.next()){
	    		   i++;
	    		   ImageTotal total = new ImageTotal();
	    		   System.out.println(rs.getInt("I_NUM"));
	    		   total.setI_NUM(rs.getInt("I_NUM"));
	    		   total.setFILENAME(rs.getString("FILENAME"));
	    		   list.add(total);
	    		   System.out.println("hotphotototal 입력완료 "+i );
	    	   }
	    	   return list;
	       }catch(Exception ex){
	    	   System.out.println("get10HotPhoto() error");
	       }finally {
	           if(rs != null)
	               try {
	                  rs.close();
	               }catch(SQLException ex) {
	                  ex.printStackTrace();
	               }
	          if(pstmt != null)
	             try {
	                pstmt.close();
	             }catch(SQLException ex) {
	                ex.printStackTrace();
	             }
	          if(con != null)
	             try {
	                con.close();
	             }catch(SQLException ex) {
	                ex.printStackTrace();
	             }
	       }//finally
		   
		   return list;
	   }
	   
	   public List<ImageTotal> get20HotPhoto(){
		   List<ImageTotal> list = new ArrayList<ImageTotal>();
		   Connection con=null;
	       PreparedStatement pstmt=null;
	       ResultSet rs =null;
	       
	       try{
	    	   con=ds.getConnection();
	    	   String total_hotphoto_sql ="select IMAGE.I_NUM, IMAGE.FILENAME from image join image_like on image.I_NUM=image_LIKE.I_NUM where YEAR_20 > 0 order by I_LIKECOUNT";
	    	   							
	    	   pstmt =con.prepareStatement(total_hotphoto_sql);
	    	   
	    	   rs = pstmt.executeQuery();
	    	   int i =0;
	    	   while(rs.next()){
	    		   i++;
	    		   ImageTotal total = new ImageTotal();
	    		   System.out.println(rs.getInt("I_NUM"));
	    		   total.setI_NUM(rs.getInt("I_NUM"));
	    		   total.setFILENAME(rs.getString("FILENAME"));
	    		   list.add(total);
	    		   System.out.println("hotphotototal 입력완료 "+i );
	    	   }
	    	   return list;
	       }catch(Exception ex){
	    	   System.out.println("get20HotPhoto() error");
	       }finally {
	           if(rs != null)
	               try {
	                  rs.close();
	               }catch(SQLException ex) {
	                  ex.printStackTrace();
	               }
	          if(pstmt != null)
	             try {
	                pstmt.close();
	             }catch(SQLException ex) {
	                ex.printStackTrace();
	             }
	          if(con != null)
	             try {
	                con.close();
	             }catch(SQLException ex) {
	                ex.printStackTrace();
	             }
	       }//finally
		   
		   return list;
	   }
	   public List<ImageTotal> get30HotPhoto(){
		   List<ImageTotal> list = new ArrayList<ImageTotal>();
		   Connection con=null;
	       PreparedStatement pstmt=null;
	       ResultSet rs =null;
	       
	       try{
	    	   con=ds.getConnection();
	    	   String total_hotphoto_sql ="select IMAGE.I_NUM, IMAGE.FILENAME from image join image_like on image.I_NUM=image_LIKE.I_NUM where YEAR_30 > 0 order by I_LIKECOUNT";
	    	   							
	    	   pstmt =con.prepareStatement(total_hotphoto_sql);
	    	   
	    	   rs = pstmt.executeQuery();
	    	   int i =0;
	    	   while(rs.next()){
	    		   i++;
	    		   ImageTotal total = new ImageTotal();
	    		   System.out.println(rs.getInt("I_NUM"));
	    		   total.setI_NUM(rs.getInt("I_NUM"));
	    		   total.setFILENAME(rs.getString("FILENAME"));
	    		   list.add(total);
	    		   System.out.println("hotphotototal 입력완료 "+i );
	    	   }
	    	   return list;
	       }catch(Exception ex){
	    	   System.out.println("get30HotPhoto() error");
	       }finally {
	           if(rs != null)
	               try {
	                  rs.close();
	               }catch(SQLException ex) {
	                  ex.printStackTrace();
	               }
	          if(pstmt != null)
	             try {
	                pstmt.close();
	             }catch(SQLException ex) {
	                ex.printStackTrace();
	             }
	          if(con != null)
	             try {
	                con.close();
	             }catch(SQLException ex) {
	                ex.printStackTrace();
	             }
	       }//finally
		   
		   return list;
	   } 
	   public List<ImageTotal> get40HotPhoto(){
		   List<ImageTotal> list = new ArrayList<ImageTotal>();
		   Connection con=null;
	       PreparedStatement pstmt=null;
	       ResultSet rs =null;
	       
	       try{
	    	   con=ds.getConnection();
	    	   String total_hotphoto_sql ="select IMAGE.I_NUM, IMAGE.FILENAME from image join image_like on image.I_NUM=image_LIKE.I_NUM where YEAR_40 > 0 order by I_LIKECOUNT";
	    	   							
	    	   pstmt =con.prepareStatement(total_hotphoto_sql);
	    	   
	    	   rs = pstmt.executeQuery();
	    	   int i =0;
	    	   while(rs.next()){
	    		   i++;
	    		   ImageTotal total = new ImageTotal();
	    		   System.out.println(rs.getInt("I_NUM"));
	    		   total.setI_NUM(rs.getInt("I_NUM"));
	    		   total.setFILENAME(rs.getString("FILENAME"));
	    		   list.add(total);
	    		   System.out.println("hotphotototal 입력완료 "+i );
	    	   }
	    	   return list;
	       }catch(Exception ex){
	    	   System.out.println("get40HotPhoto() error");
	       }finally {
	           if(rs != null)
	               try {
	                  rs.close();
	               }catch(SQLException ex) {
	                  ex.printStackTrace();
	               }
	          if(pstmt != null)
	             try {
	                pstmt.close();
	             }catch(SQLException ex) {
	                ex.printStackTrace();
	             }
	          if(con != null)
	             try {
	                con.close();
	             }catch(SQLException ex) {
	                ex.printStackTrace();
	             }
	       }//finally
		   
		   return list;
	   }
	   public List<ImageTotal> get50HotPhoto(){
		   List<ImageTotal> list = new ArrayList<ImageTotal>();
		   Connection con=null;
	       PreparedStatement pstmt=null;
	       ResultSet rs =null;
	       
	       try{
	    	   con=ds.getConnection();
	    	   String total_hotphoto_sql ="select IMAGE.I_NUM, IMAGE.FILENAME from image join image_like on image.I_NUM=image_LIKE.I_NUM where YEAR_50 > 0 order by I_LIKECOUNT";
	    	   							
	    	   pstmt =con.prepareStatement(total_hotphoto_sql);
	    	   
	    	   rs = pstmt.executeQuery();
	    	   int i =0;
	    	   while(rs.next()){
	    		   i++;
	    		   ImageTotal total = new ImageTotal();
	    		   System.out.println(rs.getInt("I_NUM"));
	    		   total.setI_NUM(rs.getInt("I_NUM"));
	    		   total.setFILENAME(rs.getString("FILENAME"));
	    		   list.add(total);
	    		   System.out.println("hotphotototal 입력완료 "+i );
	    	   }
	    	   return list;
	       }catch(Exception ex){
	    	   System.out.println("get50HotPhoto() error");
	       }finally {
	           if(rs != null)
	               try {
	                  rs.close();
	               }catch(SQLException ex) {
	                  ex.printStackTrace();
	               }
	          if(pstmt != null)
	             try {
	                pstmt.close();
	             }catch(SQLException ex) {
	                ex.printStackTrace();
	             }
	          if(con != null)
	             try {
	                con.close();
	             }catch(SQLException ex) {
	                ex.printStackTrace();
	             }
	       }//finally
		   
		   return list;
	   }

	public int getImageLikeCount(int I_NUM) {
		Connection con= null;
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		int count = 0;
		
		try {
			con= ds.getConnection();
			String sql = "select I_LIKECOUNT from IMAGE where I_NUM= ?";
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, I_NUM);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				count= rs.getInt(1);
			}else {
				return count;
			}
			
		} catch(Exception ex) {
			System.out.println("getImageLikeCount() 에러: "+ex);
		}finally {
			if(rs!= null)
	               try {
	                  rs.close();
	               }catch(SQLException ex) {
	                  ex.printStackTrace();
	               }
	            if(pstmt != null)
	               try {
	                  pstmt.close();
	               }catch(SQLException ex) {
	                  ex.printStackTrace();
	               }
	            if(con != null)
	               try {
	                  con.close();
	               }catch(SQLException ex) {
	                  ex.printStackTrace();
	               }
		}
		return count;
	}


	public String getImagecontent(int I_NUM) {
		Connection con= null;
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		String content="";
		
		try {
			con= ds.getConnection();
			String sql = "select I_CONTENT from IMAGE where I_NUM= ?";
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, I_NUM);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				content= rs.getString(1);
			}else {
				return content;
			}
			
		} catch(Exception ex) {
			System.out.println("getImagecontent() 에러: "+ex);
		}finally {
			if(rs!= null)
	               try {
	                  rs.close();
	               }catch(SQLException ex) {
	                  ex.printStackTrace();
	               }
	            if(pstmt != null)
	               try {
	                  pstmt.close();
	               }catch(SQLException ex) {
	                  ex.printStackTrace();
	               }
	            if(con != null)
	               try {
	                  con.close();
	               }catch(SQLException ex) {
	                  ex.printStackTrace();
	               }
		}
		return content;
	}
	
	public Image getLocation(int I_NUM) {//지도 api 좌표 가져오기 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Image location = new Image();

		
		try {

			
			con = ds.getConnection();
			String location_sql ="select I_LATIUDE, I_LONGTITUDE from image where I_NUM = ?";
			pstmt = con.prepareStatement(location_sql);
			pstmt.setInt(1, I_NUM);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				location.setI_LATITUDE(rs.getDouble(1));
				location.setI_LONGTITUDE(rs.getDouble(2));
				
								
			}

		}catch (Exception ex) {
			System.out.println("getLocation() error : " +ex );
		}finally {
			if(rs!= null)
	               try {
	                  rs.close();
	               }catch(SQLException ex) {
	                  ex.printStackTrace();
	               }
	            if(pstmt != null)
	               try {
	                  pstmt.close();
	               }catch(SQLException ex) {
	                  ex.printStackTrace();
	               }
	            if(con != null)
	               try {
	                  con.close();
	               }catch(SQLException ex) {
	                  ex.printStackTrace();
	               }
			
		}
		return location;
		
	}

}
