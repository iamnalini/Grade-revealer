package grading.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class stafflogin extends JFrame implements ActionListener {
    JTextField t1;
    JPasswordField t2;
    JButton b;
    
    stafflogin() {
        JFrame f2 = new JFrame();
        JLabel l = new JLabel("STAFF LOGIN");
        l.setBounds(265, 100, 230, 30);
        l.setFont(new Font("TimesRoman", Font.BOLD, 25));
        JLabel l1 = new JLabel("Staff ID");
        l1.setBounds(140, 170, 200, 30);
        l1.setFont(new Font("TimesRoman",Font.PLAIN, 17));
        t1 = new JTextField();
        t1.setBounds(350, 170, 180, 30);
        JLabel l2 = new JLabel("Password");
        l2.setBounds(140, 250, 200, 30);
        l2.setFont(new Font("TimesRoman", Font.PLAIN, 17));
        t2 = new JPasswordField();
        t2.setBounds(350, 250, 180, 30);
        b=new JButton("Submit");
        b.setBounds(265, 320, 200, 40);
        b.addActionListener(this);
        f2.add(l);
        f2.add(l1);
        f2.add(t1);
        f2.add(l2);
        f2.add(t2);
        f2.add(b);
        f2.setSize(700, 700);
        f2.setTitle("Simple");
        f2.setLayout(null);
        f2.setVisible(true);
        f2.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e)
    {
        showData();
    }
    public void showData()
    {
        //JFrame f1=new JFrame();
        //JLabel l,l0;
        
        String str1 = t1.getText();
        char[] p=t2.getPassword();
        String str2=new String(p);
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/users", "root", null);
            PreparedStatement ps= conn.prepareStatement("SELECT name FROM reg where staffid=? and password=?");
            ps.setString(1, str1);
            ps.setString(2, str2);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                staffstudrec ss=new staffstudrec();
                ss.setVisible(true);
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Incorrect staffid or password..Try again");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

}
