import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"/welcomeservlet"},name = "welcomeservlet")
public class welcomeservlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw=resp.getWriter();
       
        
       try{
        
        pw.println("<!Doctype html><html><head><title>Welcome</title>");
        pw.println("<link rel=\"shortcut icon\" type=\"image/x-icon\" href=\"images/cloud.ico\" />");
        pw.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"w.css\">");
        
        pw.println("</head><body>");
        RequestDispatcher rd1=getServletContext().getRequestDispatcher("/banner");
        rd1.include(req, resp);
        RequestDispatcher rd2=getServletContext().getRequestDispatcher("/menu");
        rd2.include(req, resp);
        RequestDispatcher rd3=getServletContext().getRequestDispatcher("/body");
        rd3.include(req, resp);
        pw.println("</body></html>");
       }catch(Exception e)
       {resp.sendRedirect("exlogin.html");
       }
    }

   
 
}
