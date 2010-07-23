<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@ taglib prefix="study" tagdir="/WEB-INF/tags/study" %>

<%@ taglib prefix="csmauthz" uri="http://csm.ncicb.nci.nih.gov/authz" %>
<!DOCTYPE html
    PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <title>Welcome to caAERS</title>
    <link rel="icon" href="../images/caaers.ico"/>
    <style>
        .division .header { margin-bottom:5px; }
    </style>
</head>
<body>



<c:set var="_regularTasksVisible" value="${false}" />
<c:set var="_regularTasks">
    <jsp:attribute name="value">
        <table id="test" class="autoclear" width="100%" border="0">
        <tr class="results" >
                <c:forEach begin="0" end="3" items="${taskgroups}" var="taskGroup" varStatus="index">
                    <c:if test="${index.index != 2}">
                        <td align="left" valign="top" width="33%">
                                <csmauthz:accesscontrol domainObject="${taskGroup}" authorizationCheckName="taskGroupAuthorizationCheck">
                                    <c:set var="_regularTasksVisible" value="${true}" />
                                    <ul><chrome:division title="${taskGroup.displayName}">
                                        <c:forEach items="${taskGroup.taskList}" var="task">
                                            <c:if test="${test}"></c:if>
                                            <csmauthz:accesscontrol domainObject="${task}" authorizationCheckName="taskAuthorizationCheck">
                                                <li><a href="<c:url value="${task.url}"/>">${task.displayName}</a></li>
                                            </csmauthz:accesscontrol>

                                        </c:forEach>
                                    </chrome:division>
                                    </ul>
                                </csmauthz:accesscontrol>
                        </td>
                    </c:if>
                </c:forEach>
        </tr>
    </table>
    </jsp:attribute>
</c:set>

<c:set var="_adminTasksVisible" value="${false}" />
<c:set var="_adminTasks">
    <jsp:attribute name="value">
        <table id="test" width="100%" border="0">
            <tr class="results">
                    <c:forEach begin="4" end="9" items="${taskgroups}" var="taskGroup" varStatus="index">
                        <csmauthz:accesscontrol domainObject="${taskGroup}" authorizationCheckName="taskGroupAuthorizationCheck">
                        <c:set var="_adminTasksVisible" value="${true}" />
                        <td align="left"  valign="top" width="33%">
                            <ul><chrome:division title="${taskGroup.displayName}">
                                <c:forEach items="${taskGroup.taskList}" var="task">
                                    <c:if test="${test}"></c:if>
                                    <csmauthz:accesscontrol domainObject="${task}" authorizationCheckName="taskAuthorizationCheck">
                                        <li><a href="<c:url value="${task.url}"/>">${task.displayName}</a></li>
                                    </csmauthz:accesscontrol>
                                </c:forEach>
                            </chrome:division>
                            </ul>
                        </td>
                        <c:if test="${index.index % 3 == 0}"></tr><tr></c:if>
                        </csmauthz:accesscontrol>
                    </c:forEach>
            </tr>
        </table>
    </jsp:attribute>
</c:set>




<%-- RENDERING PART --%>


<chrome:box title="Welcome">

    <jsp:include page="/pages/dashboard" />

<c:if test="${_regularTasksVisible}">
<chrome:division title="Regular Tasks">
    ${_regularTasks}
</chrome:division>
</c:if>

<c:if test="${_adminTasksVisible}">
<chrome:division title="Setup and Administration Tasks">
    ${_adminTasks}
</chrome:division>
</c:if>

</chrome:box>


</body>
</html>