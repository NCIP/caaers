<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="title"%>
<%@attribute name="id"%>
<%@attribute name="cssClass"%>
<%@attribute name="style"%>
<div class="division ${cssClass}"
    <tags:attribute name="id" value="${id}"/> <tags:attribute name="style" value="${style}"/>>
    <c:if test="${not empty title}">
        <!-- TODO: delete controls -->
        <h3>${title}</h3>
    </c:if>
    <div class="content">
        <jsp:doBody/>
    </div>
</div>
