<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
<meta charset="ISO-8859-1">
<%@ include file = "Header.jsp" %>
<title>Investigador</title>
</head>
<body>



<table>
<tr>
<td>${researcher.id}</td>
<td>${researcher.name}</td>
<td>${researcher.lastname}</td>
<td><a href="${researcher.scopusURL}">${researcher.scopusURL}</a></td>
<td>${researcher.email}</td>
</tr>
</table>


<table>
<tr>
<th>Publicaciones</th>
</tr>
<c:forEach items="${publications}" var="pi">
        <tr>
            <td> <a href="PublicationServlet?id=${pi.id}"> ${pi.id} </a></td>
                
        </tr>
</c:forEach>
</table>

</body>
</html>