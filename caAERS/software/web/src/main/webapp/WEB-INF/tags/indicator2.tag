<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="id" type="java.lang.String"%>
<img<c:if test="${not empty id}"> id="${id}"</c:if> class="indicator" src="<c:url value="/images/indicator.gif"/>?${requestScope.webCacheId}" alt="activity indicator" width="20px" height="20px"/>