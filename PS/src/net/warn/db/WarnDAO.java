package net.warn.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.member.db.Member;

public class WarnDAO {
	//데이터베이스 작업에 필요한 인터페이스들의 레퍼런스 변수를 선언합니다.
		DataSource ds;
		
		public WarnDAO() {
		      try {
		         Context init = new InitialContext();
		         ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		      }catch(Exception ex) {
		         System.out.println("DB 연결 실패 : " + ex);
		         return;
		      }
		   }
		
		public int getBoardListCount() {
			Connection con= null;
			PreparedStatement pstmt= null;
			ResultSet rs=null;
			
			
			int x = 0;
			try {
				con= ds.getConnection();
				
				pstmt = con.prepareStatement("select count(*) from board inner join warn on board.B_ID =warn.M_ID where W_count > 0 AND board.b_NUM =warn.b_NUM");
				rs= pstmt.executeQuery();
				
				if(rs.next()) {
					x= rs.getInt(1);
				}
			} catch(Exception ex) {
				System.out.println("warn getListCount() ����: "+ex);
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
		
		public int getListCount() {
			Connection con= null;
			PreparedStatement pstmt= null;
			ResultSet rs=null;
			
			
			int x = 0;
			try {
				con= ds.getConnection();
				
				pstmt = con.prepareStatement("select count(*) from board inner join warn on board.B_ID =warn.M_ID where W_count > 0 AND board.b_NUM =warn.b_NUM");
				rs= pstmt.executeQuery();
				
				if(rs.next()) {
					x= rs.getInt(1);
				}
			} catch(Exception ex) {
				System.out.println("warn getListCount() 에러: "+ex);
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
		
		public List<WarnBoard> getWarnBoardList(int page, int limit){
			Connection con= null;
			PreparedStatement pstmt= null;
			ResultSet rs=null;
			
			//page: 페이지
			//limit: 페이지당 목록의 수
			//BOARD_RE_REF desc, BOARD_RE_SEQ_asc에 의해 정렬할 것을 조건절에 맞는 rnum의 범위 만큼 가져오는 쿼리문 입니다.
			//보드 전체 불러오는 sql  문장임 // 수정필요 
			String warnboard_list_sql = 
			           "select  rownum, board.B_NUM, B_TITLE, board.B_ID,W_COUNT "
			           + " from board inner join warn on board.B_ID = warn.M_ID "
			           + " where W_count > 0 AND (rownum>=? AND rownum<=?) AND board.b_NUM =warn.b_NUM";
			
			List<WarnBoard> list= new ArrayList<WarnBoard>();
						//한 페이지당 10개씩 목록인 경우 1페이지, 2페이지, 3페이지, 4페이지...
			int startrow= (page-1) * limit +1;// 읽기 시작할 row 번호(1, 11, 21, 31...)
			int endrow= startrow + limit -1;
			
			try {
				con= ds.getConnection();
				pstmt= con.prepareStatement(warnboard_list_sql);
				pstmt.setInt(1, startrow);
				pstmt.setInt(2, endrow);
				rs= pstmt.executeQuery();
				
				
				
				while(rs.next()) {
					WarnBoard warnboard= new WarnBoard();
					//1>rownum 2>B_NUM 3>B_TITLE 4>M_ID 5>W_COUNT
					warnboard.setROWNUM(rs.getInt(1)); //게시번호
					warnboard.setWB_NUM(rs.getInt(2)); //게시판 번호
					warnboard.setWB_TITLE(rs.getString(3));// 제목
					warnboard.setWM_ID(rs.getString(4)); //작성자  ID
					warnboard.setWB_COUNT(rs.getInt(5)); //신고횟수
					list.add(warnboard); //값을 담은 객체를 리스트에 저장합니다.
				
					};
					
			}catch(Exception ex) {
				System.out.println("getwarnBoardList() 에러: "+ex);
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

		public int getWarnCount(String id) {
			Connection con= null;
			PreparedStatement pstmt= null;
			ResultSet rs=null;
			int count = 0;
			
			try {
				con= ds.getConnection();
				String sql = "select count(*) from warn where M_ID= ?";
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
				System.out.println("getWarnCount() 에러: "+ex);
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
		
		
		public int imginsert(WarnImage wi) {
			 Connection con=null;
		      PreparedStatement pstmt=null,pstmt2=null,pstmt3=null;
		      ResultSet rs = null;
		      int result=0;
		      try {
		         con = ds.getConnection();
		         System.out.println("getConnection:imginsert()");
		         
		         String sql2 = "select* from warn where I_NUM=?";
		         String sql_update = "update warn set W_COUNT=?, W_TAG1=?, W_TAG2=? where I_NUM=? ";
		         
		         String sql_insert = "insert into warn (M_ID,I_NUM,W_TAG1,W_TAG2,W_COUNT) "
			         		+ " values (?,?,?,?,1)";
		         
		         pstmt2 = con.prepareStatement(sql2);
		         pstmt2.setInt(1, wi.getWI_NUM());
		         rs=pstmt2.executeQuery();
		         
		         if(rs.next()) {
		        	 pstmt = con.prepareStatement(sql_update);
		        	 pstmt.setInt(1, (rs.getInt("W_COUNT")+1));
		        	 if(wi.getWI_TAG1()=="0") {
		        		 pstmt.setInt(2, rs.getInt("W_TAG1"));
			        	 pstmt.setInt(3, (rs.getInt("W_TAG2")+1));
		        	 }else {
		        		 pstmt.setInt(2, (rs.getInt("W_TAG1")+1));
			        	 pstmt.setInt(3, rs.getInt("W_TAG2"));
		        	 }
			         pstmt.setInt(4,wi.getWI_NUM());
			         result = pstmt.executeUpdate();
		         }else {
		        	 pstmt3 = con.prepareStatement(sql_insert);
		        	 pstmt3.setString(1, wi.getWM_ID());
			         pstmt3.setInt(2, wi.getWI_NUM());
			         pstmt3.setString(3, wi.getWI_TAG1());
			         pstmt3.setString(4,wi.getWI_TAG2());
			         result = pstmt3.executeUpdate(); 
		         }
		         }catch(java.sql.SQLIntegrityConstraintViolationException e) {
		        	 	result = -1;
		        	 	System.out.println("imginsert() 에러입니다.");
			            e.printStackTrace();
			     }catch(Exception e) {
		            e.printStackTrace();
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
		               return result;
		}
		public int cominsert(WarnComment wc) {
			Connection con=null;
		      PreparedStatement pstmt=null,pstmt2=null,pstmt3=null;
		      ResultSet rs = null;
		      int result=0;
		      try {
		         con = ds.getConnection();
		         System.out.println("getConnection:cominsert()");
		         String sql2 = "select* from warn where C_NUM=?";
		         String sql_update = "update warn set W_COUNT=?, W_TAG1=?, W_TAG2=? where C_NUM=? ";
		         String sql = "insert into warn (M_ID,C_NUM,W_TAG1,W_TAG2,W_COUNT) "
		         		+ " values (?,?,?,?,1)";
		         pstmt2 = con.prepareStatement(sql2);
		         pstmt2.setInt(1, wc.getWC_NUM());
		         rs=pstmt2.executeQuery();
		         
		         if(rs.next()) {
		        	 pstmt = con.prepareStatement(sql_update);
		        	 pstmt.setInt(1, (rs.getInt("W_COUNT")+1));
		        	 if(wc.getWC_TAG1()=="0") {
		        		 pstmt.setInt(2, rs.getInt("W_TAG1"));
			        	 pstmt.setInt(3, (rs.getInt("W_TAG2")+1));
		        	 }else {
		        		 pstmt.setInt(2, (rs.getInt("W_TAG1")+1));
			        	 pstmt.setInt(3, rs.getInt("W_TAG2"));
		        	 }
			         pstmt.setInt(4,wc.getWC_NUM());
			         result = pstmt.executeUpdate();
		         }else {
		        	 pstmt3 = con.prepareStatement(sql);
		        	 pstmt3.setString(1, wc.getWM_ID());
			         pstmt3.setInt(2, wc.getWC_NUM());
			         pstmt3.setString(3, wc.getWC_TAG1());
			         pstmt3.setString(4,wc.getWC_TAG2());
			         result = pstmt3.executeUpdate(); 
		         }
		         }catch(java.sql.SQLIntegrityConstraintViolationException e) {
		        	 	result = -1;
		        	 	System.out.println("cominsert() 에러입니다.");
			            e.printStackTrace();
			     }catch(Exception e) {
		            e.printStackTrace();
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
		               return result;
		}
		
		public int boinsert(WarnBoard wi) {
			Connection con=null;
		      PreparedStatement pstmt=null,pstmt2=null,pstmt3=null;
		      ResultSet rs = null;
		      int result=0;
		      try { con = ds.getConnection();
		         System.out.println("getConnection:boinsert()");
		         
		         String sql2 = "select* from warn where B_NUM=?";
		         String sql_update = "update warn set W_COUNT=?, W_TAG1=?, W_TAG2=? where B_NUM=? ";
		         String sql_insert = "insert into warn (M_ID,B_NUM,W_TAG1,W_TAG2,W_COUNT) "
			         		+ " values (?,?,?,?,1)";
		         
		         pstmt2 = con.prepareStatement(sql2);
		         pstmt2.setInt(1, wi.getWB_NUM());
		         rs=pstmt2.executeQuery();
		         
		         if(rs.next()) {
		        	 pstmt = con.prepareStatement(sql_update);
		        	 pstmt.setInt(1, (rs.getInt("W_COUNT")+1));
		        	 if(wi.getWB_TAG1()=="0") {
		        		 pstmt.setInt(2, rs.getInt("W_TAG1"));
			        	 pstmt.setInt(3, (rs.getInt("W_TAG2")+1));
		        	 }else {
		        		 pstmt.setInt(2, (rs.getInt("W_TAG1")+1));
			        	 pstmt.setInt(3, rs.getInt("W_TAG2"));
		        	 }
			         pstmt.setInt(4,wi.getWB_NUM());
			         result = pstmt.executeUpdate();
		         }else {
		        	 pstmt3 = con.prepareStatement(sql_insert);
		        	 pstmt3.setString(1, wi.getWM_ID());
			         pstmt3.setInt(2, wi.getWB_NUM());
			         pstmt3.setString(3, wi.getWB_TAG1());
			         pstmt3.setString(4,wi.getWB_TAG2());
			         result = pstmt3.executeUpdate(); 
		         }
		         }catch(java.sql.SQLIntegrityConstraintViolationException e) {
		        	 	result = -1;
		        	 	System.out.println("boinsert() 에러입니다.");
			            e.printStackTrace();
			     }catch(Exception e) {
		            e.printStackTrace();
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
		               return result;
		}
		
		public WarnImage img_info(int inum) {
			WarnImage m = new WarnImage();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs=null;

			try {
				con = ds.getConnection();
				String sql = "select * from IMAGE where I_NUM = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, inum);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					m.setWM_ID(rs.getString("M_ID"));
					m.setWI_NUM(rs.getInt("I_NUM"));
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("img_info() 에러 : "+e);
			}finally {
				if (rs!=null)
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
						con.close();
					}catch (SQLException ex) {
						ex.printStackTrace();
					}
			}
			return m;
		}
		
		public WarnComment com_info(int inum) {
			WarnComment m = new WarnComment();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs=null;

			try {
				con = ds.getConnection();
				String sql = "select * from COMMENTS where C_NUM = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, inum);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					m.setWM_ID(rs.getString("M_ID"));
					m.setWC_NUM(rs.getInt("C_NUM"));
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("com_info() 에러 : "+e);
			}finally {
				if (rs!=null)
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
						con.close();
					}catch (SQLException ex) {
						ex.printStackTrace();
					}
			}
			return m;
		}
		
		public WarnBoard board_info(int bnum) {
			WarnBoard m = new WarnBoard();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs=null;

			try {
				con = ds.getConnection();
				String sql = "select * from BOARD where B_NUM = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, bnum);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					m.setWM_ID(rs.getString("B_ID"));
					m.setWB_NUM(rs.getInt("B_NUM"));
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("board_info() 에러 : "+e);
			}finally {
				if (rs!=null)
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
						con.close();
					}catch (SQLException ex) {
						ex.printStackTrace();
					}
			}
			return m;
		}
		
		public Member member_info(String id) {
			Member m = new Member();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs=null;

			try {
				con = ds.getConnection();
				
				 String sql = "select * from member where m_id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					m.setID(rs.getString("M_ID"));
					m.setNAME(rs.getString("M_NAME"));
					m.setAGE(rs.getInt("M_AGES"));
					m.setGENDER(rs.getString("M_GENDER"));
					m.setPASSWORD(rs.getString("M_PASS"));
					m.setEMAIL(rs.getString("M_MAIL"));
					m.setTAG1(rs.getString("M_TAG1"));
					m.setTAG2(rs.getString("M_TAG2"));
					m.setTAG3(rs.getString("M_TAG3"));
					m.setTAG4(rs.getString("M_TAG4"));
					m.setTAG5(rs.getString("M_TAG5"));
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("member_info() 에러 : "+e);
			}finally {
				if (rs!=null)
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
						con.close();
					}catch (SQLException ex) {
						ex.printStackTrace();
					}
			}
			return m;
		}
		
		  public List<WarnBoard> getWarnBoard(int page, int limit){
		         Connection con= null;
		         PreparedStatement pstmt= null;
		         ResultSet rs=null;
		         
		         String warnboard_list_sql = 
		                    "select  rownum, board.B_NUM, B_TITLE, board.B_ID,W_COUNT, board.B_DATE, board.B_CONTENT, "
		                    + " warn.W_TAG1, warn.W_TAG2 "
		                    + " from board inner join warn on board.B_ID = warn.M_ID "
		                    + " where W_count > 0 AND (rownum>=? AND rownum<=?) AND board.b_NUM =warn.b_NUM";
		         
		         List<WarnBoard> list= new ArrayList<WarnBoard>();
		                  //            10               1      , 2      , 3      , 4      ...
		         int startrow= (page-1) * limit +1;//  б         row   ȣ(1, 11, 21, 31...)
		         int endrow= startrow + limit -1;
		         
		         try {
		            con= ds.getConnection();
		            pstmt= con.prepareStatement(warnboard_list_sql);
		            pstmt.setInt(1, startrow);
		            pstmt.setInt(2, endrow);
		            rs= pstmt.executeQuery();
		            
		            
		            
		            while(rs.next()) {
		               WarnBoard warnboard= new WarnBoard();
		               //1>rownum 2>B_NUM 3>B_TITLE 4>M_ID 5>W_COUNT
		               warnboard.setROWNUM(rs.getInt(1)); // Խù ȣ
		               warnboard.setWB_NUM(rs.getInt(2)); // Խ      ȣ
		               warnboard.setWB_TITLE(rs.getString(3));//     
		               warnboard.setWM_ID(rs.getString(4)); // ۼ     ID
		               warnboard.setWB_COUNT(rs.getInt(5)); // Ű Ƚ  
		               warnboard.setWB_DATE(rs.getString(6));
		               warnboard.setWB_CONTENT(rs.getString(7));
		               warnboard.setWB_TAG1(rs.getString(8));
		               warnboard.setWB_TAG2(rs.getString(9));
		               list.add(warnboard); //            ü       Ʈ        մϴ .
		            
		               };
		               
		         }catch(Exception ex) {
		            System.out.println("getwarnBoard()     : "+ex);
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
		
		  public int getCommentListCount() {
				Connection con= null;
				PreparedStatement pstmt= null;
				ResultSet rs=null;
				
				
				int x = 0;
				try {
					con= ds.getConnection();
					
					pstmt = con.prepareStatement("select count(*) from comments inner join warn on comments.M_ID =warn.M_ID where W_count > 0 AND comments.c_NUM =warn.c_NUM");
					rs= pstmt.executeQuery();
					
					if(rs.next()) {
						x= rs.getInt(1);
					}
				} catch(Exception ex) {
					System.out.println("warn getListCount() ����: "+ex);
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
			public List<WarnComment> getWarnCommentList(int page,int limit){
				Connection con= null;
				PreparedStatement pstmt= null;
				ResultSet rs=null;
				
				//page: ������
				//limit: �������� ����� ��
				//BOARD_RE_REF desc, BOARD_RE_SEQ_asc�� ���� ������ ���� �������� �´� rnum�� ���� ��ŭ �������� ������ �Դϴ�.
				//���� ��ü �ҷ����� sql  ������ // �����ʿ� 
				String warncomment_list_sql = 
				           "select  rownum, comments.C_NUM, comments.B_NUM,"
				           + " comments.I_NUM, comments.M_ID,W_COUNT, "
				           + " comments.C_CONTENT, comments.C_DATE,  "
				           + " warn.W_TAG1, warn.W_TAG2 "
				           + " from comments inner join warn on comments.M_ID = warn.M_ID "
				           + " where W_count > 0 AND (rownum>=? AND rownum<=?) AND comments.c_NUM =warn.c_NUM" ;
				
				List<WarnComment> list= new ArrayList<WarnComment>();
							//�� �������� 10���� ����� ��� 1������, 2������, 3������, 4������...
				int startrow= (page-1) * limit +1;// �б� ������ row ��ȣ(1, 11, 21, 31...)
				int endrow= startrow + limit -1;
				
				try {
					con= ds.getConnection();
					pstmt= con.prepareStatement(warncomment_list_sql);
					pstmt.setInt(1, startrow);
					pstmt.setInt(2, endrow);
					rs= pstmt.executeQuery();
					
					
					
					while(rs.next()) {
						WarnComment warncomment= new WarnComment();
						//1>rownum 2>B_NUM 3>B_TITLE 4>M_ID 5>W_COUNT
						warncomment.setROWNUM(rs.getInt(1)); //�Խù�ȣ
						warncomment.setWC_NUM(rs.getInt(2)); //�Խ��� ��ȣ
						warncomment.setWCB_NUM(rs.getInt(3));
						warncomment.setWCI_NUM(rs.getInt(4));
						warncomment.setWM_ID(rs.getString(5)); //�ۼ���  ID
						warncomment.setWC_COUNT(rs.getInt(6)); //�Ű�Ƚ��
						warncomment.setWC_DATE(rs.getString(8));
						warncomment.setWC_CONTENT(rs.getString(7));
						warncomment.setWC_TAG1(rs.getString(9));
						warncomment.setWC_TAG2(rs.getString(10));
						list.add(warncomment); //���� ���� ��ü�� ����Ʈ�� �����մϴ�.
					
						};
						
				}catch(Exception ex) {
					System.out.println("getwarnCommentList() ����: "+ex);
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
			
			public List<WarnComment> getWarnComment(int page, int limit){
				Connection con= null;
				PreparedStatement pstmt= null;
				ResultSet rs=null;
				
				//page: ������
				//limit: �������� ����� ��
				//BOARD_RE_REF desc, BOARD_RE_SEQ_asc�� ���� ������ ���� �������� �´� rnum�� ���� ��ŭ �������� ������ �Դϴ�.
				//���� ��ü �ҷ����� sql  ������ // �����ʿ� 
				String warncomment_list_sql = 
				           "select  rownum, comments.C_NUM,comments.M_ID,comments.B_NUM,"
				           + " comments.I_NUM, W_COUNT, comments.C_DATE, comments.C_CONTENT, "
				           + " warn.W_TAG1, warn.W_TAG2 "
				           + " from comments inner join warn on comments.M_ID = warn.M_ID "
				           + " where W_count > 0 AND (rownum>=? AND rownum<=?) AND comments.c_NUM =warn.c_NUM";
				
				List<WarnComment> list= new ArrayList<WarnComment>();
							//�� �������� 10���� ����� ��� 1������, 2������, 3������, 4������...
				int startrow= (page-1) * limit +1;// �б� ������ row ��ȣ(1, 11, 21, 31...)
				int endrow= startrow + limit -1;
				
				try {
					con= ds.getConnection();
					pstmt= con.prepareStatement(warncomment_list_sql);
					pstmt.setInt(1, startrow);
					pstmt.setInt(2, endrow);
					rs= pstmt.executeQuery();
					
					
					
					while(rs.next()) {
						WarnComment warncomment= new WarnComment();
						//1>rownum 2>B_NUM 3>B_TITLE 4>M_ID 5>W_COUNT
						warncomment.setROWNUM(rs.getInt(1)); //�Խù�ȣ
						warncomment.setWC_NUM(rs.getInt(2)); //�Խ��� ��ȣ
						warncomment.setWM_ID(rs.getString(3)); //�ۼ���  ID
						warncomment.setWCB_NUM(rs.getInt(4)); //�Խ��� ��ȣ
						warncomment.setWCI_NUM(rs.getInt(5)); //�Խ��� ��ȣ
						warncomment.setWC_COUNT(rs.getInt(6)); //�Ű�Ƚ��
						warncomment.setWC_DATE(rs.getString(7));
						warncomment.setWC_CONTENT(rs.getString(8));
						warncomment.setWC_TAG1(rs.getString(9));
						warncomment.setWC_TAG2(rs.getString(10));
						list.add(warncomment); //���� ���� ��ü�� ����Ʈ�� �����մϴ�.
					
						};
						
				}catch(Exception ex) {
					System.out.println("getwarnComment() ����: "+ex);
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
			
			
			public int getImageListCount() {
				Connection con= null;
				PreparedStatement pstmt= null;
				ResultSet rs=null;
				
				
				int x = 0;
				try {
					con= ds.getConnection();
					
					pstmt = con.prepareStatement("select count(*) from Image inner join warn on image.M_ID =warn.M_ID where W_count > 0 AND image.i_NUM =warn.i_NUM");
					rs= pstmt.executeQuery();
					
					if(rs.next()) {
						x= rs.getInt(1);
					}
				} catch(Exception ex) {
					System.out.println("warn getListCount() ����: "+ex);
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
			public List<WarnImage> getWarnImageList(int page,int limit){
				Connection con= null;
				PreparedStatement pstmt= null;
				ResultSet rs=null;
				
				//page: ������
				//limit: �������� ����� ��
				//BOARD_RE_REF desc, BOARD_RE_SEQ_asc�� ���� ������ ���� �������� �´� rnum�� ���� ��ŭ �������� ������ �Դϴ�.
				//���� ��ü �ҷ����� sql  ������ // �����ʿ� 
				String warnimage_list_sql = 
				           "select  rownum, image.I_NUM, Image.M_ID,W_COUNT, Image.I_DATE, "
				           + " warn.W_TAG1, warn.W_TAG2 "
				           + " from image inner join warn on image.M_ID = warn.M_ID "
				           + " where W_count > 0 AND (rownum>=? AND rownum<=?) AND image.i_NUM =warn.i_NUM";
				
				List<WarnImage> list= new ArrayList<WarnImage>();
							//�� �������� 10���� ����� ��� 1������, 2������, 3������, 4������...
				int startrow= (page-1) * limit +1;// �б� ������ row ��ȣ(1, 11, 21, 31...)
				int endrow= startrow + limit -1;
				
				try {
					con= ds.getConnection();
					pstmt= con.prepareStatement(warnimage_list_sql);
					pstmt.setInt(1, startrow);
					pstmt.setInt(2, endrow);
					rs= pstmt.executeQuery();
					
					
					
					while(rs.next()) {
						WarnImage warnimage= new WarnImage();
						//1>rownum 2>B_NUM 3>B_TITLE 4>M_ID 5>W_COUNT
						warnimage.setROWNUM(rs.getInt(1)); //�Խù�ȣ
						warnimage.setWI_NUM(rs.getInt(2)); //�Խ��� ��ȣ					
						warnimage.setWM_ID(rs.getString(3)); //�ۼ���  ID
						warnimage.setWI_COUNT(rs.getInt(4)); //�Ű�Ƚ��
						warnimage.setWI_DATE(rs.getString(5));					
						warnimage.setWI_TAG1(rs.getString(6));
						warnimage.setWI_TAG2(rs.getString(7));
						list.add(warnimage); //���� ���� ��ü�� ����Ʈ�� �����մϴ�.
					
						};
						
				}catch(Exception ex) {
					System.out.println("getwarnImageList() ����: "+ex);
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
			
			
			public List<WarnImage> getWarnImage(int page, int limit){
			Connection con= null;
			PreparedStatement pstmt= null;
			ResultSet rs=null;
			
			//page: ������
			//limit: �������� ����� ��
			//BOARD_RE_REF desc, BOARD_RE_SEQ_asc�� ���� ������ ���� �������� �´� rnum�� ���� ��ŭ �������� ������ �Դϴ�.
			//���� ��ü �ҷ����� sql  ������ // �����ʿ� 
			String warnimage_list_sql = 
			           "select  rownum, image.I_NUM,Image.M_ID,W_COUNT, Image.I_DATE, image.i_CONTENT, "
			           + " warn.W_TAG1, warn.W_TAG2 "
			           + " from image inner join warn on image.M_ID = warn.M_ID "
			           + " where W_count > 0 AND (rownum>=? AND rownum<=?) AND image.i_NUM =warn.i_NUM";
			
			List<WarnImage> list= new ArrayList<WarnImage>();
						//�� �������� 10���� ����� ��� 1������, 2������, 3������, 4������...
			int startrow= (page-1) * limit +1;// �б� ������ row ��ȣ(1, 11, 21, 31...)
			int endrow= startrow + limit -1;
			
			try {
				con= ds.getConnection();
				pstmt= con.prepareStatement(warnimage_list_sql);
				pstmt.setInt(1, startrow);
				pstmt.setInt(2, endrow);
				rs= pstmt.executeQuery();
				
				
				
				while(rs.next()) {
					WarnImage warnimage= new WarnImage();
					//1>rownum 2>B_NUM 3>B_TITLE 4>M_ID 5>W_COUNT
					warnimage.setROWNUM(rs.getInt(1)); //�Խù�ȣ
					warnimage.setWI_NUM(rs.getInt(2)); //�Խ��� ��ȣ
					warnimage.setWM_ID(rs.getString(3)); //�ۼ���  ID
					warnimage.setWI_COUNT(rs.getInt(4)); //�Ű�Ƚ��
					warnimage.setWI_DATE(rs.getString(5));
					warnimage.setWI_CONTENT(rs.getString(6));
					warnimage.setWI_TAG1(rs.getString(7));
					warnimage.setWI_TAG2(rs.getString(8));
					list.add(warnimage); //���� ���� ��ü�� ����Ʈ�� �����մϴ�.
				
					};
					
			}catch(Exception ex) {
				System.out.println("getwarnImage() ����: "+ex);
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
			
			public List<WarnMember> getWarnMemberList(int page,int limit){
				Connection con= null;
				PreparedStatement pstmt= null;
				ResultSet rs=null;
				
				String warnmember_list_sql = 
				           "select  rownum, warn.M_ID, warn.W_COUNT, "
				           + " warn.W_TAG1, warn.W_TAG2 "
				           + " from board inner join warn on board.B_ID = warn.M_ID "
				           + " where W_count > 0 AND (rownum>=? AND rownum<=?) ";
				
				List<WarnMember> list= new ArrayList<WarnMember>();
				int startrow= (page-1) * limit +1;
				int endrow= startrow + limit -1;
				
				try {
					con= ds.getConnection();
					pstmt= con.prepareStatement(warnmember_list_sql);
					pstmt.setInt(1, startrow);
					pstmt.setInt(2, endrow);
					rs= pstmt.executeQuery();
					
					
					
					while(rs.next()) {
						WarnMember warnboard= new WarnMember();
						warnboard.setROWNUM(rs.getInt(1)); 
						warnboard.setWM_ID(rs.getString(2)); 
						warnboard.setWM_COUNT(rs.getInt(3)); 
						warnboard.setWM_TAG1(rs.getString(4));
						warnboard.setWM_TAG2(rs.getString(5));
						list.add(warnboard); 
					
						};
						
				}catch(Exception ex) {
					System.out.println("getwarnMemberList() ����: "+ex);
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
			
			public List<WarnMember> getWarnMember(int page, int limit){//아직 다 안만듬
				Connection con= null;
				PreparedStatement pstmt= null;
				ResultSet rs=null;
				
				
				String warnmember_list_sql = 
				           "select  rownum, board.B_NUM, board.B_TITLE, B_ID,W_COUNT, board.B_DATE, board.B_CONTENT, "
				           + " warn.W_TAG1, warn.W_TAG2 "
				           + " from board inner join warn on board.B_ID = warn.M_ID "
				           + " where W_count > 0 AND (rownum>=? AND rownum<=?)";
				
				List<WarnMember> list= new ArrayList<WarnMember>();
						
				int startrow= (page-1) * limit +1;
				int endrow= startrow + limit -1;
				
				try {
					con= ds.getConnection();
					pstmt= con.prepareStatement(warnmember_list_sql);
					pstmt.setInt(1, startrow);
					pstmt.setInt(2, endrow);
					rs= pstmt.executeQuery();
					
					
					
					while(rs.next()) {
						WarnMember warnmember= new WarnMember();
						//1>rownum 2>B_NUM 3>B_TITLE 4>M_ID 5>W_COUNT
						warnmember.setROWNUM(rs.getInt(1)); 
						warnmember.setWM_ID(rs.getString(2));
						warnmember.setWM_COUNT(rs.getInt(3)); 
						warnmember.setWM_TAG1(rs.getString(4));
						warnmember.setWM_TAG2(rs.getString(5));
						list.add(warnmember);
					
						};
						
				}catch(Exception ex) {
					System.out.println("getwarnMember() : "+ex);
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
			
			public int getMemberListCount() {
				Connection con= null;
				PreparedStatement pstmt= null;
				ResultSet rs=null;
				
				
				int x = 0;
				try {
					con= ds.getConnection();
					
					pstmt = con.prepareStatement("select count(*) from member inner join warn on member.M_ID =warn.M_ID where W_count > 0");
					rs= pstmt.executeQuery();
					
					if(rs.next()) {
						x= rs.getInt(1);
					}
				} catch(Exception ex) {
					System.out.println("warn getMemberListCount() ����: "+ex);
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

}