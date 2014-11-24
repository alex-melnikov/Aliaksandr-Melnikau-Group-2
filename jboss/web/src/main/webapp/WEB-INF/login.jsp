<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<form action="GetAccountServlet.do">
	<label for="id" wicket:id="idLabel">Account id:</label>
	<input name="id" type="text" wicket:id="idInput" /> 
	<input type="submit" value="Login" />
</form>