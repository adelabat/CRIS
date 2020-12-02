<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
<meta charset="ISO-8859-1">
<%@ include file = "Header.jsp" %>
<title>Lista de investigadores</title>
</head>
<body>

<table>
<tr>
<th>Id</th><th>Name</th><th>Last name</th><th>URL</th><th>Email</th>
</tr>
<c:forEach items="${researcherslist}" var="ri">
        <tr>
            <td> <a href="ResearcherServlet?id=${ri.id}"> ${ri.id} </a></td>
                <td>${ri.name}</td>
                <td>${ri.lastname}</td>
                <td> <a href="${ri.scopusURL}">${ri.scopusURL}</a></td>
                <td>${ri.email}</td>
        </tr>
</c:forEach>
</table>

</body>
</html>