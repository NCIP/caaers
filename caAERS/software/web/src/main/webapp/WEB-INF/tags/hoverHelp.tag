<%@tag%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@attribute name="path" required="true" %>
<%@attribute name="code" required="true" %>
<spring:message var="msgText" code="${code}" text="NA" />
<c:if test="${msgText ne 'NA'}">
<a id="${path}-help-control" onmouseover="$('${path}-help-content').style.display='inline'"	onmouseout="$('${path}-help-content').style.display='none'">
    <img src="<c:url value="/images/q.gif"/>" alt="Help">
</a>&nbsp;&nbsp;&nbsp;
<span id="${path}-help-content" class="hint">${msgText}<span class="hint-pointer">&nbsp;</span></span>
</c:if>
