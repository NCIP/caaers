<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="footer"></div>
<tags:ssoForm/>
<c:if test="${configuration.map.showDebugInformation}">
    <tags:debugInfo/>
</c:if>
