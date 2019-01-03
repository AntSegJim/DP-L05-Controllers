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

<fieldset>
<legend><spring:message code="administrator.FixUp" /></legend>
<b><spring:message code="administrator.max" /></b>: ${fixUp7 }<br/>
<b><spring:message code="administrator.min" /></b>: ${fixUp6 }<br/>
<b><spring:message code="administrator.avg" /></b>: ${fixUp5 }<br/>
<b><spring:message code="administrator.desv" /></b>: ${fixUp8 }
</fieldset>

<fieldset>
<legend><spring:message code="administrator.FixUpApp" /></legend>
<b><spring:message code="administrator.max" /></b>: ${fixUp1 }<br/>
<b><spring:message code="administrator.min" /></b>: ${fixUp2 }<br/>
<b><spring:message code="administrator.avg" /></b>: ${fixUp3 }<br/>
<b><spring:message code="administrator.desv" /></b>: ${fixUp4 }
</fieldset>