<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="fieldGroupFactoryName" required="true" type="java.lang.String" %>
<%@attribute name="style"%>
<%@attribute name="deleteParams"%>
<%@attribute name="enableDelete"%>
<%@attribute name="id"%>
<%@attribute name="collapsed" type="java.lang.Boolean" %>
<%@attribute name="title" description="Title to be displayed on the division header" %>

<%@variable name-given="fieldGroup" %>

<c:set var="fieldGroupName"><c:out value="${fieldGroupFactoryName}" escapeXml="true"/><c:out value="${index}" escapeXml="true"/></c:set>
<c:set var="fieldGroup" value="${fieldGroups[fieldGroupName]}"/>

<chrome:division title="${empty title ? fieldGroup.displayName : title}" cssClass="<c:out value="${fieldGroupFactoryName}" escapeXml="true"/>" id="<c:out value="${fieldGroupFactoryName}" escapeXml="true"/>-<c:out value="${index}" escapeXml="true"/>" style="<c:out value="${style}" escapeXml="true"/>" collapsable="true" enableDelete="<c:out value="${enableDelete}" escapeXml="true"/>" deleteParams="<c:out value="${deleteParams}" escapeXml="true"/>" collapsed="<c:out value="${collapsed}" escapeXml="true"/>">
    <jsp:doBody/>
</chrome:division>
