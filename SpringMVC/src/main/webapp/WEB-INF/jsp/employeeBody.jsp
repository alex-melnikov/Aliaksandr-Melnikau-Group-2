<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<table align="center" border="3">
	<thead>
		<tr>
			<td width="100">
				<h3>ID</h3>
			</td>
			<td width="100">
				<h3>First Name</h3>
			</td>
			<td width="100">
				<h3>Last Name</h3>
			</td width="100">
			<td>
				<h3>Status</h3>
			</td>
			<td width="100">
				<h3>City</h3>
			</td>
			<td width="100">
				<h3>Street</h3>
			</td>
			<td width="100">
				<h3>Project ID's</h3>
			</td>
			<td width="100">
				<h3>Unit ID's</h3>
			</td>
			<td>
				<h3>Actions</h3>
			</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="employee" items="${employees}">
			<tr>
				<td><c:out value="${employee.id}" /></td>
				<form name="updateEmployee${employee.id}" method="post"
					action="<spring:url value="/employees/update/${employee.id}" htmlEscape="true" />">
					<td><c:out value="${employee.firstName}" /> <input type="text"
						size="20" name="firstName" /></td>
					<td><c:out value="${employee.lastName}" /> <input type="text"
						size="20" name="lastName" /></td>
					<td><c:out value="${employee.status}" /> <select name="status">
							<option value=""></option>
							<option value="FULL_TIME_EMPLOYE">FULL_TIME_EMPLOYE</option>
							<option value="PART_TIME_EMPLOYE">PART_TIME_EMPLOYE</option>
					</select></td>
					<td><c:out value="${employee.address.city}" /> <input
						type="text" size="20" name="city" /></td>
					<td><c:out value="${employee.address.street}" /> <input
						type="text" size="20" name="street" /></td>
					<td>
						<table>
							<c:forEach var="project" items="${employee.projects}">
								<tr>
									<td><c:out value="${project.id}" /></td>
								</tr>
							</c:forEach>
						</table> <input type="number" size="5" name="projectID" />
					</td>
					<td><c:out value="${employee.unit.id}" /> <input type="number"
						size="5" name="unitID" /></td>
				</form>
				<td><input type="submit"
					onclick="document.forms['updateEmployee${employee.id}'].submit()"
					value="Update Employee" />
					<form method="post"
						action="<spring:url value="/employees/remove/${employee.id}" htmlEscape="true" />">
						<input type="submit" value="Remove Employee" />
					</form></td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="10">
				<form
					action="<spring:url value="/employees/create" htmlEscape="true" />">
					<input type="submit" value="Create Employee" />
				</form>
			</td>
		</tr>
	</tbody>
</table>