<%@tag%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@attribute name="path" required="true" %>
<%@attribute name="key" required="true" %>
<a id="${path}-help-control" 
	onmouseover="$('${path}-help-content').style.display='inline'" 
	onmouseout="$('${path}-help-content').style.display='none'">
    <img src="<c:url value="/images/q.gif"/>" alt="Help">
</a>&nbsp;&nbsp;&nbsp;
<span id="${path}-help-content" class="hint"><spring:message code="${key}" text="No help available ${field.attributes.help}" /><span class="hint-pointer">&nbsp;</span></span>
