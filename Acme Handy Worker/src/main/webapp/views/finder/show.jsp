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

<security:authorize access="hasRole('HANDYWORKER')">

<p><spring:message code="finder.show" /></p>
<display:table pagesize="5" name="finder" id="row" requestURI="finder/show.do" >

<display:column property="ticker" titleKey="filter.ticker" />
<display:column property="description" titleKey="filter.description"/>
<display:column property="address" titleKey="filter.address" />
<display:column property="startDate" titleKey="filter.startDate" />
<display:column property="endDate" titleKey="filter.endDate" />
<display:column property="lowPrice" titleKey="filter.lowPrice" />
<display:column property="highPrice" titleKey="filter.highPrice" />
<display:column property="category.name" titleKey="filter.category" />
<display:column property="warranty.title" titleKey="filter.warranty" />

</display:table>

<div style="text-align:center;">
	<a href="finder/handy-worker/list.do" ><spring:message code="filter.busqueda-anterior" /></a>
</div>
<form:form action="finder/handy-worker/save.do" modelAttribute="finder">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="moment"/>
	
	<form:label path="ticker">
	<spring:message code="filter.ticker"/>:
	</form:label>
	<form:input path="ticker"/>
	<form:errors cssClass="error" path="ticker"/>
	<br />
	
	<form:label path="description">
	<spring:message code="filter.description"/>:
	</form:label>
	<form:input path="description"/>
	<form:errors cssClass="error" path="description"/>
	<br />
	
	<form:label path="address">
	<spring:message code="filter.address"/>:
	</form:label>
	<form:input path="address"/>
	<form:errors cssClass="error" path="address"/>
	<br />
	
	<form:label path="startDate">
	<spring:message code="filter.startDate"/>:
	</form:label>
	<form:input path="startDate"/>
	<form:errors cssClass="error" path="startDate"/>
	<br />
	
	<form:label path="endDate">
	<spring:message code="filter.endDate"/>:
	</form:label>
	<form:input path="endDate"/>
	<form:errors cssClass="error" path="endDate"/>
	<br />
	
	<form:label path="lowPrice">
	<spring:message code="filter.lowPrice"/>:
	</form:label>
	<form:input path="lowPrice"/>
	<form:errors cssClass="error" path="lowPrice"/>
	<br />
	
	<form:label path="highPrice">
	<spring:message code="filter.highPrice"/>:
	</form:label>
	<form:input path="highPrice"/>
	<form:errors cssClass="error" path="highPrice"/>
	<br />
	
	<form:label path="category">
	<spring:message code="filter.category"/>:
	</form:label>
	<form:select path="category">
		<form:options items="${categories}" itemLabel="name" itemValue="id"/>
		<form:option label="todas" value="0"></form:option>
	</form:select>
	<br />
	
	<form:label path="warranty">
	<spring:message code="filter.warranty"/>:
	</form:label>
	<form:select path="warranty">
		<form:options items="${warranties}" itemLabel="title" itemValue="id"/>
		<form:option label="todas" value="0"></form:option>
	</form:select>
	
	<br /><br />
		
	<input type="submit" name="search" 
	value="<spring:message code="filter.search" />" />
</form:form>
</security:authorize>


