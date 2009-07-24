<%@tag%>
<%@attribute name="objects" required="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type='text/javascript' src='<c:url value="/dwr/engine.js"/>'></script>
<script type='text/javascript' src='<c:url value="/dwr/util.js"/>'></script>
<c:forEach items="${objects}" var="object"><script type='text/javascript' src='<c:url value="/dwr/interface/${object}.js"/>'></script></c:forEach>
