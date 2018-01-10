package cashbook;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Toolkit;

/////////////////////////////////////////////////////////////////////관리자 로그인
public class showpictureDialog extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel pc = new JPanel(new GridLayout(1, 1));
	JPanel ps = new JPanel();

	JButton confirm = new JButton("확인");

	MenuJTabaleExam me;

	UserDefaultJTableDAO dao = new UserDefaultJTableDAO();
	static String snum;
	static int row;
	static JLabel image_l = new JLabel("");

	public showpictureDialog(MenuJTabaleExam me, String index) {
		super(me, "다이어로그");
		this.me=me;
		row = me.jt.getSelectedRow();//선택된 행
		snum=me.jt.getValueAt(row, 1).toString();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dnjsr\\Desktop\\181562711.jpg"));
		setBackground(new Color(255, 228, 225));
		setTitle("\uCC45 \uC0AC\uC9C4");
		this.me = me;
		image_l.setIcon(new ImageIcon(new BufferedImage(300, 300, Image.SCALE_SMOOTH)));
		dao.showpicture(snum);
		ps.add(confirm);
		getContentPane().add(pc, "Center");

		pc.add(image_l);
		getContentPane().add(ps, "South");
		
		setSize(900, 900);
		setVisible(true);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		// 이벤트등록
		confirm.addActionListener(this);
		
	}// 생성자끝

	/**
	 * 가입/수정/삭제 기능에 대한 부분
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String btnLabel = e.getActionCommand();// 이벤트주체 대한 Label 가져오기

		if (btnLabel.equals("확인")) {
			me.Play("C:/eclipse/Pen_Clicking.wav");
			
			 dispose();//JDialog 창닫기
			
		}

	}

	// actionPerformed끝

	/**
	 * 메시지박스 띄워주는 메소드 작성
	 */
	public static void messageBox(Object obj, String message) {
		JOptionPane.showMessageDialog((Component) obj, message);
	}

}// 클래스끝