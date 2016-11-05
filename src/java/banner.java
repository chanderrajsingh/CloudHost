import com.thoughtworks.xstream.XStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import reuse.Person;

@WebServlet(urlPatterns = {"/banner"})
public class banner extends HttpServlet {

     @Override
     public void service(HttpServletRequest res,HttpServletResponse resp) throws IOException{
       PrintWriter pw=resp.getWriter();
         HttpSession ses =res.getSession();
          String s=ses.getAttribute("name").toString();
          int n=0;
          Person p=(Person)ses.getAttribute("per");
          if(p!=null){
          
           n=p.getNos();
          }
          
       
       pw.println("<div class=\"banner\">");
       pw.println("<div class=\"lbl\">Welcome "+s+" !<br>");
       
       if(ses.getAttribute("lastlogin")!=null)
       {
       String lastlogin =ses.getAttribute("lastlogin").toString();
        pw.println("Last Login :"+lastlogin+"<br>");
       }
         pw.println("No. of visits : "+n+"</div>");
       pw.println("</div>");
         
     }
}
