import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;
public class gui {
    public static void main(String[] args) {
        JFrame frame=new JFrame("User information management");
        frame.setLayout(null);
        frame.setBounds(325,150,800,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel heading=new JLabel("USER DATA MANAGEMENT");
        heading.setBounds(320, 50, 600, 100);
        JLabel id=new JLabel("ID:");
        id.setBounds(200, 150, 100, 30);
        JTextField idf=new JTextField();
        idf.setBounds(250, 150, 300, 30);
        JLabel name=new JLabel("NAME:");
        name.setBounds(200, 200, 300, 30);
        JTextField namef=new JTextField();
        namef.setBounds(250, 200, 300, 30);
        JLabel age=new JLabel("AGE:");
        age.setBounds(200, 250, 300, 30);
        JTextField agef=new JTextField();
        agef.setBounds(250, 250, 100, 30);
        JLabel gender=new JLabel("GENDER:");
        gender.setBounds(200, 300, 100, 30);
        JRadioButton maleR=new JRadioButton("MALE");
        maleR.setBounds(195, 330, 100, 20);
        JRadioButton femaleR=new JRadioButton("FEMALE");
        femaleR.setBounds(295, 330, 100, 20);
        ButtonGroup bg=new ButtonGroup();
        bg.add(maleR);
        bg.add(femaleR);
        JLabel op=new JLabel("choose operation:");
        op.setBounds(200,360,300,30);
        JButton insert=new JButton("INSERT");
        insert.setBounds(200, 399, 90, 20);
        JButton delete=new JButton("DELETE");
        delete.setBounds(300, 399, 90, 20);
        JButton update=new JButton("UPDATE");
        update.setBounds(400, 399, 90, 20);
        JButton display=new JButton("DISPLAY");
        display.setBounds(500, 399, 90, 20);
        JButton exit=new JButton("EXIT");
        exit.setBounds(600,399,90,20);
        frame.add(op);
        frame.add(insert);
        frame.add(delete);
        frame.add(display);
        frame.add(update);
        frame.add(exit);
        frame.add(id);
        frame.add(idf);
        frame.add(name);
        frame.add(namef);
        frame.add(age);
        frame.add(agef);
        frame.add(gender);
        frame.add(maleR);
        frame.add(femaleR);
        frame.add(heading);
        frame.setVisible(true);

        ActionListener insertListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                int pid=Integer.parseInt(idf.getText());
                String name=namef.getText();
                int age=Integer.parseInt(agef.getText());
                String gender;
                if(maleR.isSelected()){
                    gender="male";
                }else{
                    gender="female";
                }
                 try{  
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","");
        Statement stmt=con.createStatement();
        stmt.execute("insert into patient values("+pid+",'"+name+"',"+age+",'"+gender+"');");
        con.close();
         System.out.println("Inserted record sucessfully");
        }catch(Exception exp){
            System.out.println("Error:"+exp);
        }
        

    }

        };
insert.addActionListener(insertListener);
    }
}

