package net.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	//데이터베이스 작업에 필요한 인터페이스들의 레퍼런스 변수를 선언합니다.
	DataSource ds;
	
	public BoardDAO() {
	      try {
	         Context init = new InitialContext();
	         ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
	      }catch(Exception ex) {
	         System.out.println("DB 연결 실패 : " + ex);
	         return;
	      }
	   }
	
	//글의 갯수 구하기
	public int getListCount() {
		Connection con= null;
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		
		
		int x = 0;
		try {
			con= ds.getConnection();
			pstmt = con.prepareStatement("select count(*) from BOARD");
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				x= rs.getInt(1);
			}
		} catch(Exception ex) {
			System.out.println("getListCount()  에러: "+ex);
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


	public List<BoardBean> getBoardList(int page, int limit) {
		Connection con= null;
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		
		//page: 페이지
		//limit: 페이지당 목록의 수
		//BOARD_RE_REF desc, BOARD_RE_SEQ_asc에 의해 정렬할 것을 조건절에 맞는 rnum의 범위 만큼 가져오는 쿼리문 입니다.
				
		
		String board_list_sql = 
		           "select* from "
		         + "  (select rownum rnum, B_NUM, B_ID,"
		         + "   B_TITLE, B_CONTENT, "
		         + "   B_VIEW, B_DATE, B_TAG"
		         + "   from board "
		         + "   ) "
		         + "where rnum>=? and rnum<=? "
		         + "order by B_DATE DESC ";
		         
		
		List<BoardBean> list= new ArrayList<BoardBean>();
		//한 페이지당 10개씩 목록인 경우 1페이지, 2페이지, 3페이지, 4페이지...
		int startrow= (page-1) * limit +1;// 읽기 시작할 row 번호(1, 11, 21, 31...)
		int endrow= startrow + limit -1;
		
		try {
			con= ds.getConnection();
			pstmt= con.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs= pstmt.executeQuery();
			
			//DB에서 가져온 데이터를 VO 객체에 담습니다.
			while(rs.next()) {
				BoardBean board= new BoardBean();
				board.setB_NUM(rs.getInt("B_NUM"));
				board.setB_ID(rs.getString("B_ID"));
				board.setB_TITLE(rs.getString("B_TITLE"));
				board.setB_CONTENT(rs.getString("B_CONTENT"));
				board.setB_VIEW(rs.getInt("B_VIEW"));
				board.setB_DATE(rs.getString("B_DATE"));
				board.setB_TAG(rs.getString("B_TAG"));
				list.add(board);//값을 담은 객체를 리스트에 저장합니다.
				}
		}catch(Exception ex) {
			System.out.println("getBoardList() 에러: "+ex);
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

	public boolean boardInsert(BoardBean board) {
		 Connection con=null;
	      PreparedStatement pstmt=null, pstmt2= null;
	      ResultSet rs =null;
	      int num=1;
	      String sql="";
	      boolean result = false;
	      try {
	         con = ds.getConnection();
	         
	       //board 테이블의 board_num 필드의 최대값을 구해와서 글을 등록할 때 글 번호를 순차적으로 지정하기 위함입니다.
	         
	         String max_sql = "select max(b_num) from board";
	         pstmt = con.prepareStatement(max_sql);
	         rs= pstmt.executeQuery();
	         
	         /*
	          * select max(board_num) from board
	          * 처음 글쓰기 하는 경우 rs.getInt(1)은 0입니다.
	          * if the value is SQL NULL, the value returned is 0
	          * 
	          * */
	         
	         if(rs.next()) {
	        	 
	        	 System.out.println("rs.getInt(1)="+rs.getInt(1));
	        	 num= rs.getInt(1)+1;// 최대값보다 	1만큼 큰값을 지정합니다.
	         }
	         
	         sql= "insert into board"
	        	+" (B_NUM, B_ID, B_PASS, B_TITLE, B_CONTENT, B_TAG, B_DATE) "
	        	+" values(?,?,?,?,?,?,sysdate)";
	         
	         
	         // 새로운 글을 등록하는 부분입니다.
	         System.out.println(sql);
	         pstmt2= con.prepareStatement(sql);
	         pstmt2.setInt(1, num);
	         pstmt2.setString(2, board.getB_ID());
	         pstmt2.setString(3, board.getB_PASS());
	         pstmt2.setString(4, board.getB_TITLE());
	         pstmt2.setString(5, board.getB_CONTENT());
	         pstmt2.setString(6, board.getB_TAG());
	         
	         int r= pstmt2.executeUpdate();
	         
	         System.out.println("데이터 삽입이 모두 완료되었습니다.");
	         if(r==1)
	        	 result= true;
	         
	         }catch(Exception ex) {
	        	 System.out.println("boardInsert() 에러: "+ex);
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

	//조회수 업데이트 - 글번호에 해당하는 조회수를 1 증가합니다.
	public void setReadCountUpdate(int num) {
		Connection con=null;
	    PreparedStatement pstmt=null;
	    
	    String sql = "update board "
	    		   +"set B_VIEW=B_VIEW+1 "
	    		   +"where B_NUM = ?";
	    
	    try {
	    	con= ds.getConnection();
	    	pstmt= con.prepareStatement(sql);
	    	pstmt.setInt(1, num);
	    	pstmt.executeUpdate();	    	
	    }catch(Exception ex) {
       	 System.out.println("setReadCountUpdate()에러: "+ex);
       	 ex.printStackTrace();
	    }finally {
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
	}

	public BoardBean getDetail(int num) {
		BoardBean board= null;
		Connection con=null;
	    PreparedStatement pstmt=null;
	    ResultSet rs =null;
	    
	    try {
	    	con = ds.getConnection();
	    	pstmt= con.prepareStatement("select * FROM BOARD WHERE B_NUM = ?");
	    	pstmt.setInt(1, num);
	    	rs= pstmt.executeQuery();   	
	    	
	    	if(rs.next()) {
	    		board= new BoardBean();
	    		board.setB_NUM(rs.getInt("B_NUM"));
				board.setB_ID(rs.getString("B_ID"));
				board.setB_TITLE(rs.getString("B_TITLE"));
				board.setB_CONTENT(rs.getString("B_CONTENT"));
				board.setB_VIEW(rs.getInt("B_VIEW"));
				board.setB_DATE(rs.getString("B_DATE"));
				board.setB_TAG(rs.getString("B_TAG"));
	    	}
	    }catch(Exception ex) {
	       	 System.out.println("getDetail()에러: "+ex);
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
	    return board;
	}

	

	public boolean isBoardWriter(int num, String pass) {
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      boolean result = false;
	      String board_sql = "select * from board where B_NUM=?";
	      try {
	         con = ds.getConnection();
	         pstmt = con.prepareStatement(board_sql);
	         pstmt.setInt(1, num);
	         rs = pstmt.executeQuery();
	         if(rs.next()) {
	            if(pass.equals(rs.getString("B_PASS"))) {
	               result = true;
	            }
	         }
	      } catch (SQLException ex) {
	         System.out.println("isBoardWriter() 에러 : "+ex);
	      } finally {
	         if (rs != null)
	            try {
	               rs.close();
	            } catch (SQLException ex) {
	               ex.printStackTrace();
	            }
	         if (pstmt != null)
	            try {
	               pstmt.close();
	            } catch (SQLException ex) {
	               ex.printStackTrace();
	            }
	         if (con !=null)
	            try {
	               con.setAutoCommit(true);   //다시 true로 설정.
	               con.close();
	            }catch (SQLException ex) {
	               ex.printStackTrace();
	            }
	      }
	      return result;
	   }


	public int boardReply(BoardBean boarddata) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getBoardCount(String id) { //내 게시글 수 불러오기
	Connection con= null;
	PreparedStatement pstmt= null;
	ResultSet rs=null;
	int count = 0;
	
	try {
		con= ds.getConnection();
		String sql = "select count(*) from board where B_ID= ?";
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
		System.out.println("getBoardCount() 에러: "+ex);
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
}//getboardCount()end

	public List<BoardBean> getMyBoardList(String id, int page, int limit) {
		Connection con= null;
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		
		//page: 페이지
		//limit: 페이지당 목록의 수
		//BOARD_RE_REF desc, BOARD_RE_SEQ_asc에 의해 정렬할 것을 조건절에 맞는 rnum의 범위 만큼 가져오는 쿼리문 입니다.
				
		
		String myboard_list_sql = 
		           "select* from "
		         + "  (select rownum rnum, B_NUM, B_ID,"
		         + "   B_TITLE, B_CONTENT, "
		         + "    B_DATE "
		         + "   from board "
		         + "   ) "
		         + "where B_ID= ? and rnum>=? and rnum<=?";
		
		List<BoardBean> list= new ArrayList<BoardBean>();
		//한 페이지당 10개씩 목록인 경우 1페이지, 2페이지, 3페이지, 4페이지...
		int startrow= (page-1) * limit +1;// 읽기 시작할 row 번호(1, 11, 21, 31...)
		int endrow= startrow + limit -1;
		
		try {
			con= ds.getConnection();
			pstmt= con.prepareStatement(myboard_list_sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs= pstmt.executeQuery();
			
			//DB에서 가져온 데이터를 VO 객체에 담습니다.
			while(rs.next()) {
				BoardBean board= new BoardBean();
				board.setB_NUM(rs.getInt("B_NUM"));
				board.setB_ID(rs.getString("B_ID"));
				board.setB_TITLE(rs.getString("B_TITLE"));
				board.setB_CONTENT(rs.getString("B_CONTENT"));
				board.setB_DATE(rs.getString("B_DATE"));
				list.add(board);//값을 담은 객체를 리스트에 저장합니다.
				}
		}catch(Exception ex) {
			System.out.println("getMyBoardList() 에러: "+ex);
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
	
	 public boolean boardDelete(int num) {
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      String sql = "DELETE FROM BOARD "
	    		  + "WHERE B_NUM = ? ";
	      boolean result=false;
	      try {
	         con = ds.getConnection();
	         pstmt = con.prepareStatement(sql);
	         pstmt.setInt(1, num);
	         pstmt.executeUpdate();
	         result = true;
	      } catch(Exception ex) {
	         System.out.println("boardDelete() 에러: "+ex);
	         ex.printStackTrace();
	      } finally {
	         if (pstmt != null)
	            try {
	               pstmt.close();
	            } catch (SQLException ex) {
	               ex.printStackTrace();
	            }
	         if (con !=null)
	            try {
	               con.close();
	            }catch (SQLException ex) {
	               ex.printStackTrace();
	            }
	      } return result;
	 }
	 
	 public boolean boardModify(BoardBean modifyboard) {
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      String sql="update board set B_TITLE= ?, "
	             + "B_CONTENT=?, B_TAG=?, B_PASS=? "
	             + "where B_NUM=? ";
	      
	      try {
	         con = ds.getConnection();
	         pstmt = con.prepareStatement(sql);   
	         pstmt.setString(1,modifyboard.getB_TITLE());
	         pstmt.setString(2, modifyboard.getB_CONTENT());
	         pstmt.setString(3, modifyboard.getB_TAG());
	         pstmt.setString(4, modifyboard.getB_PASS());
	         pstmt.setInt(5, modifyboard.getB_NUM());
	         int result = pstmt.executeUpdate();
	         if(result==1) {
	            System.out.println("성공 업데이트");
	            return true;
	         }   
	      }catch(Exception e) {
	         System.out.println("boardModify() 에러: "+e);
	      }finally {
	         if (pstmt != null)
	            try {
	               pstmt.close();
	            } catch (SQLException ex) {
	               ex.printStackTrace();
	            }
	         if (con !=null)
	            try {
	               con.setAutoCommit(true);   //다시 true로 설정.
	               con.close();
	            }catch (SQLException ex) {
	               ex.printStackTrace();
	            }
	      }
	      return false;
	   }
}
