<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="isAuthenticated()">
<p><spring:message code="customer.complaint.title" /></p>

<display:table pagesize="5" name="complaints" id="row"
requestURI="customer/showComplaint.do?complaintId=${row.id}" >

<display:column>
	<form action="customer/editComplaint.do?complaintId=${row.id}">
  	 	<input type="submit" value="<spring:message code="customer.complaint.editComplaint.link" />" />
	</form>
</display:column>

<display:column property="ticker" titleKey="complaint.ticker" />
<display:column property="moment" titleKey="complaint.moment" />
<display:column property="description" titleKey="complaint.description" />
<display:column property="numberAttachments" titleKey="complaint.numberAttachments" />
<display:column property="referee.Id" titleKey="complaint.referee" />
<display:column property="fixUpTask.Id" titleKey="complaint.fixUpTask" />

<display:column>
	<form action="customer/showComplaint.do?complaintId=${row.id}">
  	 	<input type="submit" value="<spring:message code="customer.complaint.showComplaint.link" />" />
	</form>
</display:column>

</display:table>
<form action="customer/createComplaint.do">
  	 	<input type="submit" value="<spring:message code="customer.complaint.create.link" />" />
	</form>

</security:authorize>