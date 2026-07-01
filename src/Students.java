import java.util.Scanner;
import java.sql.*;
 class Management{
    public static void insert(int pid,String name,int age,String gender) {
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
     public static void delete() {
        try{  
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","");
        Statement stmt=con.createStatement();
        System.out.println("Enter id to delete:");
         Scanner sc=new Scanner(System.in);
        int pid=sc.nextInt();
        stmt.execute("delete from patient where pid="+pid+";");
        con.close();
         System.out.println("Deleted record sucessfully");
        }catch(Exception exp){
            System.out.println("Error:"+exp);
        }
    }
     public static void update() {
        try{  
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","");
        Statement stmt=con.createStatement();
        System.out.println("Enter id to update:");
        System.out.println("Enter age(modified):");
         Scanner sc=new Scanner(System.in);
        int pid=sc.nextInt();
        int age=sc.nextInt();
        stmt.execute("update patient set age="+age+" where pid="+pid+")");
        con.close();
         System.out.println("Updated record sucessfully");
        }catch(Exception exp){
            System.out.println("Error:"+exp);
        }
    }
     public static void display() {
        try{  
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","");
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select*from patient;");
        while (rs.next()) {
            System.out.println(rs.getInt("pid")+""+rs.getString("name")+""+rs.getInt("age")+""+rs.getString("gender"));
        }
        con.close();
         System.out.println("data displayed");
        }catch(Exception exp){
            System.out.println("Error:"+exp);
        }


    }
}


public class Students {
    public static void menu(){
   System.out.println("1.Insert");
   System.out.println("2.Delete");
   System.out.println("3.Update");
   System.out.println("5.Display");
   System.out.println("6.quit");
}
    public static void main(String[] args) throws Exception {
         Scanner sc=new Scanner(System.in);
        while (true) {
            System.out.println("Student management system");
            menu();
            int choice=sc.nextInt();
            switch (choice) {
                case 1:
                Management.insert();
                    break;
                 case 2:
                    Management.delete();
                    break;
                 case 3:
                    Management.update();
                    break;
                 case 5:
                    Management.display();
                    break;
                 case 6:
                    return;
            
                default:
                    System.out.println("no operation is choosen");
                    break;
                }

            
        }
    }
}
