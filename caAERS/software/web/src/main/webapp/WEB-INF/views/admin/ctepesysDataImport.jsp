<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<tags:dwrJavascriptLink objects="ctepDataInitialization"/>
<html>
<head>

<style type="text/css">
        input.autocomplete {
            width: 450px;
            font-style: normal;
            background-color: #CCE6FF;
        }

        input.pending-search {
            width: 450px;
            color: gray;
            font-style: italic;
            background-color: #CCE6FF;
        }
        
        #criteria-div{
          
        }
		.selection {
			display:none;
			background:#dbe9ff;
			border:1px solid #6e8bb8;
			color:#2a4876;
			font-style:italic;
			width:449px;
			margin-top:5px;
			margin-bottom:15px;
			padding:3px;
		}
    </style>
    
<title>CTEP Data Import</title>
<script type="text/javascript">
		// The following code is a work around for sorting functionality for YUI tables not correctly working - https://tracker.nci.nih.gov/browse/CAAERS-5821
		//The code should be removed when YUI is upgraded to 2.9 and higher, http://yuilibrary.com/projects/yui2/ticket/2527707 
			YAHOO.widget.DataTable.prototype.getTdEl = function(cell) {
			var Dom = YAHOO.util.Dom,
			lang = YAHOO.lang,
			elCell,
			el = Dom.get(cell);
			
			// Validate HTML element
			if(el && (el.ownerDocument == document)) {
				// Validate TD element
				if(el.nodeName.toLowerCase() != "td") {
					// Traverse up the DOM to find the corresponding TR element
					elCell = Dom.getAncestorByTagName(el, "td");
				}
				else {
					elCell = el;
				}
				// Make sure the TD is in this TBODY
				if(elCell && (elCell.parentNode.parentNode == this._elTbody)) {
					// Now we can return the TD element
					return elCell;
				}
			}
			else if(cell) {
				var oRecord, nColKeyIndex;
				if(lang.isString(cell.columnKey) && lang.isString(cell.recordId)) {
					oRecord = this.getRecord(cell.recordId);
					var oColumn = this.getColumn(cell.columnKey);
					if(oColumn) {
						nColKeyIndex = oColumn.getKeyIndex();
					}
				}
				if(cell.record && cell.column && cell.column.getKeyIndex) {
					oRecord = cell.record;
					nColKeyIndex = cell.column.getKeyIndex();
				}
				var elRow = this.getTrEl(oRecord);
				if((nColKeyIndex !== null) && elRow && elRow.cells && elRow.cells.length > 0) {
					return elRow.cells[nColKeyIndex] || null;
				}
			}
			return null;
		};
