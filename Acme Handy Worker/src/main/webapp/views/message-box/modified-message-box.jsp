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

<security:authorize access="isAuthenticated()">

<p><spring:message code="messageBox.create" /></p>

<form:form action="messageBox/modified.do" modelAtributte ="messageBox">

<form:label path="name">
	<spring:message code="messageBox.modified.name" />
</form:label>
<form:input path="name" />
<form:errors path="name"/>

<input type="submit" name="save" value="<spring:message code="messageBox.modified.save" />" />
<input type="button" name="cancel" value="<spring:message code="messageBox.create.cancel" />"
			onclick="javascript: relativeRedir('messageBox/show.do');" />

</form:form>

</security:authorize>

<%-- <security:authorize access="isAnonymous()">
<p>Hola</p>
</security:authorize>--%>
