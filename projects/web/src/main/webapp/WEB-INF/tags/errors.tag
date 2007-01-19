<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="path"%>
<form:errors path="${path}">
    <ul class="errors">
        <c:forEach items="${messages}" var="message"><li>${message}</li></c:forEach>
    </ul>
</form:errors>