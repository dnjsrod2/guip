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

//////////////////////////////////////////////////////////////////////////////������û�ϱ� gui
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
	

	JButton confirm=new JButton("���");
	JButton reset=new JButton("���");

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
		super(me,"���̾�α�");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dnjsr\\Desktop\\181562711.jpg"));
		setTitle("\uC218\uAC15\uC2E0\uCCAD");
		this.me=me;
		int row = me.jt.getSelectedRow();//���õ� ��
		id.setText( me.jt.getValueAt(row, 0).toString() );
		snum.setText( me.jt.getValueAt(row, 1).toString() );
		subject.setText( me.jt.getValueAt(row, 2).toString() );
		grade.setText( me.jt.getValueAt(row, 3).toString() );
		//maxnum.setText( me.jt.getValueAt(row, 4).toString() );
		//pnum.setText( me.jt.getValueAt(row, 5).toString() );
		
		//Label�߰��κ�
		pw.add(lable_name);
	
		
		idCkP.add(id,"Center");
		
		//TextField �߰�
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
	    
	   if(btnLabel.equals("���")){
		   me.Play("C:/eclipse/Pen_Clicking.wav");
		   if(dao.subjectlistUpdate(this) > 0 ){//���Լ���
			   
			   me.Play("C:/eclipse/Pen_Clicking.wav");
			   me.dt.removeRow(me.jt.getSelectedRow());
			   dispose();//JDialog â�ݱ�
			   
			   //dao.userSelectAll(me.dt);//��緹�ڵ尡���ͼ� DefaultTableModel�� �ø���
			   
			   if(me.dt.getRowCount() > 0)
				   me.jt.setRowSelectionInterval(0, 0);//ù��° �� ����
			   
		   }else{//���Խ���
			   me.Play("C:/eclipse/Crash.wav");
			   messageBox(this,"��� ����.");
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

	public static void messageBox(Object obj, SQLException e) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog( (Component) obj, e);
	}

}//Ŭ������