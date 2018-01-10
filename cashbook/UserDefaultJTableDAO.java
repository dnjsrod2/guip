package cashbook;
import java.awt.Image;
import java.awt.Window;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/////////////////////////////////////////////////기능 구현
public class UserDefaultJTableDAO {
	
	
	 MenuJTabaleExam me;
	/**
	 * 필요한 변수선언
	 * */
	 Connection con;
	 static Statement st;
	 static PreparedStatement ps;
	 static ResultSet rs;
	Blob file;
	byte[] fileData;
	InputStream in;
	private Object output;

	/**
	 * 로드 연결을 위한 생성자
	 * */
	public UserDefaultJTableDAO() {
		try {
			// 로드
			Class.forName("com.mysql.jdbc.Driver");
			// 연결
			con = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/cashbook",
							"root", "1234");
			
		} catch (ClassNotFoundException e) {
			System.out.println(e + "=> 로드 fail");
		} catch (SQLException e) {
			System.out.println(e + "=> 연결 fail");
		}
	}//생성자 

	/**
	 * DB닫기 기능 메소드
	 * */
	public static void dbClose() {
		try {
			if (rs != null) rs.close();
			if (st != null)	st.close();
			if (ps != null)	ps.close();
		} catch (Exception e) {
			System.out.println(e + "=> dbClose fail");
		}
	}//dbClose() ---

	/**
	 * 인수로 들어온 ID에 해당하는 레코드 검색하여 중복여부 체크하기 리턴값이 true =사용가능 , false = 중복임
	 * */
	public boolean getIdByCheck(String id) {
		boolean result = true;

		try {
			ps = con.prepareStatement("SELECT * FROM TB_USERLIST1 WHERE id=?");
			ps.setString(1, id.trim());
			rs = ps.executeQuery(); //실행
			if (rs.next())
				result = false; //레코드가 존재하면 false

		} catch (SQLException e) {
			System.out.println(e + "=>  getIdByCheck fail");
		} finally {
			dbClose();
		}

		return result;

	}
	public boolean loginCheck(String id, String password) {
		boolean result = true;

		try {
			ps = con.prepareStatement("SELECT * FROM TB_USERLIST1 WHERE id=? and password=?");
			ps.setString(1, id.trim());
			ps.setString(2, password.trim());
			rs = ps.executeQuery(); //실행
			if (rs.next())
				result = false; //레코드가 존재하면 false

		} catch (SQLException e) {
			System.out.println(e + "=>  getIdByCheck fail");
		} finally {
			dbClose();
		}

		return result;

	}
	//getIdByCheck()

	/**
	 * userlist 회원가입하는 기능 메소드
	 * */
	public int userListInsert(UserJDailogGUI userJDailogGUI) {
		int result = 0;
		try {
			ps = con.prepareStatement("insert into TB_USERLIST1 values(?,?,?,?)");
			ps.setString(1, userJDailogGUI.id.getText());
			ps.setString(2, userJDailogGUI.name.getText());
			ps.setInt(3, Integer.parseInt(userJDailogGUI.grade.getText()));
			ps.setString(4, userJDailogGUI.userpassword.getText());

			result = ps.executeUpdate(); //실행 -> 저장

		} catch (SQLException e) {
			System.out.println(e + "=> userListInsert1 fail");
		} finally {
			dbClose();
		}

		return result;

	}//userListInsert()
	
	///////////////////////////////////////////////////////////강의입력(관리자)
	public int subjectInsert(SubjectAddDialog subject) {
		int result = 0;
		try {
			ps = con.prepareStatement("insert into TB_SUBJECTLIST values(?,?,?,?,?)");
			
			   /* PreparedStatement 객체는 IN 매개변수를 가지거나 가지지 않는 프리컴파일된 SQL문을 실행하는데 사용되고, 
		        PreparedStatement 인터페이스는 IN 매개변수들을 다루기 위한 메소드들이 있다.*/


			ps.setString(1, subject.snum.getText());
			ps.setString(2, subject.subject.getText());
			ps.setInt(3, Integer.parseInt(subject.grade.getText()));
			ps.setInt(4, Integer.parseInt(subject.maxnum.getText()));
			ps.setInt(5, Integer.parseInt(subject.pnum.getText()));
			result = ps.executeUpdate(); //실행 -> 저장

		} catch (SQLException e) {
			System.out.println(e + "=> userListInsert1 fail");
		} finally {
			dbClose();
		}

		return result;

	}

	/**
	 * userlist의 모든 레코드 조회
	 * 
	 * */
	public void userSelectId(DefaultTableModel dt) {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from TB_USERLIST1 order by id");

			// DefaultTableModel에 있는 기존 데이터 지우기
			for (int i = 0; i < dt.getRowCount();) {
				dt.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2),
						rs.getInt(3), rs.getString(4) };
				
				dt.addRow(data); //DefaultTableModel에 레코드 추가
				
			}

		} catch (SQLException e) {
			System.out.println(e + "=> userSelectAll fail");
		} finally {
			dbClose();
		}
	}
	public void userSelectName(DefaultTableModel dt) {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from TB_USERLIST1 order by name");

			// DefaultTableModel에 있는 기존 데이터 지우기
			for (int i = 0; i < dt.getRowCount();) {
				dt.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2),
						rs.getInt(3), rs.getString(4) };

				dt.addRow(data); //DefaultTableModel에 레코드 추가
			}

		} catch (SQLException e) {
			System.out.println(e + "=> userSelectAll fail");
		} finally {
			dbClose();
		}
	}
	public void userSelectGrade(DefaultTableModel dt) {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from TB_USERLIST1 order by grade");

			// DefaultTableModel에 있는 기존 데이터 지우기
			for (int i = 0; i < dt.getRowCount();) {
				dt.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2),
						rs.getInt(3), rs.getString(4) };

				dt.addRow(data); //DefaultTableModel에 레코드 추가
			}

		} catch (SQLException e) {
			System.out.println(e + "=> userSelectAll fail");
		} finally {
			dbClose();
		}
	}
	////////////////////////////////////////////////////////////////학수번호별 정렬
	public void SelectSnum(DefaultTableModel dt) {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from TB_SUBJECTLIST order by snum");
			
			// DefaultTableModel에 있는 기존 데이터 지우기
			for (int i = 0; i < dt.getRowCount();) {
				dt.removeRow(0);
			}
			
			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2),
						rs.getInt(3) , rs.getInt(4), rs.getInt(5)};

				dt.addRow(data); //DefaultTableModel에 레코드 추가
			}

		} catch (SQLException e) {
			System.out.println(e + "=> userSelectAll fail");
		} finally {
			dbClose();
		}
	}
	/////////////////////////////////////////////////////////////과목별 정렬
	public void SelectSubject(DefaultTableModel dt) {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from TB_SUBJECTLIST order by subject");

			// DefaultTableModel에 있는 기존 데이터 지우기
			for (int i = 0; i < dt.getRowCount();) {
				dt.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2),
						rs.getInt(3), rs.getInt(4), rs.getInt(5) };

				dt.addRow(data); //DefaultTableModel에 레코드 추가
			}

		} catch (SQLException e) {
			System.out.println(e + "=> userSelectAll fail");
		} finally {
			dbClose();
		}
	}
	///////////////////////////////////////////////////////학년별 정렬
	public void SelectGrade(DefaultTableModel dt) {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from TB_SUBJECTLIST order by sgrade");

			// DefaultTableModel에 있는 기존 데이터 지우기
			for (int i = 0; i < dt.getRowCount();) {
				dt.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2),
						rs.getInt(3), rs.getInt(4), rs.getInt(5) };

				dt.addRow(data); //DefaultTableModel에 레코드 추가
			}

		} catch (SQLException e) {
			System.out.println(e + "=> userSelectAll fail");
		} finally {
			dbClose();
		}
	}
	//////////////////////////////////////////////////////////정원별 정렬
	public void SelectMaxnum(DefaultTableModel dt) {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from TB_SUBJECTLIST order by maxnum");

			// DefaultTableModel에 있는 기존 데이터 지우기
			for (int i = 0; i < dt.getRowCount();) {
				dt.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2),
						rs.getInt(3), rs.getInt(4), rs.getInt(5) };

				dt.addRow(data); //DefaultTableModel에 레코드 추가
			}

		} catch (SQLException e) {
			System.out.println(e + "=> userSelectAll fail");
		} finally {
			dbClose();
		}
	}
																				////////////////////////////////////////////////////////////////수강가능과목 보기
	public void psubjectSelect(String id,DefaultTableModel dt) {
		try {
			ps = con.prepareStatement("select s.name, t.snum, t.subject, t.sgrade, t.maxnum, t.pnum  from tb_userlist1 s join tb_subjectlist t on(s.grade=t.sgrade) where id=?");
			ps.setString(1, id.trim());														////////////////////학생테이블과 과목테이블을 자연조인하고 파라미터로 넘어오는 id값을 where조건에 건다
			rs = ps.executeQuery();
			
			// DefaultTableModel에 있는 기존 데이터 지우기
			for (int i = 0; i < dt.getRowCount();) {
				dt.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2),rs.getString(3),
						rs.getInt(4), rs.getInt(5),rs.getInt(6) };

				dt.addRow(data); //DefaultTableModel에 레코드 추가
			
			}
				
		} catch (SQLException e) {
			System.out.println(e + "=> userSelectAll fail");
		} finally {
			dbClose();
		}
	}
																				/////////////////////////////////////////////////////////////////수강신청한 과목 보기
	public void completeSubject(String id,DefaultTableModel dt) {
		int result=0;
		try {
			ps = con.prepareStatement("  select  t.id, s.snum,s.subject,s.sgrade,s.maxnum, s.pnum "
					+ "from tb_total t natural join tb_subjectlist s "
					+ "where id=?");
			ps.setString(1, id.trim());
			rs = ps.executeQuery();
			
			// DefaultTableModel에 있는 기존 데이터 지우기
			for (int i = 0; i < dt.getRowCount();) {
				dt.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2),rs.getString(3),
						rs.getInt(4), rs.getInt(5),rs.getInt(6) };

				dt.addRow(data); //DefaultTableModel에 레코드 추가
			
			}
			
		} catch (SQLException e) {
			System.out.println(e + "=> userSelectAll fail");
		} finally {
			dbClose();
		}
		
	}
	public void userSelectAll(DefaultTableModel t_model) {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from TB_USERLIST1 order by id");

			// DefaultTableModel에 있는 기존 데이터 지우기
			for (int i = 0; i < t_model.getRowCount();) {
				t_model.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2),
						rs.getInt(3), rs.getString(4) };

				t_model.addRow(data); //DefaultTableModel에 레코드 추가
			}

		} catch (SQLException e) {
			System.out.println(e + "=> userSelectAll fail");
		} finally {
			dbClose();
		}
	}//userSelectAll()
	/////////////////////////////////
	public void subjectSelectAll(DefaultTableModel t_model) {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from TB_subjectlist order by snum");

			// DefaultTableModel에 있는 기존 데이터 지우기
			for (int i = 0; i < t_model.getRowCount();) {
				t_model.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2),
						rs.getInt(3), rs.getString(4), rs.getString(5) };

				t_model.addRow(data); //DefaultTableModel에 레코드 추가
			}

		} catch (SQLException e) {
			System.out.println(e + "=> userSelectAll fail");
		} finally {
			dbClose();
		}
	}//userSelectAll()
	/**
	 * ID에 해당하는 레코드 삭제하기
	 * */
	public int userDelete(String id) {												///////////////////회원을 삭제할 경우 수강신청했던 total에 있는 정보도 삭제한다
		int result = 0;
		String name=null;
		String snum=null;
		try {
			/*
			ps = con.prepareStatement("select name from tb_userlist1 where id = ? ");
			ps.setString(1, id.trim());
			rs = ps.executeQuery();
			rs.first();
			name=rs.getString(1);*/
			ps = con.prepareStatement( "select s.snum from tb_subjectlist s join tb_total t on(s.subject=t.subject) where id=? ");
			ps.setString(1, id.trim());
			rs = ps.executeQuery();
			rs.first();
			snum=rs.getString(1);
			ps = con.prepareStatement("delete from TB_USERLIST1 where id = ? ");
			ps.setString(1, id.trim());
			result = ps.executeUpdate();
			ps = con.prepareStatement("delete from TB_total where id = ? ");
			ps.setString(1, id.trim());
			result = ps.executeUpdate();
			
			ps = con.prepareStatement( "UPDATE tb_subjectlist SET pnum=pnum-1 WHERE snum=?");
			ps.setString(1, snum);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e + "=> userDelete fail");
		}finally {
			dbClose();
		}

		return result;
	}//userDelete()

	/**
	 * ID에 해당하는 레코드 수정하기
	 * */
	public int userUpdate(UserJDailogGUI userJDailogGUI) {
		int result = 0;
		String sql = "UPDATE TB_USERLIST1 SET NAME=?, grade=? , addr=? WHERE id=?";

		try {
			ps = con.prepareStatement(sql);
			// ?의 순서대로 값 넣기
			ps.setString(1, userJDailogGUI.name.getText());
			ps.setString(2, userJDailogGUI.grade.getText());
			ps.setString(3, userJDailogGUI.userpassword.getText());
			ps.setString(4, userJDailogGUI.id.getText().trim());

			// 실행하기
			result = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e + "=> userUpdate fail");
		} finally {
			dbClose();
		}

		return result;
	}//userUpdate()
																			/////////////////////////////////////////////////////////////수강신청하기 기능
	public int subjectlistUpdate(requestGUI snum)  {
		int result = 0;
		int maxnum=0,pnum=0;
		String sql4= "select maxnum,pnum from tb_subjectlist where snum=?";		//////정원이 초과된 과목인지 확인하기 위해서 정원과 현재인원을 select합니다
		String sql = "UPDATE tb_subjectlist SET pnum=pnum+1 WHERE snum=?";		//////일단 수강신청을 하게되면 현재정원에 +1을 합니다
		String sql2= "insert into tb_total values(?,?,?,?)";					//////수강신청한 인원의 이름과 학수번호 과목 학년이 tb_total테이블에 저장됩니다 		
																				//////저장할때 현재인원 값이 저장되게 되면 같은 과목이지만 그 당시 현재인원값이 저장되기때문에 4개의 애트리뷰트만 사용하였습니다
		String sql3 = "UPDATE tb_subjectlist SET pnum=pnum-1 WHERE snum=?";		//////같은과목을 신청할시 catch 절에서 현재정원에 -1을 합니다
		 
		try {
			ps = con.prepareStatement(sql4);									
			ps.setString(1, snum.snum.getText());
			rs = ps.executeQuery();
			while(rs.next()){
				maxnum=rs.getInt(1);											//////해당 학수번호 과목의 정원과 현재인원을 정수형 변수에 저장합니다
				pnum=rs.getInt(2);
			}
			if(maxnum>pnum){													//////정원보다 현재인원이 적을시에 수강신청 할 수 있도록 합니다.
			ps = con.prepareStatement(sql);										
			ps.setString(1, snum.snum.getText());
			result = ps.executeUpdate();
			ps = con.prepareStatement(sql2);
			ps.setString(1, snum.id.getText());
			ps.setString(2, snum.snum.getText());
			ps.setString(3, snum.subject.getText());
			ps.setInt(4, Integer.parseInt(snum.grade.getText()));
			//ps.setInt(5, Integer.parseInt(snum.maxnum.getText()));
			//ps.setInt(6, Integer.parseInt(snum.pnum.getText()));
			result = ps.executeUpdate();
			requestGUI.messageBox(snum,"신청완료");
			}else {
				requestGUI.messageBox(snum,"정원이 초과하였습니다");					///////정원초과시 메세지박스 띄웁니다.
			}
		} catch (SQLException e) {
			
			try {
				ps = con.prepareStatement(sql3);
				ps.setString(1, snum.snum.getText());
				result = ps.executeUpdate();
				requestGUI.messageBox(snum,"이미 신청한 강좌입니다");
			} catch (SQLException e1) {
				
			}
			
		} finally {
			dbClose();
		}

		return result;
	}//userUpdate()
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////사진보여주기
	public void showpicture(String snum) {
		try {
			ps = con.prepareStatement("select file from picture natural join tb_subjectlist  where snum=?");
			ps.setString(1, snum.trim());
			rs = ps.executeQuery();
			rs.first();																	////커서를 처음으로 둡니다
			ImageIcon icon=new ImageIcon(rs.getBytes(1));								////이미지를 바이트단위로 읽습니다
			showpictureDialog.image_l.setIcon(icon);									////라벨에 이미지를 올립니다
		} catch (SQLException e) {
			System.out.println(e + "=> userSelectAll fail");
		} finally {
			dbClose();
		}
	}//////////////////////////////////////////////////////////////////////////////////////////////////////////////강의 삭제
	public int subjectDelete(String subject) {
		int result = 0;
		try {
			ps = con.prepareStatement("delete from tb_subjectlist where subject = ? ");/////////////////////레코드 행의 3번째칸 값을 파라미터로 받아온다
			ps.setString(1, subject.trim());
			result = ps.executeUpdate();
			ps = con.prepareStatement("delete from tb_total where subject = ? ");
			ps.setString(1, subject.trim());
			result = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e + "=> userDelete fail");
		}finally {
			dbClose();
		}

		return result;
	}
				//////////////////////////////////////////////////////////////////////////////////////////////////////////////사진 저장
	public static int savePicture(){
		int result =0;
		try {
			
	         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cashbook", "root", "1234");
	         
			System.out.println(picture.bookimage);
	         FileInputStream bi = new FileInputStream(picture.bookimage);		///////////////////선택한 경로에 있는 파일을 바이트스트림으로 읽기 위해서 객체에 담는다
	         
	         ps =con.prepareStatement("insert into picture values (?, ?)");	         
	         ps.setBlob(1, bi);	     											///////////////////이미지 파일 저장
	         ps.setString(2,picture.subject.getText());
	        
	         ps.executeUpdate();
	         
	         
	      } catch (Exception e) {
	    	  result=result+1;
	    	  System.out.println(e);
	      }finally{
	    	 
	    	  dbClose();
	      }
		
		return result;
	   }

	public int subjectCancel(String snum) {									////////////////////////////////////////////////////////////////////수강취소
		int result = 0;
		String sql = "delete from tb_total where snum = ? ";
		String sql2 = "UPDATE tb_subjectlist SET pnum=pnum-1 WHERE snum=?";
		String sql3 = "UPDATE tb_subjectlist SET pnum=pnum+1 WHERE snum=?";
		try {
			
			ps = con.prepareStatement(sql2);
			ps.setString(1, snum.trim());
			result = ps.executeUpdate();
			ps = con.prepareStatement(sql); 
			ps.setString(1, snum.trim());
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			try {
				ps = con.prepareStatement(sql3);
				ps.setString(1, snum.trim());
				result = ps.executeUpdate();
			} catch (SQLException e1) {
				 
			} 
			
		}finally {
			
			dbClose();
		}

		return result;
	}

	
	


	

}// 클래스끝

