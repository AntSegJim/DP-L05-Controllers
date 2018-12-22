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

<display:table pagesize="5" name="messages" id="row"
requestURI="messageBox/actor/show.do" >

<%-- <fmt:formatDate value="${date}" pattern="yyyy" /> --%>
<display:column property="moment" titleKey="message.moment"  />
<display:column property="subject" titleKey="message.subject"  />
<display:column property="tag" titleKey="message.tag"  />
<display:column property="emailReceiver" titleKey="message.emailReceiver"  />


</display:table>

</security:authorize>