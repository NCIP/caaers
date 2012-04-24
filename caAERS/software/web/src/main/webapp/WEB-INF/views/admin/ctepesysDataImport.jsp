<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
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
	
	
	var progressBar = new YAHOO.widget.ProgressBar();
	
	var myColumnDefs = [{key:"name", label:"Name", sortable:true, resizeable:true, minWidth:200, maxWidth:350},
						{key:"status", label:"Status", sortable:true, resizeable:true, minWidth:300, maxWidth:350},
						{key:"select", label:"Select", sortable:true, resizeable:true, minWidth:100, maxWidth:100}];
	
	var myFields = [{key:'name', parser:"string"},{key:'status', parser:"string"},{key:'select', parser:"string"}];
	
	var fillerData =  [{name:'CTCAE',status:'last updated 04/15/12 - 00:00',select: '<input type="checkbox" name="cTCAEChecked" value="checkbox1"/>'},
						{name:'Devices',status:'last updated 04/15/12 - 00:01',select:'<input type="checkbox" name="devicesChecked" value="devicesChecked" />'},
						{name:'Pre-Existing Conditions',status:' <div> <img class="indicator" src="<c:url value="/images/indicator.white.gif"/>" alt="activity indicator"/> </div>',select:'<input type="checkbox" name="conditionChecked" value="conditionChecked" checked="checked"/>'},
						{name:'Therapies',status:'update in progress <div id="progressBarId4"> </div>',select:'<input type="checkbox" name="therapiesChecked" value="therapiesChecked" checked="checked"/>'},
						{name:'Agent Dose Units of Measure',status:'update in progress <div id="progressBarId5"> </div>',select:'<input type="checkbox" name="agentDoseUOMChecked" value="agentDoseUOMChecked" checked="checked"/>'},
						{name:'Lob',status:'last updated 04/15/12 - 00:02',select:'<input type="checkbox" name="lobChecked" value="lobChecked"/>'},
						{name:'Agents',status:'update in progress <div id="progressBarId7"> </div>',select:'<input type="checkbox" name="agentsChecked" value="agentsChecked" checked="checked"/>'},
						{name:'ASAEL',status:'update in progress <div id="progressBarId8" </div>',select:'<input type="checkbox" name="aSAELChecked" value="aSAELChecked" checked="checked"/>'},
						{name:'Organizations',status:'last updated 04/15/12 - 00:03',select:'<input type="checkbox" name="organizationsChecked" value="organizationsChecked"/>'}];

	function displayCTEPLOVInitializeTable() {
	   initializeYUITableNoPagination("tableDiv",fillerData, myColumnDefs, myFields);
	}
	
		var myAnim = new YAHOO.util.Anim('test', {
   			width: {
        	to: 400 
   		 } 
		}, 5, YAHOO.util.Easing.easeOut);
	
	Event.observe(window, "load", function() {
		displayCTEPLOVInitializeTable();
		var progressBar3 = new YAHOO.widget.ProgressBar({
		    minValue: 10,
		    maxValue: 90,
		    value: 75,
		    height: 20,
		    width: 100
		}).render("progressBarId3");
		
			
		var progressBar4 = new YAHOO.widget.ProgressBar({
		    minValue: 10,
		    maxValue: 90,
		    value: 85,
		    height: 20,
		    width: 100
		}).render("progressBarId4");
		
			
		var progressBar5 = new YAHOO.widget.ProgressBar({
		    minValue: 10,
		    maxValue: 90,
		    value: 65,
		    height: 20,
		    width: 100
		}).render("progressBarId5");
		
			
		var progressBar7 = new YAHOO.widget.ProgressBar({
		    minValue: 10,
		    maxValue: 90,
		    value: 50,
		    height: 20,
		    width: 100
		}).render("progressBarId7");
		
			
		var progressBar8= new YAHOO.widget.ProgressBar({
		    minValue: 10,
		    maxValue: 90,
		    value: 35,
		    height: 20,
		    width: 100
		}).render("progressBarId8");
	});
	
	function spinAjaxIndicator(){
		$('indicator').className='';
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
    <li id="thirdlevelnav" class="tab"><div>
        <a href="happy">System Status</a>
    </div></li>
  </ul>
</div>


<chrome:box title="CTEP Data Import">
	<form:form id="assembler">
	     <chrome:division id="single-fields">
	        <div id="tableDiv">
			</div>
			<div align="right" id="buttonDiv">
				<tags:button id="importButton" color="green" value="Import" size="large" icon="save" type="button" onclick="spinAjaxIndicator();"/>
			</div>
		</chrome:division>
	</form:form>
</chrome:box>
</body>
</html>