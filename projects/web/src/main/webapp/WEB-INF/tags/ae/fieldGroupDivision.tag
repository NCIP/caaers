<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="fieldGroupFactoryName" required="true" type="java.lang.String" %>
<%@attribute name="style"%>
<%@variable name-given="fieldGroup" %>
<c:set var="fieldGroupName">${fieldGroupFactoryName}${index}</c:set>
<c:set var="fieldGroup" value="${fieldGroups[fieldGroupName]}"/>

<chrome:division title="${fieldGroup.displayName}"
     cssClass="${fieldGroupFactoryName}" id="${fieldGroupFactoryName}-${index}" style="${style}">
    <jsp:doBody/>
</chrome:division>
