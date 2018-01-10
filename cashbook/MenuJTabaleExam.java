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
	JMenuItem insert = new JMenuItem("����");
	static JMenuItem update = new JMenuItem("����");
	static JMenuItem delete = new JMenuItem("����");
	
	JMenuItem quit = new JMenuItem("����");
	static JMenuBar mb = new JMenuBar();

	static String[] name = { "�̸�","�м���ȣ","����","�г�","����","�����ο�" };
	static String[] sname = {"���̵�","�̸�","�г�","��й�ȣ"};
	static String[] dname= {"�м���ȣ","����","�г�","����","�����ο�" };
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
	 * ȭ�鱸�� �� �̺�Ʈ���
	 * @return 
	 */
	
	
	public MenuJTabaleExam() {
		
		super("GUI ȸ���������α׷� - DB����");
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

		//�޴��������� �޴��� �߰�
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
		//�޴��� �޴��ٿ� �߰�
		mb.add(m);
		
		//�����쿡 �޴��� ����
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

		// �̺�Ʈ���
		insert.addActionListener(this);
		manager.addActionListener(this);
		update.addActionListener(this);
		delete.addActionListener(this);
		quit.addActionListener(this);
		id.addActionListener(this);
		mname.addActionListener(this);
		mgrade.addActionListener(this);
		//������û �޴�
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
		/*// ��緹�ڵ带 �˻��Ͽ� DefaultTableModle�� �ø���
		dao.userSelectAll(dt);
		
		//ù���� ����.
		if (dt.getRowCount() > 0)
			jt.setRowSelectionInterval(0, 0);
*/
	}// �����ڳ�

	/**
	 * main�޼ҵ� �ۼ�
	 */
	public static void main(String[] args) {
		
		new MenuJTabaleExam();
	}

	/**
	 * ����/����/����/�˻������ ����ϴ� �޼ҵ�
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
////////////////////////////////////////////////////////////////�α��� �޴�
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == insert) {// ���� �޴������� Ŭ��
			Play("C:/eclipse/Pen_Clicking.wav");
			
			new UserJDailogGUI(this, "����");

		}else if (e.getSource() == manager) {//  ������ ������
			Play("C:/eclipse/Pen_Clicking.wav");
			new ManagerGUI(this, "������");/////////////////////////////////////////////////�Ŵ��� GUIâ ȣ��
			
		}
		else if (e.getSource() == login) {// �α��� �޴������� Ŭ��
			Play("C:/eclipse/Pen_Clicking.wav");
			new UserLogin(this, "�л�");/////////////////////////////////////////////////�л� GUIâ ȣ��
		}  
		else if (e.getSource() == update) {// ���� �޴������� Ŭ��
			Play("C:/eclipse/Pen_Clicking.wav");
			new UserJDailogGUI(this, "����");
		}		
		else if (e.getSource() == delete) {// ���� �޴������� Ŭ��
			// ���� Jtable�� ���õ� ��� ���� ���� ���´�.
			Play("C:/eclipse/Pen_Clicking.wav");
			jt.setModel(dt2);
			int row = jt.getSelectedRow();
			System.out.println("������ : " + row);

			Object obj = jt.getValueAt(row, 0);// �� ���� �ش��ϴ� value
			System.out.println("�� : " + obj);

			if (dao.userDelete(obj.toString()) > 0) {
				UserJDailogGUI.messageBox(this, "���ڵ� �����Ǿ����ϴ�.");			
				//����Ʈ ����
				dao.userSelectAll(dt2);
				if (dt2.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);
			} else {
				UserJDailogGUI.messageBox(this, "���ڵ尡 �������� �ʾҽ��ϴ�.");
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
		else if (e.getSource() == quit) {// ���� �޴������� Ŭ��
			Play("C:/eclipse/Pen_Clicking.wav");
			System.exit(0);
////////////////////////////////////////////////////////////////������û �޴�
		}else if (e.getSource() == snum) {
			Play("C:/eclipse/Pen_Clicking.wav");
			jt.setModel(dt3);
			dao.SelectSnum(dt3);
		}
		else if (e.getSource() == subject) {//���� ����
			Play("C:/eclipse/Pen_Clicking.wav");
			jt.setModel(dt3);
			dao.SelectSubject(dt3);
		}else if (e.getSource() == grade) {//�г⺰ ����
			Play("C:/eclipse/Pen_Clicking.wav");
			jt.setModel(dt3);
			dao.SelectGrade(dt3);
		}else if (e.getSource() == maxnum) {//���������� ����
			Play("C:/eclipse/Pen_Clicking.wav");
			jt.setModel(dt3);
			dao.SelectMaxnum(dt3);
		}else if (e.getSource() == psubject) {// ������ �޴������� Ŭ��
			Play("C:/eclipse/Pen_Clicking.wav");
			jt.setModel(dt);
			dao.psubjectSelect(loginname.getText(),dt);
			
		}
		else if (e.getSource() == request) {// ������û �޴��� ������û�ϱ� ������
			Play("C:/eclipse/Pen_Clicking.wav");
			
			new requestGUI(this, "������û�ϱ�");

		}else if (e.getSource() == completesubject) {// ������ �޴������� Ŭ��
			Play("C:/eclipse/Pen_Clicking.wav");
			jt.setModel(dt);
			dao.completeSubject(loginname.getText(),dt);
			
		}else if (e.getSource() == image) {// ������
			Play("C:/eclipse/Pen_Clicking.wav");
			new showpictureDialog(this, "������");
			
		}
		else if (e.getSource() == insertsubject) {// ������û �޴��� �����Է� ������
			Play("C:/eclipse/Pen_Clicking.wav");
			new SubjectAddDialog(this, "�����Է�(������)");

		}else if (e.getSource() == deletesubject) {// ���� �޴������� Ŭ��
			// ���� Jtable�� ���õ� ��� ���� ���� ���´�.
			Play("C:/eclipse/Pen_Clicking.wav");
			jt.setModel(dt3);
			int row = jt.getSelectedRow();
			System.out.println("������ : " + row);

			Object obj = jt.getValueAt(row, 0);// �� ���� �ش��ϴ� value
			System.out.println("�� : " + obj);

			if (dao.subjectDelete(obj.toString()) > 0) {
				UserJDailogGUI.messageBox(this, "���ڵ� �����Ǿ����ϴ�.");			
				//����Ʈ ����
				dao.subjectSelectAll(dt3);
				if (dt3.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);
			} else {
				UserJDailogGUI.messageBox(this, "���ڵ尡 �������� �ʾҽ��ϴ�.");
			}

		}

	}//actionPerformed()----------
}