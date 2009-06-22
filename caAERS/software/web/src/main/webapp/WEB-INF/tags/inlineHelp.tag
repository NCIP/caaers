<%@tag%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="path" required="true" %>
<a class="inline-help-control" id="${path}-help-control">
    <img src="<c:url value="/images/q.gif"/>" alt="Help" title="Show help for this field">
</a>
<div id="${path}-help-content" class="help-content" style="display: none">
    <jsp:doBody/>
</div>
