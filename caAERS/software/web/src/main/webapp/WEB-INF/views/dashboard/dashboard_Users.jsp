<%@include file="/WEB-INF/views/taglibs.jsp" %>

<c:if test="${not empty roles.ae_reporter}">

<chrome:boxIPhone title="Users" style="width:700px;">
<jsp:body>
    <div id="dashboardUsers" class="scrollerTask">
        <c:forEach items="${users}" var="u">
            ${u.userName}, ${u.firstName}<br>
        </c:forEach>
    </div>
</jsp:body>
</chrome:boxIPhone>

</c:if>
