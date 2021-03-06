package grading.system;

import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  
import java.sql.*;  
public class staffreg extends JFrame implements ActionListener   
{  
    JLabel l1, l2, l3, l4, l5, l8;  
    JTextField tf1, tf2, tf7;  
    JButton btn1, btn2, btn3;  
    JPasswordField p1, p2;  
    staffreg()  
    {  
        setVisible(true);  
        setSize(700, 700);  
        setLayout(null);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setTitle("Registration Form in Java");  
        l1 = new JLabel("Registration Form");  
        l1.setForeground(Color.blue);  
        l1.setFont(new Font("Serif", Font.BOLD, 20));  
        l2 = new JLabel("Name:");  
        l3 = new JLabel("Staff ID:");  
        l4 = new JLabel("Create Passowrd:");  
        l5 = new JLabel("Confirm Password:");  
        l8 = new JLabel("Phone No:");   
        tf1 = new JTextField();  
        tf2 = new JTextField();  
        p1 = new JPasswordField();  
        p2 = new JPasswordField();  
        tf7 = new JTextField();  
        btn1 = new JButton("Submit");  
        btn2 = new JButton("Clear"); 
        btn3 = new JButton("Back to Login");
        btn1.addActionListener(this);  
        btn2.addActionListener(this);
        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0){
                
                GradingSystem s2=new GradingSystem();
                s2.setVisible(true);
            }
        });
        l1.setBounds(200, 30, 400, 30);  
        l2.setBounds(80, 90, 200, 30);  
        l3.setBounds(80, 150, 200, 30);  
        l4.setBounds(80, 210, 200, 30);  
        l5.setBounds(80, 270, 200, 30);  
        l8.setBounds(80, 360, 200, 30);  
        tf1.setBounds(300, 90, 200, 30);  
        tf2.setBounds(300, 150, 200, 30);  
        p1.setBounds(300, 210, 200, 30);  
        p2.setBounds(300, 270, 200, 30);  
        tf7.setBounds(300, 360, 200, 30);  
        btn1.setBounds(70, 435, 100, 30);  
        btn2.setBounds(200,435, 100, 30);
        btn3.setBounds(350, 435, 150, 30);
        add(l1);  
        add(l2);  
        add(tf1);  
        add(l3);  
        add(tf2);  
        add(l4);  
        add(p1);  
        add(l5);  
        add(p2);  
        add(l8);  
        add(tf7);  
        add(btn1);  
        add(btn2); 
        add(btn3);
    }  
    public void actionPerformed(ActionEvent e)   
    {  
        if (e.getSource() == btn1)  
         {  
            int x = 0;  
            String s1 = tf1.getText();  
            String s2 = tf2.getText();  
            char[] s3 = p1.getPassword();  
            char[] s4 = p2.getPassword();   
            String s8 = new String(s3);  
            String s9 = new String(s4);  
            String s7 = tf7.getText();  
            if (s8.equals(s9))  
            {  
                try  
                {  
                    Class.forName("com.mysql.jdbc.Driver");  
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/users", "root", null);  
                    PreparedStatement ps = con.prepareStatement("insert into reg values(?,?,?,?)");  
                    ps.setString(1, s1);  
                    ps.setString(2, s2);  
                    ps.setString(3, s8);  
                    ps.setString(4, s7);  
                    boolean rs = ps.execute();  
                    x++;  
                    if (x > 0)   
                    {  
                        JOptionPane.showMessageDialog(btn1, "Data Saved Successfully");  
                    }  
                }  
                catch (Exception ex)   
                {  
                    System.out.println(ex);  
                }  
            }  
            else  
            {  
                JOptionPane.showMessageDialog(btn1, "Password Does Not Match");  
            }   
          }   
          else  
          {  
            tf1.setText("");  
            tf2.setText("");  
            p1.setText("");  
            p2.setText("");  
            tf7.setText("");  
          }  
    }   
}  