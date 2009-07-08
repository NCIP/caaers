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
				form.submit();
			}
			
			function getRowListFromFacade(){
				advSearch.getRowList(function(ajaxOutput) {
					rowList = eval(ajaxOutput.objectContent);
					//renderNestedView();					
				});	
			}
			
			
	
			Event.observe(window, "load", function() {
				getRowListFromFacade();
				var loader = new YAHOO.util.YUILoader();
				loader.loadOptional = true;
				//loader.require("reset-fonts-grids","base","datatable", "tabview","calendar","container");
				loader.insert({
					onSuccess: function() {
						var panel = new YAHOO.widget.Panel("PanelDetalle", {visible:false, draggable:true, close:true, width:'600px' } );   
						panel.setHeader('');   
						panel.setBody('');   
						panel.render(YAHOO.util.Dom.get('bd'));
						var tabView = new YAHOO.widget.TabView();
						<c:forEach items="${nestedDependentObjects}" var="dependentObject" varStatus="dependentObjectStatus">
							tabView.addTab( new YAHOO.widget.Tab({
								label: '${dependentObject.displayName}',
								content: '<div id="tab${dependentObjectStatus.index}"></div>',
								active: true
							}));
						</c:forEach>
						tabView.appendTo(panel.body);
		
						// this variables hold information for the table content generator
						// Since you can only pass the function a single string argument
						// using this variables is the only way to pass it 4 arguments
						// without encoding and parsing a silly string.
						var baseRecordId;
						var tableId;
						var rows;
						var cols;
						
						var tableContentGenerator = function() {
							//alert('entered tableContentGenerator');
							var response = [];
							for (var row = 0;row < rows;row++) {
								var dataRow ={};
								for (var col = 0;col < cols;col++) {
									dataRow[rowList.rowListDTO[baseRecordId].listOfRowList[tableId].rowListDTO[row].columnListDTO.columnDTOList[col].columnHeader] = rowList.rowListDTO[baseRecordId].listOfRowList[tableId].rowListDTO[row].columnListDTO.columnDTOList[col].value;
								}
								response.push(dataRow);
							}
							return response;
						};
						
						<c:forEach items="${nestedDependentObjects}" var="nestedDependentObject" varStatus="dependentObjectStatus">
							var dataTable${dependentObjectStatus.index};
							var lastBaseIdShown${dependentObjectStatus.index};
							var setTable${dependentObjectStatus.index} = function (){
								//alert('entered setTable');
								if (lastBaseIdShown${dependentObjectStatus.index} == baseRecordId) return;
								lastBaseIdShown${dependentObjectStatus.index} = baseRecordId;
								
								// set arguments for tableContentGenerator
								tableId = ${dependentObjectStatus.index};
								cols = ${fn:length(command.rowList.rowListDTO[0].listOfRowList[dependentObjectStatus.index].rowListDTO[0].columnListDTO.columnDTOList)};
								//rows = ${fn:length(command.rowList.rowListDTO[0].listOfRowList[dependentObjectStatus.index].rowListDTO)};
								rows = rowList.rowListDTO[baseRecordId].listOfRowList[tableId].rowListDTO.size();
								
								// if this table does not yet exist, create it
								if (dataTable${dependentObjectStatus.index} === undefined) {
									//alert('entered undefined');
									// notice the argument is a reference to the content generator
									var ds = new YAHOO.util.DataSource(tableContentGenerator);
									// this tells the datasource that the content generator will return an array
									ds.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
									
									var x;
									ds.responseSchema = {
										fields: [
											<c:forEach items="${command.rowList.rowListDTO[0].listOfRowList[dependentObjectStatus.index].rowListDTO[0].columnListDTO.columnDTOList}" var="col" varStatus="colStatus">
												"${col.columnHeader}"
												<c:if test="${colStatus.index < fn:length(command.rowList.rowListDTO[0].listOfRowList[dependentObjectStatus.index].rowListDTO[0].columnListDTO.columnDTOList) - 1}">,</c:if>
											</c:forEach> 
										]
									};
									
									dataTable${dependentObjectStatus.index} = new YAHOO.widget.DataTable(
										'tab${dependentObjectStatus.index}',	
										[
											<c:forEach items="${command.rowList.rowListDTO[0].listOfRowList[dependentObjectStatus.index].rowListDTO[0].columnListDTO.columnDTOList}" var="col" varStatus="colStatus">
												{key:"${col.columnHeader}", sortable:true, resizeable:true}
													<c:if test="${colStatus.index < fn:length(command.rowList.rowListDTO[0].listOfRowList[dependentObjectStatus.index].rowListDTO[0].columnListDTO.columnDTOList) - 1}">,</c:if>
											</c:forEach> 
										], 
										ds
									);
									
								}else {
									dataTable${dependentObjectStatus.index}.getDataSource().sendRequest(
										'', 
										dataTable${dependentObjectStatus.index}.onDataReturnInitializeTable,
										dataTable${dependentObjectStatus.index}
									);
								}
							};//setTable
						</c:forEach>
						
						var show = function (recordId,caption,cell) {

							baseRecordId = recordId;
							panel.setHeader(caption);
							panel.cfg.setProperty("context", [cell, "tl", "bl"]);
							panel.cfg.setProperty("visible", true);
							tabView.set('activeIndex', 0); 
							tabView.selectTab(0);
						};

						var hide = function () {
							panel.cfg.setProperty("visible", false);
						};
						
						tabView.on('activeTabChange', function (ev) {
							//alert('entered activeTabChange event');
							switch(tabView.getTabIndex(ev.newValue)) {
								<c:forEach items="${nestedDependentObjects}" var="nestedDependentObject" varStatus="dependentObjectStatus">
									case ${dependentObjectStatus.index}:
										setTable${dependentObjectStatus.index}();
										break;
								</c:forEach>
							}
						});
						
						var myColumnDefs = [
							<c:forEach items="${command.resultsViewColumnList}" var="viewColumn" varStatus="viewColumnStatus">
								{key:"${viewColumn.columnTitle}", sortable:true, resizeable:true}
								<c:if test="${viewColumnStatus.index < fn:length(command.resultsViewColumnList) - 1}">,</c:if>
							</c:forEach> 
						];
						
						var ds = new YAHOO.util.DataSource(YAHOO.util.Dom.get("resultsTableDataSource"));
						ds.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
						ds.responseSchema = {
				            fields: [
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
							draggableColumns:true 
						};
						
						// Create DataTable
				        var myDataTable = new YAHOO.widget.DataTable("baseTable", myColumnDefs, ds, oConfigs);
				        myDataTable.subscribe('cellClickEvent',function (ev) {
							var target = YAHOO.util.Event.getTarget(ev);
							var record = this.getRecord(target);
							show(record.getId().substring(7),'Data for row - ' + record.getId().substring(7),target);
						})
						
					}//onSuccess	 
				});//loader.insert
			}); //event.observe
			
			
			// Done with script for YUI.
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
					Ganapati Bappa Morya !!<br>
					<div class="yui-skin-sam">
						<div id="bd">
							<div id="baseTable">
							</div>
						</div>
					</div>
					<div id="hiddenResultsTable" style="display:none">
						<table id="resultsTableDataSource">
							<thead>
								<tr>
									<c:forEach items="${command.resultsViewColumnList}" var="viewColumn" varStatus="viewColumnStatus">
										<td>${viewColumn.columnTitle }</td>
									</c:forEach>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${command.rowList.rowListDTO }" var="row" varStatus="rowStatus">
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
			</jsp:attribute>
		</tags:tabForm>
	</body>
</html>