</script>
<script language="JavaScript" type="text/JavaScript">
	
	function initializeYUITableNoPagination(tableId, responseData, columnDefs, fields) {

    YAHOO.example.CellSelection = new function() {
        var columDefs = columnDefs.clone();
        var tableFields = fields.clone();

        var activeDataSource = new YAHOO.util.DataSource(responseData);
        var rowFormatter = function(elTr, oRecord) {
	        return true;
	    };
        activeDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
        activeDataSource.responseSchema = {
            fields: tableFields
        };

        var myConfigs = {
            draggableColumns : false,
            width: "100%",
            formatRow : rowFormatter
        };

        this.activeDataTable = new YAHOO.widget.DataTable(tableId, columDefs, activeDataSource, myConfigs);
        this.activeDataTable.subscribe("rowMouseoverEvent", this.activeDataTable.onEventHighlightRow);
        this.activeDataTable.subscribe("rowMouseoutEvent", this.activeDataTable.onEventUnhighlightRow);
    }

}
	
	var myColumnDefs = [{key:"name", label:"Name", sortable:true, resizeable:true, minWidth:200, maxWidth:350},
						{key:"lastSuccessfulUpdate", label:"Last Successful Import", sortable:true, resizeable:true, minWidth:300, maxWidth:350},
						{key:"lastAttemptedUpdate", label:"Last Attempted Import", sortable:true, resizeable:true, minWidth:300, maxWidth:350},
						{key:"select", label:"Select", sortable:true, resizeable:true, minWidth:100, maxWidth:100}];
	
	var myFields = [{key:'name', parser:"string"},{key:'lastSuccessfulUpdate', parser:"string"},{key:'lastAttemptedUpdate', parser:"string"},{key:'select', parser:"string"}];
	
	var fillerData =  [{name:'CTCAE',lastSuccessfulUpdate:'${empty command.ctcaeLastSuccessfullyUpdated ? "never":command.ctcaeLastSuccessfullyUpdatedStr}',lastAttemptedUpdate:'${empty command.ctcaeLastUpdated ? "never":command.ctcaeLastUpdatedStr}  <div> <img class="indicator" id="ctcaeIndicator" src="<c:url value="/images/indicator.white.gif"/>" alt="activity indicator"/> </div> </div>',select: '<input type="checkbox" name="ctcaeChecked" id="ctcaeChecked" value="ctcaeChecked"/>'},
						{name:'Devices',lastSuccessfulUpdate:'${empty command.devicesLastSuccessfullyUpdated ? "never":command.devicesLastSuccessfullyUpdatedStr}',lastAttemptedUpdate:'${empty command.devicesLastUpdated ? "never":command.devicesLastUpdatedStr} <div> <img class="indicator" id="deviceIndicator" src="<c:url value="/images/indicator.white.gif"/>" alt="activity indicator"/> </div> </div>',select:'<input type="checkbox" name="deviceChecked" id="deviceChecked" value="deviceChecked" />'},
						{name:'Pre-Existing Conditions',lastSuccessfulUpdate:'${empty command.preExistingConditionsLastSuccessfullyUpdated ? "never":command.preExistingConditionsLastSuccessfullyUpdatedStr}',lastAttemptedUpdate:'${empty command.preExistingConditionsLastUpdated ? "never":command.preExistingConditionsLastUpdatedStr} <div> <img class="indicator" id="conditionIndicator" src="<c:url value="/images/indicator.white.gif"/>" alt="activity indicator"/> </div>',select:'<input type="checkbox" name="conditionChecked" id="conditionChecked" value="conditionChecked" />'},
						{name:'Therapies',lastSuccessfulUpdate:'${empty command.therapiesLastSuccessfullyUpdated ? "never":command.therapiesLastSuccessfullyUpdatedStr}',lastAttemptedUpdate:'${empty command.therapiesLastUpdated ? "never":command.therapiesLastUpdatedStr} <div> <img class="indicator" id="therapyIndicator" src="<c:url value="/images/indicator.white.gif"/>" alt="activity indicator"/> </div>',select:'<input type="checkbox" name="therapyChecked" id="therapyChecked" value="therapyChecked" />'},
						{name:'Agent Dose Units of Measure',lastSuccessfulUpdate:'${empty command.agentDoseMeasureLastSuccessfullyUpdated ? "never":command.agentDoseMeasureLastSuccessfullyUpdatedStr}',lastAttemptedUpdate:'${empty command.agentDoseMeasureLastUpdated ? "never":command.agentDoseMeasureLastUpdatedStr} <div> <img class="indicator" id="agentDUOMIndicator" src="<c:url value="/images/indicator.white.gif"/>" alt="activity indicator"/> </div>',select:'<input type="checkbox" name="agentDUOMChecked" id="agentDUOMChecked" value="agentDUOMChecked" />'},
						{name:'Lab',lastSuccessfulUpdate:'${empty command.labLastSuccessfullyUpdated ? "never":command.labLastSuccessfullyUpdatedStr}',lastAttemptedUpdate:'${empty command.labLastUpdated ? "never":command.labLastUpdatedStr}  <div> <img class="indicator" id="labIndicator" src="<c:url value="/images/indicator.white.gif"/>" alt="activity indicator"/> </div> </div>',select:'<input type="checkbox" name="labChecked" id="labChecked" value="labChecked"/>'},
						{name:'Agents',lastSuccessfulUpdate:'${empty command.agentsLastSuccessfullyUpdated ? "never":command.agentsLastSuccessfullyUpdatedStr}',lastAttemptedUpdate:'${empty command.agentsLastUpdated ? "never":command.agentsLastUpdatedStr} <div> <img class="indicator" id="agentIndicator" src="<c:url value="/images/indicator.white.gif"/>" alt="activity indicator"/> </div> </div>',select:'<input type="checkbox" name="agentsChecked" id="agentChecked" value="agentChecked" />'},
						{name:'ASAEL',lastSuccessfulUpdate:'${empty command.asaelLastSuccessfullyUpdated ? "never":command.asaelLastSuccessfullyUpdatedStr}',lastAttemptedUpdate:'${empty command.asaelLastUpdated ? "never":command.asaelLastUpdatedStr}  <div> <img class="indicator" id="asaelIndicator" src="<c:url value="/images/indicator.white.gif"/>" alt="activity indicator"/> </div>',select:'<input type="checkbox" name="asaelChecked" id="asaelChecked" value="asaelChecked" />'},
						{name:'Organizations',lastSuccessfulUpdate:'${empty command.organizationsLastSuccessfullyUpdated ? "never":command.organizationsLastSuccessfullyUpdatedStr}',lastAttemptedUpdate:'${empty command.organizationsLastUpdated ? "never":command.organizationsLastUpdatedStr} <div> <img class="indicator" id="organizationIndicator" src="<c:url value="/images/indicator.white.gif"/>" alt="activity indicator"/> </div> </div>',select:'<input type="checkbox" name="organizationChecked" id="organizationChecked" value="organizationsChecked"/>'}];

	function displayCTEPLOVInitializeTable() {
	   initializeYUITableNoPagination("tableDiv",fillerData, myColumnDefs, myFields);
	}

    function showPopupMessage() {
        popupDiv = new Window({className:"alphacube", width:750, height:115, zIndex:100, resizable:false, recenterAuto:true, draggable:false, closable:true, minimizable:false, maximizable:false});
        popupDiv.setContent("synchMessage");
        popupDiv.showCenter(true);
        popupDiv.show();
        return popupDiv;
    }

    Event.observe(window, "load", function() {
		displayCTEPLOVInitializeTable();
	});
	
	function spinAjaxIndicator(){
		$$("form .indicator").each(function(e){
											if( e.id == 'ctcaeIndicator' && $('ctcaeChecked').checked == true) {
													e.className='arbitrary';
											}	else if(e.id == 'deviceIndicator' && $('deviceChecked').checked == true) {
													e.className='arbitrary';
											}	else if(e.id == 'conditionIndicator' && $('conditionChecked').checked == true) {
													e.className='arbitrary';
											}	else if(e.id == 'therapyIndicator' && $('therapyChecked').checked == true) {
													e.className='arbitrary';
											}	else if(e.id == 'agentDUOMIndicator' && $('agentDUOMChecked').checked == true) {
													e.className='arbitrary';
											}	else if(e.id == 'labIndicator' && $('labChecked').checked == true) {
													e.className='arbitrary';
											}	else if(e.id == 'agentIndicator' && $('agentChecked').checked == true) {
													e.className='arbitrary';
											}	else if(e.id == 'asaelIndicator' && $('asaelChecked').checked == true) {
												 	e.className='arbitrary';
											}	else if(e.id == 'ctcaeIndicator' && $('ctcaeChecked').checked == true) {
												 	e.className='arbitrary';
											}	else if(e.id == 'organizationIndicator' && $('organizationChecked').checked == true) {
												 	e.className='arbitrary';
											}	
									}
			
			
			);
		showPopupMessage();

        document.getElementById("importButton").setAttribute("disabled", "disabled");

        ctepDataInitialization.importCTEPData($('ctcaeChecked').checked,
                $('deviceChecked').checked,
                $('conditionChecked').checked,
                $('therapyChecked').checked,
                $('agentDUOMChecked').checked,
                $('labChecked').checked,
                $('agentChecked').checked,
                $('asaelChecked').checked,
                $('organizationChecked').checked,
                ajaxCallBack);
				
	}
	
	function ajaxCallBack(jsonResult) {
	    $$("form .arbitrary").each(function(e){e.className='indicator';	});
	    document.getElementById("importButton").removeAttribute("disabled");
	    document.forms["ctepDataForm"].submit();
	    showFlashMessage(jsonResult);
	    
	}
	
	 function showFlashMessage(text) {
        jQuery("#autoRemoveElementMesage").html(text);
        jQuery("#autoRemoveElement").show();
        hideFlashMessage.delay(5);
    }
	
	 function hideFlashMessage() {
        jQuery("#autoRemoveElement").fadeOut('slow', function() {});
    }
	
