<%@include file="/WEB-INF/views/taglibs.jsp"%>

<html>
	<head>
	<tags:dwrJavascriptLink objects="advSearch"/>
		<link rel="stylesheet" type="text/css" href="/caaers/css/ae.css" />
		<script>
			var advancedSearchHelper = new AdvancedSearchHelper(advSearch);
			var rowList;
			
			function renderFlatView(){
				var form = $('command');
				form._action.value = 'flatView';
				form._page.value = '1';
				form.submit();
			}
			
			function getRowListFromFacade(){
				advSearch.getRowList(function(ajaxOutput) {
					rowList = eval(ajaxOutput.objectContent);
					//renderNestedView();					
				});	
			}
			
			function getAncestorTrElement(iconElement){
				var arr =  iconElement.ancestors();
				var trElement;
				for(var i = 0; i < arr.length; i++){
					if(arr[i].nodeName == 'TR'){
						trElement = arr[i];
						break;
					}
				}
   				return trElement;
			}
			
			function expandTable(index, nestingLevel){
				var iconId = 'expand-' + nestingLevel + '-' + index;
				var iconElement = document.getElementById(iconId);
				var trElement = getAncestorTrElement(iconElement);
				var tableContent = $('deleteMeTableDataSource').innerHTML;
				var newTR = document.createElement('tr');
				var newTD1 = document.createElement('td');
				var newTD2 = document.createElement('td');
				var newTD3 = document.createElement('td');
				newTD3.colSpan = 4;
				newTD3.appendChild($('deleteMeTableDataSource'));
				
				newTR.appendChild(newTD1);
				newTR.appendChild(newTD2);
				newTR.appendChild(newTD3);
				
				alert('newTR = ' + newTR.innerHTML);
				var parentElement = trElement.parentNode;
				parentElement.insertBefore(newTR, trElement.nextSibling);
				
				//var html = '<tr><td/><td/><td colspan=4>' + tableContent + '</td></tr>';
				
				//new Insertion.After(trElement, html);
				//trElement.insert(html);
				
				
				//var deleteMeColumnDefs = [{key: "Short title"},{key: "Long title"},{key: "Gender"},{key: "Age"}];
				//var deleteMeDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom.get("deleteMeTableDataSource"));
		        //deleteMeDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
      			//deleteMeDataSource.responseSchema = {
      			//		fields: ["Short title", "Long title", "Gender", "Age"] 
      			//}; 
      			
				//var myDataTable = new YAHOO.widget.ScrollingDataTable("pleaseDeleteMeActualYUITable", deleteMeColumnDefs, deleteMeDataSource);
			}
			
			Event.observe(window, "load", function() {
				getRowListFromFacade();
				
				// Define Columns
        var myColumnDefs = [
        			{key: ""},
				 <c:forEach items="${command.resultsViewColumnList}" var="viewColumn" varStatus="viewColumnStatus">
					{key:"${viewColumn.columnTitle}", sortable:true, resizeable:true}
					<c:if test="${viewColumnStatus.index < fn:length(command.resultsViewColumnList) - 1}">,</c:if>
				</c:forEach> 
			];

        // Create DataSource
        var myDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom.get("resultsTableDataSource"));
        myDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
        myDataSource.responseSchema = {
            fields: [   "",
					<c:forEach items="${command.resultsViewColumnList}" var="viewColumn" varStatus="viewColumnStatus">
						"${viewColumn.columnTitle }"
						<c:if test="${viewColumnStatus.index < fn:length(command.resultsViewColumnList) - 1}">,</c:if>
					</c:forEach>
				]
        };
        
        //Create config
        var oConfigs = { 
				paginator: new YAHOO.widget.Paginator({ 
					rowsPerPage: 10 
				}), 
				initialRequest: "results=${command.numberOfResults}",
				draggableColumns:true, 
				width:"70em"
			};

        // Create DataTable
        var myDataTable = new YAHOO.widget.ScrollingDataTable("columnshowhide", myColumnDefs, myDataSource, oConfigs);
        
			}); //event.observe
		</script>
	</head>
	<body>
		<tags:tabForm tab="${tab}" flow="${flow}" formName="advancedSearchForm" saveButtonLabel="Save Search" hideBox="true">
			<jsp:attribute name="singleFields">
				<input type="hidden" name="_action" id="_action" value="">
				<chrome:box title="HQL Query for testing">
					${command.hql }<br>
				</chrome:box>
				<div align="right">
					<tags:button color="green" type="button" id="flat-view" value="Flat View" onclick="javascript:renderFlatView();"/>
				</div>
				<chrome:box title="Search results">
					<div id="resultsTableDiv">
    					<div id="columnshowhide" class="yui-skin-sam"></div>
					</div>
					
					<div id="hiddenResultsTable" style="display:none">
						<table id="resultsTableDataSource">
							<thead>
								<tr>
										<td/>
									<c:forEach items="${command.resultsViewColumnList}" var="viewColumn" varStatus="viewColumnStatus">
										<td>${viewColumn.columnTitle }</td>
									</c:forEach>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${command.advancedSearchRowList }" var="row" varStatus="rowStatus">
									<tr>
										<td>
											<img id="expand-1-${rowStatus.index }" src="<c:url value="/images/arrow-right.png"/>" onclick="javascript:expandTable(${rowStatus.index }, 1)" style='cursor:pointer;'>
										</td>
										<c:forEach items="${row.columnList }" var="col" varStatus="colStatus">
											<td>${col.value }</td>
										</c:forEach>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					
					<%--  <div id="pleaseDeleteMeTableDiv">
						<div id="pleaseDeleteMeActualYUITable" class="yui-skin-sam"></div>
					</div> --%>
					
					<div id="pleaseDeleteMeTable" style="display:none">
						<table id="deleteMeTableDataSource">
								<tr>
									<td/><td background-color="#2B4186" align="center">Short title</td><td background-color="#2B4186" align="center">
									Long title</td><td background-color="#2B4186" align="center">Gender</td><td background-color="#2B4186" align="center">Age</td>
								</tr>
								<tr>
									<td>
										<img id="expand-2-1" src="<c:url value="/images/arrow-right.png"/>" onclick="javascript:expandTable(1, 1)" style='cursor:pointer;'>
									</td>
									<td>short title 1</td>
									<td>long title 1 aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa</td>
									<td>Male</td>
									<td>33</td>
								</tr>
								<tr>
									<td>
										<img id="expand-2-1" src="<c:url value="/images/arrow-right.png"/>" onclick="javascript:expandTable(1, 1)" style='cursor:pointer;'>
									</td>
									<td>short title 2</td>
									<td>long title 2gagasgasrgasrgaasgsssssssssssssssssssssssssssgggggggggggggggggggggggg</td>
									<td>Female</td>
									<td>66</td>
								</tr>
								<tr>
									<td>
										<img id="expand-2-1" src="<c:url value="/images/arrow-right.png"/>" onclick="javascript:expandTable(1, 1)" style='cursor:pointer;'>
									</td>
									<td>short title 3</td>
									<td>long title 3asrgrgggggggggggggggggggggggggggggggggggggggggggggggggggggggggg</td>
									<td>Male</td>
									<td>943</td>
								</tr>
						</table>
					</div>
					
				</chrome:box>
			</jsp:attribute>
		</tags:tabForm>
	</body>
</html>