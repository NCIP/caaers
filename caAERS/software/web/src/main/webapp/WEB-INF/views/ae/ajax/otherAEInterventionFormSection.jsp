<%@include file="/WEB-INF/views/taglibs.jsp" %>

<tags:noform>
    <c:forEach items="${indexes}" var="index" varStatus="i">
        <c:set var="otherAEIntervention" value="${otherAEInterventions[index]}" />
        <ae:oneOtherAEIntervention index="${index}" otherAE = "${otherAEIntervention}" collapsed="${fn:length(indexes) > 1}"/>
    </c:forEach>
</tags:noform>

