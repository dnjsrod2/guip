package cashbook;



import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;

public class picture extends JDialog implements ActionListener{
   private JFileChooser   jFileChooser=new JFileChooser();
   public static File bookimage;
   public static JTextField subject;
   JButton btnNewButton,btnNewButton_1;
   public picture() {
   	getContentPane().setBackground(Color.PINK);
   	setTitle("책이미지");
      getContentPane().setLayout(null);
      setSize(411, 129);
      
      btnNewButton = new JButton("사진 찾기");
      btnNewButton.setBackground(Color.CYAN);
      btnNewButton.setBounds(202, 37, 97, 23);
      btnNewButton.addActionListener(this);
      getContentPane().add(btnNewButton);
      
      subject = new JTextField();
      subject.setBounds(14, 37, 174, 21);
      getContentPane().add(subject);
      subject.setColumns(10);
      
      btnNewButton_1 = new JButton("저장");
      btnNewButton_1.setBounds(313, 37, 70, 23);
      btnNewButton_1.addActionListener(this);
      getContentPane().add(btnNewButton_1);
      setVisible(true);
      
   }
   /*
   private void savePicture(){
      try {
         Class.forName("com.mysql.jdbc.Driver");
         Connection DB = DriverManager.getConnection("jdbc:mysql://localhost:3306/cashbook", "root", "1234");
         Statement sql = DB.createStatement();
         PreparedStatement pre;
         FileInputStream fin = new FileInputStream(bookimage);
         pre = DB.prepareStatement("insert into picture (file, subject) VALUES (?, ?)");
         
         
         pre.setBlob(1, fin);
         pre.setString(2,subject.getText());
         System.out.println(fin);
         pre.executeUpdate();
         
        
         pre.close();
         sql.close();
         
      } catch (Exception e) {
    	  JOptionPane.showMessageDialog(this, e);
      }
   }*/
   private void showPicture() {
      try {
         if(JFileChooser.APPROVE_OPTION!=jFileChooser.showOpenDialog(this)) return;
         
         bookimage=new File(jFileChooser.getSelectedFile().getAbsolutePath());
         subject.setText(SubjectAddDialog.subject.getText());
         } catch (Exception e1) {
      }
   }
  
   public static void main(String[] args) {
	   picture pic = new picture();
	   pic.setVisible(true);
      // TODO Auto-generated method stub

   }

   @Override
   public void actionPerformed(ActionEvent e) {
	   if(e.getSource() == btnNewButton){
		  showPicture();
		   
	   }
      if(e.getSource() == btnNewButton_1){
    	  if(UserDefaultJTableDAO.savePicture()==0){
    		  JOptionPane.showMessageDialog(this, "완료");
    	  }else{
    		  JOptionPane.showMessageDialog(this, "실패");
    	  };
   }
   }
}