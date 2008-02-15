<%@tag%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="path" required="true" %>
<a id="${path}-help-control" 
	onmouseover="$('${path}-help-content').style.display='inline'" 
	onmouseout="$('${path}-help-content').style.display='none'">
    <img src="<c:url value="/images/q.gif"/>" alt="Help">
</a>&nbsp;&nbsp;&nbsp;
<span id="${path}-help-content" class="hint"><jsp:doBody/><span class="hint-pointer">&nbsp;</span></span>
