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
	<!-- Required CSS -->
	<link type="text/css" rel="stylesheet" href="http://yui.yahooapis.com/2.9.0/build/progressbar/assets/skins/sam/progressbar.css">
	 
	<!-- Dependency source file -->
	<script src = "http://yui.yahooapis.com/2.9.0/build/yahoo-dom-event/yahoo-dom.event.js" ></script>
	<script src = "http://yui.yahooapis.com/2.9.0/build/element/element-min.js" ></script>
	<!-- Optional dependency source file -->
	<script src="http://yui.yahooapis.com/2.9.0/build/animation/animation-min.js" type="text/javascript"></script>
	 
	<!-- ProgressBar source file -->
	<script src = "http://yui.yahooapis.com/2.9.0/build/progressbar/progressbar-min.js" ></script>
	<!-- Dependencies -->
	<script src="http://yui.yahooapis.com/2.9.0/build/yahoo-dom-event/yahoo-dom-event.js" type="text/javascript"></script>
 
	<!-- Source file -->
<script src="http://yui.yahooapis.com/2.9.0/build/animation/animation-min.js" type="text/javascript"></script>
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
						{key:"status", label:"Status", sortable:true, resizeable:true, minWidth:300, maxWidth:350},
						{key:"select", label:"Select", sortable:true, resizeable:true, minWidth:100, maxWidth:100}];
	
	var myFields = [{key:'name', parser:"string"},{key:'status', parser:"string"},{key:'select', parser:"string"}];
	
	var fillerData =  [{name:'CTCAE',status:'last updated - ${empty command.ctcaeLastUpdated ? "never":command.ctcaeLastUpdated}  <div> <img class="indicator" id="ctcaeIndicator" src="<c:url value="/images/indicator.white.gif"/>" alt="activity indicator"/> </div> </div>',select: '<input type="checkbox" name="ctcaeChecked" id="ctcaeChecked" value="ctcaeChecked"/>'},
						{name:'Devices',status:'last updated - ${empty command.devicesLastUpdated ? "never":command.devicesLastUpdated} <div> <img class="indicator" id="deviceIndicator" src="<c:url value="/images/indicator.white.gif"/>" alt="activity indicator"/> </div> </div>',select:'<input type="checkbox" name="deviceChecked" id="deviceChecked" value="deviceChecked" />'},
						{name:'Pre-Existing Conditions',status:'last updated - ${empty command.preExistingConditionsLastUpdated ? "never":command.preExistingConditionsLastUpdated} <div> <img class="indicator" id="conditionIndicator" src="<c:url value="/images/indicator.white.gif"/>" alt="activity indicator"/> </div>',select:'<input type="checkbox" name="conditionChecked" id="conditionChecked" value="conditionChecked" />'},
						{name:'Therapies',status:'last updated - ${empty command.therapiesLastUpdated ? "never":command.therapiesLastUpdated} <div> <img class="indicator" id="therapyIndicator" src="<c:url value="/images/indicator.white.gif"/>" alt="activity indicator"/> </div>',select:'<input type="checkbox" name="therapyChecked" id="therapyChecked" value="therapyChecked" />'},
						{name:'Agent Dose Units of Measure',status:'last updated - ${empty command.agentDoseMeasureLastUpdated ? "never":command.agentDoseMeasureLastUpdated} <div> <img class="indicator" id="agentDUOMIndicator" src="<c:url value="/images/indicator.white.gif"/>" alt="activity indicator"/> </div>',select:'<input type="checkbox" name="agentDUOMChecked" id="agentDUOMChecked" value="agentDUOMChecked" />'},
						{name:'Lab',status:'last updated - ${empty command.labLastUpdated ? "never":command.labLastUpdated}  <div> <img class="indicator" id="labIndicator" src="<c:url value="/images/indicator.white.gif"/>" alt="activity indicator"/> </div> </div>',select:'<input type="checkbox" name="labChecked" id="labChecked" value="labChecked"/>'},
						{name:'Agents',status:'last updated - ${empty command.agentsLastUpdated ? "never":command.agentsLastUpdated} <div> <img class="indicator" id="agentIndicator" src="<c:url value="/images/indicator.white.gif"/>" alt="activity indicator"/> </div> </div>',select:'<input type="checkbox" name="agentsChecked" id="agentChecked" value="agentChecked" />'},
						{name:'ASAEL',status:'last updated - ${empty command.asaelLastUpdated ? "never":command.asaelLastUpdated}  <div> <img class="indicator" id="asaelIndicator" src="<c:url value="/images/indicator.white.gif"/>" alt="activity indicator"/> </div>',select:'<input type="checkbox" name="asaelChecked" id="asaelChecked" value="asaelChecked" />'},
						{name:'Organizations',status:'last updated - ${empty command.organizationsLastUpdated ? "never":command.organizationsLastUpdated} <div> <img class="indicator" id="organizationIndicator" src="<c:url value="/images/indicator.white.gif"/>" alt="activity indicator"/> </div> </div>',select:'<input type="checkbox" name="organizationChecked" id="organizationChecked" value="organizationsChecked"/>'}];

	function displayCTEPLOVInitializeTable() {
	   initializeYUITableNoPagination("tableDiv",fillerData, myColumnDefs, myFields);
	}
	
	 function showPopupMessage(){
		var d = $('synchMessage');
		Dialog.alert(d.innerHTML, {className: "alphacube", width:300, okLabel: "Close" });
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
		ctepDataInitialization.importCTEPData($('ctcaeChecked').checked, $('deviceChecked').checked, $('conditionChecked').checked, $('therapyChecked').checked, 
				$('agentDUOMChecked').checked,$('labChecked').checked, $('agentChecked').checked,	$('asaelChecked').checked, $('organizationChecked').checked, ajaxCallBack);
				document.getElementById("importButton").setAttribute("disabled", "disabled");
				showPopupMessage();
	}
	
	function ajaxCallBack() {
	    $$("form .arbitrary").each(function(e){e.className='indicator';	});
	    document.getElementById("importButton").removeAttribute("disabled")
	    setTimeout(function() {document.forms["ctepDataForm"].submit();},3000);
	}
	
</script>

</head>
<body>
<div class="tabpane">
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


<chrome:box title="CTEP Data Import">

    <p><tags:instructions code="LBL_ctep.data.import.instructions"/></p>

	<form:form name="ctepDataForm"id="assembler">
	     <chrome:division id="single-fields">
	        <div id="tableDiv">
			</div>
			<div align="right" id="buttonDiv">
				<tags:button id="importButton" color="green" value="Import" size="large" icon="save" type="button" onclick="spinAjaxIndicator();"/>
			</div>
		</chrome:division>
	</form:form>
</chrome:box>

<div id="synchMessage" style="display:none; padding: 15px;">
		<div style="font-size: 10pt; padding-top: 20px; padding-bottom: 20px; padding-left: 5px; padding-right: 5px">
			<div class="label"><caaers:message code="LBL_CTEP_DataSynch_Message" /></div> <div class="value">
		</div>
</div>

</body>
</html>