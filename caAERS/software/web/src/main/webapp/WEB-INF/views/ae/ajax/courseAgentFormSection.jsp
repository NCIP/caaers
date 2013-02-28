<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<tags:noform>
    <c:forEach items="${indexes}" var="index" varStatus="i">
        <c:set var="agent" value="${agents[index]}" />
        <ae:oneCourseAgent index="${index}" agent="${agent}" collapsed="${fn:length(indexes) > 1}"/>
    </c:forEach>
</tags:noform>

