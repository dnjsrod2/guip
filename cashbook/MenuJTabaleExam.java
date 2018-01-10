package cashbook;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;


public class MenuJTabaleExam extends JFrame implements ActionListener {
	/**
	 * 
	 */
	
	Image img;
	
	private static final long serialVersionUID = 1L;
	JMenu m = new JMenu("\uB85C\uADF8\uC778");
	JMenuItem insert = new JMenuItem("가입");
	static JMenuItem update = new JMenuItem("수정");
	static JMenuItem delete = new JMenuItem("삭제");
	
	JMenuItem quit = new JMenuItem("종료");
	static JMenuBar mb = new JMenuBar();

	static String[] name = { "이름","학수번호","과목","학년","정원","현재인원" };
	static String[] sname = {"아이디","이름","학년","비밀번호"};
	static String[] dname= {"학수번호","과목","학년","정원","현재인원" };
	static DefaultTableModel dt = new DefaultTableModel(name, 0);
	static DefaultTableModel dt2 = new DefaultTableModel(sname, 0);
	static DefaultTableModel dt3 = new DefaultTableModel(dname, 0);
	JTable jt = new JTable(dt);
	JTable jt2 = new JTable(dt2);
	JScrollPane jsp = new JScrollPane(jt);
	JScrollPane jsp2 = new JScrollPane(jt2);
	String[] comboName = { "  ALL  ", "  ID  ", " name ", " addr " };

	UserDefaultJTableDAO dao = new UserDefaultJTableDAO();
	static final JMenu sm = new JMenu("\uC218\uAC15\uC2E0\uCCAD");
	static JMenuItem grade = new JMenuItem("\uD559\uB144");
	private final JMenuItem subject = new JMenuItem("\uACFC\uBAA9");
	private final JMenuItem maxnum = new JMenuItem("\uC815\uC6D0");
	static JMenuItem id = new JMenuItem("\uC544\uC774\uB514");
	static final JMenuItem mgrade = new JMenuItem("\uD559\uB144");
	static final JMenuItem mname = new JMenuItem("\uC774\uB984");
	static final JMenuItem request = new JMenuItem("\uC218\uAC15\uC2E0\uCCAD\uD558\uAE30");
	private final JMenuItem manager = new JMenuItem("\uAD00\uB9AC\uC790");
	private final JMenuItem login = new JMenuItem("\uD559\uC0DD");
	private final JMenuItem snum = new JMenuItem("\uD559\uC218\uBC88\uD638");
	static final JMenuItem insertsubject = new JMenuItem("\uAC15\uC758\uC785\uB825(\uAD00\uB9AC\uC790)");
	static final JMenuItem psubject = new JMenuItem("\uC218\uAC15\uAC00\uB2A5\uACFC\uBAA9");
	static final JLabel loginname = new JLabel("\uB85C\uADF8\uC778\uBA85");
	static final JMenuItem completesubject = new JMenuItem("\uC218\uAC15\uC2E0\uCCAD\uACFC\uBAA9");
	private final JMenuItem image = new JMenuItem("\uAD50\uACFC\uC11C");
	static final JMenuItem deletesubject = new JMenuItem("\uAC15\uC758\uC0AD\uC81C(\uAD00\uB9AC\uC790)");

