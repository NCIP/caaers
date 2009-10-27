<%@include file="/WEB-INF/views/taglibs.jsp" %>

<tags:noform>
    <c:forEach items="${indexes}" var="index" varStatus="i">
        <study:treatmentAssignment title="${command.study.treatmentAssignments[index].code}" index="${index}" ta="${command.study.treatmentAssignments[index]}" collapsed="false" />
    </c:forEach>
</tags:noform>

