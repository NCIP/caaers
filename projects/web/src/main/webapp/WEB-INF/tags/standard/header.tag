<%@taglib prefix="blue" tagdir="/WEB-INF/tags/blue" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="csmauthz" uri="http://csm.ncicb.nci.nih.gov/authz" %>
<div id="header">

    <div class="background-R">

        <a href="/caaers/pages/task" id="logo">caAERS</a>

        <div id="login-action">
          <a href="<c:url value="/j_acegi_logout"/>">Log out</a>
        </div>

        <ul id="sections" class="tabs">
        <c:forEach items="${sections}" var="section">
            <csmauthz:accesscontrol authorizationCheckName="sectionAuthorizationCheck"
                domainObject="${section}">
            <li class="${section == currentSection ? 'selected' : ''}">
			      
                <a id="firstlevelnav_${section.mainController}" href="<c:url value="${section.mainUrl}"/>">${section.displayName}</a>
              
			</li>
            </csmauthz:accesscontrol>
        </c:forEach>
        </ul>

        <div id="taskbar">
            <c:if test="${not empty currentSection.tasks}">
                <c:forEach items="${currentSection.tasks}" var="task">
                    <csmauthz:accesscontrol domainObject="${task}" authorizationCheckName="taskAuthorizationCheck">
                    	 <a class="${(task == currentTask) || (task.displayName == currentTask.displayName) ? 'selected' : ''}" id="secondlevelnav_${task.linkName}" href="<c:url value="${task.url}"/>"><img src="/caaers/images/blue/icons/${task.linkName}_icon.png"/>${task.displayName}</a> 
                    </csmauthz:accesscontrol>
                </c:forEach>
            </c:if>
        </div>
    </div>
</div>
<!-- end header -->
