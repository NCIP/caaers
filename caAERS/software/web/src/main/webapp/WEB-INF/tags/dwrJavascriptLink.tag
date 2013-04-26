<%@tag%>
<%@attribute name="objects" required="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type='text/javascript' src='<c:url value="/dwr/engine.js"/>?${requestScope.webCacheId}'></script>
<script type='text/javascript' src='<c:url value="/dwr/util.js"/>?${requestScope.webCacheId}'></script>
<c:forEach items="${objects}" var="object"><script type='text/javascript' src='<c:url value="/dwr/interface/${object}.js"/>?${requestScope.webCacheId}'></script></c:forEach>
<script>
dwr.engine.setTimeout(0);
if(!AE.DWR_ERROR_HANDLER_REGISTERED){
	//register dwr error handler. 
	dwr.engine.setErrorHandler(handleDWRError);
	dwr.engine.setOrdered(true);
	//dwr.engine.setPreHook(showDWRLoadingIndicator);
	//dwr.engine.setPostHook(hideDWRLoadingIndicator);
	AE.DWR_ERROR_HANDLER_REGISTERED=true;
}
</script>
