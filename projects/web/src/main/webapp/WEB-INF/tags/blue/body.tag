<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="title"%>
<div id="main">
 <c:if test="${not empty title}"><h1>${title}</h1></c:if>
    <jsp:doBody/>
</div>