	/**
	 * 화면구성 및 이벤트등록
	 * @return 
	 */
	
	
	public MenuJTabaleExam() {
		
		super("GUI 회원관리프로그램 - DB연동");
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent arg0) {
			}
			public void windowLostFocus(WindowEvent arg0) {
			}
		});
		setForeground(new Color(255, 153, 153));
		setBackground(Color.DARK_GRAY);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dnjsr\\Desktop\\181562711.jpg"));
		setFont(new Font("Dialog", Font.PLAIN, 38));
		setTitle("\uC218\uAC15\uC2E0\uCCAD");
		m.setBackground(SystemColor.info);
		m.setForeground(new Color(255, 153, 153));
		m.setFont(new Font("Dialog", Font.PLAIN, 15));
		jt.setGridColor(new Color(255, 153, 153));
		jt.setForeground(new Color(255, 153, 153));
		jt.setBackground(Color.WHITE);
		m.add(manager);
		
		
		
		m.add(login);

		//메뉴아이템을 메뉴에 추가
		m.add(insert); 
		update.setEnabled(false);
		m.add(update);
		delete.setEnabled(false);
		m.add(delete);
		id.setEnabled(false);
		
		m.add(id);
		mname.setEnabled(false);
		
		m.add(mname);
		mgrade.setEnabled(false);
		
		m.add(mgrade);
		m.add(quit);
		mb.setBackground(SystemColor.textHighlightText);
		mb.setForeground(SystemColor.menuText);
		//메뉴를 메뉴바에 추가
		mb.add(m);
		
		//윈도우에 메뉴바 세팅
		setJMenuBar(mb);
		sm.setFont(new Font("Dialog", Font.PLAIN, 15));
		sm.setForeground(new Color(255, 153, 153));
		
		
		mb.add(sm);
		
		sm.add(snum);
		
		sm.add(subject);
		
		sm.add(grade);
		
		sm.add(maxnum);
		
		sm.add(psubject);
		
		sm.add(request);
		
		sm.add(completesubject);
		
		sm.add(image);
		
		sm.add(insertsubject);
		insertsubject.setEnabled(false);
		sm.setEnabled(false);
		
		sm.add(deletesubject);
		loginname.setFont(new Font("Dialog", Font.BOLD, 15));
		loginname.setForeground(new Color(255, 153, 153));
		
		mb.add(loginname);
		jsp.setForeground(SystemColor.textHighlightText);
		jsp.setFont(new Font("Dialog", Font.BOLD, 20));
		jsp.setBackground(new Color(102, 51, 0));
		
		getContentPane().add(jsp, "Center");

		setSize(500, 282);
		setVisible(true);

		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 이벤트등록
		insert.addActionListener(this);
		manager.addActionListener(this);
		update.addActionListener(this);
		delete.addActionListener(this);
		quit.addActionListener(this);
		id.addActionListener(this);
		mname.addActionListener(this);
		mgrade.addActionListener(this);
		//수강신청 메뉴
		subject.addActionListener(this);
		grade.addActionListener(this);
		maxnum.addActionListener(this);
		request.addActionListener(this);
		snum.addActionListener(this);
		login.addActionListener(this);
		insertsubject.addActionListener(this);
		psubject.addActionListener(this);
		completesubject.addActionListener(this);
		image.addActionListener(this);
		deletesubject.addActionListener(this);
		/*// 모든레코드를 검색하여 DefaultTableModle에 올리기
		dao.userSelectAll(dt);
		
		//첫번행 선택.
		if (dt.getRowCount() > 0)
			jt.setRowSelectionInterval(0, 0);
*/
	}// 생성자끝

	/**
	 * main메소드 작성
	 */
	public static void main(String[] args) {
		
		new MenuJTabaleExam();
	}

	/**
	 * 가입/수정/삭제/검색기능을 담당하는 메소드
	 * */
	  public void Play(String fileName)
	    {
	        try
	        {
	            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(fileName));
	            Clip clip = AudioSystem.getClip();
	            clip.open(ais);

	            clip.start();
	        }
	        catch (Exception ex)
	        {
	        }
	    }
