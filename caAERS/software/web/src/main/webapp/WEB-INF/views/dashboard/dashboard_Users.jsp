<%@include file="/WEB-INF/views/taglibs.jsp" %>

<c:if test="${not empty roles.user_administrator}">

<chrome:boxIPhone title="Users (${fn:length(users)})" style="width:700px;">
<jsp:body>
    <div id="dashboardUsers" class="scrollerTask">
        <table border="0" cellpadding="0" cellspacing="0" class="dashboard_table" width="99%">
            <tr class="taskTitleRow">
                <th>User ID
                <th>First Name
                <th>Last Name
                <th>Email
                <th>Actions
            </tr>
            <c:forEach items="${users}" var="u" varStatus="i">
            <tr class="${i.index % 2 == 0 ? 'alt' : ''}">
                <td>${u.userName}</td>
                <td>${u.firstName}</td>
                <td>${u.lastName}</td>
                <td>${u.emailAddress}</td>
                <td><a href="#">View / Edit</a></td>
            </tr>
            </c:forEach>
        </table>
    </div>
</jsp:body>
</chrome:boxIPhone>

</c:if>
