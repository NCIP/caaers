<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@include file="/WEB-INF/views/taglibs.jsp" %>

<tags:noform>
    <c:forEach items="${indexes}" var="index" varStatus="i">
        <c:set var="surgery" value="${surgeries[index]}" />
        <ae:oneBehavioralIntervention index="${index}" behavioral="${behavioral}" collapsed="${fn:length(indexes) > 1}"/>
    </c:forEach>
</tags:noform>

