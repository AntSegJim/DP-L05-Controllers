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

<p><spring:message code="profile.action.1" /></p>
<display:table pagesize="5" name="boxes" id="row"
requestURI="messageBox/show.do" >

<display:column property="name" titleKey="messageBox.name" />
<display:column> <a href="message/show.do?messageBoxId=${row.id}"><spring:message code="messageBox.show.message" /></a> </display:column>
	<display:column>
	
		<form action="messageBox/delete.do">
  	 	 <input type="submit" value="<spring:message code="messageBox.delete" />" />
		</form>
	</display:column>

<jstl:if test="!(name == In box || name = Out box || name = Trash box || name = Spam box)">
	<display:column>
	
		<form action="messageBox/modified.do">
  	 	 <input type="submit" value="<spring:message code="messageBox.modified" />" />
		</form>
	</display:column>
</jstl:if>

</display:table>

<form action="messageBox/create.do">
    <input type="submit" value="<spring:message code="messageBox.create" />" />
</form>

<input type="button" name="cancel" value="<spring:message code="messageBox.create.cancel" />"
			onclick="javascript: relativeRedir('welcome/index.do');" />

</security:authorize>

<%-- <security:authorize access="isAnonymous()">
<p>Hola</p>
</security:authorize>--%>
