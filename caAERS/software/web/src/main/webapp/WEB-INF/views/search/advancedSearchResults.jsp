<%@include file="/WEB-INF/views/taglibs.jsp"%>

<html>
	<head>
	<tags:dwrJavascriptLink objects="advSearch"/>
		<link rel="stylesheet" type="text/css" href="/caaers/css/ae.css" />
		<style>
			.yui-tt { background:#CCC;}
		</style>
		<script>
		//var advancedSearchHelper = new AdvancedSearchHelper(advSearch);
		
		function saveSearch(){
			if($('searchName').value == '')
				alert('Search name is required');
			else{
				var searchName = $('searchName').value;
				var searchDescription = $('searchDescription').value;
				advSearch.saveSearch(searchName, searchDescription, function(ajaxOutput){
					window.parent.Windows.close('save-popup-id');
					alert('Search saved successfully');
					//$('save-popup-id').destroy();
				});
			} 
		}
		
		
		function renderSaveSearchPopup(){
			var contentWin = new Window({className:"alphacube", destroyOnClose:true, id:"save-popup-id", width:500,  height:330, top: 150, left: 400});
   	 	    contentWin.setContent( 'save_search_popup' );
   	   	 	contentWin.showCenter(true);
	        popupObserver = {
   	   			onDestroy: function(eventName, win) {
   	   				if (win == contentWin) {
   	   					$('save_search_popup').style.display='none';
   	   					
   	   					contentWin = null;
   	   					Windows.removeObserver(this);
   	   				}
   	   			}
   	   		}
   		    Windows.addObserver(popupObserver);
		}
		
		
		Event.observe(window, 'load', function() {
		
		var showTimer,hideTimer;
        // Define Columns
        var myColumnDefs = [
				<c:forEach items="${command.resultsViewColumnList}" var="viewColumn" varStatus="viewColumnStatus">
					{key:"${viewColumn.columnTitle}", sortable:true, resizeable:true, width:"600px"}
					<c:if test="${viewColumnStatus.index < fn:length(command.resultsViewColumnList) - 1}">,</c:if>
				</c:forEach>
				<c:forEach items="${command.resultsViewColumnList}" var="viewColumn" varStatus="viewColumnStatus">
					<c:if test="${viewColumn.lengthy}">
						,{key:"${viewColumn.columnTitle}-lengthy", hidden:true, sortable:true, resizeable:true, width:"600px"}
					</c:if>
				</c:forEach> 
			];
        // Create DataSource
        var myDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom.get("resultsTableDataSource"));
        myDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
        myDataSource.responseSchema = {
            fields: [
					<c:forEach items="${command.resultsViewColumnList}" var="viewColumn" varStatus="viewColumnStatus">
						"${viewColumn.columnTitle }"
						<c:if test="${viewColumnStatus.index < fn:length(command.resultsViewColumnList) - 1}">,</c:if>
					</c:forEach>
					<c:forEach items="${command.resultsViewColumnList}" var="viewColumn" varStatus="viewColumnStatus">
						<c:if test="${viewColumn.lengthy}">
							,"${viewColumn.columnTitle }-lengthy"
						</c:if>
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
				width:"70em",
				height:"30em"
			};

        // Create DataTable
        var myDataTable = new YAHOO.widget.ScrollingDataTable("columnshowhide", myColumnDefs, myDataSource, oConfigs);
        
        var tt = new YAHOO.widget.Tooltip("myTooltip", { preventoverlap:false});
        
        
        myDataTable.on('cellMouseoverEvent', function (oArgs) {
				if (showTimer) {
					window.clearTimeout(showTimer);
					showTimer = 0;
				}
				var target = oArgs.target;
				var column = this.getColumn(target);
				var record = this.getRecord(target);
				var lengthyColumnKey = column.key + '-lengthy';
				var lengthyValue = record.getData(lengthyColumnKey)
				if (lengthyValue != null) {
					var record = this.getRecord(target);
					//var xy = [parseInt(oArgs.event.pageX,10) + 10 ,parseInt(oArgs.event.pageY,10) + 10 ];
					//var xy = [oArgs.event.clientX + document.body.scrollLeft - document.body.clientLeft, oArgs.event.clientY + document.body.scrollTop  - document.body.clientTop];
					var cursor = getPosition(oArgs.event);
					var xy = [cursor.x + 10, cursor.y + 10];
					showTimer = window.setTimeout(function() {
						tt.setBody(lengthyValue);
						tt.cfg.setProperty('xy',xy);
						tt.cfg.setProperty('width','200');
						tt.cfg.setProperty('zIndex', '1000');
						tt.show();
						hideTimer = window.setTimeout(function() {
							tt.hide();
						},5000);
					},500);
				}
			});
			myDataTable.on('cellMouseoutEvent', function (oArgs) {
				if (showTimer) {
					window.clearTimeout(showTimer);
					showTimer = 0;
				}
				if (hideTimer) {
					window.clearTimeout(hideTimer);
					hideTimer = 0;
				}
				tt.hide();
			});
        
        
        
        });
		
	function renderNestedView(){
		var form = $('command');
		form._action.value = 'nestedView';
		form._page.value = '1';
		form.submit();
	}
	
	function getPosition(e) {
    e = e || window.event;
    var cursor = {x:0, y:0};
    if (e.pageX || e.pageY) {
        cursor.x = e.pageX;
        cursor.y = e.pageY;
    } 
    else {
        var de = document.documentElement;
        var b = document.body;
        cursor.x = e.clientX + 
            (de.scrollLeft || b.scrollLeft) - (de.clientLeft || 0);
        cursor.y = e.clientY + 
            (de.scrollTop || b.scrollTop) - (de.clientTop || 0);
    }
    return cursor;
}
		
	</script>	
</head>
<body>
		<tags:tabForm tab="${tab}" flow="${flow}" formName="advancedSearchForm" saveButtonLabel="Save Search" hideBox="true">
			<jsp:attribute name="singleFields">
				<input type="hidden" name="_action" id="_action" value="">
				<%-- <chrome:box title="HQL Query for testing">
					${command.hql }<br>
				</chrome:box> --%>
				<%-- <c:if test="${renderNestedViewButton }">
					<div align="right">
						<tags:button color="green" type="button" id="nested-view" value="Nested View" onclick="javascript:renderNestedView();"/>
					</div>
				</c:if> --%>
				<div align="right">
					<a style="text-decoration:none; color:black; font-weight:bold;" href="<c:url value="/pages/search/exportSearchResults"/>" />&nbsp;Export search results...</a>
				</div>
				<chrome:box title="Search results">
					<div id="resultsTableDiv">
    					<div id="columnshowhide" class="yui-skin-sam"></div>
					</div>
				</chrome:box>
				<div id="hiddenResultsTable" style="display:none">
						<table id="resultsTableDataSource">
							<thead>
								<tr>
									<c:forEach items="${command.resultsViewColumnList}" var="viewColumn" varStatus="viewColumnStatus">
										<td>${viewColumn.columnTitle }</td>
									</c:forEach>
									<c:forEach items="${command.resultsViewColumnList}" var="viewColumn" varStatus="viewColumnStatus">
										<c:if test="${viewColumn.lengthy}">
											<td>${viewColumn.columnTitle}-lengthy</td>
										</c:if>
									</c:forEach>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${command.advancedSearchRowList }" var="row" varStatus="rowStatus">
								<tr>
									<c:forEach items="${row.columnList }" var="col" varStatus="colStatus">
										<td>${col.value }</td>
									</c:forEach>
									<c:forEach items="${row.columnList }" var="col" varStatus="colStatus">
										<c:if test="${col.lengthyValue != null && col.lengthyValue != ''}">
											<td>${col.lengthyValue}</td>
										</c:if>
									</c:forEach>
								</tr>
							</c:forEach>
							</tbody> 
					</table>
				</div>
				<div id="save_search_popup" style="display:none;text-align:left" >
					<chrome:box title="Search details" id="popupId">
						<div>
							<div class="row">
				    			<div class="summarylabel">Search name</div>
				        		<div class="summaryvalue"><ui:text path="searchName" size="20" mandatory="true"/></div>
							</div>
				    		<div class="row">
				    			<div class="summarylabel">Search description</div>
				    			<div class="summaryvalue"><ui:textarea path="searchDescription" rows="3" cols="40"/></div>
				    		</div>
						</div>
						<div align="right">
							<tags:button size="small" color="blue" id="save-button" type="button" value="Save"  onclick="javascript:saveSearch();" />
						</div>
					</chrome:box>
				</div>

			</jsp:attribute>
			<jsp:attribute name="tabControls">
      			<div class="content buttons autoclear">
          			<div class="flow-buttons">
              			<span class="prev">
              				<tags:button type="submit" value="Back" cssClass="tab1" color="blue" icon="back" id="flow-prev"/>
			  			</span>
					</div>
					<div align="right">
						<tags:button color="green" type="button" id="save-search" value="Save search" onclick="javascript:renderSaveSearchPopup();"/>
					</div>
      			</div>
			</jsp:attribute>
		</tags:tabForm>
	</body>
</html>