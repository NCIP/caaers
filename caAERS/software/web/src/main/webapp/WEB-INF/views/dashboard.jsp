<%@include file="/WEB-INF/views/taglibs.jsp" %>

<table width="100%" border="0">
<tr>
    <td valign="top">
        <jsp:include page="/pages/dashboard/aeReporter" />
        <jsp:include page="/pages/dashboard/study" />
    </td>
    <td valign="top">
        <%@include file="/WEB-INF/views/dashboard/dashboardTasks.jsp"%>
    </td>
<tr>
</table>
