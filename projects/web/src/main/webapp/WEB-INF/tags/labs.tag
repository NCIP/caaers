<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ec" uri="http://www.extremecomponents.org" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ attribute name="labs" type="java.util.List" required="true" %>

	<c:if test="${fn:length(labs) > 0}">
		<tags:javascriptLink name="sidebar/ssm"/>
		<script language="JavaScript1.2">
			<c:forEach items="${labs}" var="alab" varStatus="status">
				<c:if test="${alab.dismissed == false}">
    				<c:out value='ssmItems[${status.count - 1}]=' />["<c:out value="${alab.name}"/>",'<tags:formatDate value="${alab.labDate}"/>' ,"<c:out value="${alab.result}"/>", "<c:out value="${alab.units}"/>","#", "${not empty mandatory ? 'mandatory' : ''}"]
    			</c:if>
			</c:forEach>
		</script>
		<script src="<c:url value="/js/sidebar/ssmItems.js"/>"></script>
		<tags:javascriptLink name="sidebar/ssmItems"/>
	</c:if>
