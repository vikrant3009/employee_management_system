import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

class ViewFrame extends JFrame {

JButton btnBack;
JTable tblRecord;


ViewFrame() {
Font f = new Font("Times New Roman", Font.BOLD, 60);
Font b = new Font("Futura", Font.BOLD, 30);

//header
JPanel heading;
heading = new JPanel();
heading.setBackground(new Color(0,0,0,90));
heading.setBounds(0,0,900,100);

JLabel name = new JLabel("VIEW EMPLOYEE");
name.setForeground(new Color(255,215,0));
name.setBounds(200, 50, 400,50);
name.setFont(f);
heading.add(name);

//buttons panel
JPanel panelBtn = new JPanel();
panelBtn.setLayout(null);
panelBtn.setSize(600, 350);
panelBtn.setBounds(150, 120, 600, 420);
panelBtn.setBackground(new Color(0,0,0,180));
btnBack = new JButton("Back");

//frame
setTitle("Employee Management System - View Employee");
setSize(900, 600);
setLayout(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLocationRelativeTo(null);

//background
ImageIcon background_image = new ImageIcon("img//view.jpg");
Image img = background_image.getImage();
Image temp_img = img.getScaledInstance(900, 600, Image.SCALE_SMOOTH);
background_image = new ImageIcon(temp_img);
JLabel background = new JLabel("", background_image, JLabel.CENTER);

background.add(panelBtn);
background.add(heading);	
background.setBounds(0, 0, 900, 600);
add(background);

setVisible(true);


Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory sfact = cfg.buildSessionFactory();
Session session = sfact.openSession();

try {
java.util.List<Employee> emp = new ArrayList<>();
emp = session.createQuery("from Employee").list();

//to convert arrayList to Object[][]
Object[][] data = new Object[emp.size()][];
int i = 0;
for (Employee e : emp)
{
data[i] = new Object[3];
data[i][0] = e.getId();
data[i][1] = e.getName();
data[i][2] = e.getSalary();
i++;
}

//column names
String[] columnNames = { "Id", "Name", "Salary" };

//initialization of table
tblRecord = new JTable(data, columnNames);
// tblRecord.setSize(300, 250);
// tblRecord.setBounds(50, 50, 300, 210);
tblRecord.setBackground(new Color(126, 249, 255));
tblRecord.setFont(b);
tblRecord.setRowHeight(35);

//jTable header
tblRecord.getTableHeader().setFont(b);



//table size
tblRecord.setPreferredScrollableViewportSize(tblRecord.getPreferredSize());
tblRecord.setFillsViewportHeight(true);

//adding to the scroll pane
JScrollPane sp = new JScrollPane(tblRecord);
sp.setSize(500, 350);
sp.setBounds(50, 30, 500, 300);
sp.setBackground(new Color(126, 249, 255));
sp.setFont(b);




panelBtn.add(sp);

/*for showing data in TextArea
for (Employee e: emp)
taRecord.append(e.getId() + " " + e.getName() + " " + e.getSalary() + "\n");
*/

}
catch(Exception e){
JOptionPane.showMessageDialog(new JDialog(),"Error : " + e);
}
finally {
session.close();
}

btnBack = new JButton("Back");
btnBack.setBounds(200, 350, 200, 50);
btnBack.setBackground(new Color(126, 249, 255));
btnBack.setForeground(Color.BLACK);
btnBack.setFocusPainted(false);
btnBack.setFont(b);
panelBtn.add(btnBack);

btnBack.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae) {
MainFrame mf = new MainFrame();
dispose();
}
});
}
}