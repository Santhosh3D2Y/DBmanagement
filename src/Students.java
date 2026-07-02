import java.sql.*;
import java.util.Scanner;

class PatientManager {
    public static void insert() {
        Scanner s = new Scanner(System.in);
        try{
            Connection con=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/apolohospital","root","");
            Statement st = con.createStatement();
            System.out.println("enter id, name, age, gender ");
            int pid = s.nextInt();
            String name = s.next();
            int age = s.nextInt();
            String gender = s.next();                        
            st.execute("insert into patient values("+pid+", '"+name+"', "+age+", '"+gender+"') ");
            // st.execute("update patient set age=19 where pid=3");
            // st.execute("delete from patient where pid=2");

            con.close();
            System.out.println("operation Success!");
        }
        catch(Exception exp) 
        {
            System.out.println("operation Failed!" + exp);
        }        
    }
    public static void update() {
        Scanner s=new Scanner(System.in);
        try{
            Connection con=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/apolohospital","root","");
            Statement st = con.createStatement();
            
            // st.execute("insert into patient values(5, 'steve', 30, 'male') ");
            System.out.println("enter age to modify ");
            int age = s.nextInt();
            System.out.println("enter pid ");
            int pid = s.nextInt();

            st.execute("update patient set age="+age+" where pid="+pid);
            // st.execute("delete from patient where pid=2");

            con.close();
            System.out.println("operation Success!");
        }
        catch(Exception exp) 
        {
            System.out.println("operation Failed!" + exp);
        }
    }
    public static void remove() {
        Scanner s=new Scanner(System.in);
        try{
            Connection con=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/apolohospital","root","");
            Statement st = con.createStatement();

            System.out.println("enter pid of patient to remove :");
            int pid = s.nextInt();
            
            // st.execute("insert into patient values(5, 'steve', 30, 'male') ");
            // st.execute("update patient set age=19 where pid=3");
            st.execute("delete from patient where pid="+pid);

            con.close();
            System.out.println("operation Success!");
        }
        catch(Exception exp) 
        {
            System.out.println("operation Failed!" + exp);
        }        
    }
    public static void view() {
        try{
            Connection con=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/apolohospital","root","");
            Statement st = con.createStatement();
            
            // st.execute("insert into patient values(5, 'steve', 30, 'male') ");
            // st.execute("update patient set age=19 where pid=3");
            // st.execute("delete from patient where pid=2");
            ResultSet rs = st.executeQuery("select * from patient");

            while(rs.next())
                System.out.println(
                    rs.getInt("pid")+" "+
                    rs.getString("name")+" "+
                    rs.getInt("age")+" "+
                    rs.getString("gender"));

            con.close();
            System.out.println("operation Success!");
        }
        catch(Exception exp) 
        {
            System.out.println("operation Failed!" + exp);
        }

    }

}
public class Students {
    // 1. insert row in db table
    // 2. fetch rows from db tables

    public static void menu(){
        System.out.println("1. Insert ");
        System.out.println("2. Update ");
        System.out.println("3. Delete ");
        System.out.println("4. View ");
        System.out.println("0. Quit ");
        System.out.println("Enter choice ");
    }
    public static void main(String[]args) {
        Scanner s = new Scanner(System.in);
        while(true)
        {
            System.out.println("Patient Management System ");
            menu();
            int choice = s.nextInt();
            switch(choice)
            {
                case 0: return;
                case 1: 
                    PatientManager.insert();
                    break;
                case 2:
                    PatientManager.update();
                    break;
                case 3:
                    PatientManager.remove();
                    break;
                case 4:
                    PatientManager.view();
                    break;
            }
        }
    }    
}