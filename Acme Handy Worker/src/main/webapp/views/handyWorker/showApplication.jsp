<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="isAuthenticated()">
<p><spring:message code="handyWorker.application.showApplication.title" /></p>

<spring:message code="handyWorker.application.momment" /> ${application.momment} <br/>
<spring:message code="handyWorker.application.status" /> ${application.status} <br/>
<spring:message code="handyWorker.application.price" /> ${application.price} <br/>
<spring:message code="handyWorker.application.comments" /> ${application.comments} <br/>

<jstl:set var="creditCard" value="${application.creditCard} "/>
<p><spring:message code="handyWorker.application.creditCard.title" /></p>
<spring:message code="handyWorker.application.creditCard.holderName" /> ${creditCard.holderName} <br/>
<spring:message code="handyWorker.application.creditCard.brandName" /> ${creditCard.brandName} <br/>
<spring:message code="handyWorker.application.creditCard.number" /> ${creditCard.number} <br/>
<spring:message code="handyWorker.application.creditCard.expirationMonth" /> ${creditCard.expirationMonth} <br/>
<spring:message code="handyWorker.application.creditCard.expirationYear" /> ${creditCard.expirationYear} <br/>
<spring:message code="handyWorker.application.creditCard.CW" /> ${creditCard.CW} <br/>

<jstl:set var="fixUpTask" value="${application.fixUpTask} "/>
<p><spring:message code="handyWorker.application.fixUpTask.title" /></p>
<spring:message code="handyWorker.application.fixUpTask.ticker" /> ${fixUpTask.ticker} <br/>
<spring:message code="handyWorker.application.fixUpTask.moment" /> ${fixUpTask.moment} <br/>
<spring:message code="handyWorker.application.fixUpTask.description" /> ${fixUpTask.description} <br/>
<spring:message code="handyWorker.application.fixUpTask.address" /> ${fixUpTask.address} <br/>
<spring:message code="handyWorker.application.fixUpTask.maximumPrice" /> ${fixUpTask.maximumPrice} <br/>
<spring:message code="handyWorker.application.fixUpTask.periodTime" /> ${fixUptask.periodTime} <br/>
<spring:message code="handyWorker.application.fixUpTask.category" /> ${fixUpTask.category} <br/>
<spring:message code="handyWorker.application.fixUpTask.warranty" /> ${fixUpTask.warranty} <br/>
<spring:message code="handyWorker.application.fixUpTask.customer" /> ${fixUpTask.customer} <br/>




</security:authorize>