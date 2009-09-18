<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@attribute name="hideErrorDetails" type="java.lang.Boolean" %>
<%@attribute name="path" description="The path of the error(s), if empty will use *" %>
[errors]
<form:errors path="${empty path ? '*' : path}">
<div class="errors">
    <c:if test="${not empty messages}">
        <p><caaers:message code="submision.errors"/></p>
        <c:if test="${not hideErrorDetails}">
            <ul class="errors">
                <c:forEach items="${messages}" var="msg">
                    <li>${msg}</li>
                </c:forEach>
            </ul>
        </c:if>
    </c:if>
</div>
</form:errors>
