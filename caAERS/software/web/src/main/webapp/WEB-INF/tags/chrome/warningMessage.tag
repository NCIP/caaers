<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@attribute name="key" %>
<c:if test="${not empty warningMessage}">
    <div id="flash-message" class="${empty warningMessageClass ? 'warning' : warningMessageClass}">
    	<img src= "<chrome:imageUrl name="../stop_orange.png"/>" />&nbsp;${warningMessage}
    </div>
</c:if>
<c:if test="${not empty key}">
  <div id="flash-message" class="${empty warningMessageClass ? 'warning' : warningMessageClass}">
	${requestScope[key]}
  </div>
</c:if>