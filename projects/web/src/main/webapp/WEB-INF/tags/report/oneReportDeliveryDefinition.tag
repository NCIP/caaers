<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@attribute name="rdd" required="true" type="gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition" %>
<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>
<c:set var="displayLabel">${displayName}</c:set>
<c:set var="mainGroup">main${index}</c:set>
<c:set var="title">Report Delivery Definition ${index + 1}</c:set>
<chrome:division title="${title} " id="rdd-section-${index}" cssClass="rdd-section" style="${style}">
    <div id="main-fields-${index}" class="main-fields">
        <c:forEach items="${fieldGroups[mainGroup].fields}" var="field">
            <c:if test="${fn:endsWith(field.propertyName, 'endPoint')}" >
              <c:if test="${((rdd.entityType == 2) && (field.displayName=='Role')) || ((rdd.entityType == 1) && (field.displayName=='Address')) }">
	           <tags:renderRow field="${field}"/>
              </c:if>
            </c:if>
            <c:if test="${!(fn:endsWith(field.propertyName, 'endPoint'))}">
	          <tags:renderRow field="${field}"/>
            </c:if>
        </c:forEach>
    </div>
   
</chrome:division>