</script>

</head>
<body>
     <div class="workflow-tabs2">
  <ul id="" class="tabs autoclear">
    <li id="thirdlevelnav" class="tab selected"><div>
        <a href="#">CTEP-ESYS Data Import</a>
    </div></li>
    <li id="thirdlevelnav" class="tab"><div>
        <a href="trackReports">Report Submission Logs</a>
    </div></li>
    <li id="thirdlevelnav" class="tab"><div>
        <a href="ctepesysDataIntegrationLogs">CTEP-ESYS Data Integration Logs</a>
    </div></li>
  </ul>
</div>

<chrome:box title="CTEP List Of Values">

 <div id="autoRemoveElement" style="display: none;">
        <div id="flash-message" class="info">
            <img src= "<chrome:imageUrl name="../check.png"/>" />&nbsp;<span id="autoRemoveElementMesage" />
        </div>
 </div>

    <p><tags:instructions code="LBL_ctep.data.import.instructions"/></p>

	<form:form name="ctepDataForm"id="assembler">
		<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN }"/>
	     <chrome:division id="single-fields">
	        <div id="tableDiv">
			</div>
			<div align="right" id="buttonDiv">
				<tags:button id="importButton" color="green" value="Import" size="large" icon="save" type="button" onclick="spinAjaxIndicator();"/>
			</div>
		</chrome:division>
	</form:form>
</chrome:box>

<div id="synchMessage" style="display: none;" class="info-box message" >
    <p><caaers:message code="LBL_CTEP_DataSynch_Message" /></p>
</div>

</body>
</html>