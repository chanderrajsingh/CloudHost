<%@page import="java.io.OutputStream"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.sql.Blob"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>

<%@page import="java.io.File"%>
<script>
    function srch(){
        d=document.getElementById("txt").value;
        if(d.length=="")
    {
        alert("Select atleast one");
    }else
        window.location='srch?srchString='+d;
    }
</script>

<div class="rtpanel">
    <table align="center" cellspacing="0" class="imgtbl">
           
		<tr>
			<th colspan="5" >
				
					<input type="text" id="txt" placeholder="Search">
					<button id="btn" onclick="srch()">Search</button>
				
                        </th></tr>
                
                        <%
                     String srchString=request.getParameter("srchString");
                           String id=session.getAttribute("email").toString();
	  Class.forName("com.mysql.jdbc.Driver");
        Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/cloudhost","root","12345678");
        PreparedStatement pst=c.prepareStatement("select count(*) from saveddata where email=?&&filename=?");
            pst.setString(1, id);
            pst.setString(2, srchString);
           ResultSet rs=pst.executeQuery();
           rs.next();
           int n=rs.getInt(1);
           
             pst=c.prepareStatement("select * from saveddata where email=?&&filename=?");
             pst.setString(1, id);
               pst.setString(2, srchString);
          rs=pst.executeQuery();
           rs.next();
           
            
                       
                        int p=1;
                       String t= request.getParameter("p");
                       if(t!=null)
                           p=Integer.parseInt(t);
                       
                      int z=0;
                       int start=(p-1)*15;
                       for(int i=0;i<start;i++)
                           rs.next();
                    
                       if(n!=0)
                       {
                       
                        for(int i=0;i<3;i++)
                        {
                                out.println("<tr>");int j=0;
                            for(j=i*5+start;j<5+i*5+start&&j<n;j++){
                                String fnn="";
                                String fln=rs.getString(4);
                               
                                String fn=fln.substring(fln.lastIndexOf("."));
                                   
                        if(fn.equals(".doc")||fn.equals(".txt")||fn.equals(".exe"))
                            fnn="word_icon.png";
                        else if(fn.equals(".pdf"))
                            fnn="PDF-icon.png";
                        else if(fn.equals(".mp3"))
                            fnn="audio_file.png";
                        else if(fn.equals(".mp4"))
                            fnn="Icon_10-512.png";
                        else if(fn.equals(".jsp"))
                            fnn="java_1.png";
                        else if(fn.equals(".png")||
                                fn.equals(".jpg")||
                                fn.equals(".gif")){
                           fnn="icon.png";
                         
                        }     
                               
                              
                                  out.println("<td><input type='image' class='im' src='images/"+fnn+"' onclick=\"javascript:window.location='download.jsp?j="+j+"'\" ><p>"+fln+"</p></td>");
                               z++;
                               rs.next();
                            }
                           if(j==n){
                               for(;j<5+i*5+start;j++){
                                   out.println("<td></td>");
                               }
                           }
                            out.println("</tr>");
                        }
                       System.out.println(z);
                        %>
		
		<tr>
			<td colspan="5" align="center" style="height:auto;padding:10px;">
			<button class="bt" onclick="javascript:window.location='?p='+<%= p-1 %>" <%if(p==1)out.println("disabled");%>><<</button>
                       
                        
                        <%
                        int m=n/15;
                        if(n%15!=0)
                            m+=1;
                            for(int i=0;i<m;i++){
                                %>
                       
                                <button class="bt" onclick="javascript:window.location='?p='+<%= i+1 %>" <% if(p==i+1)out.println("style='background-color:#1252ac;'");%>><%= i+1%></button>
                        <%  
                            }
                        %>
				
				
				<button class="bt" onclick="javascript:window.location='?p='+<%= p+1 %>" <%if(p==m)out.println("disabled");%> >>></button>
			</td>
		</tr>
                <% }%>
	</table>
</div>