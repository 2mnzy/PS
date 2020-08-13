package net.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
   DataSource ds;
   
   //생성자에서 JNDI 리소스를 참조하여 Connection 객체를 얻어옵니다.
   public MemberDAO() {
      try {
         Context init = new InitialContext();
         ds =
      (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
      }catch(Exception ex) {
         System.out.println("DB 연결 실패: " + ex);
         return;
      }
   }
   
   
   public int isId(String id) {
      Connection con=null;
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      int result=-1;//DB에 해당 id가 없습니다.
      try {
         con = ds.getConnection();
         System.out.println("getConnection:isId()");
         
         String sql = "select m_id from member where m_id = ? ";
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, id);
         rs = pstmt.executeQuery();
         
         if(rs.next()) {
            result = 0; //DB에 해당 id가 있습니다.
            }
         }catch(Exception e) {
            e.printStackTrace();
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
         }//finally
               return result;
}//isId end
   
   
   public int insert(Member member) {
	      Connection con=null;
	      PreparedStatement pstmt=null;
	      int result=0;//DB에 해당 id가 없습니다.
	      try {
	         con = ds.getConnection();
	         System.out.println("getConnection:isId()");
	         
	         String sql = "insert into member (M_ID,M_NAME,M_AGES,M_GENDER,M_PASS,M_MAIL,M_TAG1, "
	         		+ "M_TAG2,M_TAG3,M_TAG4,M_TAG5) "
	         		+ " values (?,?,?,?,?,?,?,?,?,?,?)";
	         pstmt = con.prepareStatement(sql);
	         pstmt.setString(1, member.getID());
	         pstmt.setString(2, member.getNAME());
	         pstmt.setInt(3, member.getAGE());
	         pstmt.setString(4,member.getGENDER());
	         pstmt.setString(5, member.getPASSWORD());
	         pstmt.setString(6,member.getEMAIL());
	         pstmt.setString(7, member.getTAG1());
	         pstmt.setString(8, member.getTAG2());
	         pstmt.setString(9, member.getTAG3());
	         pstmt.setString(10, member.getTAG4());
	         pstmt.setString(11, member.getTAG5());
	         result = pstmt.executeUpdate(); //삽입성공시 result는 1
	         
	         
	       //primary key 제약조건 위반했을 경우 발생하는 에러
	         }catch(java.sql.SQLIntegrityConstraintViolationException e) {
	        	 	result = -1;
	        	 	System.out.println("멤버 아이디 중복 에러입니다.");
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
	}//insert end
   
   
   
   public int isId(String id, String pass) {
	      Connection con=null;
	      PreparedStatement pstmt=null;
	      ResultSet rs=null;
	      int result=0;//DB에 해당 id가 없습니다.
	      try {
	         con = ds.getConnection();
	         System.out.println("getConnection:isId()");
	         
	         String sql = "select m_id, m_pass from member where m_id = ? ";
	         pstmt = con.prepareStatement(sql);
	         pstmt.setString(1, id);
	         rs = pstmt.executeQuery();
	         
	         if(rs.next()) {
	        	 if(rs.getString(2).equals(pass)) {
	        		 result =1;// 아이디와 비밀번호 일치하는 경우
	        	 }else {
	        		 result = 0;//비밀번호가 일치하지 않는경우
	        	 }
	         }else {
	        	 result = -1;//아이디가 존재하지 않습니다.
	         }
	         }catch(Exception e) {
	            e.printStackTrace();
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
	         }//finally
	               return result;
	}//isId end


   
   
   
   
public int getListCount() {
	Connection con= null;
	PreparedStatement pstmt= null;
	ResultSet rs=null;
	
	
	int x = 0;
	try {
		con= ds.getConnection();
		pstmt = con.prepareStatement("select count(*) from member where m_id != 'admin'");
		rs= pstmt.executeQuery();
		
		if(rs.next()) {
			x= rs.getInt(1);
		}
	} catch(Exception ex) {
		System.out.println("getListCount() 에러: "+ex);
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


public List<Member> getList(int page, int limit) {
	List<Member> list= new ArrayList<Member>();
	Connection con= null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try {
		con = ds.getConnection();
		
		 String sql = "select * "
                 + "  from (select b.*, rownum rnum"
                 + "         from(select m_id, m_pass, m_name, m_ages, m_gender, m_mail from member "
                 + "            where m_id != 'admin'"
                 + "            order by m_id) b"
                 +             ")"
                 + "   where rnum>=? and rnum<=?";

		pstmt = con.prepareStatement(sql);
		//한 페이지당 10개씩 목록인 경우 1페이지, 2페이지, 3페이지, 4페이지...
		int startrow = (page-1)*limit+1;
		//읽기 시작할 row 번호(1 11 21 31...
		int endrow= startrow + limit -1;
		//읽을 마지막 row 번호(10 20 30 40...
		pstmt.setInt(1, startrow);
		pstmt.setInt(2, endrow);
		rs= pstmt.executeQuery();
		
		/*
		 * create table member(
			id varchar2(15),
			password varchar2(10),
			name varchar2(15),
			age Number,
			gender varchar2(5),
			email varchar2(30),
			PRIMARY KEY(id)
		);
		 * */
		while(rs.next()) {
			Member m = new Member();
			m.setID(rs.getString("m_id"));
			m.setPASSWORD(rs.getString("m_pass"));
			m.setNAME(rs.getString("m_name"));
			m.setAGE(rs.getInt("m_ages"));
			m.setGENDER(rs.getString("m_gender"));
			m.setEMAIL(rs.getString("m_mail"));
			list.add(m);
		}		
		
	
	}catch(Exception ex) {
		System.out.println("getList1() 에러: "+ex);
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


public int getListCount(String field, String value) {
	Connection con= null;
	PreparedStatement pstmt= null;
	ResultSet rs=null;
	int x = 0;
	
	try {
		con= ds.getConnection();
		String sql = "select count(*) from member "
				   + "where m_id !='admin' "
				   + "and "+field+" like ?";
		System.out.println(sql);
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, "%"+value+"%");
		rs= pstmt.executeQuery();
		if(rs.next()) {
			x= rs.getInt(1);
		}
	} catch(Exception ex) {
		System.out.println("getListCount2() 에러: "+ex);
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
}//getListCount()end


public List<Member> getList(String field, String value, int page, int limit) {
	List<Member> list= new ArrayList<Member>();
	Connection con= null;
	PreparedStatement pstmt= null;
	ResultSet rs=null;
	try {
		con = ds.getConnection();
		String sql= 
				"select * "
			+	"from (select b.*, rownum rnum"
			+"         from (select * from member "
			+"               where m_id != 'admin' "
			+	            "and "+ field + " like ? "
			+               "order by m_id) b"
			+"              )"
			+"   where rnum between ? and  ?";
		System.out.println(sql);
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, "%"+value+"%");
		
		int startrow = (page - 1) * limit + 1;
		//읽기 시작할 row 번호(1 11 21 31...
		int endrow =startrow + limit - 1;
		//읽기 마지막 row 번호(10 20 30 40 ...
		pstmt.setInt(2, startrow);
		pstmt.setInt(3, endrow);
		rs= pstmt.executeQuery();
		
		while(rs.next()) {
			Member m = new Member();
			m.setID(rs.getString("m_id"));
			m.setPASSWORD(rs.getString("m_pass"));
			m.setNAME(rs.getString("m_name"));
			m.setAGE(rs.getInt("m_ages"));
			m.setGENDER(rs.getString("m_gender"));
			m.setEMAIL(rs.getString("m_mail"));
			list.add(m);
		}		
		
		
		}catch(Exception ex) {
			System.out.println("getList2() 에러: "+ex);
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












public String getName(String id) {
	Connection con= null;
	PreparedStatement pstmt= null;
	ResultSet rs=null;
	String name = "";
	
	try {
		con= ds.getConnection();
		String sql = "select M_NAME from member where M_ID= ?";
		System.out.println(sql);
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		rs= pstmt.executeQuery();
		if(rs.next()) {
			name= rs.getString(1);
			System.out.println("name="+name);
		}else {
			String message="이름 없다";
			return message;
		}
		
	} catch(Exception ex) {
		System.out.println("getName 에러: "+ex);
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
	return name;
}



public int isName(String name) {
    Connection con=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;
    int result=-1;//DB에 해당 id가 없습니다.
    try {
       con = ds.getConnection();
       System.out.println("getConnection:isName()");
       
       String sql = "select m_name from member where m_name = ? ";
       pstmt = con.prepareStatement(sql);
       pstmt.setString(1, name);
       System.out.println(sql);
       rs = pstmt.executeQuery();
       if(rs.next()) {
          result = 0; 
          }
       }catch(Exception e) {
          e.printStackTrace();
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
       }//finally
             return result;
}

public int update(Member m) {
    Connection con=null;
    PreparedStatement pstmt=null;
    int result=0;
    try {
       con = ds.getConnection();
       
       String sql = "update member set M_NAME = ?, M_PASS= ?, M_AGES = ? , M_GENDER = ?, M_MAIL= ?, "
    		+ " M_TAG1=?, M_TAG2=?, M_TAG3=?, M_TAG4=?, M_TAG5=?  where M_ID = ?";
       pstmt = con.prepareStatement(sql);
       pstmt.setString(1, m.getNAME());
       pstmt.setString(2, m.getPASSWORD());
       pstmt.setInt(3, m.getAGE());
       pstmt.setString(4, m.getGENDER());
       pstmt.setString(5, m.getEMAIL());
       pstmt.setString(6, m.getTAG1());
       pstmt.setString(7, m.getTAG2());
       pstmt.setString(8, m.getTAG3());
       pstmt.setString(9, m.getTAG4());
       pstmt.setString(10, m.getTAG5());
       pstmt.setString(11, m.getID());
       result = pstmt.executeUpdate();
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


public void delete(String id) {
	Connection con= null;
	PreparedStatement pstmt= null;
	try {
	      con= ds.getConnection();
	      pstmt = con.prepareStatement("delete from member where M_ID = ?");
	      pstmt.setString(1, id);
	      pstmt.executeUpdate();
	   } catch(Exception ex) {
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
	   }
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

}