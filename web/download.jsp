
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.sql.Blob"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>

<%@page import="java.sql.Connection"%>
<% 
    
    int j=Integer.parseInt(request.getParameter("j"));
    String id=session.getAttribute("email").toString();
    Class.forName("com.mysql.jdbc.Driver");
        Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/cloudhost","root","12345678");
        PreparedStatement pst=c.prepareStatement("select * from saveddata where email=?");
         pst.setString(1, id);
           ResultSet rs=pst.executeQuery();
           rs.next();
           for(int i=0;i<j;i++){
               rs.next();
           }
           Blob b=rs.getBlob(3);
           InputStream in=b.getBinaryStream();
           byte b1[]=new byte[(int)b.length()];
           String type=rs.getString(4);
           in.read(b1);
           response.setContentType("*/*");
           response.setHeader("Content-disposition", "attachment;filename=download"+type);
           OutputStream o=response.getOutputStream();
           o.write(b1);
           o.flush();
           
%>
