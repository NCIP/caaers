<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>

<div class="">
    <c:forEach items="${fieldGroups.reportingPeriodDetailsFG.fields}" var="field">
        <tags:renderRow field="${field}"/>
    </c:forEach>
</div>

<div class="">
    <c:forEach items="${fieldGroups.treatmentAssignmentFG.fields}" var="field">
        <tags:renderRow field="${field}"/>
    </c:forEach>
</div>