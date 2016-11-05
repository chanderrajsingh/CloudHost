import java.io.InputStream;
import java.sql.PreparedStatement;
import javax.servlet.http.Part;
import java.sql.*;
import javax.servlet.annotation.MultipartConfig;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@MultipartConfig(maxFileSize =2140000000 )
@WebServlet(urlPatterns = {"/uploaddataservlet"})
public class uploaddataservlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     
        HttpSession ses=req.getSession();
        String s1=ses.getAttribute("name").toString();
       String s2=ses.getAttribute("email").toString();
        Part p=req.getPart("inputfile");
        InputStream in =p.getInputStream();
        try {
           Class.forName("com.mysql.jdbc.Driver");
           Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/cloudhost","root","12345678");
           PreparedStatement pst=con.prepareStatement("insert into saveddata values(?,?,?,?)");
          // String flname= p.getName();
          String flname=req.getParameter("flname");
        // String flname="fch.jpg";
           //String ext=flname.substring(flname.lastIndexOf("."));
           
           pst.setString(1, s1);
           pst.setString(2, s2);
           pst.setBlob(3, in);
           pst.setString(4, flname);
           pst.executeUpdate();
           pst.close();
           con.close();
           ses.setAttribute("message", "y");
           
        } catch (Exception ex) {
           System.out.println(ex);
        }
        resp.sendRedirect("upload");
        
    }
    
}
