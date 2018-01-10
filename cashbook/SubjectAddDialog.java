package cashbook;
///////////////////////////////////////////////////////////���ǵ��(������)
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
	

	JButton confirm=new JButton("���");
	JButton reset=new JButton("���");

   MenuJTabaleExam me;

   JPanel idCkP =new JPanel(new BorderLayout());
   
   UserDefaultJTableDAO dao =new UserDefaultJTableDAO();
   private final JLabel lable_pnum = new JLabel("\uD604\uC7AC\uC778\uC6D0");
   static JTextField pnum = new JTextField();
   private final JButton image = new JButton("\uCC45 \uC815\uBCF4");
   

	public SubjectAddDialog(MenuJTabaleExam me, String index){
		super(me,"���̾�α�");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dnjsr\\Desktop\\181562711.jpg"));
		pnum.setColumns(10);
		setTitle("\uAC15\uC758\uB4F1\uB85D(\uAD00\uB9AC\uC790)");
		this.me=me;
		
		
		//Label�߰��κ�
		pw.add(lable_name);//�м���ȣ
		pw.add(lable_Name);//����
		pw.add(lable_grade);//�г�
		pw.add(lable_maxnum);//����
		pw.add(lable_pnum);//�����ο�
		
		idCkP.add(snum,"Center");
		
		//TextField �߰�
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
		
		//�̺�Ʈ���
        confirm.addActionListener(this); //����/���� �̺�Ʈ���
        reset.addActionListener(this);
		image.addActionListener(this);
	}//�����ڳ�
    
	/**
	 * ����/����/���� ��ɿ� ���� �κ�
	 * */
	@Override
	public void actionPerformed(ActionEvent e) {
	   String btnLabel =e.getActionCommand();//�̺�Ʈ��ü ���� Label ��������
	    
	   if(btnLabel.equals("���")){ 
		   me.Play("C:/eclipse/Pen_Clicking.wav");
		   if(dao.subjectInsert(this) > 0 ){//���Լ���
			   me.Play("C:/eclipse/Pen_Clicking.wav");
			   messageBox(this , "��ϵǾ����ϴ�.");
			   dispose();//JDialog â�ݱ�
			   
			   //dao.userSelectAll(me.dt);//��緹�ڵ尡���ͼ� DefaultTableModel�� �ø���
			   
			   if(me.dt.getRowCount() > 0)
				   me.jt.setRowSelectionInterval(0, 0);//ù��° �� ����
			   
		   }else{//���Խ���
			   me.Play("C:/eclipse/Crash.wav");
			   messageBox(this,"��� ����.");
		   }
		   
	   }else if(btnLabel.equals("å ����")){
		   me.Play("C:/eclipse/Pen_Clicking.wav");
		   new picture();
			   
			   //JDialog â�ݱ�
		   
	   }
	   else if(btnLabel.equals("���")){
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