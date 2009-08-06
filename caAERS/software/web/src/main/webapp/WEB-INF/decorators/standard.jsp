<%-- This is the standard decorator for all caAERS pages --%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<!DOCTYPE html
    PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <title>caAERS || <decorator:title/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<standard:head/>
<decorator:head/>
</head>
<body>
	<tags:js name="wz_tooltip/wz_tooltip"/>
	<div class="wide-header"></div>
	<standard:ajaxLoadingIndicator />
<div id="all">
<standard:header/>

<c:set var="__decorator_title"><decorator:title/></c:set>
<chrome:body title="${__decorator_title}">
	<c:if test="${not _noStdFlashMessage}">
		<chrome:flashMessage/>
	</c:if>
	
    <decorator:body/>
</chrome:body>

<standard:footer/>
</div>
</body>
</html>
