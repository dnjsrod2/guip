package cashbook;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Toolkit;

//////////////////////////////////////////////////////////////////////////////수강신청하기 gui
public class requestGUI extends JDialog implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel pw=new JPanel(new GridLayout(4,1));
	JPanel pc=new JPanel(new GridLayout(4,1));
	JPanel ps=new JPanel();

	JLabel lable_name = new JLabel("\uC774\uB984");


	static JTextField id=new JTextField();
	

	JButton confirm=new JButton("등록");
	JButton reset=new JButton("취소");

   MenuJTabaleExam me;

   JPanel idCkP =new JPanel(new BorderLayout());
   
   UserDefaultJTableDAO dao =new UserDefaultJTableDAO();
   private final JLabel label = new JLabel("\uD559\uC218\uBC88\uD638");
   private final JLabel label_1 = new JLabel("\uACFC\uBAA9");
   private final JLabel label_2 = new JLabel("\uD559\uB144");
   static  JTextField snum = new JTextField();
   static  JTextField subject = new JTextField();
   static  JTextField grade = new JTextField();
   

	public requestGUI(MenuJTabaleExam me, String index){
		super(me,"다이어로그");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dnjsr\\Desktop\\181562711.jpg"));
		setTitle("\uC218\uAC15\uC2E0\uCCAD");
		this.me=me;
		int row = me.jt.getSelectedRow();//선택된 행
		id.setText( me.jt.getValueAt(row, 0).toString() );
		snum.setText( me.jt.getValueAt(row, 1).toString() );
		subject.setText( me.jt.getValueAt(row, 2).toString() );
		grade.setText( me.jt.getValueAt(row, 3).toString() );
		//maxnum.setText( me.jt.getValueAt(row, 4).toString() );
		//pnum.setText( me.jt.getValueAt(row, 5).toString() );
		
		//Label추가부분
		pw.add(lable_name);
	
		
		idCkP.add(id,"Center");
		
		//TextField 추가
		pc.add(idCkP);
		
		
		
		ps.add(confirm); 
		ps.add(reset);
	
		getContentPane().add(pw,"West"); 
		
		pw.add(label);
		
		pw.add(label_1);
		
		pw.add(label_2);
		getContentPane().add(pc,"Center");
		
		pc.add(snum);
		
		pc.add(subject);
		
		pc.add(grade);
		getContentPane().add(ps,"South");
		
		setSize(300,250);
		setVisible(true);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		//이벤트등록
        confirm.addActionListener(this); //가입/수정 이벤트등록
        reset.addActionListener(this);
		
	}//생성자끝
    
	/**
	 * 가입/수정/삭제 기능에 대한 부분
	 * */
	@Override
	public void actionPerformed(ActionEvent e) {
	   String btnLabel =e.getActionCommand();//이벤트주체 대한 Label 가져오기
	    
	   if(btnLabel.equals("등록")){
		   me.Play("C:/eclipse/Pen_Clicking.wav");
		   if(dao.subjectlistUpdate(this) > 0 ){//가입성공
			   
			   me.Play("C:/eclipse/Pen_Clicking.wav");
			   me.dt.removeRow(me.jt.getSelectedRow());
			   dispose();//JDialog 창닫기
			   
			   //dao.userSelectAll(me.dt);//모든레코드가져와서 DefaultTableModel에 올리기
			   
			   if(me.dt.getRowCount() > 0)
				   me.jt.setRowSelectionInterval(0, 0);//첫번째 행 선택
			   
		   }else{//가입실패
			   me.Play("C:/eclipse/Crash.wav");
			   messageBox(this,"등록 실패.");
		   }
		   
	   }else if(btnLabel.equals("취소")){
		   me.Play("C:/eclipse/Pen_Clicking.wav");
		   dispose();
		   
	   }
		
	}//actionPerformed끝
	
	/**
	 * 메시지박스 띄워주는 메소드 작성
	 * */
	public static void messageBox(Object obj , String message){
		JOptionPane.showMessageDialog( (Component)obj , message);
	}

	public static void messageBox(Object obj, SQLException e) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog( (Component) obj, e);
	}

}//클래스끝