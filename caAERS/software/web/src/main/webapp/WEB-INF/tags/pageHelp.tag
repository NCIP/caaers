<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@attribute name="propertyKey"%>
 <c:url value="${empty configuration.map.caaersBaseHelpUrl ? '/help/caAERS_Help.htm' : configuration.map.caaersBaseHelpUrl}" scope="request" var="_caaersHelpURL" />
 <c:set var="_tabNum" value="${(not empty tab and tab.number gt 0) ? tab.number : ''}" />
 <c:set var="roboHelpKey">ROBOHELP_${currentTask.linkName}${_tabNum gt 0 ? '_' : ''}${_tabNum gt 0 ? _tabNum : ''}</c:set>
 <spring:message var="roboHelpLink" code="${roboHelpKey}" text="NO_${roboHelpKey}"/>
 <script>
  Event.observe(window, "load", ('${_caaersHelpURL}','${roboHelpLink}'));
 </script>
