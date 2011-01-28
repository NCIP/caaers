<%@include file="/WEB-INF/views/taglibs.jsp" %>

<c:if test="${not empty roles.user_administrator}">

<chrome:boxIPhone title="Personnel" style="width:700px;">
<jsp:body>
    <div id="dashboardUsers" class="<c:if test="${fn:length(users) > 9}">scrollerTask</c:if>">
        <table border="0" cellpadding="0" cellspacing="0" class="dashboard_table" width="99%">
            <tr class="taskTitleRow">
                <th>User ID
                <th>First Name
                <th>Last Name
                <th>Email
            </tr>
            <c:forEach items="${users}" var="u" varStatus="i">
            <tr class="${i.index % 2 == 0 ? 'alt' : ''}">
                <td><a style="text-decoration:underline; color:blue; font-weight:normal;" href="<c:url value="/pages/admin/editUser?id=${u.id}"/>">${u.userName}</a></td>
                <td>${u.firstName}</td>
                <td>${u.lastName}</td>
                <td>${u.emailAddress}</td>
            </tr>
            </c:forEach>
            <c:if test="${fn:length(users) == 0}">
                <tr><td colspan="5"><caaers:message code="dashboard.noResults" /></td></tr>
            </c:if>
        </table>
    </div>
</jsp:body>
</chrome:boxIPhone>

</c:if>
