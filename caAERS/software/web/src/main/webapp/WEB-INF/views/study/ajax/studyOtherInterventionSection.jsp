<%@include file="/WEB-INF/views/taglibs.jsp" %>
<tags:noform>
    <c:forEach items="${indexes}" var="index" varStatus="i">
        <c:set var="otherIntervention" value="${otherInterventions[index]}" />
        <c:if test="${!otherIntervention.retiredIndicator}">
            <study:oneOtherIntervention index="${index}" otherIntervention="${otherIntervention}" collapsed="${fn:length(command.study.activeOtherInterventions) > 1 && fn:length(indexes) > 1}"/>
        </c:if>
    </c:forEach>
</tags:noform>