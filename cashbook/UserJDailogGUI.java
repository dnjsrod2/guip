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
import java.awt.Color;
import java.awt.Toolkit;
//////////////////////////////////////////////////////////////////////회원가입 및 수정

public class UserJDailogGUI extends JDialog implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel pw=new JPanel(new GridLayout(4,1));
	JPanel pc=new JPanel(new GridLayout(4,1));
	JPanel ps=new JPanel();

	JLabel lable_Id = new JLabel("ID");
	JLabel lable_Name=new JLabel("이름");
	JLabel lable_Grade=new JLabel("\uD559\uB144");
	JLabel lable_Password=new JLabel("\uBE44\uBC00\uBC88\uD638");


	JTextField id=new JTextField();
	JTextField name=new JTextField();
	JTextField grade=new JTextField();
	JTextField userpassword=new JTextField();
	

	JButton confirm;
	JButton reset=new JButton("취소");

   MenuJTabaleExam me;

   JPanel idCkP =new JPanel(new BorderLayout());
   JButton idCkBtn = new JButton("IDCheck");
   
   UserDefaultJTableDAO dao =new UserDefaultJTableDAO();
   

	public UserJDailogGUI(MenuJTabaleExam me, String index){
		super(me,"다이어로그");
		setTitle("\uD68C\uC6D0\uAC00\uC785");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dnjsr\\Desktop\\181562711.jpg"));
		setForeground(new Color(255, 255, 255));
		idCkBtn.setBackground(Color.cyan);
		reset.setBackground(Color.WHITE);
		lable_Id.setBackground(Color.cyan);
		this.me=me;
		if(index.equals("가입")){
			confirm=new JButton(index);
		}else{
			confirm=new JButton("수정");	
			
			//text박스에 선택된 레코드의 정보 넣기
			int row = me.jt.getSelectedRow();//선택된 행
			id.setText( me.jt.getValueAt(row, 0).toString() );
			name.setText( me.jt.getValueAt(row, 1).toString() );
			grade.setText( me.jt.getValueAt(row, 2).toString() );
			userpassword.setText( me.jt.getValueAt(row, 3).toString() );
			
			//id text박스 비활성
			id.setEditable(false);
	
			//IDCheck버튼 비활성화
			idCkBtn.setEnabled(false);
		}
		lable_Id.setBackground(new Color(255, 255, 255));
		
		
		//Label추가부분
		pw.add(lable_Id);//ID
		lable_Name.setBackground(new Color(255, 255, 255));
		pw.add(lable_Name);//이름
		pw.add(lable_Grade);//나이
		pw.add(lable_Password);//주소
	
		
		idCkP.add(id,"Center");
		idCkBtn.setBackground(new Color(255, 248, 220));
		idCkP.add(idCkBtn,"East");
		
		//TextField 추가
		pc.add(idCkP);
		pc.add(name);
		pc.add(grade);
		pc.add(userpassword);
		
		
		
		ps.add(confirm); 
		ps.add(reset);
	
		getContentPane().add(pw,"West"); 
		getContentPane().add(pc,"Center");
		getContentPane().add(ps,"South");
		
		setSize(300,250);
		setVisible(true);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		//이벤트등록
        confirm.addActionListener(this); //가입/수정 이벤트등록
        reset.addActionListener(this); //취소 이벤트등록
        idCkBtn.addActionListener(this);// ID중복체크 이벤트 등록
		
	}//생성자끝
    
	/**
	 * 가입/수정/삭제 기능에 대한 부분
	 * */
	@Override
	public void actionPerformed(ActionEvent e) {
	   String btnLabel =e.getActionCommand();//이벤트주체 대한 Label 가져오기
	    
	   if(btnLabel.equals("가입")){
		   
		   if(dao.userListInsert(this) > 0 ){//가입성공
			   me.Play("C:/eclipse/Pen_Clicking.wav");
			   messageBox(this , name.getText()+"님 가입축드립니다.");
			   dispose();//JDialog 창닫기
			   
			   //dao.userSelectAll(me.dt);//모든레코드가져와서 DefaultTableModel에 올리기
			   
			   if(me.dt.getRowCount() > 0)
				   me.jt.setRowSelectionInterval(0, 0);//첫번째 행 선택
			   
		   }else{//가입실패
			   me.Play("C:/eclipse/Crash.wav");
			   messageBox(this,"가입되지 않았습니다.");
		   }
		   
	   }else if(btnLabel.equals("수정")){
		   
		    if( dao.userUpdate(this) > 0){
		    	me.Play("C:/eclipse/Pen_Clicking.wav");
		    	messageBox(this, "수정완료되었습니다.");
		    	dispose();
		    	//dao.userSelectAll(me.dt);
		    	if(me.dt.getRowCount() > 0 ) me.jt.setRowSelectionInterval(0, 0);
		    	
		    }else{
		    	me.Play("C:/eclipse/Crash.wav");
		    	messageBox(this, "수정되지 않았습니다.");
		    }
		   
		   
		   
	   }else if(btnLabel.equals("취소")){
		   dispose();
		   
	   }else if(btnLabel.equals("IDCheck")){
		   //id텍스트박스에 값 없으면 메세지 출력 있으면 DB연동한다.
		   if(id.getText().trim().equals("")){
			   messageBox(this,"ID를 입력해주세요.");
			   me.Play("C:/eclipse/Crash.wav");
			   id.requestFocus();//포커스이동
		   }else{
			   
			  if(  dao.getIdByCheck(id.getText()) ){ //중복아니다.(사용가능)
				  me.Play("C:/eclipse/Pen_Clicking.wav");
				  messageBox(this, id.getText()+"는 사용가능합니다.");  
			  }else{ //중복이다.
				  messageBox(this,id.getText()+"는 중복입니다.");
				  me.Play("C:/eclipse/Crash.wav");
				  id.setText("");//text박스지우기
				  
				  id.requestFocus();//커서놓기
			  }
			   
		   }
		   
	   }
	   
		
	}//actionPerformed끝
	
	/**
	 * 메시지박스 띄워주는 메소드 작성
	 * */
	public static void messageBox(Object obj , String message){
		JOptionPane.showMessageDialog( (Component)obj , message);
	}

}//클래스끝