<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="anchor" required="true"%>
<a class="help" href="<c:url value="/help/caAERS_Help.htm#${anchor}.htm"/>" target="_blank"><img src="<c:url value="/images/info.png"/>" alt="Help" title="Help for this page"></a>