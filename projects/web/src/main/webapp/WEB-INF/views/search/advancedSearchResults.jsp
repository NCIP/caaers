<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/extremecomponents.css"/>">
<html>
<head>
	<tags:dwrJavascriptLink objects="advSearch"/>
	<tags:javascriptLink name="advancedSearch" />
	<link rel="stylesheet" type="text/css" href="/caaers/css/ae.css" />
	
	<script>
	</script>
</head>
<body>
	<chrome:box title="Search results">
	${hql }<br>
	
	<div class="eXtremeTable" id="${dependentObject.displayName }-div-id" style="text-align:left">
		<table width="100%" border="0" cellspacing="0" class="tableRegion">
			<tr>
				<c:forEach items="${command.searchTargetObject.viewColumn}" var="viewColumn" varStatus="viewColumnStatus">
					<td width="20%" class="centerTableHeader">${viewColumn.columnTitle }</td>
				</c:forEach>
			</tr>
			<c:forEach items="${rowList.rowListDTO }" var="row" varStatus="rowStatus">
				<tr>
				<c:forEach items="${row.columnListDTO.columnDTOList }" var="col" varStatus="colStatus">
					<td width="20%" align="center">${col.value }</td>
				</c:forEach>
				</tr>
			</c:forEach>
		</table>
	</div>
	</chrome:box>
	<div align="left">
		<%-- <tags:button size="small" color="blue" id="modify-criteria-button" type="button" value="Modify criteria"  onclick="location.href='${URL}'" /> --%>
		<a href="<c:url value="/pages/search/advancedSearch?modifyCriteria=true"/>">
							Modify criteria
		</a>
	</div>
</body>
</html>