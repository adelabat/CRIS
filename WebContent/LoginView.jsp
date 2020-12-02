<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<html>
<head>
<meta charset="ISO-8859-1">
<title>CRIS</title>
</head>
<body>

<%@ include file = "Header.jsp"%>>
<form action="CreateResearcherServlet" method="post">
        <input type="text" name="id" placeholder="User Id">

        <input type="text" name="name" placeholder="Name">

        <input type="text" name="lastname" placeholder="Last name">

        <input type="text" name="email" placeholder="Email">

        <button type="submit">Create researcher</button>
</form>
</body>
</html>