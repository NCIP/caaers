<%@include file="/WEB-INF/views/taglibs.jsp" %>

This is supposed to be a dashboard.

<ul>
<c:forEach items="${pastDueReports}" var="aeReport">
    <li>${aeReport.id}<br>
    <%--<li>${reportVersion.report.reportDefinition.name}, ${reportVersion.id}, ${reportVersion.report.id}, ${reportVersion.dueOn}<br>--%>
</c:forEach>
</ul>