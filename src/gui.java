import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

public class gui {
    public static void main(String[] args) {
        JFrame frame=new JFrame("User information management");
        frame.setLayout(null);
        frame.setBounds(325,150,800,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel heading=new JLabel("USER DATA MANAGEMENT");
        heading.setBounds(320, -35, 600, 100);

        JLabel idi=new JLabel("ID:");
        idi.setBounds(200, 50, 100, 30);
        JTextField idfi=new JTextField();
        idfi.setBounds(250, 50, 300, 30);
        JLabel idd=new JLabel("ID:");
        idd.setBounds(200, 50, 100, 30);
        JTextField idfd=new JTextField();
        idfd.setBounds(250, 50, 300, 30);
        JLabel idu=new JLabel("ID:");
        idu.setBounds(200, 50, 100, 30);
        JTextField idfu=new JTextField();
        idfu.setBounds(250, 50, 300, 30);

        JLabel namei=new JLabel("NAME:");
        namei.setBounds(200, 100, 300, 30);
        JTextField namefi=new JTextField();
        namefi.setBounds(250, 100, 300, 30);

        JLabel agei=new JLabel("AGE:");
        agei.setBounds(200, 150, 300, 30);
        JTextField agefi=new JTextField();
        agefi.setBounds(250, 150, 100, 30);
        JLabel ageu=new JLabel("AGE:");
        ageu.setBounds(200, 100, 300, 30);
        JTextField agefu=new JTextField();
        agefu.setBounds(250, 100, 100, 30);


        JLabel genderi=new JLabel("GENDER:");
        genderi.setBounds(200, 200, 100, 30);
        JRadioButton maleR=new JRadioButton("MALE");
        maleR.setBounds(200, 250, 100, 20);
        JRadioButton femaleR=new JRadioButton("FEMALE");
        femaleR.setBounds(300, 250, 100, 20);

        ButtonGroup bg=new ButtonGroup();
        bg.add(maleR);
        bg.add(femaleR);
        JLabel op=new JLabel("choose operation:");
        op.setBounds(50,25,300,30);
        JButton insert=new JButton("INSERT");
        insert.setBounds(250, 300, 90, 20);
        JButton delete=new JButton("DELETE");
        delete.setBounds(250, 100, 90, 20);
        JButton update=new JButton("UPDATE");
        update.setBounds(250, 150, 90, 20);
        JButton display=new JButton("DISPLAY");
        display.setBounds(280, 350, 90, 20);
        JTabbedPane tabs=new JTabbedPane();
        JPanel ip=new JPanel();
        JPanel dp=new JPanel();
        JPanel up=new JPanel();
        JPanel disp=new JPanel();
        JTextArea ta=new JTextArea();
        JScrollPane scroll=new JScrollPane(ta);
        tabs.add("insert",ip);
        tabs.add("delete",dp);
         tabs.add("update",up);
          tabs.add("display",disp);
           ip.setLayout(null);
           dp.setLayout(null);
           up.setLayout(null);
           disp.setLayout(null);


           tabs.setBounds(39, 50, 700, 399);
           scroll.setBounds(25, 25, 650, 330);
           scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        

        frame.add(op);
        ip.add(insert);
        ip.add(idi);
        ip.add(idfi);
        ip.add(namei);
        ip.add(namefi);
        ip.add(agei);
        ip.add(agefi);
        ip.add(genderi);
        ip.add(maleR);
        ip.add(femaleR);

        dp.add(delete);
        dp.add(idd);
        dp.add(idfd);

        up.add(idu);
        up.add(idfu);
        up.add(ageu);
        up.add(agefu);
        up.add(update);



        disp.add(display);
        disp.add(scroll);
        ta.setEditable(false);


    
        frame.add(heading);
        frame.add(tabs);
        frame.setVisible(true);

        ActionListener insertListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                int pid=Integer.parseInt(idfi.getText());
                String name=namefi.getText();
                int age=Integer.parseInt(agefi.getText());

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
         JOptionPane.showMessageDialog(null,"Inserted record sucessfully" );
        }catch(Exception exp){
            System.out.println("Error:"+exp);
        }
        

    }

        };
insert.addActionListener(insertListener);



         ActionListener deleteListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                int pid=Integer.parseInt(idfd.getText());
                 try{  
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","");
        Statement stmt=con.createStatement();
        stmt.execute("delete from patient where pid="+pid+";");
        con.close();
         System.out.println("Deleted record sucessfully");
         JOptionPane.showMessageDialog(null, "Deleted record sucessfully");
        }catch(Exception exp){
            System.out.println("Error:"+exp);
        }
        

    }

        };
delete.addActionListener(deleteListener);



 ActionListener updateListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                int pid=Integer.parseInt(idfu.getText());
                int age=Integer.parseInt(agefu.getText());
                 try{  
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","");
        Statement stmt=con.createStatement();
        stmt.execute("update patient set age="+age+" where pid="+pid+";");
        con.close();
         System.out.println("Updated record sucessfully");
         JOptionPane.showMessageDialog(null, "Updated record sucessfully");
        }catch(Exception exp){
            System.out.println("Error:"+exp);
        }
        

    }

        };
update.addActionListener(updateListener);

 ActionListener dispListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                 try{  
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","");
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select*from patient;");
        ta.setText("");
         ta.append("ID"+"\t\t"+"NAME"+"\t\t"+"AGE"+"\t\t"+"GENDER"+"\n");

        while (rs.next()) {
           
          ta.append(rs.getInt("pid")+"\t\t"+rs.getString("name")+"\t\t"+rs.getInt("age")+"\t\t"+rs.getString("gender")+"\n");
            
        }

        con.close();
        System.out.println("Displayed sucessfully");
         JOptionPane.showMessageDialog(null,"Displayed sucessfully");
         
        }catch(Exception exp){
            System.out.println("Error:"+exp);
        }
        

    }

        };
display.addActionListener(dispListener);
    }
}

