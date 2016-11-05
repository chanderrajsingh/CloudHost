
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.Calendar;
import javax.servlet.http.HttpSession;
import reuse.Profile;


@WebServlet(urlPatterns = {"/loginservlet"})
public class loginservlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw=resp.getWriter();
        String user=req.getParameter("user");
        String passswd=req.getParameter("passwd");
        HttpSession ses=req.getSession();
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/cloudhost","root","12345678");
        //Statement st=c.createStatement();
        //ResultSet rs=st.executeQuery("select * from userinfo where email='"+user+"'&&passwd='"+passswd+"'");
        PreparedStatement pst=c.prepareStatement("select * from userinfo where email=?&&passwd=?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        pst.setString(1, user);
        pst.setString(2, passswd);
            
            ResultSet  rs=pst.executeQuery();
           
        if(rs.next())
        {
            String v=rs.getString("vstatus");
            if(v.equals("y")){
              
                ses.setAttribute("name", rs.getString(1)+" "+rs.getString(2));
                ses.setAttribute("email",user);
                ses.setAttribute("message", null);
                ses.setAttribute("val", "y");
                Profile p=new Profile(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(6), rs.getString(7), rs.getLong(5));
                ses.setAttribute("pro", p);
                Calendar cd=Calendar.getInstance();
                if(rs.getString("lastlogin")!=null)
                ses.setAttribute("lastlogin", rs.getString("lastlogin"));
                else
                    ses.setAttribute("lastlogin", ""+cd.getTime());
               
                Statement st=c.createStatement();
                st.execute("update userinfo set lastlogin='"+cd.getTime()+"' where email='"+user+"'&&passwd='"+passswd+"' ");
               // rs.updateString(10, ""+cd.getTime());
                //System.out.println(cd.getTime());
                 resp.sendRedirect("welcomeservlet?name="+rs.getString(1)+" "+rs.getString(2));
                 rs.close();
                }
            else{
               // pw.println("You have not verfied yet!");
                resp.sendRedirect("NotVerified.html");
                }
        }else{
            //pw.println("Loin Failed");
            resp.sendRedirect("loginfailed.html");
        }
        }catch(Exception e)
        {
            System.out.println(e);
        }
        
    }

   
}
