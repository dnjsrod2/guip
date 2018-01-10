package cashbook;
///////////////////////////////////////////////////////////강의등록(관리자)
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
import java.awt.Toolkit;


public class SubjectAddDialog extends JDialog implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel pw=new JPanel(new GridLayout(5,1));
	JPanel pc=new JPanel(new GridLayout(5,1));
	JPanel ps=new JPanel();

	JLabel lable_name = new JLabel("\uD559\uC218\uBC88\uD638");
	JLabel lable_Name=new JLabel("\uAC15\uC758\uBA85");
	JLabel lable_grade=new JLabel("\uD559\uB144");
	JLabel lable_maxnum=new JLabel("\uC815\uC6D0");


	JTextField snum=new JTextField();
	static JTextField subject=new JTextField();
	JTextField grade=new JTextField();
	JTextField maxnum=new JTextField();
	

	JButton confirm=new JButton("등록");
	JButton reset=new JButton("취소");

   MenuJTabaleExam me;

   JPanel idCkP =new JPanel(new BorderLayout());
   
   UserDefaultJTableDAO dao =new UserDefaultJTableDAO();
   private final JLabel lable_pnum = new JLabel("\uD604\uC7AC\uC778\uC6D0");
   static JTextField pnum = new JTextField();
   private final JButton image = new JButton("\uCC45 \uC815\uBCF4");
   

	public SubjectAddDialog(MenuJTabaleExam me, String index){
		super(me,"다이어로그");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dnjsr\\Desktop\\181562711.jpg"));
		pnum.setColumns(10);
		setTitle("\uAC15\uC758\uB4F1\uB85D(\uAD00\uB9AC\uC790)");
		this.me=me;
		
		
		//Label추가부분
		pw.add(lable_name);//학수번호
		pw.add(lable_Name);//과목
		pw.add(lable_grade);//학년
		pw.add(lable_maxnum);//정원
		pw.add(lable_pnum);//현재인원
		
		idCkP.add(snum,"Center");
		
		//TextField 추가
		pc.add(idCkP);
		pc.add(subject);
		pc.add(grade);
		pc.add(maxnum);
		
		ps.add(image);
		
		
		
		ps.add(confirm); 
		ps.add(reset);
	
		getContentPane().add(pw,"West"); 
		
		pw.add(lable_pnum);
		getContentPane().add(pc,"Center");
		
		pc.add(pnum);
		getContentPane().add(ps,"South");
		
		setSize(300,250);
		setVisible(true);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		//이벤트등록
        confirm.addActionListener(this); //가입/수정 이벤트등록
        reset.addActionListener(this);
		image.addActionListener(this);
	}//생성자끝
    
	/**
	 * 가입/수정/삭제 기능에 대한 부분
	 * */
	@Override
	public void actionPerformed(ActionEvent e) {
	   String btnLabel =e.getActionCommand();//이벤트주체 대한 Label 가져오기
	    
	   if(btnLabel.equals("등록")){ 
		   me.Play("C:/eclipse/Pen_Clicking.wav");
		   if(dao.subjectInsert(this) > 0 ){//가입성공
			   me.Play("C:/eclipse/Pen_Clicking.wav");
			   messageBox(this , "등록되었습니다.");
			   dispose();//JDialog 창닫기
			   
			   //dao.userSelectAll(me.dt);//모든레코드가져와서 DefaultTableModel에 올리기
			   
			   if(me.dt.getRowCount() > 0)
				   me.jt.setRowSelectionInterval(0, 0);//첫번째 행 선택
			   
		   }else{//가입실패
			   me.Play("C:/eclipse/Crash.wav");
			   messageBox(this,"등록 실패.");
		   }
		   
	   }else if(btnLabel.equals("책 정보")){
		   me.Play("C:/eclipse/Pen_Clicking.wav");
		   new picture();
			   
			   //JDialog 창닫기
		   
	   }
	   else if(btnLabel.equals("취소")){
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