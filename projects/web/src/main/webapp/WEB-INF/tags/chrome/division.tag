<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="title"%>
<%@attribute name="id"%>
<%@attribute name="cssClass"%>
<%@attribute name="style"%>
<%@attribute name="enableDelete" type="java.lang.Boolean"%>
<%@attribute name="deleteParams" %>
<div class="division ${cssClass}"
    <tags:attribute name="id" value="${id}"/> <tags:attribute name="style" value="${style}"/>>
    <c:if test="${not empty title}">
        <c:if test="${enableDelete}">
        	<h3><table cellspacing="0" cellpadding="0" border="0" width="100%"><tr><td>${title}</td><td align="right"><a href="javascript:fireAction(${deleteParams},'${id}','${cssClass}');"><img 
	   			src="/caaers/images/checkno.gif" border="0" alt="delete"></a></td></tr></table></h3>
	   	</c:if>
	   	<c:if test="${not enableDelete}">
	   		<h3>${title}</h3>
	   	</c:if>
    </c:if>
    <div class="content">
        <jsp:doBody/>
    </div>
</div>
