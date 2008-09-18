<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@attribute name="propertyKey"%>
 <c:url value="/help/caAERS_Help.htm" scope="request" var="_caaersHelpURL" />
<c:set var="roboHelpKey">ROBOHELP_${propertyKey}</c:set>
<spring:message var="roboHelpLink" code="${roboHelpKey}" text="NO_${roboHelpKey}"/>
<script>
Event.observe(window, "load",updateHelpLink('${_caaersHelpURL}','${roboHelpLink}'));
</script>
