import java.sql.*;

public class mysql {
   static Connection con;
    public static Connection getConnection(){
        try{
        Class.forName("com.jdbc.mysql.Driver");
       Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cloudhost","root","12345678");
        
        }catch(Exception e){
        }
      return con;
    }
}
