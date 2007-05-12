<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="csmauthz" uri="http://csm.ncicb.nci.nih.gov/authz" %>
<div id="header">
    <div class="background-R">
        <img src="<chrome:imageUrl name="caAERSlogo.gif"/>" alt="caAERS" id="logo">
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
        Tasks:
        <c:forEach items="${currentSection.tasks}" var="task">
            <csmauthz:accesscontrol domainObject="${task}" authorizationCheckName="taskAuthorizationCheck">
            <c:choose>
	             <c:when test="${task == currentTask}">
	            	<<img src="<c:url value="/images/arrowRight.gif"/>" width="3" height="5" align="absmiddle">a href="<c:url value="${task.url}"/>">${task.displayName}</a>
	             </c:when>
	             <c:otherwise>
	               	<a href="<c:url value="${task.url}"/>">${task.displayName}</a>
	             </c:otherwise>         
	        </c:choose>
            </csmauthz:accesscontrol>
        </c:forEach>
        </div>
    </div>
</div>
<!-- end header -->
