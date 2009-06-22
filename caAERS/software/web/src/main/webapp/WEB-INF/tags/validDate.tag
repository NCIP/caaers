<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="date" required="true" %>
<c:if test="${date ne '00/00/0000'}"><c:out value="${date}" /></c:if>