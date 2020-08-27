import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class MainFrame extends JFrame {


JButton btnAdd, btnView, btnUpdate, btnDelete;
JLabel bgimg;
//JPanel panel1;
MainFrame() {

//font
Font f = new Font("Times New Roman", Font.BOLD, 60);
Font b = new Font("Futura", Font.BOLD, 40);

//header
JPanel heading;
heading = new JPanel();
heading.setBackground(new Color(0,0,0,90));
heading.setBounds(0,0,900,100);

JLabel name = new JLabel("VIKRANT  INDUSTRIES");
name.setForeground(new Color(255,215,0));
name.setBounds(200, 50, 400,50);
name.setFont(f);
heading.add(name);

//buttons panel
JPanel panelBtn = new JPanel();
panelBtn.setLayout(null);
panelBtn.setSize(400, 350);
panelBtn.setBounds(250, 130, 400, 410);
panelBtn.setBackground(new Color(0,0,0,180));

JButton btnAdd = new JButton("Add");
btnAdd.setBounds(50, 30, 300, 50);
btnAdd.setBackground(new Color(126, 249, 255));
btnAdd.setForeground(Color.BLACK);
btnAdd.setFont(b);
btnAdd.setFocusPainted(false);
panelBtn.add(btnAdd);

JButton btnView = new JButton("View");
btnView.setBounds(50, 130, 300, 50);
btnView.setBackground(new Color(126, 249, 255));
btnView.setForeground(Color.BLACK);
btnView.setFont(b);
btnView.setFocusPainted(false);
panelBtn.add(btnView);

JButton btnUpdate = new JButton("Update");
btnUpdate.setBounds(50, 230, 300, 50);
btnUpdate.setBackground(new Color(126, 249, 255));
btnUpdate.setForeground(Color.BLACK);
btnUpdate.setFont(b);
btnUpdate.setFocusPainted(false);
panelBtn.add(btnUpdate);

JButton btnDelete = new JButton("Delete");
btnDelete.setBounds(50, 330, 300, 50);
btnDelete.setBackground(new Color(126, 249, 255));
btnDelete.setForeground(Color.BLACK);
btnDelete.setFont(b);
btnDelete.setFocusPainted(false);
panelBtn.add(btnDelete);




//frame
setTitle("Employee Management System");
setSize(900, 600);
setLayout(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLocationRelativeTo(null);

//background
ImageIcon background_image = new ImageIcon("img//bg.jpg");
Image img = background_image.getImage();
Image temp_img = img.getScaledInstance(900, 600, Image.SCALE_SMOOTH);
background_image = new ImageIcon(temp_img);
JLabel background = new JLabel("", background_image, JLabel.CENTER);

background.add(panelBtn);
background.add(heading);	
background.setBounds(0, 0, 900, 600);
add(background);

setVisible(true);



//working
btnAdd.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
AddFrame a = new AddFrame();
dispose();
}
});

btnView.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
ViewFrame a = new ViewFrame();
dispose();
}
});

btnUpdate.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
UpdateFrame a = new UpdateFrame();
dispose();
}
});

btnDelete.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
DeleteFrame a = new DeleteFrame();
dispose();
}
});

}

public static void main(String args[]) {
MainFrame mf = new MainFrame();
}
}

