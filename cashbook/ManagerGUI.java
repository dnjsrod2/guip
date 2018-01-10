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
/////////////////////////////////////////////////////////////////////������ �α���
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
	

	JButton confirm=new JButton("�α���");
	JButton reset=new JButton("���");

   MenuJTabaleExam me;
   
   UserDefaultJTableDAO dao =new UserDefaultJTableDAO();
   private final JPasswordField passwordField = new JPasswordField();
   

	public ManagerGUI(MenuJTabaleExam me, String index){
		super(me,"���̾�α�");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dnjsr\\Desktop\\181562711.jpg"));
		setBackground(new Color(255, 228, 225));
		setTitle("\uAD00\uB9AC\uC790 \uB85C\uADF8\uC778");
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
		
		setSize(300,161);
		setVisible(true);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		//�̺�Ʈ���
        confirm.addActionListener(this); //����/���� �̺�Ʈ���
        reset.addActionListener(this);
		
	}//�����ڳ�
    
	/**
	 * ����/����/���� ��ɿ� ���� �κ�
	 * */
	@Override
	public void actionPerformed(ActionEvent e) {
	   String btnLabel =e.getActionCommand();//�̺�Ʈ��ü ���� Label ��������
	   //////////////////////////////////////�����ڰ� �α��� �� ��쿡�� ������ ������ ���̺� ���� ����Ǿ����� �ʱ� ������ �ؽ�Ʈ�ʵ�� �н������ʵ忡 �ִ� ���� ���� ���Ѵ�.
	   if(btnLabel.equals("�α���")){
		   me.Play("C:/eclipse/Pen_Clicking.wav");
		   if(id.getText().equals("root")&&String.valueOf(passwordField.getPassword()).equals("1234")){//�α��μ���
			   me.Play("C:/eclipse/Pen_Clicking.wav");
			   messageBox(this , "������ �α���");
			   MenuJTabaleExam.loginname.setText("������");
			   //MenuJTabaleExam.psubject.setEnabled(false);
			   //MenuJTabaleExam.completesubject.setEnabled(false);
			   //MenuJTabaleExam.request.setEnabled(false);
			   /////////////////////////////////////////////////������ �α����� ��� ��� �޴��������� �̿��� �� �ְ� ���ش�.
			   MenuJTabaleExam.sm.setEnabled(true);
			   MenuJTabaleExam.update.setEnabled(true);
			   MenuJTabaleExam.delete.setEnabled(true);
			   MenuJTabaleExam.id.setEnabled(true);
			   MenuJTabaleExam.mname.setEnabled(true);
			   MenuJTabaleExam.mgrade.setEnabled(true);
			   MenuJTabaleExam.insertsubject.setEnabled(true);
			   MenuJTabaleExam.deletesubject.setEnabled(true);
			   dispose();//JDialog â�ݱ�
			
			   
		   }else{//���Խ���
			   me.Play("C:/eclipse/Crash.wav");
			   messageBox(this,"�α��� ����.");
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