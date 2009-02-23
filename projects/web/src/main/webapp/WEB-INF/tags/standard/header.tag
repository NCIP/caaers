<%@taglib prefix="blue" tagdir="/WEB-INF/tags/blue" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="csmauthz" uri="http://csm.ncicb.nci.nih.gov/authz" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div id="header">
  <div id="skipnav">
  <a href="#skipnav">Skip Navigation</a>
  </div>
    <div class="background-R">

        <a href="/caaers/pages/task" id="logo">caAERS</a>
		  <c:url value="/help/caAERS_Help.htm" scope="request" var="_caaersHelpURL" />
		  <c:set var="roboHelpKey">ROBOHELP_${currentTask.linkName}</c:set>
		  <spring:message var="roboHelpLink" code="${roboHelpKey}" text="NO_${roboHelpKey}"/>
          <a href="${_caaersHelpURL}#${roboHelpLink}.htm" target="_blank" id="help">Help</a>
          <a href="<c:url value="/j_acegi_logout"/>" id="logout">Log out</a>
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
				<c:set var="noOfTasks" value="${fn:length(currentSection.tasks)}" />
				 <!-- test : ${noOfTasks} , ${fn:length(currentSection.tasks)}-->
				 <ul>
                <c:forEach items="${currentSection.tasks}" var="task">
				<c:set var="lengthOfTask" value="${fn:length(task.displayName)}" />
                    <csmauthz:accesscontrol domainObject="${task}" authorizationCheckName="taskAuthorizationCheck">
                    	 <li class="${noOfTasks gt 4 ? 'gt4' : 'lte4'}"><a class="${(task == currentTask) || (task.displayName == currentTask.displayName) ?  'selected' : '' } ${(lengthOfTask gt 21 ? 'gt18' : '')}" id="secondlevelnav_${task.linkName}"  href="<c:url value="${task.url}"/>"><img class="${(lengthOfTask gt 21 ? 'imagegt18' : '')}" src="/caaers/images/blue/icons/${task.linkName}_icon.png" alt=""/><span class="spangt18">${task.displayName}</span></a></li>
                    </csmauthz:accesscontrol>
                </c:forEach>
				</ul>
            </c:if>
        </div>
    </div>
</div>
<!-- end header -->
