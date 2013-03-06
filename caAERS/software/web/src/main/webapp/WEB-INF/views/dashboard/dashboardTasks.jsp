<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@include file="/WEB-INF/views/taglibs.jsp" %>
<chrome:boxIPhone title="Quick Links" style="width:240px;">
<div style="padding-left:1pt">
<table width="100%" cellpadding="0" cellspacing="0" border="0">
    <c:forEach begin="0" end="2" items="${taskgroups}" var="taskGroup" varStatus="index">
        <csmauthz:accesscontrol domainObject="${taskGroup}" authorizationCheckName="taskGroupAuthorizationCheck">
        <c:set var="_regularTasksVisible" value="${true}" />
            <c:forEach items="${taskGroup.taskList}" var="task">
                <csmauthz:accesscontrol domainObject="${task}" authorizationCheckName="taskAuthorizationCheck">
                <tr><td class="taskItemImage"><img src="<c:url value="/images/blue/icons/${task.linkName}_icon2.png" />"></td><td width="100%" class="taskItem" style="margin-right:1px;"><a href="<c:url value="${task.url}"/>">${task.displayName}</a></td></tr>
                </csmauthz:accesscontrol>
            </c:forEach>
        </csmauthz:accesscontrol>
    </c:forEach>

    <c:forEach begin="3" end="9" items="${taskgroups}" var="taskGroup" varStatus="index">
        <csmauthz:accesscontrol domainObject="${taskGroup}" authorizationCheckName="taskGroupAuthorizationCheck">
        <c:set var="_adminTasksVisible" value="${true}" />
        <td align="center" valign="top">
            <c:forEach items="${taskGroup.taskList}" var="task">
                <csmauthz:accesscontrol domainObject="${task}" authorizationCheckName="taskAuthorizationCheck">
                <tr><td class="taskItemImage"><img src="<c:url value="/images/blue/icons/${task.linkName}_icon2.png"/>"></td><td width="100%" class="taskItem"><a href="<c:url value="${task.url}"/>">${task.displayName}</a></td></tr>
                </csmauthz:accesscontrol>
            </c:forEach>
        </csmauthz:accesscontrol>
    </c:forEach>
</table>
</div>
</chrome:boxIPhone>
