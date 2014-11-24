<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account</title>
</head>
<body>
	<c:out value="${account}" />
	<br>
	<form action="ExchangeServlet.do">
		<label for="currency" wicket:id="currencyLabel" Cyrrency:</label>
		<input name="currency" type="text" wicket:id="currencyInput" /> 
		<input name="accountId" value="${account.id}" type="hidden">
		<input type="submit" value="exchange" />
	</form>
	<a href="InputServlet.do">Back to the main page</a>
</body>
</html>