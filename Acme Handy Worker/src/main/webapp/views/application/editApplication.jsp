<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="isAuthenticated()">

<p><spring:message code="handyWorker.application.form.edit.title" /></p>

<form:form action="application/handyWorker/editApplication.do" modelAttribute="application">
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	
<form:label path="moment">
	<spring:message code="handyWorker.application.momment" />
</form:label>
<form:input path="moment" />
<form:errors path="moment"/>

<br />

<form:label path="status">
	<spring:message code="handyWorker.application.status" />
</form:label>
<form:input path="status" />
<form:errors path="status"/>

<br />

<form:label path="price">
	<spring:message code="handyWorker.application.price" />
</form:label>
<form:input path="price" />
<form:errors path="price"/>

<br />

<form:label path="comments">
	<spring:message code="handyWorker.application.comments" />
</form:label>
<form:input path="comments" />
<form:errors path="comments"/>

<br />

<!-- Faltan creditCard y fixUpTask -->

<form:label path="creditCard">
	<spring:message code="handyWorker.application.creditCard" />
</form:label>
<form:select id="creditCards" path="creditCard">
		<form:option value="0" label="----" />		
		<form:options items="${creditCards}" itemValue="id"
			itemLabel="number" />
	</form:select>
<form:errors path="creditCard"/>

<br />

<form:label path="fixUpTask">
	<spring:message code="handyWorker.application.fixUpTask" />
</form:label>
<form:select id="fixUpTasks" path="fixUpTask">
		<form:option value="0" label="----" />		
		<form:options items="${fixUpTasks}" itemValue="id"
			itemLabel="ticker" />
	</form:select>
<form:errors path="fixUpTask"/>

<br />
	
<input type="submit" name="save" value="<spring:message code="handyWorker.application.save" />" />
<input type="button" name="cancel" value="<spring:message code="handyWorker.application.cancel" />"
			onclick="javascript: relativeRedir('application/handyWorker/applications.do');" />
	

</form:form>


</security:authorize>