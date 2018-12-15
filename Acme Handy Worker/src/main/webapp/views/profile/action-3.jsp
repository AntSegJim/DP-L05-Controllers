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

<p><spring:message code="profile.action.3" /></p>
<form:form  modelAttribute="actor">

<form:label path="name"><spring:message code="profile.action.3.changePersonalData" />:</form:label><br /><br />
	<form:hidden path="id"/>
	<form:hidden path="version" />
	
	<form:label path="name"><spring:message code="profile.action.3.name" />:</form:label>
	<form:input path="name"/>
	<form:errors path="name"/>
	<br />
	
	<form:label path="middleName"><spring:message code="profile.action.3.middleName" />:</form:label>
	<form:input path="middleName" />
	<form:errors path="middleName"/>
	<br />
	
	<form:label path="surname"><spring:message code="profile.action.3.surname" />:</form:label>
	<form:input path="surname" />
	<form:errors path="surname"/>
	<br />
	
	<form:label path="email"><spring:message code="profile.action.3.email" />:</form:label>
	<form:input path="email" />
	<form:errors path="email"/>
	<br />
	
	<form:label path="phone"><spring:message code="profile.action.3.phone" />:</form:label>
	<form:input path="phone" />
	<form:errors path="phone"/>
	<br />
	
	<form:label path="address"><spring:message code="profile.action.3.address" />:</form:label>
	<form:input path="address" />
	<form:errors path="address"/>
	<br />
	
	<form:label path="numberSocialProfiles"><spring:message code="profile.action.3.numberSocialProfiles" />:</form:label>
	<form:input path="numberSocialProfiles" />
	<form:errors path="numberSocialProfiles"/>
	<br />
	
	<input type="submit" name="save" value="<spring:message code="profile.action.3.save" />" />
	<br />
	

</form:form>
