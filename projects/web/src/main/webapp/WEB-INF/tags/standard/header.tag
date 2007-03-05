<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="header">
    <!-- TOP LOGOS START HERE -->
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="header">
        <tr>
            <td width="99%"><img src="<chrome:imageUrl name="caAERS.gif"/>" alt="caAERS - cancer Adverse Event Reporting System"
                width="385" height="33"></td>
            <td align="right"></td>
        </tr>
    </table>
    <!-- TOP LOGOS END HERE -->
    <!-- TOP NAVIGATION STARTS HERE -->
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="topNav">
        <tr valign="middle">
            <td class="left">
                <c:forEach items="${sections}" var="section">
                    <c:choose>
                        <c:when test="${section == currentSection}">
                            <span class="current">${section.displayName}</span><img src="<chrome:imageUrl name="topNavR.gif"/>" width="2" height="20" align="absmiddle" class="currentR">
                        </c:when>
                        <c:otherwise>
                            <a href="<c:url value="${section.mainUrl}"/>">${section.displayName}</a><img src="<chrome:imageUrl name="topDivider.gif"/>" width="2" height="20" align="absmiddle" class="divider">
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </td>

            <td class="right"><a href="<c:url value="/j_acegi_logout"/>">Log out</a></td>
        </tr>
    </table>
    <!-- TOP NAVIGATION ENDS HERE -->
    <!-- SUB NAV STARTS HERE -->
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="subNav">
        <tr>
            <td width="99%" valign="middle">
                <img src="<chrome:imageUrl name="arrowRight.gif"/>" width="3" height="5" align="absmiddle">Tasks
                <c:forEach items="${currentSection.tasks}" var="task">
                    <img src="<chrome:imageUrl name="spacer.gif"/>" width="1" height="20" align="absmiddle" class="spacer">
                    <a href="<c:url value="${task.url}"/>">${task.displayName}</a>
                </c:forEach>
            </td>
            <td valign="middle" class="right"><a href="<c:url value="/pages/help"/>">Help</a></td>
        </tr>
    </table>
    <!-- SUB NAV ENDS HERE -->
</div>
