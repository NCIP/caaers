<%@include file="/WEB-INF/views/taglibs.jsp"%>

<html>
	<head>
	<tags:dwrJavascriptLink objects="advSearch"/>
		<link rel="stylesheet" type="text/css" href="/caaers/css/ae.css" />
		<script>
		var advancedSearchHelper = new AdvancedSearchHelper(advSearch);
		Event.observe(window, "load", function() {
	    YAHOO.example.ColumnShowHide = function() {
        // Define Columns
        var myColumnDefs = [
				 <c:forEach items="${command.resultsViewColumnList}" var="viewColumn" varStatus="viewColumnStatus">
					{key:"${viewColumn.columnTitle}", sortable:true, resizeable:true}
					<c:if test="${viewColumnStatus.index < fn:length(command.resultsViewColumnList) - 1}">,</c:if>
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
        
        // Shows dialog, creating one when necessary
        var newCols = true;
        var showDlg = function(e) {
            YAHOO.util.Event.stopEvent(e);

            if(newCols) {
                // Populate Dialog
                // Using a template to create elements for the SimpleDialog
                var allColumns = myDataTable.getColumnSet().keys;
                var elPicker = YAHOO.util.Dom.get("dt-dlg-picker");
                var elTemplateCol = document.createElement("div");
                YAHOO.util.Dom.addClass(elTemplateCol, "dt-dlg-pickercol");
                var elTemplateKey = elTemplateCol.appendChild(document.createElement("span"));
                YAHOO.util.Dom.addClass(elTemplateKey, "dt-dlg-pickerkey");
                var elTemplateBtns = elTemplateCol.appendChild(document.createElement("span"));
                YAHOO.util.Dom.addClass(elTemplateBtns, "dt-dlg-pickerbtns");
                var onclickObj = {fn:handleButtonClick, obj:this, scope:false };
                
                // Create one section in the SimpleDialog for each Column
                var elColumn, elKey, elButton, oButtonGrp;
                for(var i=0,l=allColumns.length;i<l;i++) {
                    var oColumn = allColumns[i];
                    
                    // Use the template
                    elColumn = elTemplateCol.cloneNode(true);
                    
                    // Write the Column key
                    elKey = elColumn.firstChild;
                    elKey.innerHTML = oColumn.getKey();
                    
                    // Create a ButtonGroup
                    oButtonGrp = new YAHOO.widget.ButtonGroup({ 
                                    id: "buttongrp"+i, 
                                    name: oColumn.getKey(), 
                                    container: elKey.nextSibling
                    });
                    oButtonGrp.addButtons([
                        { label: "Show", value: "Show", checked: ((!oColumn.hidden)), onclick: onclickObj},
                        { label: "Hide", value: "Hide", checked: ((oColumn.hidden)), onclick: onclickObj}
                    ]);
                                    
                    elPicker.appendChild(elColumn);
                }
                newCols = false;
        	}
            myDlg.show();
        };
        var hideDlg = function(e) {
            this.hide();
        };
        var handleButtonClick = function(e, oSelf) {
            var sKey = this.get("name");
            if(this.get("value") === "Hide") {
                // Hides a Column
                myDataTable.hideColumn(sKey);
            }
            else {
                // Shows a Column
                myDataTable.showColumn(sKey);
            }
        };
        
        // Create the SimpleDialog
        YAHOO.util.Dom.removeClass("dt-dlg", "inprogress");
        var myDlg = new YAHOO.widget.SimpleDialog("dt-dlg", {
                width: "30em",
			    visible: false,
			    modal: true,
			    buttons: [ 
					{ text:"Close",  handler:hideDlg }
                ],
                fixedcenter: true,
                constrainToViewport: true
		});
		myDlg.render();

        // Nulls out myDlg to force a new one to be created
        myDataTable.subscribe("columnReorderEvent", function(){
            newCols = true;
            YAHOO.util.Event.purgeElement("dt-dlg-picker", true);
            YAHOO.util.Dom.get("dt-dlg-picker").innerHTML = "";
        }, this, true);
		
		// Hook up the SimpleDialog to the link
		YAHOO.util.Event.addListener("dt-options-link", "click", showDlg, this, true);
		
		return {
		  oDS: myDataSource,
		  oDT: myDataTable
		};
    }();
});
		
	function renderNestedView(){
		var form = $('command');
		form._action.value = 'nestedView';
		form._page.value = '1';
		form.submit();
	}
		
	</script>	
</head>
<body>
		<tags:tabForm tab="${tab}" flow="${flow}" formName="advancedSearchForm" saveButtonLabel="Save Search" hideBox="true">
			<jsp:attribute name="singleFields">
				<input type="hidden" name="_action" id="_action" value="">
				<chrome:box title="HQL Query for testing">
					${command.hql }<br>
				</chrome:box>
				<c:if test="${renderNestedViewButton }">
					<div align="right">
						<tags:button color="green" type="button" id="nested-view" value="Nested View" onclick="javascript:renderNestedView();"/>
					</div>
				</c:if>
				<chrome:box title="Search results">
					<div id="resultsTableDiv">
		   				<div id="dt-options"><a id="dt-options-link" href="fallbacklink.html">Table Options</a></div>
    					<div id="columnshowhide" class="yui-skin-sam"></div>
					</div>
					<div id="dt-dlg">
		    			<span class="corner_tr"></span>
		 			   	<span class="corner_tl"></span>
		    			<span class="corner_br"></span>
    					<span class="corner_bl"></span>
		    			<div class="hd">
		        			Choose which columns you would like to see:
		    			</div>
		    			<div id="dt-dlg-picker" class="bd">
		    			</div>
					</div>
				</chrome:box>
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
							<tags:button size="small" color="blue" id="save-button" type="button" value="Save"  onclick="javascript:advancedSearchHelper.saveSearch();" />
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
						<tags:button color="green" type="button" id="save-search" value="Save search" onclick="javascript:advancedSearchHelper.renderSaveSearchPopup();"/>
					</div>
      			</div>
			</jsp:attribute>
		</tags:tabForm>
	</body>
</html>