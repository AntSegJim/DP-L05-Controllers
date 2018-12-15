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

<p><spring:message code="profile.action.2"/></p>

<security:authorize access="isAuthenticated()">

<img src="${actor.photo}">  <br/>
<spring:message code="profile.action.2.name" /> ${actor.name} <br/>
<spring:message code="profile.action.2.middleName" /> ${actor.middleName} <br/>
<spring:message code="profile.action.2.surname" /> ${actor.surname} <br/>
<spring:message code="profile.action.2.email" /> ${actor.email} <br/>
<spring:message code="profile.action.2.phone" /> ${actor.phone} <br/>
<spring:message code="profile.action.2.address" /> ${actor.address} <br/>
<spring:message code="profile.action.2.numberSocial" /> ${actor.numberSocialProfiles} <br/>
<%-- que hemos con la cuenta  --%>
<spring:message code="profile.action.2.socialProfile" />
<display:table name="${actor.profileSocialNetwork}" id="row">
<display:column property="nickName" titleKey="profile.nickname" />
<display:column property="nameSocialNetwork" titleKey="profile.socialNetwork" />
<display:column property="linkProfile" titleKey="profile.link" />

</display:table>

</security:authorize>

<br/>
<form action="profile/action-3.do">
    <input type="submit" value="<spring:message code="profile.edit.profile" />" />
</form>

<security:authorize access="hasRole('CUSTOMER')">
<form action="fixUpTask/customer/list-fixUpTask.do">
    <input type="submit" value="Ver lista de fixUpTask" />
</form>
</security:authorize>
