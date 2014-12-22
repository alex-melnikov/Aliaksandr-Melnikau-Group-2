<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:forEach var="project" items="${projects}">
	<form name="updateProject${project.id}" method="post"
		action="<spring:url value="/projects/update/${project.id}" htmlEscape="true" />">

		<label for="id">id</label> <input id="id"
			value='<c:out value="${project.id}"/>'>
		<%-- 		<c:forEach var="employee" items="${project.employees}"> --%>

		<%-- 			<input id="employee_id" value='<c:out value="${employee.id}"/>'> --%>
		<%-- 		</c:forEach> --%>
		<label for="employee_id">employee id</label> <input id="employee_id"
			type="text" size="5" name="employeeID" required /> <input
			type="submit"
			onclick="document.forms['updateProject${project.id}'].submit()"
			value="Update Project" />
	</form>
	<form method="post"
		action="<spring:url value="/projects/remove/${project.id}" htmlEscape="true" />">
		<input type="submit" value="Remove Project" />
	</form>
</c:forEach>
<form action="<spring:url value="/projects/create" htmlEscape="true" />">
	<input type="submit" value="Create Project" />
</form>