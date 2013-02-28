<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<page:applyDecorator name="standardNoHeader">
<html>
<head>
 
<script>
  window.parent.fetchReportGroups();
  window.parent.win.close();
</script>

</head>
<body>
</body>
</html>
</page:applyDecorator>
