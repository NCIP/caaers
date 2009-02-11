<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<page:applyDecorator name="standardNoHeader">
<html>
<head>
 
 <tags:stylesheetLink name="standard-form" />

 <style type="text/css">
		body {background: none; text-align:left;}
		div.row div.label {width: 12em;}
	 	div.row div.value, div.row div.extra { margin-left: 13em; }
        div.hr {font-size:1px; height: 1px;}
	</style>
<script>
	window.parent.rpCreator.refreshRPCrlOptionsAndShowDetails(${command.reportingPeriod.id}, false, '${command.reportingPeriod.name}');
	window.parent.Windows.close(window.parent.rpCreator.win.getId());
</script>

</head>
<body>
	<table width="100%" height="100%">
		<tr>
			<td width="100%" align="center">
				Refreshing <img src="<c:url value="/images/indicator.white.gif"/>" alt="activity indicator"/>
			</td>
		</tr>
	</table>
</body>
</html>
</page:applyDecorator>