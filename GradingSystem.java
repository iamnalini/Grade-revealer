package grading.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;

public class GradingSystem extends JFrame {
    
    JButton b1,b2,b3,b4;
    JFrame f; 
    JLabel l1,l2;
    
    GradingSystem() {
        f= new JFrame();
        l1=new JLabel("STAFF SECTION");
        l1.setBounds(260, 100, 200, 30);
        l1.setFont(new Font("TimesRoman",Font.BOLD, 20));
        b1=new JButton("Registration");
        b1.setBounds(150, 170, 150, 40);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0){
                staffreg sl=new staffreg();
                sl.setVisible(true);
            } 
        });
        b2=new JButton("Login");
        b2.setBounds(370, 170, 150, 40);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0){
                stafflogin stl=new stafflogin();
                stl.setVisible(true);
            }
        });
        l2=new JLabel("STUDENT SECTION");
        l2.setBounds(260, 280, 200, 30);
        l2.setFont(new Font("TimesRoman",Font.BOLD, 20));
        b3=new JButton("Registration");
        b3.setBounds(150, 350, 150, 40);
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0){
                studreg s2=new studreg();
                s2.setVisible(true);
            }
        });
        b4=new JButton("Login");
        b4.setBounds(370, 350, 150, 40);
        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0){
                studlogin st2=new studlogin();
                st2.setVisible(true);
            }
        });

        f.add(l1);
        f.add(b1);
        f.add(b2);
        f.add(l2);
        f.add(b3);
        f.add(b4);
        f.setSize(700, 700);
        f.setTitle("Main Window");
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        GradingSystem system=new GradingSystem();
    }
}