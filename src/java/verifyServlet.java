import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;


@WebServlet(urlPatterns = {"/verify"})
public class verifyServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String txt=req.getParameter("txt");
        PrintWriter pw=resp.getWriter();
       try{
           Class.forName("com.mysql.jdbc.Driver");
           Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/cloudhost","root","12345678");
           Statement st=c.createStatement();
           st.executeUpdate("update userinfo set vstatus='y' where tempid='"+txt+"'");
           //pw.println("You are a Verified User Now");
           resp.sendRedirect("verified.html");
       }catch(Exception e){
           System.out.println(e);
       }
    }

   
}
