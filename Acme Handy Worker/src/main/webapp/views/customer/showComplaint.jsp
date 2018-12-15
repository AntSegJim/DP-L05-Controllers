<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="isAuthenticated()">
<p><spring:message code="customer.complaint.showComplaint.title" /></p>

<spring:message code="customer.complaint.ticker" /> ${complaint.ticker} <br/>
<spring:message code="customer.complaint.moment" /> ${complaint.moment} <br/>
<spring:message code="customer.complaint.description" /> ${complaint.description} <br/>
<spring:message code="customer.complaint.numberAttachments" /> ${complaint.numberAttachments} <br/>

<jstl:set var="referee" value="${complaint.referee} "/>
<p><spring:message code="customer.complaint.referee.title" /></p>
<spring:message code="customer.complaint.referee.name" /> ${referee.name} <br/>
<spring:message code="customer.complaint.referee.middleName" /> ${referee.middleName} <br/>
<spring:message code="customer.complaint.referee.surname" /> ${referee.surname} <br/>
<spring:message code="customer.complaint.referee.address" /> ${referee.address} <br/>
<spring:message code="customer.compalint.referee.photo" /> ${referee.photo} <br/>
<spring:message code="customer.complaint.referee.phone" /> ${referee.phone} <br/>
<spring:message code="customer.complaint.referee.email" /> ${referee.email} <br/>
<spring:message code="customer.complaint.referee.numberSocialProfiles" /> ${referee.numberSocialProfiles} <br/>

<jstl:set var="fixUpTask" value="${complaint.fixUpTask} "/>
<p><spring:message code="customer.complaint.fixUpTask.title" /></p>
<spring:message code="customer.complaint.fixUpTask.ticker" /> ${fixUpTask.ticker} <br/>
<spring:message code="customer.complaint.fixUpTask.moment" /> ${fixUpTask.moment} <br/>
<spring:message code="customer.complaint.fixUpTask.description" /> ${fixUpTask.description} <br/>
<spring:message code="customer.complaint.fixUpTask.address" /> ${fixUpTask.address} <br/>
<spring:message code="customer.complaint.fixUpTask.maximumPrice" /> ${fixUpTask.maximumPrice} <br/>
<spring:message code="customer.complaint.fixUpTask.periodTime" /> ${fixUptask.periodTime} <br/>
<spring:message code="customer.complaint.fixUpTask.category" /> ${fixUpTask.category} <br/>
<spring:message code="customer.complaint.fixUpTask.warranty" /> ${fixUpTask.warranty} <br/>


</security:authorize>