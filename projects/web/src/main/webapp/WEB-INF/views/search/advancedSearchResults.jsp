<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/extremecomponents.css"/>">
<html>
<head>
	<tags:dwrJavascriptLink objects="advSearch"/>
	<tags:javascriptLink name="advancedSearch" />
	<tags:includeYUI />
	<link rel="stylesheet" type="text/css" href="/caaers/css/ae.css" />
	<script>
		Event.observe(window, "load", function() {
			var myDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom.get("myTable"));
			myDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
			myDataSource.responseSchema = { 
				fields: [
					<c:forEach items="${command.searchTargetObject.viewColumn}" var="viewColumn" varStatus="viewColumnStatus">
						"${viewColumn.columnTitle }"
						<c:if test="${viewColumnStatus.index < fn:length(command.searchTargetObject.viewColumn) - 1}">,</c:if>
					</c:forEach>
				]
			};
			
			var myColumnDefs = [
				 <c:forEach items="${command.searchTargetObject.viewColumn}" var="viewColumn" varStatus="viewColumnStatus">
					{key:"${viewColumn.columnTitle}", sortable:true, resizeable:true}
					<c:if test="${viewColumnStatus.index < fn:length(command.searchTargetObject.viewColumn) - 1}">,</c:if>
				</c:forEach> 
			];
			
			var myDataTable = new YAHOO.widget.DataTable("myMarkedUpContainer", myColumnDefs, myDataSource, {draggableColumns:true});
		});
	</script>
	
</head>
<body >
	<chrome:box title="Search results">
	${hql }<br>
	
	<div class="eXtremeTable" id="${dependentObject.displayName }-div-id" style="text-align:left;display:none">
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
		<a href="<c:url value="/pages/search/advancedSearch?modifyCriteria=true"/>">
							Modify criteria
		</a>
	</div>
	<br><br>
	<chrome:box title="Search results">
		<div id="myMarkedUpContainer" class="yui-skin-sam">
			<table id="myTable">
				<thead>
					<tr>
						<c:forEach items="${command.searchTargetObject.viewColumn}" var="viewColumn" varStatus="viewColumnStatus">
							<td>${viewColumn.columnTitle }</td>
						</c:forEach>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${rowList.rowListDTO }" var="row" varStatus="rowStatus">
						<tr>
							<c:forEach items="${row.columnListDTO.columnDTOList }" var="col" varStatus="colStatus">
								<td>${col.value }</td>
							</c:forEach>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</chrome:box>
</body>
</html>