<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<tags:noform>
    <c:forEach items="${indexes}" var="index" varStatus="i">
        <c:set var="agent" value="${agents[index]}" />
        <ae:oneCourseAgent index="${index}" agent="${agent}"/>
    </c:forEach>
</tags:noform>

