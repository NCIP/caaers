<%@ tag import="java.util.HashSet" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@attribute name="hideErrorDetails" type="java.lang.Boolean" %>
<%@attribute name="path" description="The path of the error(s), if empty will use *" %>

<form:errors path="${empty path ? '*' : path}">

<%
    // remove duplicate messages from the header
    if (messages != null) {
        HashSet<String> serverSideErrorHash = new HashSet<String>();
        for (Object s : messages) {
            serverSideErrorHash.add(s.toString());
        }
        request.setAttribute("serverSideErrorHash", serverSideErrorHash);
    }
%>

<div class="errors" id="SERVER_SIDE_ERRORS">
    <c:if test="${not empty serverSideErrorHash}">
        <p><caaers:message code="submision.errors"/></p>
        <c:if test="${not hideErrorDetails}">
            <ul class="errors">
                <c:forEach items="${serverSideErrorHash}" var="msg">
                    <li>${msg}</li>
                </c:forEach>
            </ul>
        </c:if>
    </c:if>
</div>

</form:errors>
