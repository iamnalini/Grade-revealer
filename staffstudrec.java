package grading.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class staffstudrec extends JFrame implements ActionListener {

    JFrame frame1;
    JLabel l0, l1, l2, l3, l4, l5, l6, l7;
    JComboBox c1;
    JButton b1, b2,b3;
    JTextField t1, t2, t3;
    Connection con;
    ResultSet rs, rs1;
    Statement st, st1;
    PreparedStatement pst;
    String ids;
    static JTable table;
    String[] columnNames = {"Student name", "Roll no", "Phone no"};
    String from;

    staffstudrec() {

        l0 = new JLabel("REGISTERED STUDENTS");
        l0.setForeground(Color.red);
        l0.setFont(new Font("Serif", Font.BOLD, 21));
        l1 = new JLabel("Select Rollno");
        l1.setFont(new Font("TimesRoman", Font.PLAIN, 17));
        b1 = new JButton("Submit");
        b3 = new JButton("Back to Home");
        l0.setBounds(220, 50, 350, 40);
        l1.setBounds(195, 165, 100, 35);
        b1.setBounds(275, 260, 150, 30);
        b1.addActionListener(this);
        b1.setFont(new Font("TimesRoman", Font.PLAIN, 17));
        b3.setBounds(245, 340, 200, 30);
        b3.setFont(new Font("TimesRoman", Font.PLAIN, 17));
        setTitle("Fetching Student Info From DataBase");
        setLayout(null);
        setVisible(true);
        setSize(700, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(b3);
        add(l0);
        add(l1);
        add(b1);
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0){
                GradingSystem sl=new GradingSystem();
                sl.setVisible(true);
            } 
        });
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", null);
            st = con.createStatement();
            rs = st.executeQuery("select rollno from studreg");
            Vector v = new Vector();
            while (rs.next()) {
                ids = rs.getString(1);
                v.add(ids);
            }
            c1 = new JComboBox(v);
            c1.setBounds(315, 165, 170, 35);
            c1.setFont(new Font("TimesRoman", Font.PLAIN, 17));
            add(c1);
            st.close();
            rs.close();
        } catch (Exception e) {
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            showTableData();
        }

    }

    public void showTableData() {

        frame1 = new JFrame("Database Search Result");
        frame1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame1.setLayout(new BorderLayout());
        frame1.setSize(700, 700);
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
          
        table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        table.setFont(new Font("TimesRoman", Font.PLAIN, 17));
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        from = (String) c1.getSelectedItem();
        String name = "";
        String rollno = "";
        String phno = "";
        try {
            pst = con.prepareStatement("select * from studreg where rollno='" + from + "'");
            ResultSet rs = pst.executeQuery();
            int i = 0;
            if (rs.next()) {
                name = rs.getString("name");
                rollno = rs.getString("rollno");
                //password = rs.getString("password");
                phno = rs.getString("phno");
                model.addRow(new Object[]{name, rollno, phno});
                i++;
                l3 = new JLabel("UPDATE STUDENTS GRADE");
                l3.setBounds(190, 100, 350, 30);
                l3.setFont(new Font("TimesRoman", Font.BOLD, 21));
                l3.setForeground(Color.red);
        
                l4 = new JLabel("Student ID:  ".concat(rollno));
                l4.setBounds(70, 180, 250, 30);
                l4.setFont(new Font("TimesRoman", Font.PLAIN, 17));

                l5 = new JLabel("First Grade");
                l5.setBounds(140, 240, 150, 40);
                l5.setFont(new Font("TimesRoman", Font.PLAIN, 17));
                l6 = new JLabel("Second Grade");
                l6.setBounds(140, 290, 150, 40);
                l6.setFont(new Font("TimesRoman", Font.PLAIN, 17));
                l7 = new JLabel("Third Grade:");
                l7.setBounds(140, 340, 150, 40);
                l7.setFont(new Font("TimesRoman", Font.PLAIN, 17));
                t1 = new JTextField();
                t1.setBounds(360, 240, 150, 30);
                t2 = new JTextField();
                t2.setBounds(360, 300, 150, 30);
                t3 = new JTextField();
                t3.setBounds(360, 360, 150, 30);
                b2 = new JButton("Update");
                b2.setBounds(265, 430, 120, 35);
                b2.setFont(new Font("TimesRoman", Font.PLAIN, 18));
                b2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        int s1 = Integer.parseInt(t1.getText());
                        int s2 = Integer.parseInt(t2.getText());
                        int s3 = Integer.parseInt(t3.getText());
                        int grade = (s1+s2+s3)/3;
                        String result;
                        int x=0;
                        if(grade>=40)
                            result="PASS";
                        else
                            result="FAIL";
                        try {
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/users", "root", null);
                            PreparedStatement ps = conn.prepareStatement("UPDATE grade set firstgrade=?, secondgrade=?, thirdgrade=?, avg=?, result=? where rollno='" + from + "'");
                            ps.setInt(1, s1);
                            ps.setInt(2, s2);
                            ps.setInt(3, s3);
                            ps.setInt(4, grade);
                            ps.setString(5, result);
                            boolean rs = ps.execute();
                            x++;  
                            if (x > 0)   
                            {  
                                JOptionPane.showMessageDialog(b2, "Grades updated Successfully");  
                            }
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                });
                frame1.add(l3);
                frame1.add(l4);
                frame1.add(l5);
                frame1.add(l6);
                frame1.add(l7);
                frame1.add(t1);
                frame1.add(t2);
                frame1.add(t3);
                frame1.add(b2);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        frame1.add(scroll);
        frame1.setVisible(true);
        frame1.setSize(700, 700);
    }
}
