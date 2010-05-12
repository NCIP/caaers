<%@include file="/WEB-INF/views/taglibs.jsp" %>

This is supposed to be a dashboard.

<ol>
<c:forEach items="${pastDueReports}" var="aeReport">
    <c:forEach items="${aeReport.reports}" var="report">
        <li>${report.name} ${report.dueOn}
    </c:forEach>
    <%--<li>${reportVersion.report.reportDefinition.name}, ${reportVersion.id}, ${reportVersion.report.id}, ${reportVersion.dueOn}<br>--%>
</c:forEach>
</ol>