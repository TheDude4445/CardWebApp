<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action = "EditCardServlet" method="post">
number: <input type ="text" name = "cardNumber" value= "${cardToEdit.cardNumber}">
name: <input type = "text" name = "cardName" value= "${cardToEdit.cardName}">
<input type = "hidden" name = "id" value="${cardToEdit.id}">
<input type = "submit" value="Save Edited Card">
</form>
</body>
</html>