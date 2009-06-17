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
    YAHOO.example.ColumnShowHide = function() {
        // Define Columns
        var myColumnDefs = [
				 <c:forEach items="${command.searchTargetObject.viewColumn}" var="viewColumn" varStatus="viewColumnStatus">
					{key:"${viewColumn.columnTitle}", sortable:true, resizeable:true}
					<c:if test="${viewColumnStatus.index < fn:length(command.searchTargetObject.viewColumn) - 1}">,</c:if>
				</c:forEach> 
			];

        // Create DataSource
        var myDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom.get("resultsTableDataSource"));
        myDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
        myDataSource.responseSchema = {
            fields: [
					<c:forEach items="${command.searchTargetObject.viewColumn}" var="viewColumn" varStatus="viewColumnStatus">
						"${viewColumn.columnTitle }"
						<c:if test="${viewColumnStatus.index < fn:length(command.searchTargetObject.viewColumn) - 1}">,</c:if>
					</c:forEach>
				]
        };

        // Create DataTable
        var myDataTable = new YAHOO.widget.DataTable("columnshowhide", myColumnDefs, myDataSource, {draggableColumns:true});
                    
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
		
		
	</script>
	
</head>
<body >
	<chrome:box title="HQL Query for testing">
		${hql }<br>
	</chrome:box>
	<div align="left">
		<a href="<c:url value="/pages/search/advancedSearch?modifyCriteria=true"/>">
							Modify criteria
		</a>
	</div>
	<br><br>
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
</body>
</html>