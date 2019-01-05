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

<security:authorize access="hasRole('ADMIN')">

<img src="${actor.photo}"> <br/>
<b><spring:message code="actor.name" />:</b>${actor.name } <br/>
<b><spring:message code="actor.middleName" />:</b>${actor.middleName } <br/>
<b><spring:message code="actor.surname" />:</b>${actor.surname } <br/>
<b><spring:message code="actor.email" />:</b>${actor.email } <br/>
<b><spring:message code="actor.phone" />:</b>${actor.phone } <br/>
<b><spring:message code="actor.address" />:</b>${actor.address } <br/>
<b><spring:message code="actor.numberSocialProfiles" />:</b>${actor.numberSocialProfiles } <br/>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>


<form:form action="${action }" modelAttribute="actor">

<form:hidden path="id"/>
<form:hidden path="version"/>
<form:hidden path="name"/>
<form:hidden path="middleName"/>
<form:hidden path="surname"/>
<form:hidden path="photo"/>
<form:hidden path="email"/>
<form:hidden path="phone"/>
<form:hidden path="address"/>
<form:hidden path="numberSocialProfiles"/>
<form:hidden path="userAccount"/>

<jstl:choose>

<jstl:when test = "${fn:contains(row.userAccount.authorities, 'CUSTOMER')}">
<form:hidden path="score"/>
<form:hidden path="endorseCustomer"/>
<form:hidden path="receiveEndorseFromCustomer"/>
</jstl:when>

<jstl:when test = "${fn:contains(row.userAccount.authorities, 'HANDYWORKER')}">
<form:hidden path="makeHandyWorker"/>
<form:hidden path="score"/>
<form:hidden path="endorseHWorker"/>
<form:hidden path="receiveEndorseFromHWorker"/>
<form:hidden path="application"/>
<form:hidden path="finder"/>
</jstl:when>

</jstl:choose>

<form:label path="isBanned"><spring:message code="actor.isBanned" />:</form:label>
	<form:select path="isBanned">
		<form:option value="0" label="----" />	
		<form:option value="1" label="Ban" />	
	</form:select>
	<form:errors path="isBanned"/>
	<br />

<input type="submit" name="save" 
	value="<spring:message code="actor.save" />" />

<input type="button" name="cancel" value="<spring:message code="category.cancel" />"
			onclick="javascript: relativeRedir('suspiciousActor/administrator/list.do');" />
</form:form>

</security:authorize>
