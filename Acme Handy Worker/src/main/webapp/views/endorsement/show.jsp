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

<security:authorize access="hasAnyRole('CUSTOMER','HANDYWORKER')">

	<p>
		<spring:message code="endorsement.show" />
	</p>

	<spring:message code="endorsement.moment" />: ${endorsement.moment} <br />
	<security:authorize access="hasRole('HANDYWORKER')">
		<spring:message code="endorsement.sender" />: ${endorsement.handyWorkerSender.name} -> ${endorsement.handyWorkerSender.email} <br />
		<spring:message code="endorsement.receiver" />: ${endorsement.customerReceiver.name} -> ${endorsement.customerReceiver.email} <br />
	</security:authorize>
	<security:authorize access="hasRole('CUSTOMER')">
		<spring:message code="endorsement.sender" />: ${endorsement.customerSender.name} -> ${endorsement.customerSender.email} <br />
		<spring:message code="endorsement.receiver" />: ${endorsement.handyWorkerReceiver.name} -> ${endorsement.handyWorkerReceiver.email} <br />
	</security:authorize>
	<spring:message code="endorsement.comments" />: ${endorsement.comments} <br />

	<br>
	<div style="text-align: center;">
		<a href="endorsement/customer,handy-worker/list.do"> <spring:message
				code="endorsement.back" />
		</a>
	</div>

</security:authorize>


