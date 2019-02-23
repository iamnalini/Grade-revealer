package grading.system;

import static grading.system.staffstudrec.table;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class studlogin extends JFrame implements ActionListener {

    JTextField t1;
    JPasswordField t2;
    JButton b, b1;

    studlogin() {
        JFrame f = new JFrame();
        JLabel l = new JLabel("STUDENT LOGIN");
        l.setBounds(280, 100, 260, 30);
        l.setFont(new Font("TimesRoman", Font.BOLD, 25));
        JLabel l1 = new JLabel("Student ID");
        l1.setBounds(140, 170, 200, 30);
        l1.setFont(new Font("TimesRoman", Font.PLAIN, 17));
        t1 = new JTextField();
        t1.setBounds(350, 170, 180, 30);
        JLabel l2 = new JLabel("Password");
        l2.setBounds(140, 250, 200, 30);
        l2.setFont(new Font("TimesRoman", Font.PLAIN, 17));
        t2 = new JPasswordField();
        t2.setBounds(350, 250, 180, 30);
        b = new JButton("Submit");
        b.setBounds(270, 320, 200, 40);
        b.addActionListener(this);
        b1 = new JButton("Back to Home");
        b1.setBounds(270, 375, 200, 40);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                GradingSystem sl = new GradingSystem();
                sl.setVisible(true);
            }
        });
        f.add(l);
        f.add(l1);
        f.add(t1);
        f.add(l2);
        f.add(t2);
        f.add(b);
        f.add(b1);
        f.setSize(700, 700);
        f.setTitle("Simple");
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            showData();
        } catch (SQLException ex) {
            Logger.getLogger(studlogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(studlogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showData() throws SQLException, ClassNotFoundException {
        String[] columnNames = {"Student name", "Roll no", "First Grade", "Second Grade", "Third Grade", "Average Grade", "Result"};
        String from;
        JFrame frame1 = new JFrame();
        JLabel l, l0;
        PreparedStatement pst;
        Connection con;
        String str1 = t1.getText();
        char[] p = t2.getPassword();
        String str2 = new String(p);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/users", "root", null);
            PreparedStatement ps = conn.prepareStatement("SELECT name FROM studreg where rollno=? and password=?");
            ps.setString(1, str1);
            ps.setString(2, str2);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                frame1 = new JFrame("Grade Sheet");
                frame1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame1.setLayout(new BorderLayout());
                frame1.setSize(700, 700);
                DefaultTableModel model = new DefaultTableModel();
                model.setColumnIdentifiers(columnNames);
                table = new JTable();
                table.setModel(model);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                table.setFillsViewportHeight(true);
                JScrollPane scroll = new JScrollPane(table);
                scroll.setHorizontalScrollBarPolicy(
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scroll.setVerticalScrollBarPolicy(
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                from = (String) str1;
                String name = "";
                String rollno = "";
                int firstgrade = 0, secondgrade = 0, thirdgrade = 0;
                int avg = 0;
                String result = "FAIL";
                try {
                    pst = conn.prepareStatement("select studreg.name, studreg.rollno, grade.firstgrade, grade.secondgrade, grade.thirdgrade, grade.avg, grade.result from studreg inner join grade on studreg.rollno=grade.rollno where studreg.rollno='" + from + "' ");
                    ResultSet rs1 = pst.executeQuery();
                    int i = 0;
                    if (rs1.next()) {
                        name = rs1.getString("name");
                        rollno = rs1.getString("rollno");
                        firstgrade = rs1.getInt("firstgrade");
                        secondgrade = rs1.getInt("secondgrade");
                        thirdgrade = rs1.getInt("thirdgrade");
                        avg = rs1.getInt("avg");
                        result = rs1.getString("result");
                        model.addRow(new Object[]{name, rollno, firstgrade, secondgrade, thirdgrade, avg, result});
                        i++;
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                JButton btn1;
                btn1 = new JButton("Log out");
                btn1.setBounds(250, 250, 130, 30);
                btn1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        GradingSystem sl = new GradingSystem();
                        sl.setVisible(true);
                    }
                });
                frame1.add(btn1);
                frame1.add(scroll);
                frame1.setVisible(true);
                frame1.setSize(700, 700);
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect student rollno or password..Try again");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
