package cashbook;
////////////////////////////////////////////////////////////////////학생 로그인
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
import java.awt.Toolkit;

public class UserLogin extends JDialog implements ActionListener{
	
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
   

	public UserLogin(MenuJTabaleExam me, String index){
		super(me,"다이어로그");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dnjsr\\Desktop\\181562711.jpg"));
		setTitle("\uD559\uC0DD");
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
		
		setSize(300,148);
		setVisible(true);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		//이벤트등록
        confirm.addActionListener(this); //가입/수정 이벤트등록
        reset.addActionListener(this);
		
	}//생성자끝
    
	
	@Override
	public void actionPerformed(ActionEvent e) {
	   String btnLabel =e.getActionCommand();//이벤트주체 대한 Label 가져오기
	    
	   if(btnLabel.equals("로그인")){ 
		   me.Play("C:/eclipse/Pen_Clicking.wav");
		   if(dao.loginCheck(id.getText(),String.valueOf(passwordField.getPassword()))){
			   /////////////////////////////////////////////////DAO클래스의 logincheck()함수를 호출 파라미터 값으로 id필드에 있는 값과 패스워드필드에 있는 값을 넘긴다
			   me.Play("C:/eclipse/Crash.wav");
			   messageBox(this , "id와 패스워드를 확인하세요");
			   
		   }else{
			   me.Play("C:/eclipse/Pen_Clicking.wav");
			   messageBox(this , "로그인 성공");
			   /////////////////////////////////////////////////
			   MenuJTabaleExam.loginname.setText(id.getText());//수강신청 메뉴 버튼 옆의 라벨에 id필드에 있는값을 올린다
			   MenuJTabaleExam.update.setEnabled(false);
			   MenuJTabaleExam.delete.setEnabled(false);
			   MenuJTabaleExam.id.setEnabled(false);
			   MenuJTabaleExam.mname.setEnabled(false);
			   MenuJTabaleExam.mgrade.setEnabled(false);
			   MenuJTabaleExam.sm.setEnabled(true);
			   MenuJTabaleExam.insertsubject.setEnabled(false);
			   MenuJTabaleExam.deletesubject.setEnabled(false);
			   
			   dispose();
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