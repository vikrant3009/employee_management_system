import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

class DeleteFrame extends JFrame {

JLabel lblId;
JTextField txtId;
JButton btnDelete, btnBack;
int id;

DeleteFrame() {

//font
Font f = new Font("Times New Roman", Font.BOLD, 60);
Font b = new Font("Futura", Font.BOLD, 40);

//header
JPanel heading;
heading = new JPanel();
heading.setBackground(new Color(0,0,0,90));
heading.setBounds(0,0,900,100);

JLabel name1 = new JLabel("DELETE  EMPLOYEE");
name1.setForeground(new Color(255,215,0));
name1.setBounds(200, 50, 400,50);
name1.setFont(f);
heading.add(name1);

//buttons panel
JPanel panelBtn = new JPanel();
panelBtn.setLayout(null);
panelBtn.setSize(400, 350);
panelBtn.setBounds(150, 210, 600, 210);
panelBtn.setBackground(new Color(0,0,0,180));

lblId = new JLabel("Enter ID        : ");
lblId.setBounds(20, 30, 300, 50);
lblId.setForeground(new Color(21, 244, 238));
lblId.setFont(b);
panelBtn.add(lblId);

txtId = new JTextField(20);
txtId.setBounds(290, 30, 280, 50);
txtId.setBackground(new Color(76, 81, 109));
txtId.setForeground(Color.WHITE);
txtId.setFont(b);
panelBtn.add(txtId);

btnDelete = new JButton("Delete");
btnDelete.setBounds(90, 130, 200, 50);
btnDelete.setBackground(new Color(126, 249, 255));
btnDelete.setForeground(Color.BLACK);
btnDelete.setFocusPainted(false);
btnDelete.setFont(b);
panelBtn.add(btnDelete);

btnBack = new JButton("Back");
btnBack.setBounds(310, 130, 200, 50);
btnBack.setBackground(new Color(126, 249, 255));
btnBack.setForeground(Color.BLACK);
btnBack.setFocusPainted(false);
btnBack.setFont(b);
panelBtn.add(btnBack);

//frame
setTitle("Employee Management System - Delete Employee");
setSize(900, 600);
setLayout(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLocationRelativeTo(null);
getRootPane().setDefaultButton( btnDelete );

//background
ImageIcon background_image = new ImageIcon("img//delete.jpg");
Image img = background_image.getImage();
Image temp_img = img.getScaledInstance(900, 600, Image.SCALE_SMOOTH);
background_image = new ImageIcon(temp_img);
JLabel background = new JLabel("", background_image, JLabel.CENTER);

background.add(panelBtn);
background.add(heading);	
background.setBounds(0, 0, 900, 600);
add(background);

setVisible(true);

btnBack.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
MainFrame a = new MainFrame();
dispose();
}
});

btnDelete.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {

Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory sfact = cfg.buildSessionFactory();
Session session = sfact.openSession();

Transaction t = null;

try {
t = session.beginTransaction();

try {
id = Integer.parseInt(txtId.getText());
}
catch(NumberFormatException nfe) {
String soundName = "sounds\\validid.wav";
AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
Clip clip = AudioSystem.getClip();    
clip.open(audioInputStream);    
clip.start();
JOptionPane.showMessageDialog(new JDialog(),"Please Enter Valid Employee Id");
return;
}

Employee e = (Employee)session.get(Employee.class, id);
if(e != null) {
session.delete(e);
t.commit();
String soundName = "sounds\\deleted.wav";
AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
Clip clip = AudioSystem.getClip();    
clip.open(audioInputStream);
clip.start();
JOptionPane.showMessageDialog(new JDialog(), "Record Deleted");
txtId.setText("");
txtId.requestFocus();
}
else {
String soundName = "sounds\\notfound.wav";
AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
Clip clip = AudioSystem.getClip();    
clip.open(audioInputStream);
clip.start();
JOptionPane.showMessageDialog(new JDialog(), "Record Not Found");
}
}
catch(Exception e) {
t.rollback();
JOptionPane.showMessageDialog(new JDialog(), "Error : " + e);
}
finally {
session.close();
}
}
});

}
}