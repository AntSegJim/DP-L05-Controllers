<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="isAuthenticated()">

<p><spring:message code="customer.complaint.form.edit.title" /></p>

<form:form action="customer/editComplaint.do" modelAttribute="complaint">
	
<form:label path="moment">
	<spring:message code="customer.complaint.moment" />
</form:label>
<form:input path="moment" />
<form:errors path="moment"/>

<form:label path="description">
	<spring:message code="customer.complaint.description" />
</form:label>
<form:input path="description" />
<form:errors path="description"/>

<form:label path="numberAttachments">
	<spring:message code="customer.complaint.numberAttachments" />
</form:label>
<form:input path="numberAttachments" />
<form:errors path="numberAttachments"/>

<!-- Faltan referee y fixUpTask -->
	
<input type="submit" name="save" value="<spring:message code="customer.complaint.save" />" />
<input type="button" name="cancel" value="<spring:message code="customer.complaint.cancel" />"
			onclick="javascript: relativeRedir('customer/showComplaint.do');" />
	

</form:form>


</security:authorize>