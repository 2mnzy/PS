package net.comment.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class CommentDAO {

	DataSource ds;
	   
	   //생성자에서 JNDI 리소스를 참조하여 Connection 객체를 얻어옵니다.
	   public CommentDAO() {
	      try {
	         Context init = new InitialContext();
	         ds =
	      (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
	      }catch(Exception ex) {
	         System.out.println("DB 연결 실패: " + ex);
	         return;
	      }
	   }
	
	
	public int getCommentCount(String id) { //내 댓글 수 불러오기
		Connection con= null;
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		int count = 0;
		
		try {
			con= ds.getConnection();
			String sql = "select count(*) from COMMENTS where M_ID= ?";
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
			System.out.println("getCommentCount() 에러: "+ex);
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
	}//getCommentCount()end
	
	public int getListCount() {
		Connection con= null;
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		
		
		int x = 0;
		try {
			con= ds.getConnection();
			pstmt = con.prepareStatement("select count(*) from COMMENTS");
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				x= rs.getInt(1);
			}
		} catch(Exception ex) {
			System.out.println("comments의 getListCount()  에러: "+ex);
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
		return x;
	}


	public List<Comment> getMycommentlist(String id, int page, int limit) {
		Connection con= null;
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		
		//page: 페이지
		//limit: 페이지당 목록의 수
		//BOARD_RE_REF desc, BOARD_RE_SEQ_asc에 의해 정렬할 것을 조건절에 맞는 rnum의 범위 만큼 가져오는 쿼리문 입니다.
				
		
		String mycomment_list_sql = 
		           "select* from "
		         + "  (select rownum rnum, C_NUM, M_ID, "
		         + "   B_NUM, C_CONTENT, "
		         + "    C_DATE "
		         + "   from COMMENTS "
		         + "   ) "
		         + "where M_ID= ? and rnum>=? and rnum<=?";
		
		List<Comment> list= new ArrayList<Comment>();
		//한 페이지당 10개씩 목록인 경우 1페이지, 2페이지, 3페이지, 4페이지...
		int startrow= (page-1) * limit +1;// 읽기 시작할 row 번호(1, 11, 21, 31...)
		int endrow= startrow + limit -1;
		
		try {
			con= ds.getConnection();
			pstmt= con.prepareStatement(mycomment_list_sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs= pstmt.executeQuery();
			
			//DB에서 가져온 데이터를 VO 객체에 담습니다.
			while(rs.next()) {
				Comment comment= new Comment();
				comment.setC_NUM(rs.getInt("C_NUM"));
				comment.setM_ID(rs.getString("M_ID"));
				comment.setB_NUM(rs.getInt("B_NUM"));
				comment.setC_CONTENT(rs.getString("C_CONTENT"));
				comment.setC_DATE(rs.getString("C_DATE"));
				list.add(comment);//값을 담은 객체를 리스트에 저장합니다.
				}
		}catch(Exception ex) {
			System.out.println("getMyCommentList() 에러: "+ex);
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


	public List<Comment> getcommentlist(int i_NUM) {
		Connection con= null;
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		
		//page: 페이지
		//limit: 페이지당 목록의 수
		//BOARD_RE_REF desc, BOARD_RE_SEQ_asc에 의해 정렬할 것을 조건절에 맞는 rnum의 범위 만큼 가져오는 쿼리문 입니다.
				
		
		String comment_list_sql = 
		           "SELECT C_NUM, M_ID, C_CONTENT, C_DATE FROM COMMENTS WHERE I_NUM= ?";
		
		List<Comment> list= new ArrayList<Comment>();
		
		try {
			con= ds.getConnection();
			pstmt= con.prepareStatement(comment_list_sql);
			pstmt.setInt(1, i_NUM);
			rs= pstmt.executeQuery();
			
			//DB에서 가져온 데이터를 VO 객체에 담습니다.
			while(rs.next()) {
				Comment comment= new Comment();
				comment.setC_NUM(rs.getInt("C_NUM"));
				comment.setM_ID(rs.getString("M_ID"));
				comment.setC_CONTENT(rs.getString("C_CONTENT"));
				comment.setC_DATE(rs.getString("C_DATE"));
				list.add(comment);//값을 담은 객체를 리스트에 저장합니다.
				}
		}catch(Exception ex) {
			System.out.println("getCommentList() 에러: "+ex);
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


	public int getImageCommentCount(int i_NUM) {
		Connection con= null;
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		
		
		int x = 0;
		try {
			con= ds.getConnection();
			pstmt = con.prepareStatement("select count(I_NUM) from COMMENTS where I_NUM = ?");
			pstmt.setInt(1, i_NUM);
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				x= rs.getInt(1);
			}
		} catch(Exception ex) {
			System.out.println("comments의 getImageCommentCount  에러: "+ex);
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
		return x;
	}
	
	public boolean commentInsert(Comment commentdata) {
		   Connection con= null;
		   PreparedStatement pstmt= null, pstmt2= null;
		   ResultSet rs = null;
		   int num = 1;
		   boolean result = false;
		   
		   try {
			   con = ds.getConnection();
			   
			   String max_sql = "select max(c_num) from comments";
			   pstmt = con.prepareStatement(max_sql);
			   rs = pstmt.executeQuery();
			   
			   if (rs.next()) {
				   num = rs.getInt(1)+1;
			   }
			  //이곳에서 B_NUM 값은 어디에 있어요?
			   String sql = "insert into comments"
					   + "(C_NUM,B_NUM, M_ID, C_CONTENT, C_DATE) "
					   + "values(?,?,?,?,sysdate)";
			   
			   pstmt2 = con.prepareStatement(sql);
			   pstmt2.setInt(1, num);
			   pstmt2.setInt(2, commentdata.getB_NUM());
			   pstmt2.setString(3, commentdata.getM_ID());
			   pstmt2.setString(4, commentdata.getC_CONTENT());
			   
			   int r = pstmt2.executeUpdate();
			   
			   System.out.println("commentInsert 데이터 삽입 모두 완료되었습니다.");
			   if(r==1) {
				   result = true;
			   }   
		   } catch(Exception ex) {
			   System.out.println("commentInsert() 에러 : " + ex);
			   ex.printStackTrace();
		   } finally {
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
		   }
		return result;
		}


	public boolean ImagecommentInsert(Comment commentdata) {
		Connection con= null;
		   PreparedStatement pstmt= null, pstmt2= null;
		   ResultSet rs = null;
		   int num = 1;
		   boolean result = false;
		   
		   try {
			   con = ds.getConnection();
			   
			   String max_sql = "select max(c_num) from comments";
			   pstmt = con.prepareStatement(max_sql);
			   rs = pstmt.executeQuery();
			   
			   if (rs.next()) {
				   num = rs.getInt(1)+1;
			   }
			  //이곳에서 B_NUM 값은 어디에 있어요?
			   String sql = "insert into comments"
					   + "(C_NUM,I_NUM, M_ID, C_CONTENT, C_DATE) "
					   + "values(?,?,?,?,sysdate)";
			   
			   pstmt2 = con.prepareStatement(sql);
			   pstmt2.setInt(1, num);
			   pstmt2.setInt(2, commentdata.getI_NUM());
			   pstmt2.setString(3, commentdata.getM_ID());
			   pstmt2.setString(4, commentdata.getC_CONTENT());
			   
			   int r = pstmt2.executeUpdate();
			   
			   System.out.println("ImagecommentInsert 데이터 삽입 모두 완료되었습니다.");
			   if(r==1) {
				   result = true;
			   }   
		   } catch(Exception ex) {
			   System.out.println("ImagecommentInsert() 에러 : " + ex);
			   ex.printStackTrace();
		   } finally {
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
		   }
		return result;
	}
	
	 public List<Comment> getCommentList(int num) {
		   Connection con= null;
		   PreparedStatement pstmt= null;
		   ResultSet rs=null;
		   
		   //page: 페이지
		   //limit: 페이지당 목록의 수
		   //BOARD_RE_REF desc, BOARD_RE_SEQ_asc에 의해 정렬할 것을 조건절에 맞는 rnum의 범위 만큼 가져오는 쿼리문 입니다.
		         
		   
		   String comment_list_sql = 
		              "SELECT M_ID, C_CONTENT, C_DATE FROM COMMENTS WHERE B_NUM= ? ORDER BY C_NUM DESC ";
		   
		   List<Comment> list= new ArrayList<Comment>();
		   
		   try {
		      con= ds.getConnection();
		      pstmt= con.prepareStatement(comment_list_sql);
		      pstmt.setInt(1, num);
		      rs= pstmt.executeQuery();
		      
		      //DB에서 가져온 데이터를 VO 객체에 담습니다.
		      while(rs.next()) {
		         Comment comment= new Comment();
		         comment.setM_ID(rs.getString("M_ID"));
		         comment.setC_CONTENT(rs.getString("C_CONTENT"));
		         comment.setC_DATE(rs.getString("C_DATE"));
		         list.add(comment);//값을 담은 객체를 리스트에 저장합니다.
		         }
		   }catch(Exception ex) {
		      System.out.println("getCommentList() 에러: "+ex);
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
	 
	 public boolean commentdelete(int c_num) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = "DELETE FROM COMMENTS " 
					   + "WHERE C_NUM = ? ";
			boolean result = false;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, c_num);
				pstmt.executeUpdate();
				result = true;
			} catch (Exception ex) {
				System.out.println("commentDelete() 에러: " + ex);
				ex.printStackTrace();
			} finally {
				if (pstmt != null)
					try {
						pstmt.close();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				if (con != null)
					try {
						con.close();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
			}
			return result;
		}
}
