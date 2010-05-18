<%@include file="/WEB-INF/views/taglibs.jsp" %>

<h1>Dashboard</h1>

<%--<ol>
<c:forEach items="${pastDueReports}" var="rv">
    <li>${rv.report.name} - Due Date: <tags:formatDate value="${rv.dueOn}" /> [${rv.reportStatus}]
&lt;%&ndash;
        <ol>
    <c:forEach items="${aeReport.reports}" var="report">
        <li> Report [Report: ${report.id}, ${report.name}]
            <ul>
            <c:forEach items="${report.reportVersions}" var="version">
                <li> (version:${version.id} ${version.reportStatus})
            </c:forEach>
            </ul>
    </c:forEach>
        </ol>
&ndash;%&gt;
</c:forEach>--%>

<table>
<tr><td>


<div id="inside-box">
    <div class="inside-box_top">Expedited Reports</div>
    <div class="inside-box_content">
        <div class="past-due">PAST DUE REPORTS</div>

        <table border="0" cellpadding="0" cellspacing="0" id="dashboard_table">
            <c:forEach items="${pastDueReports}" var="rv" varStatus="index">
                <c:set var="ALT" value="${index.count % 2 == 0 ? 'alt' : ''}"></c:set>
        <tr class="${ALT}">
            <td width="276"><a href="#">${rv.report.name}</a></td>
            <td><i>Due Date:</i> <span style="color:#ea4b4b">&nbsp;&nbsp;&nbsp;<tags:formatDate value="${rv.dueOn}" /></span></td>
        </tr>
            </c:forEach>
        </table>
        
    </div>
    <div class="inside-box_bottom"></div>
</div>

</td></tr></table>