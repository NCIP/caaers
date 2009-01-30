<%@include file="/WEB-INF/views/taglibs.jsp" %>

<tags:noform>
    <c:forEach items="${indexes}" var="index" varStatus="i">
        <c:set var="radiation" value="${radiations[index]}" />
        <ae:oneRadiationIntervention index="${index}" radiation="${radiation}"/>
    </c:forEach>
</tags:noform>

