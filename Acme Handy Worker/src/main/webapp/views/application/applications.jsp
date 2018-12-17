<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="isAuthenticated()">
<p><spring:message code="handyWorker.application.title" /></p>

<display:table pagesize="5" name="applications" id="row"
requestURI="${requestURI}" >

<display:column>
	<a href="application/handyWorker/editApplication.do?applicationId=${row.id}"><spring:message code="handyWorker.application.editApplication.link" /></a>
</display:column>

<display:column property="moment" titleKey="application.moment" />
<display:column property="status" titleKey="application.status" />
<display:column property="price" titleKey="application.price" />
<display:column property="comments" titleKey="application.comments" />
<display:column property="creditCard.number" titleKey="application.creditCard" />
<display:column property="fixUpTask.id" titleKey="application.fixUpTask" />

</display:table>
<form action="application/handyWorker/createApplication.do">
  	 	<input type="submit" value="<spring:message code="handyWorker.application.create.link" />" />
	</form>

</security:authorize>