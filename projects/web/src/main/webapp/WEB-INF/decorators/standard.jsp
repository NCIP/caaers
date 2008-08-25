<%-- This is the standard decorator for all caAERS pages --%>
<%@taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="standard" tagdir="/WEB-INF/tags/standard"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>

<!DOCTYPE html
    PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<title>caAERS || <decorator:title/></title>
<link rel="icon" href="../../images/caaers.ico"/> 
<standard:head/>
<decorator:head/>
</head>
<body>
<div id="all">
<standard:header/>

<c:set var="__decorator_title"><decorator:title/></c:set>
<chrome:body title="${__decorator_title}">
    <chrome:flashMessage/>
    <decorator:body/>
</chrome:body>

<standard:footer/>
</div>
</body>
</html>
