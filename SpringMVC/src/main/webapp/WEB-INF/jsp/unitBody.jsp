<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:forEach var="unit" items="${units}">
	<label for="id">id</label>
	<input id="id" value='<c:out value="${unit.id}"/>'>
	<form name="updateUnit${unit.id}" method="post"
		action="<spring:url value="/units/update/${unit.id}" htmlEscape="true" />">
		<%-- 		<c:forEach var="employee" items="${unit.employees}"> --%>

		<%-- 			<input id="employee_id" value='<c:out value="${employee.id}"/>'> --%>
		<%-- 		</c:forEach> --%>
		<label for="employee_id">employee id</label><input id="employee_id"
			type="text" size="5" name="employeeID" />
	</form>
	<input type="submit"
		onclick="document.forms['updateUnit${unit.id}'].submit()"
		value="Update Unit" />
	<form method="post"
		action="<spring:url value="/units/remove/${unit.id}" htmlEscape="true" />">
		<input type="submit" value="Remove Unit" />
	</form>
</c:forEach>
<form action="<spring:url value="/units/create" htmlEscape="true" />">
	<input type="submit" value="Create Unit" />
</form>