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
/////////////////////////////////////////////////��� ����
public class UserDefaultJTableDAO {
	
	
	 MenuJTabaleExam me;
	/**
	 * �ʿ��� ��������
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
	 * �ε� ������ ���� ������
	 * */
	public UserDefaultJTableDAO() {
		try {
			// �ε�
			Class.forName("com.mysql.jdbc.Driver");
			// ����
			con = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/cashbook",
							"root", "1234");
			
		} catch (ClassNotFoundException e) {
			System.out.println(e + "=> �ε� fail");
		} catch (SQLException e) {
			System.out.println(e + "=> ���� fail");
		}
	}//������ 

	/**
	 * DB�ݱ� ��� �޼ҵ�
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
	 * �μ��� ���� ID�� �ش��ϴ� ���ڵ� �˻��Ͽ� �ߺ����� üũ�ϱ� ���ϰ��� true =��밡�� , false = �ߺ���
	 * */
	public boolean getIdByCheck(String id) {
		boolean result = true;

		try {
			ps = con.prepareStatement("SELECT * FROM TB_USERLIST1 WHERE id=?");
			ps.setString(1, id.trim());
			rs = ps.executeQuery(); //����
			if (rs.next())
				result = false; //���ڵ尡 �����ϸ� false

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
			rs = ps.executeQuery(); //����
			if (rs.next())
				result = false; //���ڵ尡 �����ϸ� false

		} catch (SQLException e) {
			System.out.println(e + "=>  getIdByCheck fail");
		} finally {
			dbClose();
		}

		return result;

	}
	//getIdByCheck()

	/**
	 * userlist ȸ�������ϴ� ��� �޼ҵ�
	 * */
	public int userListInsert(UserJDailogGUI userJDailogGUI) {
		int result = 0;
		try {
			ps = con.prepareStatement("insert into TB_USERLIST1 values(?,?,?,?)");
			ps.setString(1, userJDailogGUI.id.getText());
			ps.setString(2, userJDailogGUI.name.getText());
			ps.setInt(3, Integer.parseInt(userJDailogGUI.grade.getText()));
			ps.setString(4, userJDailogGUI.userpassword.getText());

			result = ps.executeUpdate(); //���� -> ����

		} catch (SQLException e) {
			System.out.println(e + "=> userListInsert1 fail");
		} finally {
			dbClose();
		}

		return result;

	}//userListInsert()
	
	///////////////////////////////////////////////////////////�����Է�(������)
	public int subjectInsert(SubjectAddDialog subject) {
		int result = 0;
		try {
			ps = con.prepareStatement("insert into TB_SUBJECTLIST values(?,?,?,?,?)");
			
			   /* PreparedStatement ��ü�� IN �Ű������� �����ų� ������ �ʴ� ���������ϵ� SQL���� �����ϴµ� ���ǰ�, 
		        PreparedStatement �������̽��� IN �Ű��������� �ٷ�� ���� �޼ҵ���� �ִ�.*/


			ps.setString(1, subject.snum.getText());
			ps.setString(2, subject.subject.getText());
			ps.setInt(3, Integer.parseInt(subject.grade.getText()));
			ps.setInt(4, Integer.parseInt(subject.maxnum.getText()));
			ps.setInt(5, Integer.parseInt(subject.pnum.getText()));
			result = ps.executeUpdate(); //���� -> ����

		} catch (SQLException e) {
			System.out.println(e + "=> userListInsert1 fail");
		} finally {
			dbClose();
		}

		return result;

	}

	/**
	 * userlist�� ��� ���ڵ� ��ȸ
	 * 
	 * */
	public void userSelectId(DefaultTableModel dt) {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from TB_USERLIST1 order by id");

			// DefaultTableModel�� �ִ� ���� ������ �����
			for (int i = 0; i < dt.getRowCount();) {
				dt.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2),
						rs.getInt(3), rs.getString(4) };
				
				dt.addRow(data); //DefaultTableModel�� ���ڵ� �߰�
				
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

			// DefaultTableModel�� �ִ� ���� ������ �����
			for (int i = 0; i < dt.getRowCount();) {
				dt.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2),
						rs.getInt(3), rs.getString(4) };

				dt.addRow(data); //DefaultTableModel�� ���ڵ� �߰�
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

			// DefaultTableModel�� �ִ� ���� ������ �����
			for (int i = 0; i < dt.getRowCount();) {
				dt.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2),
						rs.getInt(3), rs.getString(4) };

				dt.addRow(data); //DefaultTableModel�� ���ڵ� �߰�
			}

		} catch (SQLException e) {
			System.out.println(e + "=> userSelectAll fail");
		} finally {
			dbClose();
		}
	}
	////////////////////////////////////////////////////////////////�м���ȣ�� ����
	public void SelectSnum(DefaultTableModel dt) {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from TB_SUBJECTLIST order by snum");
			
			// DefaultTableModel�� �ִ� ���� ������ �����
			for (int i = 0; i < dt.getRowCount();) {
				dt.removeRow(0);
			}
			
			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2),
						rs.getInt(3) , rs.getInt(4), rs.getInt(5)};

				dt.addRow(data); //DefaultTableModel�� ���ڵ� �߰�
			}

		} catch (SQLException e) {
			System.out.println(e + "=> userSelectAll fail");
		} finally {
			dbClose();
		}
	}
	/////////////////////////////////////////////////////////////���� ����
	public void SelectSubject(DefaultTableModel dt) {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from TB_SUBJECTLIST order by subject");

			// DefaultTableModel�� �ִ� ���� ������ �����
			for (int i = 0; i < dt.getRowCount();) {
				dt.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2),
						rs.getInt(3), rs.getInt(4), rs.getInt(5) };

				dt.addRow(data); //DefaultTableModel�� ���ڵ� �߰�
			}

		} catch (SQLException e) {
			System.out.println(e + "=> userSelectAll fail");
		} finally {
			dbClose();
		}
	}
	///////////////////////////////////////////////////////�г⺰ ����
	public void SelectGrade(DefaultTableModel dt) {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from TB_SUBJECTLIST order by sgrade");

			// DefaultTableModel�� �ִ� ���� ������ �����
			for (int i = 0; i < dt.getRowCount();) {
				dt.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2),
						rs.getInt(3), rs.getInt(4), rs.getInt(5) };

				dt.addRow(data); //DefaultTableModel�� ���ڵ� �߰�
			}

		} catch (SQLException e) {
			System.out.println(e + "=> userSelectAll fail");
		} finally {
			dbClose();
		}
	}
	//////////////////////////////////////////////////////////������ ����
	public void SelectMaxnum(DefaultTableModel dt) {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from TB_SUBJECTLIST order by maxnum");

			// DefaultTableModel�� �ִ� ���� ������ �����
			for (int i = 0; i < dt.getRowCount();) {
				dt.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2),
						rs.getInt(3), rs.getInt(4), rs.getInt(5) };

				dt.addRow(data); //DefaultTableModel�� ���ڵ� �߰�
			}

		} catch (SQLException e) {
			System.out.println(e + "=> userSelectAll fail");
		} finally {
			dbClose();
		}
	}
																				////////////////////////////////////////////////////////////////�������ɰ��� ����
	public void psubjectSelect(String id,DefaultTableModel dt) {
		try {
			ps = con.prepareStatement("select s.name, t.snum, t.subject, t.sgrade, t.maxnum, t.pnum  from tb_userlist1 s join tb_subjectlist t on(s.grade=t.sgrade) where id=?");
			ps.setString(1, id.trim());														////////////////////�л����̺�� �������̺��� �ڿ������ϰ� �Ķ���ͷ� �Ѿ���� id���� where���ǿ� �Ǵ�
			rs = ps.executeQuery();
			
			// DefaultTableModel�� �ִ� ���� ������ �����
			for (int i = 0; i < dt.getRowCount();) {
				dt.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2),rs.getString(3),
						rs.getInt(4), rs.getInt(5),rs.getInt(6) };

				dt.addRow(data); //DefaultTableModel�� ���ڵ� �߰�
			
			}
				
		} catch (SQLException e) {
			System.out.println(e + "=> userSelectAll fail");
		} finally {
			dbClose();
		}
	}
																				/////////////////////////////////////////////////////////////////������û�� ���� ����
	public void completeSubject(String id,DefaultTableModel dt) {
		int result=0;
		try {
			ps = con.prepareStatement("  select  t.id, s.snum,s.subject,s.sgrade,s.maxnum, s.pnum "
					+ "from tb_total t natural join tb_subjectlist s "
					+ "where id=?");
			ps.setString(1, id.trim());
			rs = ps.executeQuery();
			
			// DefaultTableModel�� �ִ� ���� ������ �����
			for (int i = 0; i < dt.getRowCount();) {
				dt.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2),rs.getString(3),
						rs.getInt(4), rs.getInt(5),rs.getInt(6) };

				dt.addRow(data); //DefaultTableModel�� ���ڵ� �߰�
			
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

			// DefaultTableModel�� �ִ� ���� ������ �����
			for (int i = 0; i < t_model.getRowCount();) {
				t_model.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2),
						rs.getInt(3), rs.getString(4) };

				t_model.addRow(data); //DefaultTableModel�� ���ڵ� �߰�
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

			// DefaultTableModel�� �ִ� ���� ������ �����
			for (int i = 0; i < t_model.getRowCount();) {
				t_model.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2),
						rs.getInt(3), rs.getString(4), rs.getString(5) };

				t_model.addRow(data); //DefaultTableModel�� ���ڵ� �߰�
			}

		} catch (SQLException e) {
			System.out.println(e + "=> userSelectAll fail");
		} finally {
			dbClose();
		}
	}//userSelectAll()
	/**
	 * ID�� �ش��ϴ� ���ڵ� �����ϱ�
	 * */
	public int userDelete(String id) {												///////////////////ȸ���� ������ ��� ������û�ߴ� total�� �ִ� ������ �����Ѵ�
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
	 * ID�� �ش��ϴ� ���ڵ� �����ϱ�
	 * */
	public int userUpdate(UserJDailogGUI userJDailogGUI) {
		int result = 0;
		String sql = "UPDATE TB_USERLIST1 SET NAME=?, grade=? , addr=? WHERE id=?";

		try {
			ps = con.prepareStatement(sql);
			// ?�� ������� �� �ֱ�
			ps.setString(1, userJDailogGUI.name.getText());
			ps.setString(2, userJDailogGUI.grade.getText());
			ps.setString(3, userJDailogGUI.userpassword.getText());
			ps.setString(4, userJDailogGUI.id.getText().trim());

			// �����ϱ�
			result = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e + "=> userUpdate fail");
		} finally {
			dbClose();
		}

		return result;
	}//userUpdate()
																			/////////////////////////////////////////////////////////////������û�ϱ� ���
	public int subjectlistUpdate(requestGUI snum)  {
		int result = 0;
		int maxnum=0,pnum=0;
		String sql4= "select maxnum,pnum from tb_subjectlist where snum=?";		//////������ �ʰ��� �������� Ȯ���ϱ� ���ؼ� ������ �����ο��� select�մϴ�
		String sql = "UPDATE tb_subjectlist SET pnum=pnum+1 WHERE snum=?";		//////�ϴ� ������û�� �ϰԵǸ� ���������� +1�� �մϴ�
		String sql2= "insert into tb_total values(?,?,?,?)";					//////������û�� �ο��� �̸��� �м���ȣ ���� �г��� tb_total���̺� ����˴ϴ� 		
																				//////�����Ҷ� �����ο� ���� ����ǰ� �Ǹ� ���� ���������� �� ��� �����ο����� ����Ǳ⶧���� 4���� ��Ʈ����Ʈ�� ����Ͽ����ϴ�
		String sql3 = "UPDATE tb_subjectlist SET pnum=pnum-1 WHERE snum=?";		//////���������� ��û�ҽ� catch ������ ���������� -1�� �մϴ�
		 
		try {
			ps = con.prepareStatement(sql4);									
			ps.setString(1, snum.snum.getText());
			rs = ps.executeQuery();
			while(rs.next()){
				maxnum=rs.getInt(1);											//////�ش� �м���ȣ ������ ������ �����ο��� ������ ������ �����մϴ�
				pnum=rs.getInt(2);
			}
			if(maxnum>pnum){													//////�������� �����ο��� �����ÿ� ������û �� �� �ֵ��� �մϴ�.
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
			requestGUI.messageBox(snum,"��û�Ϸ�");
			}else {
				requestGUI.messageBox(snum,"������ �ʰ��Ͽ����ϴ�");					///////�����ʰ��� �޼����ڽ� ���ϴ�.
			}
		} catch (SQLException e) {
			
			try {
				ps = con.prepareStatement(sql3);
				ps.setString(1, snum.snum.getText());
				result = ps.executeUpdate();
				requestGUI.messageBox(snum,"�̹� ��û�� �����Դϴ�");
			} catch (SQLException e1) {
				
			}
			
		} finally {
			dbClose();
		}

		return result;
	}//userUpdate()
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////���������ֱ�
	public void showpicture(String snum) {
		try {
			ps = con.prepareStatement("select file from picture natural join tb_subjectlist  where snum=?");
			ps.setString(1, snum.trim());
			rs = ps.executeQuery();
			rs.first();																	////Ŀ���� ó������ �Ӵϴ�
			ImageIcon icon=new ImageIcon(rs.getBytes(1));								////�̹����� ����Ʈ������ �н��ϴ�
			showpictureDialog.image_l.setIcon(icon);									////�󺧿� �̹����� �ø��ϴ�
		} catch (SQLException e) {
			System.out.println(e + "=> userSelectAll fail");
		} finally {
			dbClose();
		}
	}//////////////////////////////////////////////////////////////////////////////////////////////////////////////���� ����
	public int subjectDelete(String subject) {
		int result = 0;
		try {
			ps = con.prepareStatement("delete from tb_subjectlist where subject = ? ");/////////////////////���ڵ� ���� 3��°ĭ ���� �Ķ���ͷ� �޾ƿ´�
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
				//////////////////////////////////////////////////////////////////////////////////////////////////////////////���� ����
	public static int savePicture(){
		int result =0;
		try {
			
	         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cashbook", "root", "1234");
	         
			System.out.println(picture.bookimage);
	         FileInputStream bi = new FileInputStream(picture.bookimage);		///////////////////������ ��ο� �ִ� ������ ����Ʈ��Ʈ������ �б� ���ؼ� ��ü�� ��´�
	         
	         ps =con.prepareStatement("insert into picture values (?, ?)");	         
	         ps.setBlob(1, bi);	     											///////////////////�̹��� ���� ����
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

	public int subjectCancel(String snum) {									////////////////////////////////////////////////////////////////////�������
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

	
	


	

}// Ŭ������

