
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    .rtpanel{
     color: white;
    }
    .update{
        margin-left: 20px;
    }
    .upd{
      margin: 20px; 
      padding: 20px;
    }
    th,td{
        margin:10px 20px 10px 20px;
        padding: 5px;
    }
    .btn{
        padding: 10px;
        background-color: #1252ac;
        border:none;
        border-radius: 5px;
        color: white;
        font-size: 20px;
        cursor: pointer;
        margin: 15px;
    }
    .btn:hover{
         background-color:#ff6600;
    }
    
</style>


<jsp:useBean scope="session" id="pro" class="reuse.Profile" />
    
    


<div class="rtpanel" >
    <h1 style="color:white;margin-left:4%;">Update Your Profile</h1>
    
    <form action="updated.jsp" method="post"  class="upd">
	
        <table  cellspacing="5"  class="update">
				<tr>
					<td>First Name</td>
                                        <td><input type="text" name="fname"  placeholder="Enter First Name" value="<jsp:getProperty property="fname" name="pro"/>" autofocus required /></td>
				</tr>
				<tr>
					<td>Last Name</td>
                                        <td><input type="text" name="lname" placeholder="Last Name" value="<jsp:getProperty property="lname" name="pro"/>" required /></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><input type="email" name="email" placeholder="abc@gmail.com" value="<jsp:getProperty property="email" name="pro"/>" disabled="disabled" /></td>
				</tr>
				
				<tr>
					<td>Contact</td>
                                        <td><input type="number" name="contact" value="<jsp:getProperty name="pro" property="contact" />" required></td>
				</tr>
				<tr>
					<td>Gender</td>
                                        <td><input type="radio" name="rad" value="male" <% if(pro.getGen().equals("male")){%>checked="checked"<%}%> />Male<input type="radio" name="rad" value="female" <% if(pro.getGen().equals("female")){%>checked="checked"<%}%> />Female</td>
				</tr>
				<tr>
					<td>Country</td>
					<td><select name="country" id="coun" required  >
						<option value="india">India</option>
						<option value="pakistan">Pakistan</option>
						<option value="us">United States </option></select>
					</td>
				</tr>
                                <script>
    l=document.getElementById("coun");
    i=0;
    for(;i<l.options.length;i++)
    {
        if(l.options[i].value=="<jsp:getProperty name="pro" property="country"/>")
            break;
    }
    l.selectedIndex=i;
</script>
                                <tr>
                                    <td colspan="2">
                                        <input type=submit value="Save Data" id="btn" class="btn">
                                    </td>
                                </tr>
				</table>
				
				
		</form>
    
</div>

