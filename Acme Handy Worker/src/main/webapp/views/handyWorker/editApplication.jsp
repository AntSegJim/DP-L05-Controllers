<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="isAuthenticated()">

<p><spring:message code="handyWorker.application.form.edit.title" /></p>

<form:form  modelAttribute="application">
	
<form:label path="momment">
	<spring:message code="handyWorker.application.momment" />
</form:label>
<form:input path="momment" />
<form:errors path="momment"/>

<form:label path="status">
	<spring:message code="handyWorker.application.status" />
</form:label>
<form:input path="status" />
<form:errors path="status"/>

<form:label path="price">
	<spring:message code="handyWorker.application.price" />
</form:label>
<form:input path="price" />
<form:errors path="price"/>

<form:label path="comments">
	<spring:message code="handyWorker.application.comments" />
</form:label>
<form:input path="comments" />
<form:errors path="comments"/>

<!-- Faltan creditCard y fixUpTask -->
	
<input type="submit" name="save" value="<spring:message code="handyWorker.application.save" />" />
<input type="button" name="cancel" value="<spring:message code="handyWorker.application.cancel" />"
			onclick="javascript: relativeRedir('handyWorker/showApplication.do');" />
	

</form:form>


</security:authorize>