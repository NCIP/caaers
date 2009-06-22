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
<head><title>Welcome to caAERS</title>
<link rel="icon" href="../images/caaers.ico"/>
<style>
.division .header { margin-bottom:5px; }
</style>
</head>
<body><chrome:box title="Task">
<chrome:division title="Regular Tasks">
    <table id="test" class="autoclear" width="100%">
        <tr class="results" >
            <td align="left" valign="top" width="30%">
                <c:forEach begin="0" end="1" items="${taskgroups}" var="taskGroup">
                    <csmauthz:accesscontrol domainObject="${taskGroup}"
                                            authorizationCheckName="taskGroupAuthorizationCheck">

                        <ul><chrome:division title="${taskGroup.displayName}">
                            <c:forEach items="${taskGroup.taskList}" var="task">
                                <c:if test="${test}"></c:if>
                                <csmauthz:accesscontrol domainObject="${task}"
                                                        authorizationCheckName="taskAuthorizationCheck">
                                    <li><a href="<c:url value="${task.url}"/>">${task.displayName}</a></li>
                                </csmauthz:accesscontrol>

                            </c:forEach>
                        </chrome:division>
                        </ul>

                    </csmauthz:accesscontrol>
                </c:forEach>

            </td>

            <td align="left" valign="top" width="30%">
                <c:forEach begin="2" end="2" items="${taskgroups}" var="taskGroup">
                    <csmauthz:accesscontrol domainObject="${taskGroup}"
                                            authorizationCheckName="taskGroupAuthorizationCheck">

                        <ul><chrome:division title="${taskGroup.displayName}">
                            <c:forEach items="${taskGroup.taskList}" var="task">
                                <c:if test="${test}"></c:if>
                                <csmauthz:accesscontrol domainObject="${task}"
                                                        authorizationCheckName="taskAuthorizationCheck">
                                    <li><a href="<c:url value="${task.url}"/>">${task.displayName}</a></li>
                                </csmauthz:accesscontrol>

                            </c:forEach>
                        </chrome:division>
                        </ul>
                    </csmauthz:accesscontrol>
                </c:forEach>

            </td>
            <td align="left" valign="top" width="30%">
                <c:forEach begin="3" end="3" items="${taskgroups}" var="taskGroup">
                    <csmauthz:accesscontrol domainObject="${taskGroup}"
                                            authorizationCheckName="taskGroupAuthorizationCheck">

                        <ul><chrome:division title="${taskGroup.displayName}">
                            <c:forEach items="${taskGroup.taskList}" var="task">
                                <c:if test="${test}"></c:if>
                                <csmauthz:accesscontrol domainObject="${task}"
                                                        authorizationCheckName="taskAuthorizationCheck">
                                    <li><a href="<c:url value="${task.url}"/>">${task.displayName}</a></li>
                                </csmauthz:accesscontrol>

                            </c:forEach>
                        </chrome:division>
                        </ul>
                    </csmauthz:accesscontrol>
                </c:forEach>

            </td>

        </tr>
    </table>

</chrome:division>
<chrome:division title="Setup and Administration Tasks">
    <table id="test" width="100%" >
        <tr class="results">
            <td align="left"  valign="top" width="30%">
                <c:forEach begin="4" end="5" items="${taskgroups}" var="taskGroup">
                    <csmauthz:accesscontrol domainObject="${taskGroup}"
                                            authorizationCheckName="taskGroupAuthorizationCheck">

                        <ul><chrome:division title="${taskGroup.displayName}">
                            <c:forEach items="${taskGroup.taskList}" var="task">
                                <c:if test="${test}"></c:if>
                                <csmauthz:accesscontrol domainObject="${task}"
                                                        authorizationCheckName="taskAuthorizationCheck">
                                    <li><a href="<c:url value="${task.url}"/>">${task.displayName}</a></li>
                                </csmauthz:accesscontrol>

                            </c:forEach>
                        </chrome:division>
                        </ul>
                    </csmauthz:accesscontrol>
                </c:forEach>

            </td>

            <td align="left" valign="top" width="30%">
                <c:forEach begin="6" end="7" items="${taskgroups}" var="taskGroup">
                    <csmauthz:accesscontrol domainObject="${taskGroup}"
                                            authorizationCheckName="taskGroupAuthorizationCheck">
                        <ul><chrome:division title="${taskGroup.displayName}">
                            <c:forEach items="${taskGroup.taskList}" var="task">
                                <csmauthz:accesscontrol domainObject="${task}"
                                                        authorizationCheckName="taskAuthorizationCheck">
                                    <li><a href="<c:url value="${task.url}"/>">${task.displayName}</a></li>
                                </csmauthz:accesscontrol>

                            </c:forEach>
                        </chrome:division>
                        </ul>
                    </csmauthz:accesscontrol>
                </c:forEach>

            </td>
            <td align="left" valign="top" width="30%">
                <c:forEach begin="8" end="9" items="${taskgroups}" var="taskGroup">
                    <csmauthz:accesscontrol domainObject="${taskGroup}"
                                            authorizationCheckName="taskGroupAuthorizationCheck">
                        <ul><chrome:division title="${taskGroup.displayName}">
                            <c:forEach items="${taskGroup.taskList}" var="task">
                                <c:if test="${test}"></c:if>
                                <csmauthz:accesscontrol domainObject="${task}"
                                                        authorizationCheckName="taskAuthorizationCheck">
                                    <li><a href="<c:url value="${task.url}"/>">${task.displayName}</a></li>
                                </csmauthz:accesscontrol>

                            </c:forEach>
                        </chrome:division>
                        </ul>
                    </csmauthz:accesscontrol>
                </c:forEach>

            </td>

        </tr>
    </table>
</chrome:division>


</chrome:box>
</body>
</html>