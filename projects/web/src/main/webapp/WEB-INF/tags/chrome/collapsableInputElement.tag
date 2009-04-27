<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="id" required="false" %>
<%@attribute name="targetID" required="true" %>
<%@attribute name="collapsed" required="false" type="java.lang.Boolean" %>

<c:if test="${collapsed}">
<script language="JavaScript1.2">_collapsedELs['${targetID}'] = 1;</script>
<input type="image" id="${id }" src="<c:url value="/images/arrow-right.png"/>" onClick="javascript: if ($('${targetID}').style.display == 'none') { $('${targetID}').show(); this.src = this.src.replace('right','down');} else { $('${targetID}').hide(); this.src = this.src.replace('down','right');}">
</c:if>

<c:if test="${!collapsed}">
<input type="image" id="${id }" src="<c:url value="/images/arrow-right.png"/>" onClick="javascript: if ($('${targetID}').style.display == 'none') { $('${targetID}').show(); this.src = this.src.replace('right','down');} else { $('${targetID}').hide(); this.src = this.src.replace('down','right');}">
</c:if>

