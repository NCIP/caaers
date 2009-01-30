<%@include file="/WEB-INF/views/taglibs.jsp" %>

<tags:noform>
    <c:forEach items="${indexes}" var="index" varStatus="i">
        <c:set var="surgery" value="${surgeries[index]}" />
        <ae:oneSurgeryIntervention index="${index}" surgery="${surgery}"/>    
    </c:forEach>
</tags:noform>

