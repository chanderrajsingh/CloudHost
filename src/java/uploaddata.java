

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"/uploaddata"})
public class uploaddata extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw=resp.getWriter();
         HttpSession ses=req.getSession();
        pw.println("<div class=\"uppanel\" >");
        pw.println("<form  method='post' enctype='multipart/form-data' onsubmit='return sendData(this)' >");
        pw.println("<h1 style=\"color: white;margin-left:20px; \">Upload Your Files Here</h1>");
        pw.println("<input type=\"file\" id=\"inputfile\" class=\"inputfile\" name=\"inputfile\"><br><br>");
        pw.println("<input type=\"submit\" value=\"Upload\" name=\"upld\" id=\"upld\" />");
       
      
        pw.println("</form>");
                if(ses.getAttribute("message")!=null)
                         pw.println("<br><span style='margin:15px;color:green;' id='sp'>Data Saved Successfully!!</span>"); 
                  ses.setAttribute("message", null);
        pw.println("</div>");
        
    }
    
}