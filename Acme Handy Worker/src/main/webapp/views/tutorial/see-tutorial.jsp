<%--
 * action-2.jsp
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
<security:authorize access="hasRole('HANDYWORKER')">

<p><spring:message code="tutotial" /></p>
<display:table pagesize="5" name="tutorials" id="row"
requestURI="tutorial/handyWorker/show.do" >

<display:column property="title" titleKey="tutorial.title" />
<display:column property="moment" titleKey="tutotial.moment" />
<display:column property="summary" titleKey="tutotial.summary" />
	<display:column>
		<form action="picture/handyWorker/show.do?tutorialId=${row.id}">
  	 	 <input type="submit" value="<spring:message code="tutorial.seePicture" />" />
		</form>
	</display:column>
</display:table>

<form action="tutorial/handyWorker/create.do">
    <input type="submit" value="<spring:message code="tutorial.create" />" />
    <input type="button" name="cancel" value="<spring:message code="tutorial.cancel" />"
			onclick="javascript: relativeRedir('welcome/index.do');" />
</form>



</security:authorize>
