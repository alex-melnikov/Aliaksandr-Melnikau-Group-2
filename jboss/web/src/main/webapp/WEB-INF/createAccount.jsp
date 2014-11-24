<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="CreateNewAccountServlet.do">
		<label for="name" wicket:id="nameLabel"> Name:</label> 
		<input name="name" type="text" wicket:id="nameInput" /> 
		<label for="balance" wicket:id="balanceLabel"> Balance:</label> 
		<input name="balance" type="text" wicket:id="balanceInput" /> 
		<label for="currency" wicket:id="currencyLabel"> Currency:</label> 
		<input name="currency" type="text" wicket:id="currencyInput" /> 
		<input type="submit" value="create" />
	</form>
	<a href="InputServlet.do">Back to the main page</a>
</body>
</html>