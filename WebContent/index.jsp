<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.swift.acad.servlets.*" %>
<%@ page import="com.swift.acad.entities.*" %>
<%@ page import="com.swift.acad.db.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" href="r-icon.png">
        <title>Rumblr</title>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" type="text/css" href="main.css" media="screen">
        <script src="main.js"></script>
    </head>
    <body>
        <header class="banner">
            Rumblr  
            <% if (LoginStatus.LOGGED_IN == session.getAttribute("logged_status") || session.isNew()) { %>
            <form action="Logout" method="get">
            <button class="logout" type="submit" value="Logout">Logout</button>
            </form>
            <%} %>
        </header>
   

 		<% if (session.isNew() || LoginStatus.LOGGED_IN != session.getAttribute("logged_status")) { %>
 		<h1>Welcome be</h1>
        <div class="wrapper">
	        <form class="transparent" action="Login" method="post">
	            Username: <input type="text" name="loguser_name"><br>
	            Password: <input type="password" name="logpassword"><br>
	            <button type="submit" value="login"> Sumbit </button><br>
	          <input type="checkbox" name="remember"> Remember me <div>
	          <a href="https://akphoto3.ask.fm/633/694/215/-349997000-1soacm9-bfg8kl53blkrna0/original/kottakoathumb.png">Forgot your password?</a></div>
	        </form> 
	        <br>
	        <br>
	        <br>
	        <form class="transparent" action="RegisterPage" method="post">
	            You new here? Don't worry fam we gotchu. <br>
	            <input type="text" name="loguser_name" placeholder="Your username"><br>
	            <input type="text" name="email" placeholder="Your email"><br>
	            <input type="password" name="passwod" placeholder="The password"><br>                
	            <input type="password" name="password" placeholder="The password...again"> <br> 
	            <button type="submit" value="register"> Sumbit </button>
	        </form>
        </div>
   <% }else{ %>
             <h1>Welcome be 	
		  <% User user =(User)session.getAttribute("loguser_name"); %>
		   <%= user.getUser_name() %>
			<c:out value="${sessionScope.user.username }"/>
 		</h1>
        <% } %> 
        <footer>
            <p>Copyright: Toni Tenev (c) 2016 version 0.0.1</p>
        </footer>
    </body>
</html>