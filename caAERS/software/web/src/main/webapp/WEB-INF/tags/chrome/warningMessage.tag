<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@attribute name="key" %>
<c:if test="${not empty warningMessage}">
    <div class="warning-box message"><p>${warningMessage}</p></div>
</c:if>
<c:if test="${not empty key}">
  <div class="warning-box message"><p>${requestScope[key]}</p></div>
</c:if>