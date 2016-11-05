import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/menu"})
public class menu extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw=resp.getWriter();
        String n=req.getParameter("name");
        pw.println("<div class=\"menu\">");
        pw.println("<ul class=\"navi\">");
        pw.println("<li><a href=\"upload\">Upload</a></li>");
       
        pw.println("<li><a href=\"updateprofile\">Update Profile</a></li>");
        pw.println("<li><a href=\"saveddata\">Saved Data</a></li>");
        
        pw.println("<li><a href=\"logout\">Log Out</a></li>");
        pw.println("</ul>");
        pw.println("</div>");
        
    }
    
}
