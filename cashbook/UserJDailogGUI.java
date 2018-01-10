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
//////////////////////////////////////////////////////////////////////ȸ������ �� ����

public class UserJDailogGUI extends JDialog implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel pw=new JPanel(new GridLayout(4,1));
	JPanel pc=new JPanel(new GridLayout(4,1));
	JPanel ps=new JPanel();

	JLabel lable_Id = new JLabel("ID");
	JLabel lable_Name=new JLabel("�̸�");
	JLabel lable_Grade=new JLabel("\uD559\uB144");
	JLabel lable_Password=new JLabel("\uBE44\uBC00\uBC88\uD638");


	JTextField id=new JTextField();
	JTextField name=new JTextField();
	JTextField grade=new JTextField();
	JTextField userpassword=new JTextField();
	

	JButton confirm;
	JButton reset=new JButton("���");

   MenuJTabaleExam me;

   JPanel idCkP =new JPanel(new BorderLayout());
   JButton idCkBtn = new JButton("IDCheck");
   
   UserDefaultJTableDAO dao =new UserDefaultJTableDAO();
   

	public UserJDailogGUI(MenuJTabaleExam me, String index){
		super(me,"���̾�α�");
		setTitle("\uD68C\uC6D0\uAC00\uC785");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dnjsr\\Desktop\\181562711.jpg"));
		setForeground(new Color(255, 255, 255));
		idCkBtn.setBackground(Color.cyan);
		reset.setBackground(Color.WHITE);
		lable_Id.setBackground(Color.cyan);
		this.me=me;
		if(index.equals("����")){
			confirm=new JButton(index);
		}else{
			confirm=new JButton("����");	
			
			//text�ڽ��� ���õ� ���ڵ��� ���� �ֱ�
			int row = me.jt.getSelectedRow();//���õ� ��
			id.setText( me.jt.getValueAt(row, 0).toString() );
			name.setText( me.jt.getValueAt(row, 1).toString() );
			grade.setText( me.jt.getValueAt(row, 2).toString() );
			userpassword.setText( me.jt.getValueAt(row, 3).toString() );
			
			//id text�ڽ� ��Ȱ��
			id.setEditable(false);
	
			//IDCheck��ư ��Ȱ��ȭ
			idCkBtn.setEnabled(false);
		}
		lable_Id.setBackground(new Color(255, 255, 255));
		
		
		//Label�߰��κ�
		pw.add(lable_Id);//ID
		lable_Name.setBackground(new Color(255, 255, 255));
		pw.add(lable_Name);//�̸�
		pw.add(lable_Grade);//����
		pw.add(lable_Password);//�ּ�
	
		
		idCkP.add(id,"Center");
		idCkBtn.setBackground(new Color(255, 248, 220));
		idCkP.add(idCkBtn,"East");
		
		//TextField �߰�
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
		
		//�̺�Ʈ���
        confirm.addActionListener(this); //����/���� �̺�Ʈ���
        reset.addActionListener(this); //��� �̺�Ʈ���
        idCkBtn.addActionListener(this);// ID�ߺ�üũ �̺�Ʈ ���
		
	}//�����ڳ�
    
	/**
	 * ����/����/���� ��ɿ� ���� �κ�
	 * */
	@Override
	public void actionPerformed(ActionEvent e) {
	   String btnLabel =e.getActionCommand();//�̺�Ʈ��ü ���� Label ��������
	    
	   if(btnLabel.equals("����")){
		   
		   if(dao.userListInsert(this) > 0 ){//���Լ���
			   me.Play("C:/eclipse/Pen_Clicking.wav");
			   messageBox(this , name.getText()+"�� ������帳�ϴ�.");
			   dispose();//JDialog â�ݱ�
			   
			   //dao.userSelectAll(me.dt);//��緹�ڵ尡���ͼ� DefaultTableModel�� �ø���
			   
			   if(me.dt.getRowCount() > 0)
				   me.jt.setRowSelectionInterval(0, 0);//ù��° �� ����
			   
		   }else{//���Խ���
			   me.Play("C:/eclipse/Crash.wav");
			   messageBox(this,"���Ե��� �ʾҽ��ϴ�.");
		   }
		   
	   }else if(btnLabel.equals("����")){
		   
		    if( dao.userUpdate(this) > 0){
		    	me.Play("C:/eclipse/Pen_Clicking.wav");
		    	messageBox(this, "�����Ϸ�Ǿ����ϴ�.");
		    	dispose();
		    	//dao.userSelectAll(me.dt);
		    	if(me.dt.getRowCount() > 0 ) me.jt.setRowSelectionInterval(0, 0);
		    	
		    }else{
		    	me.Play("C:/eclipse/Crash.wav");
		    	messageBox(this, "�������� �ʾҽ��ϴ�.");
		    }
		   
		   
		   
	   }else if(btnLabel.equals("���")){
		   dispose();
		   
	   }else if(btnLabel.equals("IDCheck")){
		   //id�ؽ�Ʈ�ڽ��� �� ������ �޼��� ��� ������ DB�����Ѵ�.
		   if(id.getText().trim().equals("")){
			   messageBox(this,"ID�� �Է����ּ���.");
			   me.Play("C:/eclipse/Crash.wav");
			   id.requestFocus();//��Ŀ���̵�
		   }else{
			   
			  if(  dao.getIdByCheck(id.getText()) ){ //�ߺ��ƴϴ�.(��밡��)
				  me.Play("C:/eclipse/Pen_Clicking.wav");
				  messageBox(this, id.getText()+"�� ��밡���մϴ�.");  
			  }else{ //�ߺ��̴�.
				  messageBox(this,id.getText()+"�� �ߺ��Դϴ�.");
				  me.Play("C:/eclipse/Crash.wav");
				  id.setText("");//text�ڽ������
				  
				  id.requestFocus();//Ŀ������
			  }
			   
		   }
		   
	   }
	   
		
	}//actionPerformed��
	
	/**
	 * �޽����ڽ� ����ִ� �޼ҵ� �ۼ�
	 * */
	public static void messageBox(Object obj , String message){
		JOptionPane.showMessageDialog( (Component)obj , message);
	}

}//Ŭ������