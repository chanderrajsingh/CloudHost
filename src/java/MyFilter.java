import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import reuse.Person;
import reuse.users;




@WebFilter(servletNames = "welcomeservlet")
public class MyFilter implements Filter 
{
    FilterConfig fcon;
    @Override
    public void init(FilterConfig fc) throws ServletException {
        this.fcon=fc;
    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        Person p=null;
        users u=null;
        
        HttpServletRequest req=(HttpServletRequest)sr;
        HttpSession ses= req.getSession();
        if(ses.getAttribute("val")==null)
        {
            HttpServletResponse resp=(HttpServletResponse)sr1;
            resp.sendRedirect("exlogin.html");
        }
        String email=ses.getAttribute("email").toString();
        String name =ses.getAttribute("name").toString();
        int nos=1;
        Calendar c=Calendar.getInstance();
        String date =c.get(Calendar.DATE)+"/"+c.get(Calendar.MONTH)+"/"+c.get(Calendar.YEAR);
        
        XStream xs=new XStream();
        
        File f=new File(fcon.getServletContext().getRealPath("/")+"info.xml");
        if(f.exists())
        {
            u=(users)xs.fromXML(f);
            ArrayList<Person> al=u.getPers();
            boolean flag=true;
            for(int i=0;i<al.size();i++)
            {
                p=al.get(i);
                if(p.getEmail().equals(email)&&p.getDate().equals(date)){
                    flag=false;
                    break;
                }
            }
            if(flag)
            {
                p=new Person(name, email, date, nos);
                al.add(p);
                
            }else{
                int n=p.getNos();
                n=n+1;
                p.setNos(n);
            }
            
        }
        else{
        p=new Person(name, email, date, nos);
        ArrayList<Person> al=new ArrayList<Person>();
        al.add(p);
        u=new users();
        u.setPers(al);
        }
        
        FileOutputStream fout =new FileOutputStream(fcon.getServletContext().getRealPath("/")+"info.xml");
       
        ses.setAttribute("per", p);
        xs.toXML(u,fout);
        fc.doFilter(sr, sr1);
    }

    @Override
    public void destroy() {

    }
    
}
