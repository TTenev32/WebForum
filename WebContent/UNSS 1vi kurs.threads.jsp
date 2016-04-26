<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.swift.acad.entities.*" %>
<%@ page import="com.swift.acad.db.*" %>
<%@ page import="java.util.List" %>
<%@ page import="com.swift.acad.entities.Thread" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="main.css" media="screen">
<%SubforumDAO sf = new SubforumDAO(); %>
<title><%=sf.geSubtForum(4).getSubforum_name() %></title>
</head>
<body>
	
	<% List<Thread> threads = sf.getSuborumThreads(4); %>
	<header class="banner">
            Rumblr  ---
            <%=sf.geSubtForum(4).getSubforum_name() %>
            <% if (session.isNew() || LoginStatus.LOGGED_IN == session.getAttribute("logged_status")) { %>
            <form action="Logout" method="get">
            <button class="logout" type="submit" value="Logout">Logout</button>
            </form>
            <%} %>
        </header> 

	<% for (int i = 0; i< threads.size(); i++) {%>
	<% UserDAO threadUser = new UserDAO(); %>
	<article class="threads">
		<p>	
			<a href="<%= threads.get(i).getThread_name() %>.jsp">
						<%= threads.get(i).getThread_name() %>
			 </a> 
		 </p>
	 	 <%String username = threadUser.getUser(threads.get(i).getUser_id()).getUser_name(); %> 
		<p class="author">Created on <%= threads.get(i).getCreated_at() %> by <%= username %></p>
		
		<%ThreadDAO thr = new ThreadDAO(); %>
		<% List<Message> messages = thr.getThreadMessages(threads.get(i).getThread_id()); %>
		<% for (int j = 0; j< messages.size(); j++) {%>
			<div class="messages">
				<p>	
					<%= messages.get(j).getMessage() %>
				 </p>
			 	 <%String msgUsername = threadUser.getUser(messages.get(j).getUser_id()).getUser_name(); %> 
				<p class="author">Created on <%= messages.get(j).getCreated_at() %> by <%= msgUsername %></p>
			</div>
		<%} %>
	<form class="transparent" action="CreateSubforumMessage" method="post">
		<p class="createmsg">Add message to Thread</p>
		<input type="hidden" name="viewid" value="<%=sf.geSubtForum(4).getSubforum_name() %>.threads.jsp">
		<input type="hidden" name="thread_id" value="<%=threads.get(i).getThread_id() %>">
		<textarea rows="2" cols="60" name="message">	
		</textarea><br>
		  <button type="submit" value="createSubforumMessage"> Sumbit </button>	
		</form>
	</article>
		<br>
	<%} %>
	
	<form class="thrform transparent" action="CreateSubforumThread" method="post">
		<p class="createth">Create a new Thread</p>
		<input type="hidden" name="viewid" value="<%=sf.geSubtForum(4).getSubforum_name() %>.threads.jsp">
		<input type="hidden" name="subforum_id" value="<%=sf.geSubtForum(4).getSubforum_id() %>">
		<textarea rows="5" cols="65" name="thread">
	
	</textarea><br>
	  <button type="submit" value="createSubforumThread"> Sumbit </button>	
	</form>

</body>
</html>