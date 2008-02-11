<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="csmauthz" uri="http://csm.ncicb.nci.nih.gov/authz" %>
<div id="header">

    <div class="background-R">

        <a href="/caaers/pages/task">

            <img src="<chrome:imageUrl name="caAERSlogo.gif" />" alt="caAERS" id="logo" border="0">
       </a>

        <img src="<chrome:imageUrl name="caAERS.gif"/>" alt="cancer Adverse Event Reporting System" id="tagline">

        <div id="login-action">
          <a href="<c:url value="/j_acegi_logout"/>">Log out</a>
        </div>

        <ul id="sections" class="tabs">
        <c:forEach items="${sections}" var="section">
            <csmauthz:accesscontrol authorizationCheckName="sectionAuthorizationCheck"
                domainObject="${section}">
            <li class="${section == currentSection ? 'selected' : ''}"><div>
                <a href="<c:url value="${section.mainUrl}"/>">${section.displayName}</a>
            </div></li>
            </csmauthz:accesscontrol>
        </c:forEach>
        </ul>

        <div id="taskbar">
            <c:if test="${not empty currentSection.tasks}">
                Tasks:
                <c:forEach items="${currentSection.tasks}" var="task">
                    <csmauthz:accesscontrol domainObject="${task}" authorizationCheckName="taskAuthorizationCheck">
                        <a href="<c:url value="${task.url}"/>">${task.displayName}</a> 
                    </csmauthz:accesscontrol>
                </c:forEach>
            </c:if>
        </div>
    </div>
</div>
<!-- end header -->
