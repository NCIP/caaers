<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
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
	
	function selectType()
	{
		var type = $('select-type-id').value;
		if(type == 'medDRA'){
			$('meddra-import-id').style.display = '';
			$('file-id').style.display = 'none';
		}else{
			$('meddra-import-id').style.display = 'none';
			$('file-id').style.display = '';
		}		
	}
	
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
						{name:'Pre-Existing Conditions',status:'update in progress <div id="progressBarId3"> </div>',select:'<input type="checkbox" name="conditionChecked" value="conditionChecked" checked="checked"/>'},
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
		selectType();
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
	
</script>

</head>
<body>


<chrome:box title="Import">
	<form:form id="command" name="ImportForm" enctype="multipart/form-data">
	<div>		
		<input type="hidden" name="_action" value="">
		<input type="hidden" name="_selected" value="">
		<tags:instructions code="LBL_import_instruction"/>
	</div>
	
	<div class="row">
    	<div class="label"><tags:requiredIndicator/><caaers:message code="LBL_type"/></div>
		<div class="value">
			<form:select path="type" id="select-type-id" onchange="javascript:selectType()">
				<form:option value="">Please select</form:option>
     			<form:option value="study">Study / Protocol</form:option>
     			<form:option value="participant">Subject</form:option>
     			<form:option value="investigator">Investigator</form:option>
     			<form:option value="researchStaff">Research staff</form:option>
     			<form:option value="organization">Organization</form:option>
     			<form:option value="agent">Agent</form:option>
     			<form:option value="agentSpecificAEList">Agent specific expected AEs</form:option>
     			<form:option value="medDRA">MedDRA</form:option>
    		</form:select>
		</div>
	</div>
	<div class="row" style="display:none" id="file-id">
		<div class="label">
			<tags:requiredIndicator/>
			<caaers:message code="LBL_dataFile"/>
		</div>
		<div class="value" >
			<input type="file" name="dataFile" id="file-input-id"/>
		</div>
	</div>
	<div id="meddra-import-id" style="display:none">
		<div class="row">
		<div class="label">
			<tags:requiredIndicator/>
			<caaers:message code="LBL_meddraVersionName"/>
		</div>
		<div class="value">
			<form:input id="meddraVersionName-id" path="meddraVersionName" />
		</div>
		</div>
		<div class="row">
		<div class="label">
			<tags:requiredIndicator/>
			<caaers:message code="LBL_socFile"/>
		</div>
		<div class="value">
			<input id="socFile-id" type="file" name="socFile" />
		</div>
		</div>
		<div class="row">
		<div class="label">
			<tags:requiredIndicator/>
			<caaers:message code="LBL_hlgtFile"/>
		</div>
		<div class="value">
			<input id="" type="file" name="hlgtFile" />
		</div>
		</div>
		<div class="row">
		<div class="label">
			<tags:requiredIndicator/>
			<caaers:message code="LBL_socHlgtFile"/>
		</div>
		<div class="value">
			<input id="" type="file" name="socHlgtFile" />
		</div>
		</div>
		<div class="row">
		<div class="label">
			<tags:requiredIndicator/>
			<caaers:message code="LBL_hltFile"/>
		</div>
		<div class="value">
			<input id="" type="file" name="hltFile" />
		</div>
		</div>
		<div class="row">
		<div class="label">
			<tags:requiredIndicator/>
			<caaers:message code="LBL_hlgtHltFile"/>
		</div>
		<div class="value">
			<input id="" type="file" name="hlgtHltFile" />
		</div>
		</div>
		<div class="row">
		<div class="label">
			<tags:requiredIndicator/>
			<caaers:message code="LBL_ptFile"/>
		</div>
		<div class="value">
			<input id="" type="file" name="ptFile" />
		</div>
		</div>
		<div class="row">
		<div class="label">
			<tags:requiredIndicator/>
			<caaers:message code="LBL_hltPtFile"/>
		</div>
		<div class="value">
			<input id="" type="file" name="hltPtFile" />
		</div>
		</div>
		<div class="row">
		<div class="label">
			<tags:requiredIndicator/>
			<caaers:message code="LBL_lltFile"/>
		</div>
		<div class="value">
			<input id="" type="file" name="lltFile" />
		</div>
		</div>
	</div>
   <tags:tabControls tab="${tab}" flow="${flow}" willSave="false" saveButtonLabel="Save"/>
   <tags:tabFields tab="${tab}" />
   </form:form>
</chrome:box>

<chrome:box title="CTEP Data Import">
	<form:form id="assembler">
	     <chrome:division id="single-fields">
	        <div id="tableDiv">
			</div>
			<div align="right" id="buttonDiv">
				<tags:button id="importButton" color="green" value="Import" size="large" icon="save" type="button"/>
			</div>
		</chrome:division>
	</form:form>
</chrome:box>
</body>
</html>