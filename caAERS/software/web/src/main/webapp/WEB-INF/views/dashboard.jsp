<%@include file="/WEB-INF/views/taglibs.jsp" %>

<table width="100%" border="0">
<tr>
    <td valign="top">
        <jsp:include page="/pages/dashboard/aeReporter" />
        <jsp:include page="/pages/dashboard/study" />
        <jsp:include page="/pages/dashboard/subject" />
        <jsp:include page="/pages/dashboard/users" />
    </td>
    <td valign="top">
        <%@include file="/WEB-INF/views/dashboard/dashboardTasks.jsp"%>
    </td>
<tr>
</table>
