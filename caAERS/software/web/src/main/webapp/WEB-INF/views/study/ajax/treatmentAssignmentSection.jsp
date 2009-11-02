<%@include file="/WEB-INF/views/taglibs.jsp" %>

<tags:noform>
    <c:forEach items="${indexes}" var="index" varStatus="i">
        <%--<c:if test="${!command.study.treatmentAssignments[index].retiredIndicator}">--%>
            <study:treatmentAssignment title="${command.study.treatmentAssignments[index].code}" index="${index}" ta="${command.study.treatmentAssignments[index]}" collapsed="false" collapsable="true"/>
        <%--</c:if>--%>
    </c:forEach>
</tags:noform>

