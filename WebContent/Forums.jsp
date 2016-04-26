<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.swift.acad.entities.*" %>
<%@ page import="com.swift.acad.db.*" %>
<%@ page import="com.swift.acad.entities.Thread" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="main.css" media="screen">
<title>Forums</title>
</head>
<body>

       <header class="banner">
            Rumblr  
            <% if (session.isNew() || LoginStatus.LOGGED_IN == session.getAttribute("logged_status")) { %>
            <form action="Logout" method="get">
            <button class="logout" type="submit" value="Logout">Logout</button>
            </form>
            <%} %>
        </header> 
	<%ForumDAO f = new ForumDAO(); %>
	<%List<Forum> forums = f.getAll(); %>
	<% for (int i = 0; i< forums.size(); i++) {%>
	<article class="forums">
		<p>	
			<a href="<%= forums.get(i).getForum_name() %>.subforums.jsp">
						<%= forums.get(i).getForum_name() %>
			 </a> 
		 </p>
	</article>
	<%} %>
</body>
</html>