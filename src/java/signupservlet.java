import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@WebServlet(urlPatterns = {"/signupservlet"})
public class signupservlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest req,HttpServletResponse res){
        String fname=req.getParameter("fname");
        String lname=req.getParameter("lname");
        String email=req.getParameter("email");
        String passwd=req.getParameter("passwd");
        String contact=req.getParameter("contact");
        String gen=req.getParameter("rad");
        String country=req.getParameter("country");
        String vstatus="n";
        String tempid=getRandom();
        int ex=0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
        Connection c =DriverManager.getConnection("jdbc:mysql://localhost:3306/cloudhost","root","12345678");
        Statement st=c.createStatement();
        try{
            st.executeUpdate("insert into userinfo values('"+fname+"','"+lname+"','"+email+"','"+passwd+"','"+contact+"','"+gen+"','"+country+"','"+vstatus+"','"+tempid+"')");
        }catch(Exception e1){
            res.sendRedirect("alreadyexit.html");
            ex=1;
        }
        st.close();
        c.close();
            if(ex==0){
              sendMail(email,tempid);}
            PrintWriter pw=res.getWriter();
           // pw.println("Value Saved");
           res.sendRedirect("signupcomplete.html");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void sendMail(String email,String tempid)
    {
        try{
            String user="cloudhost.com";
           String sender="cloudhost.com@gmail.com";
           String passwd="houseofhunters";
           String host="smtp.gmail.com";
           String port="465";
           Properties props=new Properties();
           props.put("mail.smtp.user",user);
           props.put("mail.smtp.password",passwd);
           props.put("mail.smtp.host",host);
           props.put("mail.smtps.auth","true");
           Session session=Session.getDefaultInstance(props);
           MimeMessage message=new MimeMessage(session);
           message.setSubject("Verification Mail From Cloudhost.com");
           InternetAddress from =new InternetAddress(sender);
           InternetAddress to=new InternetAddress(email);
           message.setSender(from);
           message.setContent("Welcome to Cloudhost!<br><br>Click Below Link For Verification<br><br>http://localhost:42315/WebApp1/verify?txt="+tempid,"text/html" );
           message.setRecipient(Message.RecipientType.TO, to);
           Transport transport=session.getTransport("smtps");
           transport.connect(host, user, passwd);
           transport.sendMessage(message,message.getAllRecipients());
        }catch(Exception e){
            System.out.println(e);
        }
                   
     }
    public String getRandom()
    {
        String s="";
        String r="zxcvbnmasdfghjklqwertyuiop1234567890ZXCVBNMASDFGHJKLQWERTYUIOP";
        for(int i=0;i<10;i++)
        {
            int a=(int)(10*Math.random());
            s+=r.charAt(a);
        }
        return s;
    }

}