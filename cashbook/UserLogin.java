package cashbook;
////////////////////////////////////////////////////////////////////�л� �α���
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
	

	JButton confirm=new JButton("�α���");
	JButton reset=new JButton("���");

   MenuJTabaleExam me;
   
   UserDefaultJTableDAO dao =new UserDefaultJTableDAO();
   private final JPasswordField passwordField = new JPasswordField();
   

	public UserLogin(MenuJTabaleExam me, String index){
		super(me,"���̾�α�");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dnjsr\\Desktop\\181562711.jpg"));
		setTitle("\uD559\uC0DD");
		this.me=me;
		
		pw.add(lable_Name);//�̸�
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
		
		//�̺�Ʈ���
        confirm.addActionListener(this); //����/���� �̺�Ʈ���
        reset.addActionListener(this);
		
	}//�����ڳ�
    
	
	@Override
	public void actionPerformed(ActionEvent e) {
	   String btnLabel =e.getActionCommand();//�̺�Ʈ��ü ���� Label ��������
	    
	   if(btnLabel.equals("�α���")){ 
		   me.Play("C:/eclipse/Pen_Clicking.wav");
		   if(dao.loginCheck(id.getText(),String.valueOf(passwordField.getPassword()))){
			   /////////////////////////////////////////////////DAOŬ������ logincheck()�Լ��� ȣ�� �Ķ���� ������ id�ʵ忡 �ִ� ���� �н������ʵ忡 �ִ� ���� �ѱ��
			   me.Play("C:/eclipse/Crash.wav");
			   messageBox(this , "id�� �н����带 Ȯ���ϼ���");
			   
		   }else{
			   me.Play("C:/eclipse/Pen_Clicking.wav");
			   messageBox(this , "�α��� ����");
			   /////////////////////////////////////////////////
			   MenuJTabaleExam.loginname.setText(id.getText());//������û �޴� ��ư ���� �󺧿� id�ʵ忡 �ִ°��� �ø���
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
	   }else if(btnLabel.equals("���")){
		   me.Play("C:/eclipse/Pen_Clicking.wav");
		   dispose();
		   
	   }
			   
		   
		   
	   
	    
		
	}//actionPerformed��
	
	/**
	 * �޽����ڽ� ����ִ� �޼ҵ� �ۼ�
	 * */
	public static void messageBox(Object obj , String message){
		JOptionPane.showMessageDialog( (Component)obj , message);
	}

}//Ŭ������