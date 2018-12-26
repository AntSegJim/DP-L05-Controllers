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

<p><spring:message code="endorsement.create" /></p>

<jstl:if test="${not empty exception}">
		<p style="color:red;font-weight: bold;" > <spring:message code="endorsement.error" /> </p>
</jstl:if>

<form:form action="endorsement/customer,handy-worker/edit.do" modelAttribute="endorsement">
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="moment"/>
	<form:hidden path="handyWorkerSender"/>
	<form:hidden path="customerSender"/>
	
	<jstl:choose>
    	<jstl:when test="${endorsement.handyWorkerSender.email==myEmail}">
			<form:label path="customerReceiver"><spring:message code="endorsement.receiver.customer"/>:</form:label>
			<form:select path="customerReceiver">
				<form:options items="${customerReceivers}" itemLabel="correo" itemValue="id"/>
			</form:select>
    	</jstl:when>    
    	<jstl:otherwise>
     	    <form:label path="handyWorkerReceiver"><spring:message code="endorsement.receiver.handy"/>:</form:label>
			<form:select path="handyWorkerReceiver">
				<form:options items="${handyWorkerReceivers}" itemLabel="email" itemValue="id"/>
			</form:select>
    	</jstl:otherwise>
	</jstl:choose>
	<br>
	<form:label path="comments">
	<spring:message code="endorsement.comments"/>:
	</form:label>
	<form:input path="comments"/>
	<form:errors cssClass="error" path="comments"/>
		
	<br /><br />
	<input type="submit" name="save" 
	value="<spring:message code="endorsement.save" />" />
</form:form>

<div style="text-align:center;">
	<a href="endorsement/customer,handy-worker/list.do">
		<spring:message code="endorsement.cancel" />
	</a>
</div>
</security:authorize>


