<%-- This is the standard decorator for all C3PR pages --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cntRF" value="${0}" scope="request" />
<html>
<head>
    <title><decorator:title/></title>
</head>

<body>
<decorator:body/>
</body>
</html>
