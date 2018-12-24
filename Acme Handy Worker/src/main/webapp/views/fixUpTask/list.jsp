<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('CUSTOMER')">

	<p><spring:message code="fixUpTask.list" /></p>
	<display:table pagesize="5" name="fixUpTasks" id="row"
		requestURI="fix-up-task/customer/list.do">

		<display:column>
			<form action="fix-up-task/customer/edit.do?fixUpTaskId=${row.id}">
				<input type="submit"
					value="<spring:message code="fixUpTask.edit" />" />
			</form>
		</display:column>

		<display:column property="moment" titleKey="fixUpTask.moment"
			sortable="true" format="{0,date,dd/MM/yyyy HH:mm}" />
		<display:column property="description"
			titleKey="fixUpTask.description" />
		<display:column property="address" titleKey="fixUpTask.address" />

		<display:column>

			<a
				href="fixUpTask/customer,handyWorker/show.do?fixUpTaskId=${row.id}">
				<spring:message code="fixUpTask.show" />
			</a>

		</display:column>

	</display:table>


	<input type="button" name="create"
		value="<spring:message code="fixUpTask.create" />"
		onclick="javascript: relativeRedir('fix-up-task/customer/create.do');" />

</security:authorize>

