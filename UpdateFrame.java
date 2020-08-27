import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


class UpdateFrame extends JFrame {

JLabel lblId, lblName, lblSalary;
JTextField txtId, txtName, txtSalary;
JButton btnUpdate, btnBack;
int id;
String name;
double salary;

UpdateFrame() {

//font
Font f = new Font("Times New Roman", Font.BOLD, 60);
Font b = new Font("Futura", Font.BOLD, 40);

//header
JPanel heading;
heading = new JPanel();
heading.setBackground(new Color(0,0,0,90));
heading.setBounds(0,0,900,100);

JLabel name1 = new JLabel("UPDATE  EMPLOYEE");
name1.setForeground(new Color(255,215,0));
name1.setBounds(200, 50, 400,50);
name1.setFont(f);
heading.add(name1);

//buttons panel
JPanel panelBtn = new JPanel();
panelBtn.setLayout(null);
panelBtn.setSize(400, 350);
panelBtn.setBounds(150, 130, 600, 410);
panelBtn.setBackground(new Color(0,0,0,180));

lblId = new JLabel("Enter ID        : ");
lblId.setBounds(20, 30, 300, 50);
lblId.setForeground(new Color(21, 244, 238));
lblId.setFont(b);
panelBtn.add(lblId);

lblName = new JLabel("Enter Name  : ");
lblName.setBounds(20, 130, 300, 50);
lblName.setForeground(new Color(21, 244, 238));
lblName.setFont(b);
panelBtn.add(lblName);

lblSalary = new JLabel("Enter Salary : ");
lblSalary.setBounds(20, 230, 300, 50);
lblSalary.setForeground(new Color(21, 244, 238));
lblSalary.setFont(b);
panelBtn.add(lblSalary);

txtId = new JTextField(20);
txtId.setBounds(290, 30, 280, 50);
txtId.setBackground(new Color(76, 81, 109));
txtId.setForeground(Color.WHITE);
txtId.setFont(b);
panelBtn.add(txtId);

txtName = new JTextField(20);
txtName.setBounds(290, 130, 280, 50);
txtName.setBackground(new Color(76, 81, 109));
txtName.setForeground(Color.WHITE);
txtName.setFont(b);
panelBtn.add(txtName);

txtSalary = new JTextField(20);
txtSalary.setBounds(290, 230, 280, 50);
txtSalary.setBackground(new Color(76, 81, 109));
txtSalary.setForeground(Color.WHITE);
txtSalary.setFont(b);
panelBtn.add(txtSalary);

btnUpdate = new JButton("Update");
btnUpdate.setBounds(90, 330, 200, 50);
btnUpdate.setBackground(new Color(126, 249, 255));
btnUpdate.setForeground(Color.BLACK);
btnUpdate.setFont(b);
btnUpdate.setFocusPainted(false);
panelBtn.add(btnUpdate);

btnBack = new JButton("Back");
btnBack.setBounds(310, 330, 200, 50);
btnBack.setBackground(new Color(126, 249, 255));
btnBack.setForeground(Color.BLACK);
btnBack.setFocusPainted(false);
btnBack.setFont(b);
panelBtn.add(btnBack);

//frame
setTitle("Employee Management System - Update Employee");
setSize(900, 600);
setLayout(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLocationRelativeTo(null);
getRootPane().setDefaultButton( btnUpdate );

//background
ImageIcon background_image = new ImageIcon("img//update.jpg");
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

btnUpdate.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory sfact = cfg.buildSessionFactory();
Session session = sfact.openSession();

Transaction t = null;

try{
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

try{
name = txtName.getText();
if(!name.matches("^[a-zA-Z]*$"))
throw new NullPointerException();
if(name.length() < 2)
throw new IllegalAccessException();
}
catch(NullPointerException npe){
String soundName = "sounds\\alphabet.wav";
AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
Clip clip = AudioSystem.getClip();    
clip.open(audioInputStream);
clip.start();
JOptionPane.showMessageDialog(new JDialog(),"Name Should only include Alphabets");
return;
}
catch(IllegalAccessException iae) {
String soundName = "sounds\\two.wav";
AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
Clip clip = AudioSystem.getClip();    
clip.open(audioInputStream);
clip.start();
JOptionPane.showMessageDialog(new JDialog(),"Name Should be atleast Two letters long");
return;
}
e.setName(name);

try {
salary = Double.parseDouble(txtSalary.getText());
if (salary < 8000.0)
throw new NullPointerException();
}
catch(NullPointerException npe) {
String soundName = "sounds\\gov.wav";
AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
Clip clip = AudioSystem.getClip();    
clip.open(audioInputStream);
clip.start();
JOptionPane.showMessageDialog(new JDialog(),"Minimum Salary Should Be 8000");
return;
}
catch(NumberFormatException nfe) {
String soundName = "sounds\\validsal.wav";
AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
Clip clip = AudioSystem.getClip();    
clip.open(audioInputStream);
clip.start();
JOptionPane.showMessageDialog(new JDialog(),"Please Enter Valid Salary");
return;
}

e.setSalary(salary);
t.commit();
String soundName = "sounds\\updated.wav";
AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
Clip clip = AudioSystem.getClip();    
clip.open(audioInputStream);
clip.start();
JOptionPane.showMessageDialog(new JDialog(), "Record Updated");
txtId.setText("");
txtName.setText("");
txtSalary.setText("");
txtId.requestFocus();
}
else{
String soundName = "sounds\\notfound.wav";
AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
Clip clip = AudioSystem.getClip();    
clip.open(audioInputStream);
clip.start();
JOptionPane.showMessageDialog(new JDialog(), "Record Not Found");
}
}
catch(Exception e) {
JOptionPane.showMessageDialog(new JDialog(), "Error : " + e);
}
finally {
session.close();
}
}
});
}
}