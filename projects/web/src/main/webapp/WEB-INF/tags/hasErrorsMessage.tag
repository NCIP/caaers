<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form:errors path="*">
    <c:if test="${not empty messages}">
        <p class="errors">
            There are problems with your submission.
            Please correct them before proceeding.
        </p>
    </c:if>
</form:errors>