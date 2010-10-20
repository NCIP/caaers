<%@include file="/WEB-INF/views/taglibs.jsp" %>
<tags:noform>
    <c:forEach items="${indexes}" var="index" varStatus="i">
        <c:set var="studyDevice" value="${studyDevices[index]}" />
        <c:if test="${!studyDevice.retiredIndicator}">
            <study:oneStudyDevice index="${index}" sd="${studyDevice}" />
        </c:if>
    </c:forEach>
</tags:noform>