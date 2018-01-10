package cashbook;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
public class ManagerGUI extends JDialog implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel pw=new JPanel(new GridLayout(2,2));
	JPanel pc=new JPanel(new GridLayout(2,2));
	JPanel ps=new JPanel();
	JLabel lable_Name=new JLabel("\uC544\uC774\uB514");
	JLabel lable_Grade=new JLabel("\uD328\uC2A4\uC6CC\uB4DC");
	JTextField id=new JTextField();
	

	JButton confirm=new JButton("로그인");
	JButton reset=new JButton("취소");

   MenuJTabaleExam me;
   
   UserDefaultJTableDAO dao =new UserDefaultJTableDAO();
   private final JPasswordField passwordField = new JPasswordField();
   

	public ManagerGUI(MenuJTabaleExam me, String index){
		super(me,"다이어로그");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dnjsr\\Desktop\\181562711.jpg"));
		setBackground(new Color(255, 228, 225));
		setTitle("\uAD00\uB9AC\uC790 \uB85C\uADF8\uC778");
		this.me=me;
		
		pw.add(lable_Name);//이름
		pw.add(lable_Grade);
		pc.add(id);
		
		
		
		ps.add(confirm); 
		ps.add(reset);
	
		getContentPane().add(pw,"West"); 
		getContentPane().add(pc,"Center");
		
		pc.add(passwordField);
		getContentPane().add(ps,"South");
		
		setSize(300,161);
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
	   //////////////////////////////////////관리자가 로그인 할 경우에는 관리자 정보가 테이블에 따로 저장되어있지 않기 때문에 텍스트필드와 패스워드필드에 있는 값과 직접 비교한다.
	   if(btnLabel.equals("로그인")){
		   me.Play("C:/eclipse/Pen_Clicking.wav");
		   if(id.getText().equals("root")&&String.valueOf(passwordField.getPassword()).equals("1234")){//로그인성공
			   me.Play("C:/eclipse/Pen_Clicking.wav");
			   messageBox(this , "관리자 로그인");
			   MenuJTabaleExam.loginname.setText("관리자");
			   //MenuJTabaleExam.psubject.setEnabled(false);
			   //MenuJTabaleExam.completesubject.setEnabled(false);
			   //MenuJTabaleExam.request.setEnabled(false);
			   /////////////////////////////////////////////////관리자 로그인일 경우 모든 메뉴아이템을 이용할 수 있게 해준다.
			   MenuJTabaleExam.sm.setEnabled(true);
			   MenuJTabaleExam.update.setEnabled(true);
			   MenuJTabaleExam.delete.setEnabled(true);
			   MenuJTabaleExam.id.setEnabled(true);
			   MenuJTabaleExam.mname.setEnabled(true);
			   MenuJTabaleExam.mgrade.setEnabled(true);
			   MenuJTabaleExam.insertsubject.setEnabled(true);
			   MenuJTabaleExam.deletesubject.setEnabled(true);
			   dispose();//JDialog 창닫기
			
			   
		   }else{//가입실패
			   me.Play("C:/eclipse/Crash.wav");
			   messageBox(this,"로그인 실패.");
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

}//클래스끝