<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



<body>
<display:table pagesize="5" name="applications" id="row"
requestURI="${requestURI}" >

<security:authorize access="hasRole('CUSTOMER')">

<display:column>
<jstl:if test="${row.status==1}">
	<a href="application/handyWorker,customer/edit.do?applicationId=${row.id}"><spring:message code="customer.application.editApplication.link" /></a>
</jstl:if>
</display:column>

</security:authorize>

<display:column property="moment" titleKey="application.moment"  />

<display:column  titleKey="application.status" >
<jstl:choose>
<jstl:when test="${row.status eq 0}">
<spring:message code="status.aceptado.tabla" />
</jstl:when>
<jstl:when test="${row.status eq 1}">
<spring:message code="status.pendiente" />
</jstl:when>
<jstl:otherwise>
<spring:message code="status.rechazado.tabla" />
</jstl:otherwise>
</jstl:choose>
</display:column>
<display:column property="price" titleKey="application.price" />
<display:column property="comments" titleKey="application.comments" />
<display:column property="creditCard.number" titleKey="application.creditCard" />
<display:column property="fixUpTask.id" titleKey="application.fixUpTask" />

<security:authorize access="hasRole('HANDYWORKER')">

	<display:column >
	<jstl:if test="${row.status==0}">
			<a href="phase/handyWorker/list.do?applicationId=${row.id}">
				<spring:message	code="application.phase.list" />
			</a>
			</jstl:if>
	</display:column>


<display:column>

	<a href="application/handyWorker,customer/show.do?applicationId=${row.id}"><spring:message code="application.show" /></a>
</display:column>

</security:authorize>
</display:table>

<security:authorize access="hasRole('HANDYWORKER')">
<div>
		<a href="application/handyWorker,customer/create.do"> <spring:message code="application.create" />
		</a>
	</div>
</security:authorize>