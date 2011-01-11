<%@include file="/WEB-INF/views/taglibs.jsp" %>

<table width="100%" border="0">
    <tr>
        <td valign="top">
            <c:if test="${not empty roles.ae_reporter}">
                <%@include file="dashboard_AEReporter.jsp"%>
            </c:if>
        </td>
        <td valign="top">
            <%@include file="dashboardTasks.jsp"%>
        </td>
    <tr>
</table>