////////////////////////////////////////////////////////////////로그인 메뉴
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == insert) {// 가입 메뉴아이템 클릭
			Play("C:/eclipse/Pen_Clicking.wav");
			
			new UserJDailogGUI(this, "가입");

		}else if (e.getSource() == manager) {//  관리자 아이템
			Play("C:/eclipse/Pen_Clicking.wav");
			new ManagerGUI(this, "관리자");/////////////////////////////////////////////////매니저 GUI창 호출
			
		}
		else if (e.getSource() == login) {// 로그인 메뉴아이템 클릭
			Play("C:/eclipse/Pen_Clicking.wav");
			new UserLogin(this, "학생");/////////////////////////////////////////////////학색 GUI창 호출
		}  
		else if (e.getSource() == update) {// 수정 메뉴아이템 클릭
			Play("C:/eclipse/Pen_Clicking.wav");
			new UserJDailogGUI(this, "수정");
		}		
		else if (e.getSource() == delete) {// 삭제 메뉴아이템 클릭
			// 현재 Jtable의 선택된 행과 열의 값을 얻어온다.
			Play("C:/eclipse/Pen_Clicking.wav");
			jt.setModel(dt2);
			int row = jt.getSelectedRow();
			System.out.println("선택행 : " + row);

			Object obj = jt.getValueAt(row, 0);// 행 열에 해당하는 value
			System.out.println("값 : " + obj);

			if (dao.userDelete(obj.toString()) > 0) {
				UserJDailogGUI.messageBox(this, "레코드 삭제되었습니다.");			
				//리스트 갱신
				dao.userSelectAll(dt2);
				if (dt2.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);
			} else {
				UserJDailogGUI.messageBox(this, "레코드가 삭제되지 않았습니다.");
			}

		} else if (e.getSource() == id){
			Play("C:/eclipse/Pen_Clicking.wav");
			jt.setModel(dt2);
			dao.userSelectId(dt2);
			if (dt2.getRowCount() > 0)
				jt.setRowSelectionInterval(0, 0);
		} else if (e.getSource() == mname){
			Play("C:/eclipse/Pen_Clicking.wav");
			jt.setModel(dt2);
			dao.userSelectName(dt2);
			if (dt2.getRowCount() > 0)
				jt.setRowSelectionInterval(0, 0);
		} else if (e.getSource() == mgrade){
			Play("C:/eclipse/Pen_Clicking.wav");
			jt.setModel(dt2);
			dao.userSelectGrade(dt2);
			if (dt2.getRowCount() > 0)
				jt.setRowSelectionInterval(0, 0);
		} 
		else if (e.getSource() == quit) {// 종료 메뉴아이템 클릭
			Play("C:/eclipse/Pen_Clicking.wav");
			System.exit(0);
////////////////////////////////////////////////////////////////수강신청 메뉴
		}else if (e.getSource() == snum) {
			Play("C:/eclipse/Pen_Clicking.wav");
			jt.setModel(dt3);
			dao.SelectSnum(dt3);
		}
		else if (e.getSource() == subject) {//과목별 정렬
			Play("C:/eclipse/Pen_Clicking.wav");
			jt.setModel(dt3);
			dao.SelectSubject(dt3);
		}else if (e.getSource() == grade) {//학년별 정렬
			Play("C:/eclipse/Pen_Clicking.wav");
			jt.setModel(dt3);
			dao.SelectGrade(dt3);
		}else if (e.getSource() == maxnum) {//정원에따라 정렬
			Play("C:/eclipse/Pen_Clicking.wav");
			jt.setModel(dt3);
			dao.SelectMaxnum(dt3);
		}else if (e.getSource() == psubject) {// 관리자 메뉴아이템 클릭
			Play("C:/eclipse/Pen_Clicking.wav");
			jt.setModel(dt);
			dao.psubjectSelect(loginname.getText(),dt);
			
		}
		else if (e.getSource() == request) {// 수강신청 메뉴의 수강신청하기 아이템
			Play("C:/eclipse/Pen_Clicking.wav");
			
			new requestGUI(this, "수강신청하기");

		}else if (e.getSource() == completesubject) {// 관리자 메뉴아이템 클릭
			Play("C:/eclipse/Pen_Clicking.wav");
			jt.setModel(dt);
			dao.completeSubject(loginname.getText(),dt);
			
		}else if (e.getSource() == image) {// 교과서
			Play("C:/eclipse/Pen_Clicking.wav");
			new showpictureDialog(this, "교과서");
			
		}
		else if (e.getSource() == insertsubject) {// 수강신청 메뉴의 강의입력 아이템
			Play("C:/eclipse/Pen_Clicking.wav");
			new SubjectAddDialog(this, "강의입력(관리자)");

		}else if (e.getSource() == deletesubject) {// 삭제 메뉴아이템 클릭
			// 현재 Jtable의 선택된 행과 열의 값을 얻어온다.
			Play("C:/eclipse/Pen_Clicking.wav");
			jt.setModel(dt3);
			int row = jt.getSelectedRow();
			System.out.println("선택행 : " + row);

			Object obj = jt.getValueAt(row, 0);// 행 열에 해당하는 value
			System.out.println("값 : " + obj);

			if (dao.subjectDelete(obj.toString()) > 0) {
				UserJDailogGUI.messageBox(this, "레코드 삭제되었습니다.");			
				//리스트 갱신
				dao.subjectSelectAll(dt3);
				if (dt3.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);
			} else {
				UserJDailogGUI.messageBox(this, "레코드가 삭제되지 않았습니다.");
			}

		}

	}//actionPerformed()----------
}