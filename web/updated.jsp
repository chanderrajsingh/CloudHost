
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"  %>


<sql:setDataSource driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/cloudhost" user="root" password="12345678"  var="con"/>

<sql:update dataSource="${con}" >
    update userinfo set fname=?, lname=?, contact=?, gen=?, country=? where email=?
    
    <sql:param value="${param.fname}"/>
    <sql:param value="${param.lname}"/>
    <sql:param value="${param.contact}"/>
    <sql:param value="${param.rad}"/>
    <sql:param value="${param.country}"/>
    <sql:param value="${sessionScope['email']}" />
</sql:update>
   
    <jsp:useBean id="pro" scope="session" class="reuse.Profile" />
    <jsp:setProperty name="pro" property="fname" param="fname"/>
    <jsp:setProperty name="pro" property="lname" param="lname"/>
    <jsp:setProperty name="pro" property="contact" param="contact"/>
    <jsp:setProperty name="pro" property="gen" param="rad"/>
    <jsp:setProperty name="pro" property="country" param="country"/>
    
    <% session.setAttribute("name",request.getParameter("fname")+" "+request.getParameter("lname")); %>
    
       <c:redirect url="updateprofile" >
    </c:redirect>  