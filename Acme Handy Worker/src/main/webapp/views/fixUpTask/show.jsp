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



<security:authorize access="isAuthenticated()">


<spring:message code="fixUpTask.moment" /> ${fixUpTask.moment} <br/>
<spring:message code="fixUpTask.description" /> ${ficUpTask.description} <br/>
<spring:message code="fixUpTask.address" /> ${fixUpTask.address} <br/>
<spring:message code="fixUpTask.maximunPrice" /> ${fixUpTask.maximunPrice} <br/>
<spring:message code="fixUpTask.periodTime" /> ${fixUpTask.periodTime} <br/>
<spring:message code="fixUpTask.category" /> ${fixUpTask.category} <br/>
<spring:message code="fixUpTask.warranty" /> ${fixUpTask.warranty} <br/>
<spring:message code="fixUpTask.customer" /> ${fixUpTask.customer} <br/>

<spring:message code="fixUpTask.application" />
<display:table name="${fixUpTask.application}" id="row">
<display:column property="moment" titleKey="fixUpTask.application.moment" />
<display:column property="status" titleKey="fixUpTask.application.status" />
<display:column property="price" titleKey="fixUpTask.application.price" />

</display:table>

	<input type="submit" name="save"
		value="<spring:message code="fixUpTask.save" />" />&nbsp; 
	<jstl:if test="${fixUpTask.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="fixUpTask.delete" />"
			onclick="return confirm('<spring:message code="fixUpTask.confirm.delete" />')" />&nbsp;
		</jstl:if>
<input type="button" name="return" value="<spring:message code="fixUpTask.return" />"
			onclick="javascript: relativeRedir('fixUpTask/customer/list.do');" />

</security:authorize>


