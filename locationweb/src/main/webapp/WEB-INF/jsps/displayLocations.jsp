<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All location page</title>
</head>

<body>

<h2>Locations:</h2>

<table>
<tr>
<th>id</th>
<th>code</th>
<th>name</th>
<th>type</th>
</tr>
<tr>
<c:forEach items="${locations}" var="location">
<tr>
<td>${location.id}</td>
<td>${location.code}</td>
<td>${location.name}</td>
<td>${location.type}</td>
<td><a href="deleteLocation?id=${location.id}">delete</a></td>
<td><a href="showUpdate ?id=${location.id}">edit</a></td>
</tr>
</c:forEach>
</tr>
</table>
<a href="showCreate">Add Location</a>
</body>
</html>