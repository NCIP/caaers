<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty flashMessage}">
    <div id="flash-message" class="${empty flashMessageClass ? 'info' : flashMessageClass}">
        ${flashMessage}
    </div>
</c:if>