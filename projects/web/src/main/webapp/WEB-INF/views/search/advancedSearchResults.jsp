<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/extremecomponents.css"/>">
<html>
<head>
	<tags:dwrJavascriptLink objects="advSearch"/>
	<tags:javascriptLink name="advancedSearch" />
	<link rel="stylesheet" type="text/css" href="/caaers/css/ae.css" />
</head>
<body>
	<chrome:box title="Search results">
	${hql }<br>
	
	
	<div class="eXtremeTable" id="${dependentObject.displayName}-div-id" style="text-align:left">
		<table width="100%" border="0" cellspacing="0" class="tableRegion">
			<tr align="center" class="label">
				<td class="centerTableHeader" width="20%">First name</td>
				<td class="centerTableHeader" width="20%">Last name</td>
				<td class="centerTableHeader" width="20%">Gender</td>
				<td class="centerTableHeader" width="20%">Ethnicity</td>
				<td class="centerTableHeader" width="20%">Birth date</td>
			</tr>
			<c:forEach items="${results}" var="result" varStatus="resultStatus">
				<tr>
					<td width="20%" align="center">${result.firstName }</td>
					<td width="20%" align="center">${result.lastName }</td>
					<td width="20%" align="center">${result.gender }</td>
					<td width="20%" align="center">${result.ethnicity }</td>
					<td width="20%" align="center">${result.birthDate }</td>
				</tr>
			</c:forEach>			
		</table>
	</div>
	
	</chrome:box>
</body>
</html>