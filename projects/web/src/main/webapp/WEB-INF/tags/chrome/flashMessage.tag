<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="key" %>
<c:if test="${not empty flashMessage}">
    <div id="flash-message" class="${empty flashMessageClass ? 'info' : flashMessageClass}">
        ${flashMessage}
    </div>
</c:if>
<c:if test="${not empty key}">
  <div id="flash-message" class="${empty flashMessageClass ? 'info' : flashMessageClass}">
	${requestScope[key]}
  </div>
</c:if>