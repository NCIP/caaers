<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@attribute name="key" %>
<c:if test="${not empty flashMessage}">
    <div id="flash-message" class="${empty flashMessageClass ? 'info' : flashMessageClass}">
    	<img src= "<chrome:imageUrl name="../check.png"/>" />&nbsp;${flashMessage}
    </div>
</c:if>
<c:if test="${not empty key}">
  <div id="flash-message" class="${empty flashMessageClass ? 'info' : flashMessageClass}">
	${requestScope[key]}
  </div>
</c:if>