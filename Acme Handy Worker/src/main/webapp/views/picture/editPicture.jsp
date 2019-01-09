<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="isAuthenticated()">

<p><spring:message code="picture.edit.title" /></p>

<form:form action="picture/handyWorker/editPicture.do?pictureId=${picture.id}&tutorialId=${tutorialId}" modelAttribute="picture">
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	
<form:label path="picture">
	<spring:message code="picture.picture.form" />
</form:label>
<form:input path="picture" />
<form:errors path="picture"/>

<br />
	
<input type="submit" name="save" value="<spring:message code="picture.save" />" />
<input type="button" name="cancel" value="<spring:message code="picture.cancel" />"
			onclick="javascript: relativeRedir('picture/handyWorker/showPictures.do');" />
	

</form:form>


</security:authorize>