<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


</head>
<body>

<security:authorize access="hasRole('CUSTOMER')">
<form:form action="fixUpTask/customer/create.do" modelAttribute="fixUpTask">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="customer" />
	
 
 	<form:label path="moment">
		<spring:message code="fixUpTask.moment" />:
	</form:label>
	<form:input path="moment" />
	<form:errors cssClass="error" path="moment" />
	<br />
	
	<form:label path="description">
		<spring:message code="fixUpTask.description" />:
	</form:label>
	<form:input path="description" />
	<form:errors cssClass="error" path="description" />
	<br />
	
	<form:label path="address">
		<spring:message code="fixUpTask.address" />:
	</form:label>
	<form:input path="address" />
	<form:errors cssClass="error" path="address" />
	<br />
	
	<form:label path="maximunPrice">
		<spring:message code="fixUpTask.maximunPrice" />:
	</form:label>
	<form:input path="maximunPrice" />
	<form:errors cssClass="error" path="maximunPrice" />
	<br />
	
	<form:label path="periodTime">
		<spring:message code="fixUpTask.periodTime" />:
	</form:label>
	<form:input path="PeriodDate" />
	<form:errors cssClass="error" path="periodTime" />
	<br />
		<form:label path="category">
		<spring:message code="fixUpTask.category" />:
	</form:label>
	<form:select id="categorys" path="category">
		<form:option value="0" label="----" />		
		<form:options items="${categorys}" itemValue="id" itemLabel="name" />
	</form:select>
	<form:errors cssClass="error" path="categorys" />
	<br />
	
		<form:label path="warranty">
		<spring:message code="fixUpTask.warranty" />:
	</form:label>
	<form:select id="warrantys" path="warranty">
		<form:option value="0" label="----" />		
		<form:options items="${warrantys}" itemValue="id" itemLabel="name" />
	</form:select>
	<form:errors cssClass="error" path="warratys" />
	<br />
	
	<form:label path="application">
		<spring:message code="fixUpTask.application" />:
	</form:label>
	<jstl:forEach var="application" items="${applications}"> 

	<input type="checkbox" name="application" value="${application.moment}"/>
	
	</jstl:forEach>
	<form:errors cssClass="error" path="application" />
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="fixUpTask.save" />" />&nbsp; 
	<jstl:if test="${fixUpTask.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="fixUpTask.delete" />"
			onclick="return confirm('<spring:message code="fixUpTask.confirm.delete" />')" />&nbsp;
	</jstl:if>
		
		<input type="submit" name="cancel"
		value="<spring:message code="fixUpTask.cancel" />"
		onclick="javascript: relativeRedir('fixUpTask/customer/list.do');" />
	<br />
 	
</form:form>
</security:authorize>
</body>
</html>